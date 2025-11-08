import path from 'path';
import { existsSync, mkdirSync, readFileSync, writeFileSync, renameSync } from 'fs';
import type { ExampleInfo, ExamplesJson } from './previewUtils';

interface ExampleEntry {
  entrypoint: string; // Absolute path to mill build output main.js
  outputPath: string; // Absolute path to examples-build output
  exampleInfo: ExampleInfo;
}

/**
 * Extract all examples from the ExamplesJson structure
 * ExamplesJson uses a flat array, but paths reflect hierarchical directory structure
 */
function extractExamples(
  json: ExamplesJson,
  workspaceRoot: string
): ExampleEntry[] {
  const examples: ExampleEntry[] = [];

  if (!json.examples || !Array.isArray(json.examples)) {
    return examples;
  }

  for (const exampleInfo of json.examples) {
    const entrypoint = path.join(workspaceRoot, exampleInfo.millBuildOutPath);
    const outputPath = path.join(workspaceRoot, exampleInfo.exampleBuildsPath);

    examples.push({
      entrypoint,
      outputPath,
      exampleInfo,
    });
  }

  return examples;
}

/**
 * Read examples.json file
 */
function readExamplesJson(workspaceRoot: string): ExamplesJson | null {
  const examplesJsonPath = path.join(workspaceRoot, 'examples.json');

  if (!existsSync(examplesJsonPath)) {
    console.warn('examples.json not found. Run the dev server or build process first to generate it.');
    return null;
  }

  try {
    const content = readFileSync(examplesJsonPath, 'utf-8');
    const parsed = JSON.parse(content) as ExamplesJson;
    // Handle migration from old nested structure
    if (!parsed.examples || !Array.isArray(parsed.examples)) {
      console.warn('examples.json has invalid structure. Expected { examples: ExampleInfo[] }');
      return null;
    }
    return parsed;
  } catch (error) {
    console.error(`Failed to read examples.json:`, error);
    return null;
  }
}

/**
 * Update markdown file to trigger HMR by updating lastUpdated timestamp in frontmatter
 * Uses atomic write with retry logic to avoid Turbopack panics
 */
async function updateMarkdownFile(docPath: string, workspaceRoot: string): Promise<void> {
  const fullPath = path.join(workspaceRoot, docPath);

  if (!existsSync(fullPath)) {
    console.warn(`Markdown file not found: ${fullPath}`);
    return;
  }

  const maxRetries = 3;
  let retries = 0;

  while (retries < maxRetries) {
    try {
      // Read file with a small delay to ensure file system is ready
      await new Promise(resolve => setTimeout(resolve, 50));
      
      const content = readFileSync(fullPath, 'utf-8');
      const timestamp = new Date().toISOString();

      // Check if frontmatter exists
      const frontmatterMatch = content.match(/^---\n([\s\S]*?)\n---/);

      let updatedContent: string;
      if (frontmatterMatch) {
        // Frontmatter exists, update or add lastUpdated field
        let frontmatter = frontmatterMatch[1];
        
        // Check if lastUpdated already exists
        if (frontmatter.match(/^lastUpdated:/m)) {
          // Update existing lastUpdated
          frontmatter = frontmatter.replace(/^lastUpdated:.*$/m, `lastUpdated: ${timestamp}`);
        } else {
          // Add lastUpdated at the end of frontmatter
          frontmatter = frontmatter.trim() + `\nlastUpdated: ${timestamp}`;
        }

        updatedContent = content.replace(
          /^---\n([\s\S]*?)\n---/,
          `---\n${frontmatter}\n---`
        );
      } else {
        // No frontmatter, add it at the beginning
        updatedContent = `---\nlastUpdated: ${timestamp}\n---\n\n${content}`;
      }

      // Atomic write: write to temp file first, then rename
      const tempPath = `${fullPath}.tmp`;
      writeFileSync(tempPath, updatedContent, 'utf-8');
      
      // Small delay before rename to ensure write is complete
      await new Promise(resolve => setTimeout(resolve, 10));
      
      // Use rename for atomic operation (works on most file systems)
      renameSync(tempPath, fullPath);
      
      return; // Success
    } catch (error: any) {
      retries++;
      if (retries >= maxRetries) {
        console.error(`Failed to update markdown file ${fullPath} after ${maxRetries} attempts:`, error);
        return;
      }
      // Exponential backoff
      await new Promise(resolve => setTimeout(resolve, 100 * Math.pow(2, retries)));
    }
  }
}

async function main() {
  const workspaceRoot = process.cwd();

  // Read examples.json
  const examplesJson = readExamplesJson(workspaceRoot);
  
  if (!examplesJson) {
    console.log('No examples.json found. Run the dev server or build process first to generate it.');
    return;
  }

  // Extract all examples from examples.json
  const examples = extractExamples(examplesJson, workspaceRoot);

  if (examples.length === 0) {
    console.log('No examples found to build.');
    return;
  }

  // Filter examples to only those with valid mill build outputs
  const validExamples = examples.filter(ex => {
    if (!existsSync(ex.entrypoint)) {
      console.warn(`Mill build output not found: ${ex.entrypoint}. Skipping.`);
      return false;
    }
    return true;
  });

  if (validExamples.length === 0) {
    console.log('No valid examples to build (all mill build outputs are missing).');
    return;
  }

  console.log(`Found ${validExamples.length} example(s) to build:`);
  validExamples.forEach(ex => {
    console.log(`  - ${ex.entrypoint} -> ${ex.outputPath}`);
  });

  // Collect all entrypoints and create mappings
  const entrypoints = validExamples.map(ex => ex.entrypoint);
  const entrypointToOutputPath = new Map<string, string>();
  const entrypointToDocPath = new Map<string, string>();
  const entrypointToHash = new Map<string, string>();

  for (const example of validExamples) {
    entrypointToOutputPath.set(example.entrypoint, example.outputPath);
    entrypointToDocPath.set(example.entrypoint, example.exampleInfo.docPath);
    entrypointToHash.set(example.entrypoint, example.exampleInfo.hash);
  }

  // Track which markdown files need to be updated
  const markdownFilesToUpdate = new Set<string>();

  try {
    // Build each example individually with its own banner
    for (const entrypoint of entrypoints) {
      const outputPath = entrypointToOutputPath.get(entrypoint);
      const hash = entrypointToHash.get(entrypoint);

      if (!outputPath) {
        console.warn(`No output path found for entrypoint: ${entrypoint}`);
        continue;
      }

      // Ensure output directory exists
      const outputDir = path.dirname(outputPath);
      if (!existsSync(outputDir)) {
        mkdirSync(outputDir, { recursive: true });
      }

      // Create banner comment with example hash
      const banner = hash ? `// Example hash: ${hash}\n` : '';

      // Build with banner
      const result = await Bun.build({
        entrypoints: [entrypoint],
        target: 'browser',
        format: 'esm',
        minify: false,
        banner: banner,
      });

      if (!result.success) {
        console.error(`Failed to build ${entrypoint}:`, result.logs);
        continue;
      }

      if (result.outputs.length === 0) {
        console.warn(`No output generated for ${entrypoint}`);
        continue;
      }

      // Write bundled output (banner is already included)
      await Bun.write(outputPath, result.outputs[0]);
      console.log(`✓ Built: ${outputPath}${hash ? ` (hash: ${hash})` : ''}`);

      // Track markdown file for update
      const docPath = entrypointToDocPath.get(entrypoint);
      if (docPath) {
        markdownFilesToUpdate.add(docPath);
      }
    }
  } catch (error) {
    console.error('Error building examples:', error);
    return;
  }

  // Update markdown files to trigger HMR
  // Update files sequentially with delays to avoid Turbopack panics
  if (markdownFilesToUpdate.size > 0) {
    console.log(`\nUpdating ${markdownFilesToUpdate.size} markdown file(s) to trigger HMR...`);
    const docPaths = Array.from(markdownFilesToUpdate);
    for (let i = 0; i < docPaths.length; i++) {
      await updateMarkdownFile(docPaths[i], workspaceRoot);
      // Add delay between updates to prevent concurrent file operations
      if (i < docPaths.length - 1) {
        await new Promise(resolve => setTimeout(resolve, 200));
      }
    }
  }

  console.log('\nBuild complete!');
}

main().catch(console.error);

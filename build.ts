import path from 'path';
import { existsSync, mkdirSync, readFileSync, writeFileSync, renameSync } from 'fs';

/**
 * JSON Structure Types matching previewPlugin.ts
 */
interface ExampleInfo {
  path: string; // Example directory path relative to workspace root
  docPath: string; // Docs file path relative to workspace root
  millBuildOutPath: string; // Mill build output path relative to workspace root
  exampleBuildsPath: string; // examples-build path relative to workspace root
  lastUpdated: string; // ISO timestamp string
}

type ExamplesJson = {
  examples: ExampleInfo[];
};

interface ExampleEntry {
  entrypoint: string; // Absolute path to mill build output main.js
  outputPath: string; // Absolute path to examples-build output
  exampleInfo: ExampleInfo;
}

/**
 * Extract all examples from the flat ExamplesJson structure
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

  // Extract all examples from the flat structure
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

  for (const example of validExamples) {
    entrypointToOutputPath.set(example.entrypoint, example.outputPath);
    entrypointToDocPath.set(example.entrypoint, example.exampleInfo.docPath);
  }

  // Track which markdown files need to be updated
  const markdownFilesToUpdate = new Set<string>();

  try {
    // Build all examples in a single Bun.build call
    const result = await Bun.build({
      entrypoints,
      target: 'browser',
      format: 'esm',
      minify: true,
    });

    if (!result.success) {
      console.error('Failed to build examples:', result.logs);
      return;
    }

    // Verify outputs match entrypoints count
    if (result.outputs.length !== entrypoints.length) {
      console.warn(
        `Output count mismatch: expected ${entrypoints.length} outputs, got ${result.outputs.length}`
      );
    }

    // Match outputs to entrypoints and write them
    // Bun.build outputs array order should match entrypoints order
    const outputCount = Math.min(result.outputs.length, entrypoints.length);
    for (let i = 0; i < outputCount; i++) {
      const entrypoint = entrypoints[i];
      const output = result.outputs[i];
      const outputPath = entrypointToOutputPath.get(entrypoint);

      if (!outputPath) {
        console.warn(`No output path found for entrypoint: ${entrypoint}`);
        continue;
      }

      // Ensure output directory exists
      const outputDir = path.dirname(outputPath);
      if (!existsSync(outputDir)) {
        mkdirSync(outputDir, { recursive: true });
      }

      // Write bundled output
      await Bun.write(outputPath, output);
      console.log(`✓ Built: ${outputPath}`);

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

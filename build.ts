import path from 'path';
import { existsSync, mkdirSync, readFileSync, writeFileSync } from 'fs';

/**
 * JSON Structure Types matching previewPlugin.ts
 */
interface Position {
  start: {
    line: number;
    column: number;
    offset?: number;
  };
  end: {
    line: number;
    column: number;
    offset?: number;
  };
}

interface ExampleInfo {
  path: string; // Example directory path relative to workspace root
  position: Position | null; // Position in source MDX file
  millBuildOutPath: string; // Mill build output path relative to workspace root
  exampleBuildsPath: string; // examples-build path relative to workspace root
  lastUpdated: string; // ISO timestamp string
}

interface ComponentInfo {
  path: string; // Docs file path relative to workspace root
  examples: ExampleInfo[];
}

type ExamplesJson = {
  [category: string]: ExamplesJson | ComponentInfo;
};

interface ExampleEntry {
  entrypoint: string; // Absolute path to mill build output main.js
  outputPath: string; // Absolute path to examples-build output
  docPath: string; // Path to the MDX file relative to workspace root
  exampleInfo: ExampleInfo;
}

/**
 * Check if an object is a ComponentInfo (has 'path' and 'examples' properties)
 */
function isComponentInfo(obj: ExamplesJson | ComponentInfo): obj is ComponentInfo {
  return typeof obj === 'object' && obj !== null && 'path' in obj && 'examples' in obj && Array.isArray(obj.examples);
}

/**
 * Recursively extract all examples from the nested ExamplesJson structure
 */
function extractExamples(
  json: ExamplesJson,
  workspaceRoot: string
): ExampleEntry[] {
  const examples: ExampleEntry[] = [];

  function traverse(obj: ExamplesJson | ComponentInfo, currentPath: string[] = []): void {
    if (isComponentInfo(obj)) {
      // This is a ComponentInfo, extract all its examples
      for (const exampleInfo of obj.examples) {
        const entrypoint = path.join(workspaceRoot, exampleInfo.millBuildOutPath);
        const outputPath = path.join(workspaceRoot, exampleInfo.exampleBuildsPath);

        examples.push({
          entrypoint,
          outputPath,
          docPath: obj.path,
          exampleInfo,
        });
      }
    } else {
      // This is a nested ExamplesJson, continue traversing
      for (const [key, value] of Object.entries(obj)) {
        traverse(value, [...currentPath, key]);
      }
    }
  }

  traverse(json);
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
    return JSON.parse(content) as ExamplesJson;
  } catch (error) {
    console.error(`Failed to read examples.json:`, error);
    return null;
  }
}

/**
 * Update markdown file to trigger HMR by updating lastUpdated timestamp in frontmatter
 */
function updateMarkdownFile(docPath: string, workspaceRoot: string): void {
  const fullPath = path.join(workspaceRoot, docPath);

  if (!existsSync(fullPath)) {
    console.warn(`Markdown file not found: ${fullPath}`);
    return;
  }

  try {
    const content = readFileSync(fullPath, 'utf-8');
    const timestamp = new Date().toISOString();

    // Check if frontmatter exists
    const frontmatterMatch = content.match(/^---\n([\s\S]*?)\n---/);

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

      const updatedContent = content.replace(
        /^---\n([\s\S]*?)\n---/,
        `---\n${frontmatter}\n---`
      );

      writeFileSync(fullPath, updatedContent, 'utf-8');
    } else {
      // No frontmatter, add it at the beginning
      const updatedContent = `---\nlastUpdated: ${timestamp}\n---\n\n${content}`;
      writeFileSync(fullPath, updatedContent, 'utf-8');
    }
  } catch (error) {
    console.error(`Failed to update markdown file ${fullPath}:`, error);
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

  // Extract all examples from the nested structure
  const examples = extractExamples(examplesJson, workspaceRoot);

  if (examples.length === 0) {
    console.log('No examples found to build.');
    return;
  }

  console.log(`Found ${examples.length} example(s) to build:`);
  examples.forEach(ex => {
    console.log(`  - ${ex.entrypoint} -> ${ex.outputPath}`);
  });

  // Track which markdown files need to be updated
  const markdownFilesToUpdate = new Set<string>();

  // Build each example
  for (const example of examples) {
    try {
      // Check if mill build output exists
      if (!existsSync(example.entrypoint)) {
        console.warn(`Mill build output not found: ${example.entrypoint}. Skipping.`);
        continue;
      }

      const result = await Bun.build({
        entrypoints: [example.entrypoint],
        target: 'browser',
        format: 'esm',
        minify: true,
      });

      if (!result.success) {
        console.error(`Failed to build ${example.entrypoint}:`, result.logs);
        continue;
      }

      // Ensure output directory exists
      const outputDir = path.dirname(example.outputPath);
      if (!existsSync(outputDir)) {
        mkdirSync(outputDir, { recursive: true });
      }

      // Write bundled output
      for (const output of result.outputs) {
        await Bun.write(example.outputPath, output);
        console.log(`✓ Built: ${example.outputPath}`);
      }

      // Track markdown file for update
      markdownFilesToUpdate.add(example.docPath);
    } catch (error) {
      console.error(`Error building ${example.entrypoint}:`, error);
    }
  }

  // Update markdown files to trigger HMR
  for (const docPath of markdownFilesToUpdate) {
    updateMarkdownFile(docPath, workspaceRoot);
  }

  console.log('\nBuild complete!');
}

main().catch(console.error);

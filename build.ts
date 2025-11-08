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
  const entrypointToCounter = new Map<string, number>();

  for (const example of validExamples) {
    entrypointToOutputPath.set(example.entrypoint, example.outputPath);
    entrypointToDocPath.set(example.entrypoint, example.exampleInfo.docPath);
    entrypointToCounter.set(example.entrypoint, example.exampleInfo.counter);
  }

  // Track which markdown files need to be updated
  const markdownFilesToUpdate = new Set<string>();

  try {
    // Build each example individually with its own banner
    for (const entrypoint of entrypoints) {
      const outputPath = entrypointToOutputPath.get(entrypoint);
      const counter = entrypointToCounter.get(entrypoint);

      if (!outputPath) {
        console.warn(`No output path found for entrypoint: ${entrypoint}`);
        continue;
      }

      // Ensure output directory exists
      const outputDir = path.dirname(outputPath);
      if (!existsSync(outputDir)) {
        mkdirSync(outputDir, { recursive: true });
      }

      // Create banner comment with example counter
      const banner = counter !== undefined ? `// Example counter: ${counter}\n` : '';

      // Build with banner
      const result = await Bun.build({
        entrypoints: [entrypoint],
        target: 'browser',
        format: 'esm',
        minify: true,
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
      console.log(`✓ Built: ${outputPath}${counter !== undefined ? ` (counter: ${counter})` : ''}`);

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

  console.log('\nBuild complete!');
}

main().catch(console.error);

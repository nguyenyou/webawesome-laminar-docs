import path from 'path';
import { readdirSync, existsSync, statSync, mkdirSync } from 'fs';

interface ExampleEntry {
  entrypoint: string;
  outputPath: string;
  docName: string;
  exampleName: string;
}

/**
 * Recursively find all main.js files in the out/examples/autogen directory
 */
function findExamples(outDir: string, baseDir: string = ''): ExampleEntry[] {
  const examples: ExampleEntry[] = [];
  const fullPath = path.join(outDir, baseDir);

  if (!existsSync(fullPath)) {
    return examples;
  }

  const entries = readdirSync(fullPath);

  for (const entry of entries) {
    const entryPath = path.join(fullPath, entry);
    const relativePath = baseDir ? path.join(baseDir, entry) : entry;
    const stat = statSync(entryPath);

    if (stat.isDirectory()) {
      // Check if this directory contains main.js (it's an example directory)
      const mainJsPath = path.join(entryPath, 'fullLinkJS.dest', 'main.js');
      if (existsSync(mainJsPath)) {
        // Extract full path structure preserving all intermediate directories
        // relativePath format: {docName}/{intermediate}/example{number}
        const parts = relativePath.split(path.sep);
        const exampleName = parts[parts.length - 1]; // e.g., "example1" (last part)
        const pathParts = parts.slice(0, -1); // All parts except the last (example name)

        // Construct output path preserving full structure: examples-build/{pathParts}/example{N}.js
        const outputPath = pathParts.length > 0
          ? path.join('examples-build', ...pathParts, `${exampleName}.js`)
          : path.join('examples-build', `${exampleName}.js`);

        examples.push({
          entrypoint: mainJsPath,
          outputPath,
          docName: pathParts[0] || '', // First part for backward compatibility
          exampleName,
        });
      } else {
        // Continue searching in subdirectories (docName directories)
        examples.push(...findExamples(outDir, relativePath));
      }
    }
  }

  return examples;
}

async function main() {
  const outDir = './out/examples';
  
  if (!existsSync(outDir)) {
    console.log('No examples found. Run mill build first.');
    return;
  }

  // Find all examples
  const examples = findExamples(outDir);

  if (examples.length === 0) {
    console.log('No examples found to build.');
    return;
  }

  console.log(`Found ${examples.length} example(s) to build:`);
  examples.forEach(ex => {
    console.log(`  - ${ex.entrypoint} -> ${ex.outputPath}`);
  });

  // Build each example
  for (const example of examples) {
    try {
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
    } catch (error) {
      console.error(`Error building ${example.entrypoint}:`, error);
    }
  }

  console.log('\nBuild complete!');
}

main().catch(console.error);

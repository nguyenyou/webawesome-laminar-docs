import path from 'path';
import { existsSync, mkdirSync, readdirSync } from 'fs';

interface ExampleEntry {
  entrypoint: string; // Absolute path to mill build output main.js
  outputPath: string; // Absolute path to examples-build output
  counter: number; // Example counter number
}

/**
 * Discover examples by scanning the filesystem
 * Scans out/examples/ directory recursively to find all example{N}/fullLinkJS.dest/main.js files
 */
function discoverExamples(workspaceRoot: string): ExampleEntry[] {
  const examples: ExampleEntry[] = [];
  const outExamplesPath = path.join(workspaceRoot, 'out', 'examples');

  if (!existsSync(outExamplesPath)) {
    return examples;
  }

  /**
   * Recursively scan directory for example directories
   */
  function scanDirectory(dirPath: string, pathSegments: string[]): void {
    if (!existsSync(dirPath)) {
      return;
    }

    try {
      const entries = readdirSync(dirPath, { withFileTypes: true });

      for (const entry of entries) {
        const fullPath = path.join(dirPath, entry.name);

        // Check if this is an example{N} directory
        const exampleMatch = entry.name.match(/^example(\d+)$/);
        if (entry.isDirectory() && exampleMatch) {
          const counter = parseInt(exampleMatch[1], 10);
          const mainJsPath = path.join(fullPath, 'fullLinkJS.dest', 'main.js');

          // Check if main.js exists
          if (existsSync(mainJsPath)) {
            // Derive output path: examples-build/{category}_{component}_example{N}.js
            const camelCaseSegments = pathSegments.map(segment => segment); // Already camelCase from directory names
            const flattenedPrefix = camelCaseSegments.join('_');
            const outputPath = path.join(
              workspaceRoot,
              'examples-build',
              `${flattenedPrefix}_example${counter}.js`
            );

            examples.push({
              entrypoint: mainJsPath,
              outputPath,
              counter,
            });
          }
        } else if (entry.isDirectory()) {
          // Recursively scan subdirectories
          scanDirectory(fullPath, [...pathSegments, entry.name]);
        }
      }
    } catch (error) {
      console.warn(`Failed to scan directory ${dirPath}:`, error);
    }
  }

  scanDirectory(outExamplesPath, []);
  return examples;
}

/**
 * Format duration in milliseconds to a human-readable string
 * @param durationMs Duration in milliseconds
 * @returns Formatted string like "0.5 seconds" or "2.3 seconds"
 */
function formatDuration(durationMs: number): string {
  const seconds = durationMs / 1000;
  return `${seconds.toFixed(1)} seconds`;
}

async function main() {
  const workspaceRoot = process.cwd();

  // Discover examples by scanning filesystem
  const examples = discoverExamples(workspaceRoot);

  if (examples.length === 0) {
    console.log('No examples found to build. Run mill build first to generate example outputs.');
    return;
  }

  try {
    // Ensure output directory exists for all examples
    const outputDir = path.join(workspaceRoot, 'examples-build');
    if (!existsSync(outputDir)) {
      mkdirSync(outputDir, { recursive: true });
    }

    // Start timing
    const startTime = performance.now();
    const fileCount = examples.length;
    let completedCount = 0;

    // Build all examples in parallel
    const buildPromises = examples.map(async (example, index) => {
      const result = await Bun.build({
        entrypoints: [example.entrypoint],
        target: 'browser',
        format: 'esm',
        minify: true,
      });

      if (!result.success) {
        throw new Error(`Build failed for ${example.entrypoint}: ${JSON.stringify(result.logs)}`);
      }

      if (result.outputs.length === 0) {
        throw new Error(`No output generated for ${example.entrypoint}`);
      }

      await Bun.write(example.outputPath, result.outputs[0]);
      
      // Update and log progress (increment happens atomically in single-threaded JS)
      completedCount++;
      const progressMsg = `[${completedCount}/${fileCount}] Bundling...`;
      process.stdout.write(`\r${progressMsg.padEnd(50)}`);
    });

    // Wait for all builds to complete
    await Promise.all(buildPromises);
    
    // Clear the progress line and move to next line
    process.stdout.write('\r' + ' '.repeat(50) + '\r');

    // Calculate and display timing
    const endTime = performance.now();
    const durationMs = endTime - startTime;
    const durationStr = formatDuration(durationMs);
    
    console.log(`Bundled ${fileCount} examples in ${durationStr}`);
  } catch (error) {
    console.error('Error building examples:', error);
    return;
  }
}

main().catch(console.error);

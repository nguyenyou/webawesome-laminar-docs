import path from 'path';
import { watch, existsSync, readFileSync, statSync, Stats } from 'fs';
import { spawn } from 'child_process';

/**
 * JSON Structure Types matching previewPlugin.ts and build.ts
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

interface WatchedFile {
  filePath: string;
  dirPath: string;
  lastMtime: number;
  watcher: ReturnType<typeof watch> | null;
  example: ExampleInfo;
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
 * Get file stats safely
 */
function getFileStats(filePath: string): Stats | null {
  try {
    if (!existsSync(filePath)) {
      return null;
    }
    return statSync(filePath);
  } catch (error) {
    return null;
  }
}

/**
 * Execute build.ts script
 */
function runBuild(workspaceRoot: string): Promise<void> {
  return new Promise((resolve, reject) => {
    const buildScript = path.join(workspaceRoot, 'build.ts');
    
    if (!existsSync(buildScript)) {
      reject(new Error(`build.ts not found at ${buildScript}`));
      return;
    }

    console.log('\n🔄 Changes detected, running build...\n');
    
    const buildProcess = spawn('bun', ['run', 'build.ts'], {
      cwd: workspaceRoot,
      stdio: 'inherit',
      shell: true,
    });

    buildProcess.on('close', (code) => {
      if (code === 0) {
        console.log('\n✅ Build completed successfully\n');
        resolve();
      } else {
        console.error(`\n❌ Build failed with exit code ${code}\n`);
        reject(new Error(`Build failed with exit code ${code}`));
      }
    });

    buildProcess.on('error', (error) => {
      console.error(`\n❌ Failed to start build process:`, error);
      reject(error);
    });
  });
}

/**
 * Debounce function to prevent multiple rapid builds
 */
function debounce<T extends (...args: any[]) => void>(
  func: T,
  wait: number
): (...args: Parameters<T>) => void {
  let timeout: NodeJS.Timeout | null = null;
  
  return function executedFunction(...args: Parameters<T>) {
    const later = () => {
      timeout = null;
      func(...args);
    };
    
    if (timeout) {
      clearTimeout(timeout);
    }
    timeout = setTimeout(later, wait);
  };
}

/**
 * Setup directory watcher for a file
 */
function setupDirectoryWatcher(
  watchedFile: WatchedFile,
  debouncedBuild: () => void,
  workspaceRoot: string
): void {
  const { filePath, dirPath, example } = watchedFile;

  // Close existing watcher if any
  if (watchedFile.watcher) {
    try {
      watchedFile.watcher.close();
    } catch (error) {
      // Ignore errors when closing
    }
    watchedFile.watcher = null;
  }

  // Check if directory exists
  if (!existsSync(dirPath)) {
    console.warn(`⚠️  Directory not found: ${dirPath}`);
    return;
  }

  try {
    // Watch the directory instead of the file
    const watcher = watch(dirPath, { recursive: false }, (eventType, filename) => {
      // Only react to changes to main.js
      if (filename === 'main.js' || filename === path.basename(filePath)) {
        const stats = getFileStats(filePath);
        if (stats) {
          const currentMtime = stats.mtimeMs;
          // Only trigger if the file actually changed
          if (currentMtime !== watchedFile.lastMtime) {
            watchedFile.lastMtime = currentMtime;
            console.log(`📝 File changed: ${example.millBuildOutPath}`);
            debouncedBuild();
          }
        } else if (eventType === 'rename') {
          // File might have been deleted, check if it exists now
          setTimeout(() => {
            const stats = getFileStats(filePath);
            if (stats) {
              watchedFile.lastMtime = stats.mtimeMs;
              console.log(`📝 File recreated: ${example.millBuildOutPath}`);
              debouncedBuild();
            }
          }, 100);
        }
      }
    });

    watcher.on('error', (error) => {
      console.error(`❌ Watcher error for ${dirPath}:`, error);
      // Try to re-establish watcher after a delay
      setTimeout(() => {
        setupDirectoryWatcher(watchedFile, debouncedBuild, workspaceRoot);
      }, 1000);
    });

    watchedFile.watcher = watcher;
  } catch (error) {
    console.error(`❌ Failed to setup watcher for ${dirPath}:`, error);
  }
}

/**
 * Polling function to check file modification times
 */
function startPolling(
  watchedFiles: Map<string, WatchedFile>,
  debouncedBuild: () => void,
  workspaceRoot: string
): NodeJS.Timeout {
  return setInterval(() => {
    for (const [filePath, watchedFile] of watchedFiles.entries()) {
      const stats = getFileStats(filePath);
      
      if (stats) {
        const currentMtime = stats.mtimeMs;
        // File exists and has changed
        if (currentMtime !== watchedFile.lastMtime) {
          watchedFile.lastMtime = currentMtime;
          console.log(`📝 [Polling] File changed: ${watchedFile.example.millBuildOutPath}`);
          debouncedBuild();
        }
      } else {
        // File doesn't exist, but might be created soon
        // This is handled by directory watchers, but polling helps catch missed events
      }
    }
  }, 2000); // Poll every 2 seconds
}

/**
 * Setup watchers for all mill build outputs
 */
function setupWatchers(workspaceRoot: string, examplesJson: ExamplesJson): void {
  const watchedFiles = new Map<string, WatchedFile>();
  let isBuilding = false;
  let pollingInterval: NodeJS.Timeout | null = null;

  // Debounced build function (500ms delay)
  const debouncedBuild = debounce(async () => {
    if (isBuilding) {
      console.log('⏳ Build already in progress, skipping...');
      return;
    }

    try {
      isBuilding = true;
      await runBuild(workspaceRoot);
      
      // After build completes, update file stats and re-establish watchers
      setTimeout(() => {
        updateWatchedFiles(watchedFiles, workspaceRoot, debouncedBuild);
      }, 500);
    } catch (error) {
      console.error('Build error:', error);
    } finally {
      isBuilding = false;
    }
  }, 500);

  /**
   * Update watched files - re-read examples.json and update watchers
   */
  function updateWatchedFiles(
    watchedFiles: Map<string, WatchedFile>,
    workspaceRoot: string,
    debouncedBuild: () => void
  ): void {
    const examplesJson = readExamplesJson(workspaceRoot);
    if (!examplesJson) {
      return;
    }

    // Update existing watchers and add new ones
    for (const example of examplesJson.examples) {
      const filePath = path.join(workspaceRoot, example.millBuildOutPath);
      const dirPath = path.dirname(filePath);

      const stats = getFileStats(filePath);
      const currentMtime = stats ? stats.mtimeMs : 0;

      if (watchedFiles.has(filePath)) {
        // Update existing watcher
        const watchedFile = watchedFiles.get(filePath)!;
        watchedFile.example = example;
        if (stats) {
          watchedFile.lastMtime = currentMtime;
        }
        // Re-establish watcher if needed
        if (!watchedFile.watcher) {
          setupDirectoryWatcher(watchedFile, debouncedBuild, workspaceRoot);
        }
      } else {
        // Create new watcher
        const watchedFile: WatchedFile = {
          filePath,
          dirPath,
          lastMtime: currentMtime,
          watcher: null,
          example,
        };
        watchedFiles.set(filePath, watchedFile);
        setupDirectoryWatcher(watchedFile, debouncedBuild, workspaceRoot);
      }
    }

    // Remove watchers for files that no longer exist in examples.json
    const currentPaths = new Set(examplesJson.examples.map(e => 
      path.join(workspaceRoot, e.millBuildOutPath)
    ));
    
    for (const [filePath, watchedFile] of watchedFiles.entries()) {
      if (!currentPaths.has(filePath)) {
        if (watchedFile.watcher) {
          try {
            watchedFile.watcher.close();
          } catch (error) {
            // Ignore errors
          }
        }
        watchedFiles.delete(filePath);
      }
    }
  }

  // Initial setup
  for (const example of examplesJson.examples) {
    const filePath = path.join(workspaceRoot, example.millBuildOutPath);
    const dirPath = path.dirname(filePath);

    const stats = getFileStats(filePath);
    const currentMtime = stats ? stats.mtimeMs : 0;

    const watchedFile: WatchedFile = {
      filePath,
      dirPath,
      lastMtime: currentMtime,
      watcher: null,
      example,
    };

    watchedFiles.set(filePath, watchedFile);
    setupDirectoryWatcher(watchedFile, debouncedBuild, workspaceRoot);
  }

  // Start polling as backup
  pollingInterval = startPolling(watchedFiles, debouncedBuild, workspaceRoot);

  // Periodically re-read examples.json to pick up new examples (every 5 seconds)
  const examplesJsonRefreshInterval = setInterval(() => {
    updateWatchedFiles(watchedFiles, workspaceRoot, debouncedBuild);
  }, 5000);

  console.log(`\n✅ Watching ${watchedFiles.size} file(s) for changes...\n`);
  console.log('📡 Using directory watching + polling fallback\n');
  console.log('Press Ctrl+C to stop watching.\n');

  // Cleanup on exit
  const cleanup = () => {
    if (pollingInterval) {
      clearInterval(pollingInterval);
    }
    clearInterval(examplesJsonRefreshInterval);
    for (const watchedFile of watchedFiles.values()) {
      if (watchedFile.watcher) {
        try {
          watchedFile.watcher.close();
        } catch (error) {
          // Ignore errors
        }
      }
    }
  };

  process.on('SIGINT', () => {
    cleanup();
    console.log('\n\n👋 Stopping watch...');
    process.exit(0);
  });

  process.on('SIGTERM', () => {
    cleanup();
    console.log('\n\n👋 Stopping watch...');
    process.exit(0);
  });
}

/**
 * Main function
 */
async function main() {
  const workspaceRoot = process.cwd();

  console.log('🚀 Starting build watch...\n');

  // Read examples.json
  const examplesJson = readExamplesJson(workspaceRoot);

  if (!examplesJson) {
    console.error('❌ Failed to read examples.json. Exiting.');
    process.exit(1);
  }

  if (examplesJson.examples.length === 0) {
    console.log('ℹ️  No examples found in examples.json. Nothing to watch.');
    process.exit(0);
  }

  console.log(`📋 Found ${examplesJson.examples.length} example(s) to watch.\n`);

  // Setup watchers
  setupWatchers(workspaceRoot, examplesJson);
}

main().catch((error) => {
  console.error('Fatal error:', error);
  process.exit(1);
});

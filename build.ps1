# build.ps1 - Full build script for MiniLang compiler + IDE
# Usage:
#   .\build.ps1              - build + start GUI IDE
#   .\build.ps1 -Run file    - build + run a .ml file in CLI mode
#   .\build.ps1 -Test        - build + run all test cases

param(
    [string]$Run   = "",
    [switch]$Test  = $false
)

$antlrJar     = ".\antlr-4.13.1-complete.jar"
$generatedDir = ".\generated"
$buildDir     = ".\build"
$srcDir       = ".\src"
$cp           = "$buildDir;$antlrJar"

# Step 1: Create output folders
if (-not (Test-Path $generatedDir)) { New-Item -ItemType Directory -Path $generatedDir | Out-Null }
if (-not (Test-Path $buildDir))     { New-Item -ItemType Directory -Path $buildDir     | Out-Null }

# Step 2: Generate ANTLR parser from grammar
Write-Host ">> Generating parser from MiniLang.g4 ..." -ForegroundColor Cyan
java -jar $antlrJar -visitor -o $generatedDir MiniLang.g4
if ($LASTEXITCODE -ne 0) { Write-Host "ANTLR generation failed." -ForegroundColor Red; exit 1 }

# Step 3: Collect all Java source files
$generatedSrcs = Get-ChildItem -Path $generatedDir -Filter "*.java" | ForEach-Object { $_.FullName }
$srcFiles = Get-ChildItem -Path $srcDir -Filter "*.java" -Recurse | ForEach-Object { $_.FullName }
$allSources = @($generatedSrcs) + @($srcFiles)

# Step 4: Compile everything into build/
Write-Host ">> Compiling $($allSources.Count) source files ..." -ForegroundColor Cyan
javac -cp ".;$antlrJar" -d $buildDir -sourcepath "$srcDir;$generatedDir" @allSources 2>$null
if ($LASTEXITCODE -ne 0) { Write-Host "Compilation failed." -ForegroundColor Red; exit 1 }

# Copy GUI assets to build directory
if (Test-Path ".\gui") {
    $guiDest = "$buildDir\gui"
    if (-not (Test-Path $guiDest)) { New-Item -ItemType Directory -Path $guiDest | Out-Null }
    Copy-Item ".\gui\*" -Destination $guiDest -Recurse -Force
}

Write-Host ">> Build successful." -ForegroundColor Green

# Step 5: Execute based on mode
if ($Test) {
    Write-Host ""
    Write-Host ">> Running test suite ..." -ForegroundColor Cyan
    & ".\tests\test_runner.ps1"
}
elseif ($Run -ne "") {
    Write-Host ">> Running: $Run" -ForegroundColor Green
    java -cp $cp Main $Run
}
else {
    Write-Host ">> Starting MiniLang IDE on http://localhost:3000 ..." -ForegroundColor Green
    Write-Host "   Press Ctrl+C to stop." -ForegroundColor DarkGray
    Write-Host ""
    java -cp $cp Main
}

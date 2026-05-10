# test_runner.ps1 - Automated test suite for MiniLang
# Usage:
#   .\tests\test_runner.ps1          - run all tests
#   .\tests\test_runner.ps1 -Filter 08  - run only tests matching "08"

param(
    [string]$Filter = ""
)

$buildDir  = "$PSScriptRoot\..\build"
$antlrJar  = "$PSScriptRoot\..\antlr-4.13.1-complete.jar"
$testsDir  = $PSScriptRoot
$expectDir = "$PSScriptRoot\expected"

$passCount = 0
$failCount = 0
$skipCount = 0

function Run-Test($testFile) {
    $name    = [System.IO.Path]::GetFileNameWithoutExtension($testFile)
    $outFile = "$expectDir\$name.out"

    if (-not (Test-Path $outFile)) {
        Write-Host "  [SKIP] $name  (no expected output file)" -ForegroundColor DarkGray
        $script:skipCount++
        return
    }

    # Run the interpreter
    $tmpOut = [System.IO.Path]::GetTempFileName()
    $tmpErr = [System.IO.Path]::GetTempFileName()

    $proc = Start-Process -FilePath "java" `
        -ArgumentList "-cp", "`"$buildDir;$antlrJar`"", "Main", "`"$testFile`"" `
        -RedirectStandardOutput $tmpOut `
        -RedirectStandardError  $tmpErr `
        -Wait -NoNewWindow -PassThru

    $stdoutRaw = Get-Content $tmpOut -Raw -ErrorAction SilentlyContinue
    $stderrRaw = Get-Content $tmpErr -Raw -ErrorAction SilentlyContinue
    Remove-Item $tmpOut, $tmpErr -ErrorAction SilentlyContinue

    if ($null -eq $stdoutRaw) { $stdoutRaw = "" }
    if ($null -eq $stderrRaw) { $stderrRaw = "" }

    # Combine stdout + stderr
    $combined = ($stdoutRaw + $stderrRaw).Trim() -replace "`r", ""

    $expected = (Get-Content $outFile -Raw).Trim() -replace "`r", ""

    # Fuzzy compare: ignore trailing whitespace per line
    $actualLines   = ($combined  -split "`n") | ForEach-Object { $_.TrimEnd() }
    $expectedLines = ($expected  -split "`n") | ForEach-Object { $_.TrimEnd() }

    $actualStr   = $actualLines   -join "`n"
    $expectedStr = $expectedLines -join "`n"

    if ($actualStr -eq $expectedStr) {
        Write-Host "  [PASS] $name" -ForegroundColor Green
        $script:passCount++
    } else {
        Write-Host "  [FAIL] $name" -ForegroundColor Red
        Write-Host "         Expected: $($expectedStr -replace "`n"," | ")" -ForegroundColor DarkYellow
        Write-Host "         Actual:   $($actualStr   -replace "`n"," | ")" -ForegroundColor DarkRed
        $script:failCount++
    }
}

# -- Banner -----------------------------------------------------
Write-Host ""
Write-Host "=======================================" -ForegroundColor Cyan
Write-Host "   MiniLang Test Runner" -ForegroundColor Cyan
Write-Host "=======================================" -ForegroundColor Cyan
Write-Host ""

# -- Run all .ml test files -------------------------------------
$tests = Get-ChildItem -Path $testsDir -Filter "*.ml" | Where-Object { $_.Name -like "*$Filter*" } | Sort-Object Name

foreach ($test in $tests) {
    Run-Test $test.FullName
}

# -- Summary ----------------------------------------------------
$totalCount = $passCount + $failCount + $skipCount
Write-Host ""
Write-Host "---------------------------------------" -ForegroundColor DarkGray
$color = "Green"
if ($failCount -gt 0) { $color = "Yellow" }
$summaryMsg = "  Results:  $passCount / $totalCount passed  -  $failCount failed  -  $skipCount skipped"
Write-Host $summaryMsg -ForegroundColor $color
Write-Host ""

if ($failCount -gt 0) { exit 1 } else { exit 0 }

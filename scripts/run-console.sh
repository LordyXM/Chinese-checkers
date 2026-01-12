#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
OUT="$ROOT/out"
java -cp "$OUT/engine:$OUT/graphics:$OUT/demo-console" com.example.democonsole.ConsoleDemoMain

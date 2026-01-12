#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
OUT="$ROOT/out"
java -ea -cp "$OUT/engine:$OUT/graphics:$OUT/tests" com.example.tests.TestRunner

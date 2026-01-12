#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
OUT="$ROOT/out"
rm -rf "$OUT"
mkdir -p "$OUT"

find "$ROOT/engine/src/main/java" -name "*.java" > "$OUT/sources_engine.txt"
javac -d "$OUT/engine" @"$OUT/sources_engine.txt"

find "$ROOT/graphics/src/main/java" -name "*.java" > "$OUT/sources_graphics.txt"
javac -cp "$OUT/engine" -d "$OUT/graphics" @"$OUT/sources_graphics.txt"

find "$ROOT/demo-console/src/main/java" -name "*.java" > "$OUT/sources_demo_console.txt"
javac -cp "$OUT/engine:$OUT/graphics" -d "$OUT/demo-console" @"$OUT/sources_demo_console.txt"

find "$ROOT/demo-gui/src/main/java" -name "*.java" > "$OUT/sources_demo_gui.txt"
javac -cp "$OUT/engine:$OUT/graphics" -d "$OUT/demo-gui" @"$OUT/sources_demo_gui.txt"

find "$ROOT/tests/src/main/java" -name "*.java" > "$OUT/sources_tests.txt"
javac -cp "$OUT/engine:$OUT/graphics" -d "$OUT/tests" @"$OUT/sources_tests.txt"

echo "OK: compiled to $OUT"

# Java Mini Game Demo (Engine + Graphics + Demos)

This archive contains separate Java projects:

- `engine/` — game logic (engine)
- `graphics/` — Swing rendering helpers
- `demo-console/` — mini console demo (ASCII)
- `demo-gui/` — mini GUI demo (Swing)

Also:
- `tests/` — lightweight tests (no external libs; uses `assert`)
- `docs/` — sample branch/log exports
- `scripts/` — compile/run scripts (Linux/macOS + Windows)

## Build / Run

Linux/macOS:
```bash
bash scripts/compile.sh
bash scripts/run-tests.sh
bash scripts/run-console.sh
bash scripts/run-gui.sh
```

Windows PowerShell:
```powershell
.\scripts\compile.bat
.\scripts\run-tests.bat
.\scripts\run-console.bat
.\scripts\run-gui.bat
```

Controls:
- Console demo: `W A S D` + Enter, `Q` to quit
- GUI demo: arrow keys, `Esc` to quit

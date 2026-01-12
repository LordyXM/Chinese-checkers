package com.example.engine.grid;

/**
 * Tile types for the grid.
 */
public enum TileType {
    FLOOR(true, '.'),
    WALL(false, '#');

    private final boolean walkable;
    private final char consoleChar;

    TileType(boolean walkable, char consoleChar) {
        this.walkable = walkable;
        this.consoleChar = consoleChar;
    }

    /** @return true if walkable */
    public boolean isWalkable() { return walkable; }

    /** @return console representation */
    public char consoleChar() { return consoleChar; }
}

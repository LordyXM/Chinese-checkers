package com.example.engine.grid;

import com.example.engine.util.Preconditions;

/**
 * A simple tile grid with collision checks.
 */
public final class GridWorld {
    private final int width;
    private final int height;
    private final TileType[][] tiles;

    /**
     * Creates a grid filled with FLOOR.
     * @param width width > 0
     * @param height height > 0
     */
    public GridWorld(int width, int height) {
        Preconditions.checkArgument(width > 0, "width must be > 0");
        Preconditions.checkArgument(height > 0, "height must be > 0");
        this.width = width;
        this.height = height;
        this.tiles = new TileType[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) tiles[y][x] = TileType.FLOOR;
        }
    }

    /** @return width */
    public int width() { return width; }
    /** @return height */
    public int height() { return height; }

    /**
     * Sets a tile (ignored if out of bounds).
     * @param x x
     * @param y y
     * @param type tile type
     */
    public void setTile(int x, int y, TileType type) {
        Preconditions.checkNotNull(type, "type");
        if (!inBounds(x, y)) return;
        tiles[y][x] = type;
    }

    /**
     * Gets a tile (WALL if out of bounds).
     * @param x x
     * @param y y
     * @return tile type
     */
    public TileType getTile(int x, int y) {
        if (!inBounds(x, y)) return TileType.WALL;
        return tiles[y][x];
    }

    /**
     * @param x x
     * @param y y
     * @return true if in bounds
     */
    public boolean inBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * @param x x
     * @param y y
     * @return true if in bounds and walkable
     */
    public boolean isWalkable(int x, int y) {
        return inBounds(x, y) && getTile(x, y).isWalkable();
    }

    /**
     * Factory: world with border walls.
     * @param width width
     * @param height height
     * @return world with border walls
     */
    public static GridWorld withBorderWalls(int width, int height) {
        GridWorld w = new GridWorld(width, height);
        for (int x = 0; x < width; x++) {
            w.setTile(x, 0, TileType.WALL);
            w.setTile(x, height - 1, TileType.WALL);
        }
        for (int y = 0; y < height; y++) {
            w.setTile(0, y, TileType.WALL);
            w.setTile(width - 1, y, TileType.WALL);
        }
        return w;
    }
}

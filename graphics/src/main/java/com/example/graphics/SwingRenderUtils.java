package com.example.graphics;

import com.example.engine.grid.GridWorld;
import com.example.engine.grid.TileType;
import com.example.engine.math.Vec2i;

import java.awt.*;

/**
 * Utility drawing helpers for Swing demo.
 */
public final class SwingRenderUtils {
    private SwingRenderUtils() {}

    /**
     * Draws the grid. WALL tiles are filled rectangles, FLOOR tiles are outlines.
     *
     * @param g graphics
     * @param world grid
     * @param tileSize tile pixel size
     */
    public static void drawGrid(Graphics2D g, GridWorld world, int tileSize) {
        for (int y = 0; y < world.height(); y++) {
            for (int x = 0; x < world.width(); x++) {
                TileType t = world.getTile(x, y);
                if (t == TileType.WALL) {
                    g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
                } else {
                    g.drawRect(x * tileSize, y * tileSize, tileSize, tileSize);
                }
            }
        }
    }

    /**
     * Draws entity as an oval marker.
     *
     * @param g graphics
     * @param pos grid position
     * @param tileSize tile pixel size
     */
    public static void drawEntity(Graphics2D g, Vec2i pos, int tileSize) {
        int px = pos.x() * tileSize;
        int py = pos.y() * tileSize;
        g.fillOval(px + tileSize / 8, py + tileSize / 8, tileSize * 3 / 4, tileSize * 3 / 4);
    }
}

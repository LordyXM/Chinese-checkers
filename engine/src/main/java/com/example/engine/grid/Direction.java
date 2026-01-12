package com.example.engine.grid;

import com.example.engine.math.Vec2i;

/**
 * Cardinal directions.
 */
public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final Vec2i delta;

    Direction(int dx, int dy) {
        this.delta = new Vec2i(dx, dy);
    }

    /** @return one-tile delta */
    public Vec2i delta() { return delta; }
}

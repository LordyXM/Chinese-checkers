package com.example.engine.components;

import com.example.engine.math.Vec2i;
import com.example.engine.util.Preconditions;

/**
 * Stores entity position in grid coordinates.
 */
public final class PositionComponent {
    private Vec2i pos;

    /**
     * Creates position component.
     * @param initial initial position
     */
    public PositionComponent(Vec2i initial) {
        this.pos = Preconditions.checkNotNull(initial, "initial");
    }

    /** @return current position */
    public Vec2i get() { return pos; }

    /**
     * Sets position.
     * @param p new position
     */
    public void set(Vec2i p) { this.pos = Preconditions.checkNotNull(p, "p"); }
}

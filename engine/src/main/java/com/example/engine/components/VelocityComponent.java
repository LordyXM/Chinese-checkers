package com.example.engine.components;

import com.example.engine.math.Vec2i;
import com.example.engine.util.Preconditions;

/**
 * Stores requested movement delta for the next tick.
 */
public final class VelocityComponent {
    private Vec2i delta = Vec2i.zero();

    /** @return delta for this tick */
    public Vec2i getDelta() { return delta; }

    /**
     * Sets delta for this tick.
     * @param d delta
     */
    public void setDelta(Vec2i d) { this.delta = Preconditions.checkNotNull(d, "d"); }

    /** Resets delta to (0,0). */
    public void reset() { this.delta = Vec2i.zero(); }
}

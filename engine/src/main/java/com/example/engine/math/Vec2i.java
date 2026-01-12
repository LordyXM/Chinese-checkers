package com.example.engine.math;

import java.util.Objects;

/**
 * Immutable 2D integer vector.
 */
public final class Vec2i {
    private final int x;
    private final int y;

    /**
     * Creates a vector.
     * @param x x coordinate
     * @param y y coordinate
     */
    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** @return x coordinate */
    public int x() { return x; }

    /** @return y coordinate */
    public int y() { return y; }

    /**
     * Adds two vectors.
     * @param other other vector
     * @return sum
     */
    public Vec2i add(Vec2i other) {
        return new Vec2i(this.x + other.x, this.y + other.y);
    }

    /** @return zero vector */
    public static Vec2i zero() { return new Vec2i(0, 0); }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vec2i v)) return false;
        return x == v.x && y == v.y;
    }

    @Override public int hashCode() { return Objects.hash(x, y); }

    @Override public String toString() { return "Vec2i(" + x + "," + y + ")"; }
}

package com.example.engine.ecs;

/**
 * Entity is a lightweight identifier. Components live in {@link Scene}.
 */
public final class Entity {
    private final int id;

    /**
     * Creates an entity wrapper.
     * @param id unique id
     */
    public Entity(int id) {
        this.id = id;
    }

    /** @return numeric id */
    public int id() { return id; }

    @Override public String toString() { return "Entity(" + id + ")"; }
}

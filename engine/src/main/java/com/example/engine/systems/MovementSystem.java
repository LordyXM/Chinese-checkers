package com.example.engine.systems;

import com.example.engine.components.PositionComponent;
import com.example.engine.components.VelocityComponent;
import com.example.engine.ecs.Entity;
import com.example.engine.ecs.Scene;
import com.example.engine.grid.GridWorld;
import com.example.engine.math.Vec2i;
import com.example.engine.util.Preconditions;

import java.util.List;

/**
 * Applies velocity delta to position with optional grid collision.
 */
public final class MovementSystem {

    /**
     * Updates entities with Position+Velocity. If a GridWorld resource exists,
     * movement is blocked by non-walkable tiles.
     *
     * @param scene scene
     */
    public void tick(Scene scene) {
        Preconditions.checkNotNull(scene, "scene");
        GridWorld world = scene.getResource(GridWorld.class);

        List<Entity> entities = scene.query(PositionComponent.class, VelocityComponent.class);
        for (Entity e : entities) {
            PositionComponent pos = scene.getComponent(e, PositionComponent.class);
            VelocityComponent vel = scene.getComponent(e, VelocityComponent.class);

            Vec2i d = vel.getDelta();
            if (d.x() == 0 && d.y() == 0) continue;

            Vec2i next = pos.get().add(d);
            if (world == null || world.isWalkable(next.x(), next.y())) {
                pos.set(next);
            }
            vel.reset();
        }
    }
}

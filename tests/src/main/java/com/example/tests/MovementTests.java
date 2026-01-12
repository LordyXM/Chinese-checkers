package com.example.tests;

import com.example.engine.components.PositionComponent;
import com.example.engine.components.VelocityComponent;
import com.example.engine.ecs.Entity;
import com.example.engine.ecs.Scene;
import com.example.engine.grid.Direction;
import com.example.engine.grid.GridWorld;
import com.example.engine.grid.TileType;
import com.example.engine.math.Vec2i;
import com.example.engine.systems.MovementSystem;

/**
 * Tests for movement and collision.
 */
public final class MovementTests {
    private MovementTests() {}

    /** Ensure movement applies delta on walkable tile. */
    public static void testMoveOnFloor() {
        Scene scene = new Scene();
        GridWorld world = GridWorld.withBorderWalls(8, 8);
        scene.putResource(GridWorld.class, world);

        Entity e = scene.createEntity();
        scene.addComponent(e, PositionComponent.class, new PositionComponent(new Vec2i(2, 2)));
        scene.addComponent(e, VelocityComponent.class, new VelocityComponent());

        scene.getComponent(e, VelocityComponent.class).setDelta(Direction.RIGHT.delta());
        new MovementSystem().tick(scene);

        Vec2i p = scene.getComponent(e, PositionComponent.class).get();
        assert p.equals(new Vec2i(3, 2)) : "Expected (3,2) but got " + p;
    }

    /** Ensure movement does not go through walls. */
    public static void testBlockedByWall() {
        Scene scene = new Scene();
        GridWorld world = new GridWorld(6, 6);
        world.setTile(3, 2, TileType.WALL);
        scene.putResource(GridWorld.class, world);

        Entity e = scene.createEntity();
        scene.addComponent(e, PositionComponent.class, new PositionComponent(new Vec2i(2, 2)));
        scene.addComponent(e, VelocityComponent.class, new VelocityComponent());

        scene.getComponent(e, VelocityComponent.class).setDelta(Direction.RIGHT.delta());
        new MovementSystem().tick(scene);

        Vec2i p = scene.getComponent(e, PositionComponent.class).get();
        assert p.equals(new Vec2i(2, 2)) : "Expected blocked at (2,2) but got " + p;
    }
}

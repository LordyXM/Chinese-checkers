package com.example.democonsole;

import com.example.engine.components.PositionComponent;
import com.example.engine.components.VelocityComponent;
import com.example.engine.ecs.Entity;
import com.example.engine.ecs.Scene;
import com.example.engine.grid.Direction;
import com.example.engine.grid.GridWorld;
import com.example.engine.grid.TileType;
import com.example.engine.math.Vec2i;
import com.example.engine.systems.MovementSystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Mini console ASCII demo using the engine.
 *
 * Controls: W A S D + Enter, Q to quit.
 */
public final class ConsoleDemoMain {

    public static void main(String[] args) throws Exception {
        Scene scene = new Scene();
        GridWorld world = GridWorld.withBorderWalls(20, 10);
        scene.putResource(GridWorld.class, world);

        Entity player = scene.createEntity();
        scene.addComponent(player, PositionComponent.class, new PositionComponent(new Vec2i(2, 2)));
        scene.addComponent(player, VelocityComponent.class, new VelocityComponent());

        MovementSystem movement = new MovementSystem();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Console demo. Use W/A/S/D + Enter. Q to quit.");
        while (true) {
            render(world, scene.getComponent(player, PositionComponent.class).get());
            System.out.print("> ");
            String line = in.readLine();
            if (line == null) break;
            line = line.trim().toLowerCase();
            if (line.isEmpty()) continue;

            char c = line.charAt(0);
            if (c == 'q') break;

            Direction dir = switch (c) {
                case 'w' -> Direction.UP;
                case 's' -> Direction.DOWN;
                case 'a' -> Direction.LEFT;
                case 'd' -> Direction.RIGHT;
                default -> null;
            };

            if (dir != null) {
                scene.getComponent(player, VelocityComponent.class).setDelta(dir.delta());
                movement.tick(scene);
            }
        }
        System.out.println("Bye!");
    }

    /**
     * Renders grid + player.
     * @param world world
     * @param playerPos player position
     */
    private static void render(GridWorld world, Vec2i playerPos) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int y = 0; y < world.height(); y++) {
            for (int x = 0; x < world.width(); x++) {
                if (playerPos.x() == x && playerPos.y() == y) sb.append('@');
                else {
                    TileType t = world.getTile(x, y);
                    sb.append(t.consoleChar());
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

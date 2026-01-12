package com.example.demogui;

import com.example.engine.components.PositionComponent;
import com.example.engine.components.VelocityComponent;
import com.example.engine.ecs.Entity;
import com.example.engine.ecs.Scene;
import com.example.engine.grid.GridWorld;
import com.example.engine.math.Vec2i;

import javax.swing.*;

/**
 * Swing GUI demo entry point.
 */
public final class GuiDemoMain {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Scene scene = new Scene();
            GridWorld world = GridWorld.withBorderWalls(30, 18);
            scene.putResource(GridWorld.class, world);

            Entity player = scene.createEntity();
            scene.addComponent(player, PositionComponent.class, new PositionComponent(new Vec2i(3, 3)));
            scene.addComponent(player, VelocityComponent.class, new VelocityComponent());

            JFrame frame = new JFrame("Mini GUI Demo");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setContentPane(new SwingGamePanel(scene, player));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

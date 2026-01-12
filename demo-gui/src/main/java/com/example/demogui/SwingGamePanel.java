package com.example.demogui;

import com.example.engine.components.PositionComponent;
import com.example.engine.components.VelocityComponent;
import com.example.engine.ecs.Entity;
import com.example.engine.ecs.Scene;
import com.example.engine.grid.Direction;
import com.example.engine.grid.GridWorld;
import com.example.engine.systems.MovementSystem;
import com.example.graphics.SwingRenderUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Panel that runs a small tick loop and draws the grid and player.
 */
public final class SwingGamePanel extends JPanel {
    private static final int TILE_SIZE = 24;

    private final Scene scene;
    private final Entity player;
    private final MovementSystem movement = new MovementSystem();

    /**
     * Creates panel.
     * @param scene scene containing GridWorld resource
     * @param player player entity
     */
    public SwingGamePanel(Scene scene, Entity player) {
        this.scene = scene;
        this.player = player;

        setFocusable(true);
        GridWorld world = scene.getResource(GridWorld.class);
        setPreferredSize(new Dimension(world.width() * TILE_SIZE, world.height() * TILE_SIZE));

        addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    Window w = SwingUtilities.getWindowAncestor(SwingGamePanel.this);
                    if (w != null) w.dispose();
                    return;
                }
                Direction dir = switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> Direction.UP;
                    case KeyEvent.VK_DOWN -> Direction.DOWN;
                    case KeyEvent.VK_LEFT -> Direction.LEFT;
                    case KeyEvent.VK_RIGHT -> Direction.RIGHT;
                    default -> null;
                };
                if (dir != null) {
                    scene.getComponent(player, VelocityComponent.class).setDelta(dir.delta());
                }
            }
        });

        Timer timer = new Timer(40, e -> {
            movement.tick(scene);
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GridWorld world = scene.getResource(GridWorld.class);
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            SwingRenderUtils.drawGrid(g2, world, TILE_SIZE);
            PositionComponent pos = scene.getComponent(player, PositionComponent.class);
            if (pos != null) SwingRenderUtils.drawEntity(g2, pos.get(), TILE_SIZE);
        } finally {
            g2.dispose();
        }
    }
}

package com.example.tests;

import com.example.engine.ecs.Scene;

/**
 * Tests for scene resource API.
 */
public final class SceneTests {
    private SceneTests() {}

    /** Ensure put/get resource works. */
    public static void testResources() {
        Scene s = new Scene();
        s.putResource(String.class, "hello");
        String v = s.getResource(String.class);
        assert "hello".equals(v) : "Expected 'hello' but got " + v;
    }
}

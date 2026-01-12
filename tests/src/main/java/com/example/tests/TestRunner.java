package com.example.tests;

/**
 * Minimal test runner using Java assertions.
 * Run with `-ea`.
 */
public final class TestRunner {
    public static void main(String[] args) {
        int failures = 0;
        failures += run("MovementSystem moves on floor", MovementTests::testMoveOnFloor);
        failures += run("MovementSystem blocks on wall", MovementTests::testBlockedByWall);
        failures += run("Scene resources store/retrieve", SceneTests::testResources);

        if (failures == 0) System.out.println("ALL TESTS PASSED");
        else {
            System.err.println("TEST FAILURES: " + failures);
            System.exit(1);
        }
    }

    private static int run(String name, Runnable test) {
        try {
            test.run();
            System.out.println("[PASS] " + name);
            return 0;
        } catch (AssertionError e) {
            System.err.println("[FAIL] " + name + " -> " + e.getMessage());
            return 1;
        } catch (Throwable t) {
            System.err.println("[ERROR] " + name + " -> " + t);
            return 1;
        }
    }
}

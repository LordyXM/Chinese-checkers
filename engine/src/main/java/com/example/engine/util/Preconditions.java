package com.example.engine.util;

/**
 * Small precondition utilities without external dependencies.
 */
public final class Preconditions {
    private Preconditions() {}

    /**
     * Throws {@link NullPointerException} if the value is null.
     *
     * @param value value to check
     * @param name name to appear in the exception message
     * @param <T> type
     * @return the same non-null value
     */
    public static <T> T checkNotNull(T value, String name) {
        if (value == null) throw new NullPointerException(name + " must not be null");
        return value;
    }

    /**
     * Throws {@link IllegalArgumentException} if condition is false.
     *
     * @param condition condition to validate
     * @param message message for exception
     */
    public static void checkArgument(boolean condition, String message) {
        if (!condition) throw new IllegalArgumentException(message);
    }
}

package com.example.engine.ecs;

import com.example.engine.util.Preconditions;

import java.util.*;

/**
 * Tiny ECS scene that stores components by (component class -> entity id -> component instance).
 * Also supports shared resources by class key.
 */
public final class Scene {
    private int nextEntityId = 1;

    private final Map<Class<?>, Map<Integer, Object>> components = new HashMap<>();
    private final Map<Class<?>, Object> resources = new HashMap<>();

    /**
     * Creates a new entity.
     * @return created entity
     */
    public Entity createEntity() {
        return new Entity(nextEntityId++);
    }

    /**
     * Adds a component to an entity.
     *
     * @param entity entity
     * @param componentClass component key
     * @param component component instance
     * @param <T> type
     */
    public <T> void addComponent(Entity entity, Class<T> componentClass, T component) {
        Preconditions.checkNotNull(entity, "entity");
        Preconditions.checkNotNull(componentClass, "componentClass");
        Preconditions.checkNotNull(component, "component");
        components.computeIfAbsent(componentClass, k -> new HashMap<>()).put(entity.id(), component);
    }

    /**
     * Gets a component or null.
     *
     * @param entity entity
     * @param componentClass component key
     * @param <T> type
     * @return component or null
     */
    @SuppressWarnings("unchecked")
    public <T> T getComponent(Entity entity, Class<T> componentClass) {
        Preconditions.checkNotNull(entity, "entity");
        Preconditions.checkNotNull(componentClass, "componentClass");
        Map<Integer, Object> map = components.get(componentClass);
        if (map == null) return null;
        return (T) map.get(entity.id());
    }

    /**
     * Returns entities that have all given component types.
     * Simple and slow — fine for small demos.
     *
     * @param required component classes required
     * @return list of matching entities
     */
    public List<Entity> query(Class<?>... required) {
        Preconditions.checkNotNull(required, "required");
        if (required.length == 0) return List.of();

        List<Class<?>> req = new ArrayList<>(Arrays.asList(required));
        req.sort(Comparator.comparingInt(c -> components.getOrDefault(c, Map.of()).size()));

        Map<Integer, Object> base = components.get(req.get(0));
        if (base == null) return List.of();

        List<Entity> out = new ArrayList<>();
        for (Integer id : base.keySet()) {
            boolean ok = true;
            for (int i = 1; i < req.size(); i++) {
                Map<Integer, Object> m = components.get(req.get(i));
                if (m == null || !m.containsKey(id)) { ok = false; break; }
            }
            if (ok) out.add(new Entity(id));
        }
        return out;
    }

    /**
     * Stores a shared resource.
     * @param key resource key
     * @param value value
     * @param <T> type
     */
    public <T> void putResource(Class<T> key, T value) {
        Preconditions.checkNotNull(key, "key");
        Preconditions.checkNotNull(value, "value");
        resources.put(key, value);
    }

    /**
     * Gets a shared resource or null.
     * @param key resource key
     * @param <T> type
     * @return resource or null
     */
    @SuppressWarnings("unchecked")
    public <T> T getResource(Class<T> key) {
        Preconditions.checkNotNull(key, "key");
        return (T) resources.get(key);
    }
}

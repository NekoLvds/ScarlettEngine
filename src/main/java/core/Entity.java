package core;

import data.Property;

//TODO component system

/**
 * Basic entity representation.
 * <p>
 * The Entity represents something in the simulation. This can be an Entity composed of other entities
 */
public abstract class Entity {
    private final Property<Long> idProperty;

    public Entity(long id){
        this.idProperty = new Property<>(id);
    }

    public abstract void update(long elapsedMilli);
}

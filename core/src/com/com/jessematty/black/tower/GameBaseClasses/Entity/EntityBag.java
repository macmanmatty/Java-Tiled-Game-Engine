package com.jessematty.black.tower.GameBaseClasses.Entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

/**
 * a bag of entities  that all share a common owner
 */
public class EntityBag {

   private Array<Entity> entities = new Array<>(); // the owned entities
   private Entity owner; // the entity owner
    public Array<Entity> getEntities() {
        return entities;
    }

    public Entity getOwner() {
        return owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }
}

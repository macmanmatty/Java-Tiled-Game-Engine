package com.jessematty.black.tower.GameBaseClasses.Entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

public class EntityBag {
   private Array<Entity> entities = new Array<>();
   private Entity owner;
    public Array<Entity> getEntities() {
        return entities;
    }
}

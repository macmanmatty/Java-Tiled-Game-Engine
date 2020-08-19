package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.ObjectMap;

public class EntityCreateable implements Component {
    private ObjectMap<String, Entity> entitiesToCreate = new ObjectMap<>();


    public EntityCreateable() {

    }

    public ObjectMap<String, Entity> getEntitiesToCreate() {
        return entitiesToCreate;
    }


}

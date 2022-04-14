package com.jessematty.black.tower.Components.CreateEntity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.ObjectMap;

public class CreateEntityOnAction implements Component {
    private ObjectMap<String, ParticleEntity> entitiesToCreate = new ObjectMap<>();


    public CreateEntityOnAction() {

    }

    public ObjectMap<String, ParticleEntity> getEntitiesToCreate() {
        return entitiesToCreate;
    }


}

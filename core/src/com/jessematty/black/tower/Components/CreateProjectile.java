package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.ObjectMap;

public class CreateProjectile implements Component {

  private   ObjectMap<String,  String> projectilesToCreate = new ObjectMap<>();

    public ObjectMap<String, String> getProjectilesToCreate() {
        return projectilesToCreate;
    }
}

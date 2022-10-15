package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

import java.util.UUID;

public class EntityId implements Component {
    private  String id= UUID.randomUUID().toString();

    public String getId() {
        return id;
    }
}

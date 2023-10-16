package com.jessematty.black.tower.GameBaseClasses.Entity;

import com.badlogic.ashley.core.Entity;

import java.util.UUID;

public class GameEntity extends Entity {
    String id;

    public GameEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}

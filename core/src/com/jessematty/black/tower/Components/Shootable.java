package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Actions.ActionableComponent;

public class Shootable implements ActionableComponent {

    Entity targetEntity;

    public Entity getTargetEntity() {
        return targetEntity;
    }

    public void setTargetEntity(Entity targetEntity) {
        this.targetEntity = targetEntity;
    }
}


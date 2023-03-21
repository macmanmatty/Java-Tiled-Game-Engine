package com.jessematty.black.tower.Systems.Entity;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.Engine.EventSystem;

public class CombineEntity extends EventSystem {



    Entity [] entities;

    public CombineEntity(MapDraw draw, PositionComponent position, MovableComponent movableComponent, Entity... entities) {
        super(draw);
        this.entities = entities;
    }

    @Override
    public void act(float deltaTime) {


    }
}

package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

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

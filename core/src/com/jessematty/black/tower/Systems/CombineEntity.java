package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class CombineEntity extends EventSystem {



    Entity [] entities;

    public CombineEntity(MapDraw draw, Position position, Movable movable, Entity... entities) {
        super(draw);
        this.entities = entities;
    }

    @Override
    public void act(float deltaTime) {


    }
}

package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.RemoveFromEngine;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class RemoveEntityFromEngineSystem extends GameEntitySystem {

    private ComponentMapper<Position> positions;

    private ImmutableArray<Entity> entities;


    public RemoveEntityFromEngineSystem(MapDraw draw) {
        super( Integer.MAX_VALUE, draw);
    }


    @Override
    public void addedToEngine(Engine engine) {

        positions=getGameComponentMapper().getPositionComponentMapper();
    }

    @Override
    public void update(float deltaTime) {

        ImmutableArray<Entity>   entities= getEngine().getEntitiesFor(Family.all(RemoveFromEngine.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            Position position=positions.get(entity);
           getDraw().getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY()).removeEntity(entity);
           getEngine().removeEntity(entity);

        }




    }


}




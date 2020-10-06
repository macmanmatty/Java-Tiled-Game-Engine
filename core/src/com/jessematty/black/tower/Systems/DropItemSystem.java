package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Drop;
import com.jessematty.black.tower.Components.Container;


import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MapUtilities;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class DropItemSystem extends GameEntitySystem {

  ComponentMapper<PositionComponent> positionGameComponentMapper;
  ComponentMapper<Container> containerComponentMapper;


    public DropItemSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {

        positionGameComponentMapper=getGameComponentMapper().getPositionComponentMapper();
        containerComponentMapper=getGameComponentMapper().getContainerComponentMapper();

    }

    @Override
    public void update(float deltaTime) {

       ImmutableArray<Entity> entities=getEngine().getEntitiesFor(Family.all(Drop.class, PositionComponent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){

            Entity entity=entities.get(count);
            PositionComponent position=positionGameComponentMapper.get(entity);
            LandSquareTile tile= MapUtilities.getNextTile(getWorld(), position);







            }

        }





}

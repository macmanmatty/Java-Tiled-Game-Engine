package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.AddItemToContainerComponent;
import com.jessematty.black.tower.Components.Container;
import com.jessematty.black.tower.Components.ID;


import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class AddItemToContainerSystem extends GameEntitySystem {


    ComponentMapper<PositionComponent> positionGameComponentMapper;
    ComponentMapper<Container> containerComponentMapper;
    ComponentMapper<AddItemToContainerComponent> addItemToContainerComponentComponentMapper;
    ComponentMapper<ID> idComponentMapper;


    public AddItemToContainerSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {

        positionGameComponentMapper=getGameComponentMapper().getPositionComponentMapper();
        containerComponentMapper=getGameComponentMapper().getContainerComponentMapper();
        addItemToContainerComponentComponentMapper=getGameComponentMapper().getAddItemToContainerComponentComponentMapper();
        idComponentMapper=getGameComponentMapper().getIdComponentMapper();

    }

    @Override
    public void update(float deltaTime) {

        ImmutableArray<Entity> entities=getEngine().getEntitiesFor(Family.all( Container.class, AddItemToContainerComponent.class, PositionComponent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){

            Entity entity=entities.get(count);
           AddItemToContainerComponent addItemToContainerComponent= addItemToContainerComponentComponentMapper.get(entity);
            Entity containerEntity=getWorld().getEntity(addItemToContainerComponent.getEntityContainerId());
            if(containerEntity!=null) {

                Container container=containerComponentMapper.get(containerEntity);
                container.getEntitiesInContainerIds().add(idComponentMapper.get(entity).getId());
                entity.remove(AddItemToContainerComponent.class);

            }







        }

    }







}

package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.AddOwnerComponent;
import com.jessematty.black.tower.Components.Attachable;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.RemoveOwnerComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class RemoveOwnerSystem extends GameEntitySystem { // checks  the die when zero  stats  for all entities  if all stats are zero
    // marks the entities  as dying.

    private ComponentMapper<AddOwnerComponent> addOwnerComponentComponentMapper;
    private ImmutableArray<Entity> entities;
    private ComponentMapper<Attachable> attachableComponentMapper;


    public RemoveOwnerSystem(MapDraw draw) {
        super(draw);


    }

    @Override
    public void addedToEngine(Engine engine) {
        addOwnerComponentComponentMapper=getGameComponentMapper().getAddOwnerComponentComponentMapper();
        attachableComponentMapper=getGameComponentMapper().getAttachableComponentMapper();
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        entities= getEngine().getEntitiesFor(Family.all(RemoveOwnerComponent.class).get());

        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            AddOwnerComponent addOwnerComponent=addOwnerComponentComponentMapper.get(entity);
            entity.remove(RemoveOwnerComponent.class);



        }



        super.update(deltaTime);
    }


}




package com.jessematty.black.tower.Systems.Owner;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatChangeable;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;

public class TransferStatsToOwner extends EntitySystem {

    private ComponentMapper<Attachable> owners= ComponentMapper.getFor(Attachable.class);
    private ComponentMapper<NumericStatChangeable> positions=ComponentMapper.getFor(NumericStatChangeable.class);
    private ImmutableArray<Entity> entities;




    @Override
    public void update(float deltaTime) {

        entities= getEngine().getEntitiesFor(Family.all(Attachable.class, PositionComponent.class, MovableComponent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            Attachable attachable =owners.get(entity);

        }



    }
}




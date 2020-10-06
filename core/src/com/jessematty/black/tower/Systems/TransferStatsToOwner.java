package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;

public class TransferStatsToOwner extends EntitySystem {

    private ComponentMapper<Attachable> owners= ComponentMapper.getFor(Attachable.class);
    private ComponentMapper<ChangableNumericStat> positions=ComponentMapper.getFor(ChangableNumericStat.class);
    private ImmutableArray<Entity> entities;




    @Override
    public void update(float deltaTime) {

        entities= getEngine().getEntitiesFor(Family.all(Attachable.class, PositionComponent.class, Movable.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            Attachable attachable =owners.get(entity);

        }



    }
}




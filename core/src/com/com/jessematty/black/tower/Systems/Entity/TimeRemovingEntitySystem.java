package com.jessematty.black.tower.Systems.Entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.RemoveAfterTime;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class TimeRemovingEntitySystem extends GameEntitySystem {

    private ComponentMapper<RemoveAfterTime> removables= ComponentMapper.getFor(RemoveAfterTime.class);
    private ImmutableArray<Entity> entities;


    public TimeRemovingEntitySystem(MapDraw draw) {
        super(draw);


    }

    @Override
    public void update(float deltaTime) {

        entities= getEngine().getEntitiesFor(Family.all(RemoveAfterTime.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            RemoveAfterTime removeAfterTime= removables.get(entity);
            removeAfterTime.increaseTime();
            if(removeAfterTime.getTimeCounter()==removeAfterTime.getTimeToExist()){
                getEngine().removeEntity(entity);
            }





        }



        super.update(deltaTime);
    }


}




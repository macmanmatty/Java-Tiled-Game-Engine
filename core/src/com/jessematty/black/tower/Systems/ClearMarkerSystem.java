package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Fruit;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class ClearMarkerSystem extends GameEntitySystem {


    public ClearMarkerSystem(MapDraw draw) {
        super( Integer.MAX_VALUE-1, draw);


    }

    @Override
    public void update(float deltaTime) {

        ImmutableArray<Entity> entities= getEngine().getEntitiesFor(Family.all().get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);





        }



        super.update(deltaTime);
    }


}




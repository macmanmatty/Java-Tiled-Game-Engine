package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class SetSpeedToMovableSystem extends GameEntitySystem {

    private ComponentMapper<MovableComponent> movableComponentMapper;
    private ComponentMapper<NumericStats> numericStatsComponentMapper;
    private ImmutableArray<Entity> entities;


    public SetSpeedToMovableSystem(MapDraw draw) {
        super(draw);


    }

    @Override
    public void addedToEngine(Engine engine) {

        movableComponentMapper= GameComponentMapper.getMovableComponentMapper();
        numericStatsComponentMapper=GameComponentMapper.getNumericStatsComponentMapper();
    }

    @Override
    public void update(float deltaTime) {

        entities= getEngine().getEntitiesFor(Family.all(MovableComponent.class, NumericStats.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            MovableComponent movableComponent = movableComponentMapper.get(entity);
            NumericStat speed=numericStatsComponentMapper.get(entity).getNumericStat("speed");
            if(speed!=null){
                movableComponent.setCurrentSpeed(speed.getFloatValue());
            }





        }



        super.update(deltaTime);
    }


}




package com.jessematty.black.tower.Systems.Plants;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Growable;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameTimeIntervalSystem;

public class GrowSystem extends GameTimeIntervalSystem {

    private ComponentMapper<Growable> growables;
    private ComponentMapper<ActionComponent> actions;
    private ComponentMapper<PositionComponent> positions;
    private ComponentMapper<NumericStats> numericStatsComponentMapper;

    private ImmutableArray<Entity> entities;




    public GrowSystem(float interval, MapDraw draw) {
        super(interval, draw);
    }

    @Override
    public void addedToEngine(Engine engine) {

        growables= GameComponentMapper.getGrowableComponentMapper();
        numericStatsComponentMapper=GameComponentMapper.getNumericStatsComponentMapper();
        actions=GameComponentMapper.getActionComponentMapper();
        positions=GameComponentMapper.getPositionComponentMapper();



    }

    @Override
    protected void updateInterval() {
        entities= getEngine().getEntitiesFor(Family.all(Growable.class, ActionComponent.class, PositionComponent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){




        }




    }



}




package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Dying;

import com.jessematty.black.tower.Components.FlagComponents.Killable;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class DieSystem extends GameEntitySystem { // checks  the die when zero  stats  for all entities  if all stats are zero
    // marks the entities  as dying.

    private ComponentMapper<Action> actions;
    private ImmutableArray<Entity> entities;
    private ComponentMapper<NumericStats> numericStatsComponentMapper;
    private ComponentMapper<Dying> dyingComponentMapper;


    public DieSystem(MapDraw draw) {
        super(draw);


    }

    @Override
    public void addedToEngine(Engine engine) {
        actions=GameComponentMapper.getActionComponentMapper();
        numericStatsComponentMapper= GameComponentMapper.getNumericStatsComponentMapper();
        dyingComponentMapper=GameComponentMapper.getDyingComponentMapper();
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        entities= getEngine().getEntitiesFor(Family.all(Action.class, NumericStats.class, Killable.class).get());

        int size=entities.size();
        for(int count=0; count<size; count++){

            Entity entity=entities.get(count);
            Dying dying=dyingComponentMapper.get(entity);
            // entity is already dying  don't do anything.
            if(dying!=null){
                continue;
            }
             NumericStats numericStats=numericStatsComponentMapper.get(entity);

             Array<NumericStat> statsToCheck=numericStats.getDieWhenZero();
             int numberOfKillWhenZeroStats=statsToCheck.size;
             int zeroStats=0;
             for(int count2=0; count2<numberOfKillWhenZeroStats; count2++){
                 NumericStat stat=statsToCheck.get(count);
                 if (stat.getDoubleValue()>0){ // stat is greater than zero entity is alive continue
                     continue;
                 }
                 else{

                     zeroStats++;
                 }





             }
             if(zeroStats==numberOfKillWhenZeroStats && numberOfKillWhenZeroStats!=0) {
                 actions.get(entity).setStat("die");
                 entity.add(new Dying());
             }

        }



        super.update(deltaTime);
    }


}




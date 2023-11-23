package com.jessematty.black.tower.Systems.Stats;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.FlagComponents.NumericStatChanged;
import com.jessematty.black.tower.Components.FlagComponents.StatChanged;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.SelfChangableNumericStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.TimeChangeStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.TimeChangingStats;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class NumericStatChangeSystem extends GameEntitySystem {
    int numberOfStatsToChange;
    private ComponentMapper<NumericStats> stats;
    private ComponentMapper<NumericStatsChangeable>changeNumericStats;
    private ComponentMapper<NumericStatsChangeComponent> numericStatChangeComponentComponentMapper;
    private ComponentMapper<TimeChangingStats> timeChangingNumericStatsComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;

    private ComponentMapper<Attachable>owners;

    public NumericStatChangeSystem(MapDraw draw) {
        super(draw);

    }
    @Override
    public void addedToEngine(Engine engine) {

        // get component mappers
        stats= GameComponentMapper.getNumericStatsComponentMapper();
        changeNumericStats=GameComponentMapper.getNumericStatsChangableComponentMapper();
        owners=GameComponentMapper.getAttachableComponentMapper();
        numericStatChangeComponentComponentMapper=GameComponentMapper.getNumericStatChangeComponentMapper();
        timeChangingNumericStatsComponentMapper=GameComponentMapper.getTimeChangingNumericStatsComponentMapper();
        ownedComponentComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
    }
    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }
    @Override
    public void update(float deltaTime) {
        // get entities
       ImmutableArray<Entity> entities= getEngine().getEntitiesFor(Family.all(NumericStats.class, NumericStatsChangeComponent.class).get());

       int size=entities.size();
       for(int count=0; count<size; count++) {

           Entity entityToChange=entities.get(count);
           NumericStatsChangeComponent numericStatChange=numericStatChangeComponentComponentMapper.get(entityToChange);
           Entity entityThatDoesTheChanging= numericStatChange.getChangerEntity();
           NumericStatsChangeable numericStatsChangeAmounts=changeNumericStats.get(entityThatDoesTheChanging);
           String changeAction=numericStatChange.getChangeAction();
           boolean addStat=numericStatChange.isAddStat();


           if (numericStatsChangeAmounts != null) {
               // get stats to change
              Array<NumericStatChangeable> numericStatsToChange = numericStatsChangeAmounts.getStatsToChange();
               if (numericStatsChangeAmounts != null) {

                   // change stats
                   changeStats( changeAction, addStat, numericStatsToChange, numericStatsChangeAmounts, entityToChange);
               }
           }

           entityToChange.remove(NumericStatsChangeComponent.class);

       }

    }
    private void changeStats(String action, boolean addStat, Array<NumericStatChangeable>  numericStatsToChange, NumericStatsChangeable numericStatsChangeAmounts, Entity entityToChange){
        NumericStats entityStatsToChange=stats.get(entityToChange);
        numberOfStatsToChange=numericStatsToChange.size;
        // see if entity has owner
        OwnedComponent ownedComponent =ownedComponentComponentMapper.get(entityToChange);

        if(ownedComponent !=null){

            // get owner   entity (owner of current entity aka attached entity)   in world  from entity id
            Entity entityOwner=getWorld().getEntity(ownedComponent.getOwnerEntityID());
            // get owner  entity stats
            NumericStatsChangeable numericStatsToLessen=changeNumericStats.get(entityOwner);


            // if owner has stats
            if(numericStatsToLessen!=null) {
                for (int count = 0; count < numberOfStatsToChange; count++) {
                    //  change amount  of stats changes transferred to owner
                    NumericStatChangeable statToChange= numericStatsToChange.get(count);
                    NumericStatChangeable statToLessen = numericStatsToLessen.getStat(statToChange.getName());
                    // if entity  has stat to change change it.
                    if(statToLessen!=null){
                        statToChange.addValues(statToLessen);
                    }
                    
                }
            }
            // call change stats again  to see if owner entity has owner
            changeStats( action, addStat,  numericStatsToChange, numericStatsChangeAmounts, entityOwner); // change stats for the owner entity
        }
        if (entityStatsToChange != null && numericStatsChangeAmounts != null) {
            for (int count = 0; count < numberOfStatsToChange; count++) {
                // change stats for the entity
                NumericStatChangeable changableNumericStat = numericStatsToChange.get(count);

                changeStat(entityToChange, entityStatsToChange, entityStatsToChange.getNumericStat(changableNumericStat.getName()), changableNumericStat,action,  addStat, 1);

            }
        }
    }



    public void changeStat(Entity entityToChange, NumericStats entityToChangeStats, NumericStat stat, NumericStatChangeable changeStat, String action, boolean addStat , int changeInterval){


        boolean change=false;
            if (stat != null && changeStat != null) {

                // get the groups that can change the stat
                Array<String> statChangeGroups = stat.getChangeGroups();

                // get the groups the stat can change on
                Array<String> changeStatChangeGroups = changeStat.getChangeGroups();
                boolean groupsMatch=true;
                boolean actionChange=true;
                // see if groups and actions match
                if (!(statChangeGroups.isEmpty()) && !(changeStatChangeGroups.isEmpty())) {// if stat can be changed
                    groupsMatch = InList.isInList(changeStatChangeGroups, statChangeGroups);
                    actionChange = InList.isInList(changeStat.getActionsToChangeOn(), action);
                }


                if (groupsMatch == true && actionChange == true) { //  if stat can be changed change stat
                    if(changeStat.getAmountOfTimeToChangeFor()<=0) {
                        // if stat is randomly changed randomly change between min max values
                        if (changeStat.isRandomChange()) {
                            if (addStat == true) {
                                stat.addValuesRandom(changeStat, .1f * changeInterval);
                            } else {
                                stat.subtractValuesRandom(changeStat, .1f * changeInterval);
                            }
                        } else {
                            // just add  or subtract the value of the stat
                            if (addStat == true) {
                                stat.addValue(changeStat.getDoubleValue());
                            } else {
                                stat.subtractValue(changeStat.getDoubleValue());
                            }
                        }
                        change = true;
                    }
                    else{

                        TimeChangingStats timeChangingStats =timeChangingNumericStatsComponentMapper.get(entityToChange);
                        timeChangingStats.getTimeChangeNumericStats().add(new TimeChangeStat(stat, changeStat.getAmountOfTimeToChangeFor()));

                    }

                        // flag to notify other systems a  stat changed
                        entityToChange.add(new NumericStatChanged());
                        entityToChange.add(new StatChanged());




                }



                // change linked stats
                Array<SelfChangableNumericStatChangeable> selfChangableNumericStats=stat.getLinkedStatsToChange();
                int numberOfLinkedStats=selfChangableNumericStats.size;
                for(int count3=0; count3<numberOfLinkedStats; count3++){
                    SelfChangableNumericStatChangeable selfChangableNumericStat=selfChangableNumericStats.get(count3);
                    NumericStat selfChangingStat=entityToChangeStats.getNumericStat(selfChangableNumericStat.getName());
                    if(selfChangingStat!=null){
                        changeStat(  entityToChange, entityToChangeStats, selfChangingStat, selfChangableNumericStat, "self", true, changeInterval);

                    }

                }


            }



        }


}







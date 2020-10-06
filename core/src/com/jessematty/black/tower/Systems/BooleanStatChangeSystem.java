package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Markers.NumericStatChanged;
import com.jessematty.black.tower.Components.Markers.StatChanged;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.TimeChangeStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.TimeChangingStats;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.Transient;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangable;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableBooleanStat;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

@Transient

public class BooleanStatChangeSystem extends GameEntitySystem{



    private ComponentMapper<BooleanStats> stats;
    private ComponentMapper<BooleanStatsChangable>changeBooleanStats;
    private ComponentMapper<BooleanStatsChangeComponent> booleanStatChangeComponentComponentMapper;
    private ComponentMapper<TimeChangingStats> timeChangingNumericStatsComponentMapper;



    public BooleanStatChangeSystem(MapDraw draw) {
        super(draw);



    }


    @Override
    public void addedToEngine(Engine engine) {

        stats=getGameComponentMapper().getBooleanStatsComponentMapper();
        changeBooleanStats=getGameComponentMapper().getBooleanStatsChangableComponentMapper();
        booleanStatChangeComponentComponentMapper=getGameComponentMapper().getBooleanStatChangeComponentComponentMapper();
        timeChangingNumericStatsComponentMapper=getGameComponentMapper().getTimeChangingNumericStatsComponentMapper();


    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {

        ImmutableArray<Entity> entities= getEngine().getEntitiesFor(Family.all(NumericStats.class, BooleanStatsChangeComponent.class).get());

        int size=entities.size();
        for(int count=0; count<size; count++) {
            Entity entityToChange=entities.get(count);
            BooleanStats statsToChange;
            BooleanStatsChangable booleanStatsChangeAmounts;
            boolean invert;

            BooleanStatsChangeComponent booleanStatsChangeComponent =booleanStatChangeComponentComponentMapper.get(entityToChange);
            Entity entityThatDoesTheChanging= booleanStatsChangeComponent.getChangerEntity();
            statsToChange=stats.get(entityToChange);
            booleanStatsChangeAmounts=changeBooleanStats.get(entityThatDoesTheChanging);
            invert= booleanStatsChangeComponent.isAddStat();
            String changeAction= booleanStatsChangeComponent.getChangeAction();

        if (statsToChange != null && booleanStatsChangeAmounts != null) {

            Array<ChangableBooleanStat> BooleanStatChanges = booleanStatsChangeAmounts.getStatsToChange();
            int size2 = BooleanStatChanges.size;

            for (int count2 = 0; count2 < size2; count2++) {

                ChangableBooleanStat changableBooleanStat = BooleanStatChanges.get(count);
                changeStat(entityToChange, statsToChange, statsToChange.getBooleanStat(changableBooleanStat.getName()), changableBooleanStat, changeAction, invert);


            }
        }


        entityToChange.remove(BooleanStatsChangeComponent.class);


        }
    }



    public void changeStat(Entity entityToChange, BooleanStats entityToChangeStats, BooleanStat stat, ChangableBooleanStat changeStat, String action, boolean addStat){


        boolean change=false;
        if (stat != null && changeStat != null) {

            // get the groups that can change the stat
            Array<String> statChangeGroups = stat.getChangeGroups();

            // get the groups the sdtat can change on
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

                    stat.addFlag(changeStat.getFlag(), changeStat.getCombineMode(), addStat);
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










    }


}





}

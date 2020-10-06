package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Markers.NumericStatChanged;
import com.jessematty.black.tower.Components.Markers.StatChanged;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableStringStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.TimeChangeStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.TimeChangingStats;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangable;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.Transient;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

@Transient
public class StringStatChangeSystem extends GameEntitySystem{

    private ComponentMapper<StringStats> stringStatsComponentMapper;
    private ComponentMapper<StringStatsChangable> stringStatsChangableComponentMapper;
    private ComponentMapper<StringStatsChangeComponent> stringStatChangeComponentComponentMapper;
    ComponentMapper<TimeChangingStats> timeChangingStatsComponentMapper;
    public StringStatChangeSystem(MapDraw draw) {
        super(draw);

    }
    @Override
    public void addedToEngine(Engine engine) {
        stringStatsComponentMapper =getGameComponentMapper().getStringStatsComponentMapper();
        stringStatsChangableComponentMapper =getGameComponentMapper().getStringStatsChangableComponentMapper();
        stringStatChangeComponentComponentMapper=getGameComponentMapper().getStringStatChangeComponentComponentMapper();
        timeChangingStatsComponentMapper=getGameComponentMapper().getTimeChangingNumericStatsComponentMapper();
    }
    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }
    @Override
    public void update(float deltaTime) {


        ImmutableArray<Entity> entities= getEngine().getEntitiesFor(Family.all(NumericStats.class, StringStatsChangeComponent.class).get());



        int size=entities.size();
        for(int count=0; count<size; count++){


            Entity entityToChange=entities.get(count);
            StringStatsChangeComponent stringStatsChangeComponent =stringStatChangeComponentComponentMapper.get(entityToChange);
            Entity enitityThatDoesTheChanging= stringStatsChangeComponent.getChangerEntity();

            String action= stringStatsChangeComponent.getChangeAction();
            boolean addStat= stringStatsChangeComponent.isAddStat();
            StringStats statsToChange=stringStatsComponentMapper.get(entityToChange);
            StringStatsChangable stringStatsChangable=stringStatsChangableComponentMapper.get(enitityThatDoesTheChanging);






        if (statsToChange != null && stringStatsChangable != null) {
            Array<ChangableStringStat> stringStatList = stringStatsChangable.getStatsToChange();
            changeStringStats(action, addStat, statsToChange, entityToChange, stringStatList);

        }

        entityToChange.remove(StringStatsChangeComponent.class);
    }
    }


    private void changeStringStats( String action, boolean addStat,  StringStats statsToChange,   Entity entityToChange, Array<ChangableStringStat> changableStringStats){

        int size = changableStringStats.size;
        for (int count = 0; count < size; count++) {
            ChangableStringStat changeableStringStat = changableStringStats.get(count);
            changeStat(entityToChange, statsToChange, statsToChange.getStringStat(changeableStringStat.getName()), changeableStringStat, action, addStat);

        }
    }


    public void changeStat(Entity entityToChange, StringStats entityToChangeStats, StringStat stat, ChangableStringStat changeStat, String action, boolean addStat){


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
                actionChange = InList.isInList(action, changeStat.getActionsToChangeOn());
            }


            if (groupsMatch == true && actionChange == true) { //  if stat can be changed change stat
                if(changeStat.getAmountOfTimeToChangeFor()<=0) {
                    // if stat is randomly changed randomly change between min max values

                    if(addStat) {
                        stat.addTextToStat(changeStat.getStat());
                    }
                    else{

                        stat.removeTextFromStat(changeStat.getStat());
                    }

                    change = true;
                }
                else{

                    TimeChangingStats timeChangingStats =timeChangingStatsComponentMapper.get(entityToChange);
                    timeChangingStats.getTimeChangeNumericStats().add(new TimeChangeStat(stat, changeStat.getAmountOfTimeToChangeFor()));

                }

                // flag to notify other systems a  stat changed
                entityToChange.add(new NumericStatChanged());
                entityToChange.add(new StatChanged());




            }










        }


    }




}

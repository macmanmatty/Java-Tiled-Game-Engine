package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.CompareMode;
import com.jessematty.black.tower.Components.Markers.NumericStatChanged;
import com.jessematty.black.tower.Components.Markers.StatChanged;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.TimeChangeStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.TimeChangingStats;
import com.jessematty.black.tower.Components.Stats.FollowingNumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsSelfChangable;
import com.jessematty.black.tower.Components.SelfChangableNumericStat;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class SelfChangeNumericStatsSystem extends GameTimeIntervalSystem {
    private ComponentMapper<NumericStats> numericStatsComponentMapper;
    private ComponentMapper<NumericStatsSelfChangable> numericStatsChangableComponentMapper;
    private ComponentMapper<Action> actionComponentMapper;
    private ImmutableArray<Entity> entities;
    private ComponentMapper<TimeChangingStats> timeChangingStatsComponentMapper;
    private GameTime gameTime;
    private int interval;
    @Override
    public void addedToEngine(Engine engine) {
        numericStatsComponentMapper=getGameComponentMapper().getNumericStatsComponentMapper();
            numericStatsChangableComponentMapper =getGameComponentMapper().getNumericStatsSelfChangableComponentMapper();
            actionComponentMapper=getGameComponentMapper().getActionComponentMapper();
            timeChangingStatsComponentMapper=getGameComponentMapper().getTimeChangingNumericStatsComponentMapper();
    }
    public SelfChangeNumericStatsSystem(int interval, MapDraw draw) {
        super(interval, draw);
        this.interval=interval;
        gameTime=getGameTime();
    }
    @Override
    protected void updateInterval() {
        entities= getEngine().getEntitiesFor(Family.all( SelfChangableNumericStat.class, NumericStats.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++) {
            Entity entity = entities.get(count);
            NumericStats numericStats = numericStatsComponentMapper.get(entity);
            NumericStatsSelfChangable numericStatsSelfChangable = numericStatsChangableComponentMapper.get(entity);
            Array<SelfChangableNumericStat> changeableNumericStats= numericStatsSelfChangable.getStatsToChange();
            int size2=changeableNumericStats.size;
            for(int count2=0; count2<size2; count2++ ){
                // get self changing  numeric stat
                SelfChangableNumericStat changeableNumericStat=changeableNumericStats.get(count2);
                //get numeric stat to be changed
                NumericStat numericStat=numericStats.getNumericStat(changeableNumericStat.getName());

                // if stat can be changed and it changes with time add a new change system.
                if(numericStat!=null && changeableNumericStat.isChangesWithTime()){
                    changeStat( entity, numericStats, numericStat, changeableNumericStat, "self", true, interval);
                }
                else {
                    boolean changeStat = canChangeStat(changeableNumericStat, numericStats);
                    if (changeStat == true) {
                        changeStat( entity, numericStats, numericStat, changeableNumericStat, "self", true, interval);
                    }
                }

            }
        }
    }
    private boolean canChangeStat(SelfChangableNumericStat stat, NumericStats numericStats){
        Array<FollowingNumericStat> followingNumericStatArray=stat.getStatsToFollow();
        int size=followingNumericStatArray.size;
        for(int count=0; count<size; count++){
            FollowingNumericStat followingNumericStat=followingNumericStatArray.get(count);
            NumericStat numericStat =numericStats.getNumericStat(followingNumericStat.getName());
            if(numericStat.getName().equalsIgnoreCase(followingNumericStat.getName())) {



                boolean canChange = statCheck(followingNumericStat, numericStat);
                return canChange;


            }

            
            
        }
        
        return false;
        
    }
    
    
    private boolean statCheck(FollowingNumericStat followingNumericStat, NumericStat numericStat){
        if(numericStat==null || followingNumericStat==null){
            return  false;
            
        }


        CompareMode compareMode=followingNumericStat.getCompareMode();
        switch (compareMode){
            case GREATER_THAN:
                if(numericStat.getDoubleValue()>=followingNumericStat.getDoubleValue()){
                    return true;
                }
                
                return false;
            case LESS_THAN:
                if(numericStat.getDoubleValue()<=followingNumericStat.getDoubleValue()){
                    return true;
                }
                return false;          
            case EQUALS:
                if(numericStat.getDoubleValue()==followingNumericStat.getDoubleValue()){
                    return true;
                }
                return false;            
                case NOT_EQUAL_TO:
                    if(numericStat.getDoubleValue()!=followingNumericStat.getDoubleValue()){
                        return true;
                    }
                    return false;
                
        }
        
        return false;
        
        
    }


    public void changeStat( Entity entityToChange, NumericStats entityToChangeStats,  NumericStat stat, ChangableNumericStat changeStat, String action,  boolean addStat , int changeInterval){


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

                    TimeChangingStats timeChangingStats =timeChangingStatsComponentMapper.get(entityToChange);
                    timeChangingStats.getTimeChangeNumericStats().add(new TimeChangeStat(stat, changeStat.getAmountOfTimeToChangeFor()));

                }

                // flag to notify other systems a  stat changed
                entityToChange.add(new NumericStatChanged());
                entityToChange.add(new StatChanged());




            }



            // change linked stats
            Array<SelfChangableNumericStat> selfChangableNumericStats=stat.getLinkedStatsToChange();
            int numberOfLinkedStats=selfChangableNumericStats.size;
            for(int count3=0; count3<numberOfLinkedStats; count3++){
                SelfChangableNumericStat selfChangableNumericStat=selfChangableNumericStats.get(count3);
                NumericStat selfChangingStat=entityToChangeStats.getNumericStat(selfChangableNumericStat.getName());
                if(selfChangingStat!=null){
                    changeStat(  entityToChange, entityToChangeStats, selfChangingStat, selfChangableNumericStat, "self", true, changeInterval);

                }

            }


        }



    }


}

package com.jessematty.black.tower.Components.Stats;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.OrderedMap;
import com.jessematty.black.tower.Components.Stats.BooleanStat;

/**
 * class the holds the map of boolean stats for an entity
 */
public class BooleanStats implements Component {

    protected OrderedMap <String, BooleanStat>  booleanStats= new OrderedMap<String, com.jessematty.black.tower.Components.Stats.BooleanStat>();
    /**
     * flag for whether nor not the stat has changed
     */
    private  boolean statHasChanged;

    /**
     * sets a boolean stat if it exists  if not does nothing
     * @param stat
     */
    public void addStat(BooleanStat stat){
        if(booleanStats.get(stat.getName())!=null){
            return;

        }
            booleanStats.put((stat.getName()), (BooleanStat)stat);

    }

    /**
     * removes a boolean stat
     * @param name
     */
    public  void removeStat(  String name){

            booleanStats.remove(name);

    }

    /**
     * returns a boolean stat from the map with given string name as the key
     * if key doesn't exist returns null
     * @param statName the name of the stat to get
     * @return the stat if it exists if not returns null
     */
    public BooleanStat getBooleanStat(String statName){
        return booleanStats.get(statName);
    }

    public OrderedMap<String, BooleanStat> getBooleanStats() {
        return booleanStats;
    }

    public boolean isStatHasChanged() {
        return statHasChanged;
    }

    public void setStatHasChanged(boolean statHasChanged) {
        this.statHasChanged = statHasChanged;
    }

    /**
     * adds a boolean to the stat in map if exists
     * otherwise add it to the map if it doesn't exist yet
     * @param booleanStat the stat to combine with
     */
    public void addOrCombineStat(BooleanStat booleanStat) {
        if(booleanStats.get(booleanStat.getName())!=null) {
            booleanStat.setFlag(booleanStat.getFlag() && booleanStat.getFlag());
        }

        else {
            booleanStats.put((booleanStat.getName()), (BooleanStat) booleanStat);
        }

    }
}

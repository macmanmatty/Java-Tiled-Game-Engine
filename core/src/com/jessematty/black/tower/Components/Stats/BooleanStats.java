package com.jessematty.black.tower.Components.Stats;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.OrderedMap;
import com.jessematty.black.tower.Components.Stats.BooleanStat;

public class BooleanStats implements Component {

    protected OrderedMap <String, BooleanStat>  booleanStats= new OrderedMap<String, com.jessematty.black.tower.Components.Stats.BooleanStat>();
    private  boolean statHasChanged;
    public void addStat(BooleanStat stat){
        if(booleanStats.get(stat.getName())!=null){
            return;

        }
            booleanStats.put((stat.getName()), (BooleanStat)stat);

    }
    public  void removeStat(  String name){

            booleanStats.remove(name);

    }
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

    public void addOrCombineStat(BooleanStat booleanStat) {
        if(booleanStats.get(booleanStat.getName())!=null) {
           BooleanStat booleanStatInStats = booleanStats.get(booleanStat.getName());
            booleanStat.setFlag(booleanStat.getFlag() && booleanStat.getFlag());
        }

        else {
            booleanStats.put((booleanStat.getName()), (BooleanStat) booleanStat);
        }

    }
}

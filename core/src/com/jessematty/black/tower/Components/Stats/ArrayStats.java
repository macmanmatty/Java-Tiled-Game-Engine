package com.jessematty.black.tower.Components.Stats;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.OrderedMap;

public class ArrayStats implements Component {

    protected OrderedMap <String, ArrayStat> arrayStats = new OrderedMap<String, ArrayStat>();
    private  boolean statHasChanged;
    public void addNewStat(ArrayStat stat){
        if(arrayStats.get(stat.getName())!=null){
            return;

        }
        arrayStats.put((stat.getName()), stat);

    }

    public void addToStat(String statName, Object statToAdd){
        if(arrayStats.get(statName)==null){
            return;

        }
        arrayStats.get(statName).getStat().add(statToAdd);

    }
    public  void removeStat(  String name){

            arrayStats.remove(name);

    }

    public OrderedMap<String, ArrayStat> getArrayStats() {
        return arrayStats;
    }


    public boolean isStatHasChanged() {
        return statHasChanged;
    }

    public void setStatHasChanged(boolean statHasChanged) {
        this.statHasChanged = statHasChanged;
    }


}

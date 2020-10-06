package com.jessematty.black.tower.Components.Stats;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.badlogic.gdx.utils.OrderedMap;

public class StringStats implements Component {

    protected OrderedMap< String,  StringStat> stringStats= new OrderedMap<String, StringStat>();
    private boolean statHasChanged;
    public void addStat(StringStat stat){

        if(stringStats.get(stat.getName())!=null){
            return;
        }

            stringStats.put(stat.getName(), stat);


    }
    public  void removeStat(  String name){

            stringStats.remove(name);

    }
    public StringStat getStringStat(String  statName){
        return stringStats.get(statName);
    }

    public OrderedMap<String, StringStat> getStringStats() {
        return stringStats;
    }

    public Array<StringStat> getStatsAsAnArray(){ // used for displaying of stats
        Array<StringStat>  stringStatArray= new Array<StringStat>();
        Values<StringStat> values=stringStats.values();
        while(values.hasNext){
            stringStatArray.add(values.next());
        }
        return stringStatArray;
    }

    public boolean isStatHasChanged() {
        return statHasChanged;
    }

    public void setStatHasChanged(boolean statHasChanged) {
        this.statHasChanged = statHasChanged;
    }

    public void addOrCombineStat(StringStat stat) {
        if(stringStats.get(stat.getName())!=null){
            stat.addTextToStat(stat.getStat());
        }

        stringStats.put(stat.getName(), stat);
    }
}

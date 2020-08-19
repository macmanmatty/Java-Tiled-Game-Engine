package com.jessematty.black.tower.Components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;
public class TileWeatherNumericStatsChangable implements Component {
   private Array<TileWeatherChangableNumericStat> amountsToChange= new Array<TileWeatherChangableNumericStat>();
   
    public Array<TileWeatherChangableNumericStat> getStatsToChange() {
        return amountsToChange;
    }

    public void addStatToChange(TileWeatherChangableNumericStat amount ){
        amountsToChange.add(amount);
    }
    public TileWeatherChangableNumericStat getStat(String name){
        int size=amountsToChange.size;
        for(int count=0; count<size; count++){
            TileWeatherChangableNumericStat numericStatChangeable=amountsToChange.get(count);
            if(numericStatChangeable.getName().equals(name)){
                return numericStatChangeable;
            }
        }
        return  null;
    }
    public void removeStatToChange(String stat ){
        int size=amountsToChange.size;
        for(int count=0; count<size; count++){
            if (amountsToChange.get(count).getName().equals(stat)){
               amountsToChange.removeIndex(count);
            }
        }
    }
}

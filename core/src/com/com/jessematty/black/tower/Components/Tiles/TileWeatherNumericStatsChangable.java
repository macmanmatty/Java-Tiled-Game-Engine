package com.jessematty.black.tower.Components.Tiles;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class TileWeatherNumericStatsChangable implements Component {
   private Array<TileWeatherChangableNumericStatChangeable> amountsToChange= new Array<TileWeatherChangableNumericStatChangeable>();
   
    public Array<TileWeatherChangableNumericStatChangeable> getStatsToChange() {
        return amountsToChange;
    }

    public void addStatToChange(TileWeatherChangableNumericStatChangeable amount ){
        amountsToChange.add(amount);
    }
    public TileWeatherChangableNumericStatChangeable getStat(String name){
        int size=amountsToChange.size;
        for(int count=0; count<size; count++){
            TileWeatherChangableNumericStatChangeable numericStatChangeable=amountsToChange.get(count);
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

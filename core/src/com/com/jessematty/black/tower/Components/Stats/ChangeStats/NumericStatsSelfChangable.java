package com.jessematty.black.tower.Components.Stats.ChangeStats;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
public class NumericStatsSelfChangable implements Component {
   private Array<SelfChangableNumericStatChangeable> statsToChange = new Array<>();
    public Array<SelfChangableNumericStatChangeable> getStatsToChange() {
        return statsToChange;
    }
    public void addStatToChange(SelfChangableNumericStatChangeable amount ){
        statsToChange.add(amount);
    }
    public SelfChangableNumericStatChangeable getStat(String name){
        int size= statsToChange.size;
        for(int count=0; count<size; count++){
            SelfChangableNumericStatChangeable numericStatChangeable= statsToChange.get(count);
            if(numericStatChangeable.getName().equals(name)){
                return numericStatChangeable;
            }
        }
        return  null;
    }
    public void removeStatToChange(String stat ){
        int size= statsToChange.size;
        for(int count=0; count<size; count++){
            if (statsToChange.get(count).getName().equals(stat)){
               statsToChange.removeIndex(count);
            }
        }
    }
}

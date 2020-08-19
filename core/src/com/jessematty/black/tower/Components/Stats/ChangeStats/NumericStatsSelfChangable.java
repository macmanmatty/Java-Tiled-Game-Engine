package com.jessematty.black.tower.Components.Stats.ChangeStats;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.SelfChangableNumericStat;

public class NumericStatsSelfChangable implements Component {
   private Array<SelfChangableNumericStat> statsToChange = new Array<>();

    public Array<SelfChangableNumericStat> getStatsToChange() {
        return statsToChange;
    }

    public void addStatToChange(SelfChangableNumericStat amount ){
        statsToChange.add(amount);
    }
    public SelfChangableNumericStat getStat(String name){
        int size= statsToChange.size;
        for(int count=0; count<size; count++){
            SelfChangableNumericStat numericStatChangeable= statsToChange.get(count);
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

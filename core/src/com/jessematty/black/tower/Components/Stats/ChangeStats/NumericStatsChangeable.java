package com.jessematty.black.tower.Components.Stats.ChangeStats;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class NumericStatsChangable implements Component {
   private Array<ChangeableNumericStat> statsToChange = new Array<ChangeableNumericStat>();

    public Array<ChangeableNumericStat> getStatsToChange() {
        return statsToChange;
    }

    public void addStatToChange(ChangeableNumericStat changableNumericStat ){

        int size=statsToChange.size;
        boolean addStat=true;
        for(int count=0; count<size; count++){
            if(statsToChange.get(count).getName().equals(changableNumericStat.getName())){
                addStat=false;
                break;
            }
        }

        if(addStat==true) {
            statsToChange.add(changableNumericStat);
        }


    }

    public ChangeableNumericStat getStat(String name){

        int size= statsToChange.size;
        for(int count=0; count<size; count++){

            ChangeableNumericStat numericStatChangeable= statsToChange.get(count);
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


    public void addOrCombineStat(ChangeableNumericStat changableNumericStat) {
        int size=statsToChange.size;
        boolean addStat=true;
        for(int count=0; count<size; count++){
            if(statsToChange.get(count).getName().equals(changableNumericStat.getName())){
                statsToChange.add(changableNumericStat);
                addStat=false;
                break;
            }
        }

        if(addStat==true) {
            statsToChange.add(changableNumericStat);
        }

    }
}

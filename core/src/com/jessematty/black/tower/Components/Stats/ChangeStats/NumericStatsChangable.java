package com.jessematty.black.tower.Components.Stats.ChangeStats;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat;

public class NumericStatsChangable implements Component {
   private Array<com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat> statsToChange = new Array<com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat>();

    public Array<com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat> getStatsToChange() {
        return statsToChange;
    }

    public void addStatToChange(com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat changableNumericStat ){

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

    public com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat getStat(String name){

        int size= statsToChange.size;
        for(int count=0; count<size; count++){

            com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat numericStatChangeable= statsToChange.get(count);
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


    public void addOrCombineStat(ChangableNumericStat changableNumericStat) {
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

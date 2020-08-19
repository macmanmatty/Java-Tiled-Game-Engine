package com.jessematty.black.tower.Components.Stats.ChangeStats;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class BooleanStatsChangable implements Component {



   private  Array<ChangableBooleanStat> statsToChange = new Array<ChangableBooleanStat>();


    public Array<ChangableBooleanStat> getStatsToChange() {
        return statsToChange;
    }

    public void addStatToChange(ChangableBooleanStat changableBooleanStat ){

        int size=statsToChange.size;
        boolean addStat=true;
        for(int count=0; count<size; count++){
            if(statsToChange.get(count).getName().equals(changableBooleanStat.getName())){
                addStat=false;
                break;
            }
        }

        if(addStat==true) {
            statsToChange.add(changableBooleanStat);
        }

    }
    public void removeStatToChange(String name){

        int size= statsToChange.size;
        for(int count=0; count<size; count++){

            if (statsToChange.get(count).getName().equals(name)){


               statsToChange.removeIndex(count);

            }
        }


    }

    public void addOrCombineStat(ChangableBooleanStat changableBooleanStat) {
        int size=statsToChange.size;
        boolean addStat=true;
        for(int count=0; count<size; count++){
            if(statsToChange.get(count).getName().equals(changableBooleanStat.getName())){
                statsToChange.add(changableBooleanStat);
                addStat=false;
                break;
            }
        }

        if(addStat==true) {
            statsToChange.add(changableBooleanStat);
        }

    }

    public ChangableBooleanStat getStat(String name){

        int size= statsToChange.size;
        for(int count=0; count<size; count++){

            ChangableBooleanStat numericStatChangeable= statsToChange.get(count);
            if(numericStatChangeable.getName().equals(name)){

                return numericStatChangeable;

            }
        }

        return  null;


    }



}

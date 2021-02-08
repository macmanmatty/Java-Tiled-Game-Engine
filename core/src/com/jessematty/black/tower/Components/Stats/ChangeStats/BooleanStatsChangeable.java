package com.jessematty.black.tower.Components.Stats.ChangeStats;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class BooleanStatsChangable implements Component {



   private  Array<ChangeableBooleanStat> statsToChange = new Array<ChangeableBooleanStat>();


    public Array<ChangeableBooleanStat> getStatsToChange() {
        return statsToChange;
    }

    public void addStatToChange(ChangeableBooleanStat changableBooleanStat ){

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

    public void addOrCombineStat(ChangeableBooleanStat changableBooleanStat) {
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

    public ChangeableBooleanStat getStat(String name){

        int size= statsToChange.size;
        for(int count=0; count<size; count++){

            ChangeableBooleanStat numericStatChangeable= statsToChange.get(count);
            if(numericStatChangeable.getName().equals(name)){

                return numericStatChangeable;

            }
        }

        return  null;


    }



}

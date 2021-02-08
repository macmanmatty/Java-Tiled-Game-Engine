package com.jessematty.black.tower.Components.Stats.ChangeStats;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class StringStatsChangable implements Component {



   private Array<ChangeableStringStat> statsToChange = new Array<ChangeableStringStat>();


    public Array<ChangeableStringStat> getStatsToChange() {
        return statsToChange;
    }

    public void addStatToChange(ChangeableStringStat changableStringStat ){

        int size=statsToChange.size;
        boolean addStat=true;
        for(int count=0; count<size; count++){
            if(statsToChange.get(count).getName().equals(changableStringStat.getName())){
                addStat=false;
                break;
            }
        }

        if(addStat==true) {
            statsToChange.add(changableStringStat);
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

    public void addOrCombineStat(ChangeableStringStat changableStringStat) {
        int size=statsToChange.size;
        boolean addStat=true;
        for(int count=0; count<size; count++){
            if(statsToChange.get(count).getName().equals(changableStringStat.getName())){
                statsToChange.add(changableStringStat);
                addStat=false;
                break;
            }
        }

        if(addStat==true) {
            statsToChange.add(changableStringStat);
        }

    }


    public ChangeableStringStat getStat(String name){

        int size= statsToChange.size;
        for(int count=0; count<size; count++){

            ChangeableStringStat numericStatChangeable= statsToChange.get(count);
            if(numericStatChangeable.getName().equals(name)){

                return numericStatChangeable;

            }
        }

        return  null;


    }
}

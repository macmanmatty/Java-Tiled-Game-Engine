package com.jessematty.black.tower.Components.Stats.ChangeStats;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableStringStat;

public class StringStatsChangable implements Component {



   private Array<com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableStringStat> statsToChange = new Array<com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableStringStat>();


    public Array<com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableStringStat> getStatsToChange() {
        return statsToChange;
    }

    public void addStatToChange(com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableStringStat changableStringStat ){

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

    public void addOrCombineStat(com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableStringStat changableStringStat) {
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


    public com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableStringStat getStat(String name){

        int size= statsToChange.size;
        for(int count=0; count<size; count++){

            ChangableStringStat numericStatChangeable= statsToChange.get(count);
            if(numericStatChangeable.getName().equals(name)){

                return numericStatChangeable;

            }
        }

        return  null;


    }
}

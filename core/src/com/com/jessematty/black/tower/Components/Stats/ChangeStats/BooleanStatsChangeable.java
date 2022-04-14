package com.jessematty.black.tower.Components.Stats.ChangeStats;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class BooleanStatsChangeable implements Component {



   private  Array<BooleanStatChangeable> statsToChange = new Array<BooleanStatChangeable>();


    public Array<BooleanStatChangeable> getStatsToChange() {
        return statsToChange;
    }

    public void addStatToChange(BooleanStatChangeable changableBooleanStat ){

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

    public void addOrCombineStat(BooleanStatChangeable booleanStatChangeable) {
        int size=statsToChange.size;
        boolean addStat=true;
        for(int count=0; count<size; count++){
            if(statsToChange.get(count).getName().equals(booleanStatChangeable.getName())){
                statsToChange.add(booleanStatChangeable);
                addStat=false;
                break;
            }
        }

        if(addStat==true) {
            statsToChange.add(booleanStatChangeable);
        }

    }

    public BooleanStatChangeable getStat(String name){

        int size= statsToChange.size;
        for(int count=0; count<size; count++){

            BooleanStatChangeable numericStatChangeable= statsToChange.get(count);
            if(numericStatChangeable.getName().equals(name)){

                return numericStatChangeable;

            }
        }

        return  null;


    }



}

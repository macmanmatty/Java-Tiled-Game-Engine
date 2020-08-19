package com.jessematty.black.tower.GameBaseClasses.Dice;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Values;

public class GameDice {

   private ObjectMap<String, Dice> dice= new ObjectMap<String, Dice>();



   public void roll(){
       Values<Dice> dice= this.dice.values();
       while(dice.hasNext){
           dice.next().roll();
       }

   }

    public ObjectMap<String, Dice> getDice() {
        return dice;
    }

    public void addDice(Dice dice){

       this.dice.put(dice.getName(), dice);
    }
    public double getRoll(String diceName){


       return dice.get(diceName).getRollNumber();
    }


}

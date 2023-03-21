package com.jessematty.black.tower.Components.Actions.ActionComponentMarkers;

import com.badlogic.ashley.core.Component;

public class Dying implements Component { // this flag indicates that an entity is dying and will be dead soon


   private  int timeToDie=5;
   private  int dieCounter=0;
   private boolean dying;

    public int getTimeToDie() {
        return timeToDie;
    }

    public void setTimeToDie(int timeToDie) {
        this.timeToDie = timeToDie;
    }

    public int getDieCounter() {
        return dieCounter;
    }

    public void tick(){

        dieCounter++;

    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }
}



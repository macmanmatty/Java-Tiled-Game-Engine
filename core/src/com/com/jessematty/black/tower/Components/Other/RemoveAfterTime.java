package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;

public class RemoveAfterTime implements Component {




    private  int timeToExist;
   private int timeCounter;
    public void increaseTime(){

        timeCounter++;

    }

    public int getTimeToExist() {
        return timeToExist;
    }

    public void setTimeToExist(int timeToExist) {
        this.timeToExist = timeToExist;
    }

    public int getTimeCounter() {
        return timeCounter;
    }
}


package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

public class Ears implements Component {

     private float hearing;
     private  float minFrequency;
     private float maxFrequency;

    public float getHearingDistance() {
        return hearing;
    }

    public void setHearing(float hearing) {
        this.hearing = hearing;
    }

    public float getMinFrequency() {
        return minFrequency;
    }

    public void setMinFrequency(float minFrequency) {
        this.minFrequency = minFrequency;
    }

    public float getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(float maxFrequency) {
        this.maxFrequency = maxFrequency;
    }
}


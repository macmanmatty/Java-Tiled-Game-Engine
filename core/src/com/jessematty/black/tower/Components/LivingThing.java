package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

public class LivingThing implements Component {


    private float healthPoints;

    public float getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(float healthPoints) {
        this.healthPoints = healthPoints;
    }
}

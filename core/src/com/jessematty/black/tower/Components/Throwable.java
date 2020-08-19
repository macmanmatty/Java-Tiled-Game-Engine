package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ActionComponent;

public class Throwable implements ActionableComponent  {
    private float throwSpeed;

    public float getThrowSpeed() {
        return throwSpeed;
    }

    public void setThrowSpeed(float throwSpeed) {
        this.throwSpeed = throwSpeed;
    }
}

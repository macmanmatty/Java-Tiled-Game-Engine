package com.jessematty.black.tower.Components.Attacks;

import com.jessematty.black.tower.Components.Actions.ActionableComponent;

public class Throwable implements ActionableComponent {
    private float throwSpeed;

    public float getThrowSpeed() {
        return throwSpeed;
    }

    public void setThrowSpeed(float throwSpeed) {
        this.throwSpeed = throwSpeed;
    }
}

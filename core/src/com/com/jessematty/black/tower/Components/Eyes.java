package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

public class Eyes implements Component {

    private float vision;
    private float nightVision;
    private float visionDegrees;
    private boolean blinded;



    public float getNightVision() {
        return nightVision;
    }

    public void setNightVision(float nightVision) {
        this.nightVision = nightVision;
    }

    public float getVision() {
        return vision;
    }

    public void setVision(float vision) {
        this.vision = vision;
    }

    public float getVisionDegrees() {
        return visionDegrees;
    }

    public void setVisionDegrees(float visionDegrees) {
        this.visionDegrees = visionDegrees;
    }

    public boolean isBlinded() {
        return blinded;
    }

    public void setBlinded(boolean blinded) {
        this.blinded = blinded;
    }
}


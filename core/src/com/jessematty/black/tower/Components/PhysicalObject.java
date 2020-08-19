package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

public class PhysicalObject implements Component { // component for objects that have a  mass and  a volume

    private  float mass = 1f;
    private float  volume = 1f;


    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }
    public float getDensity() {
        return mass/volume;
    }

}

package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

public class PhysicalObjectComponent implements Component { // component for objects that have a  mass and  a volume

    private  float mass = 1f; // the entities mass
    private float  volume = 1f;// the entities  volume
    private float entitySolidity=1f; // 0=gas .5=fluid 1=solid
    private float coefficientOfFriction=1f; // the objects cofficent of friction




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

    public float getEntitySolidity() {
        return entitySolidity;
    }

    public void setEntitySolidity(float entitySolidity) {
        this.entitySolidity = entitySolidity;
    }

    public float getCoefficientOfFriction() {
        return coefficientOfFriction;
    }

    public void setCoefficientOfFriction(float coefficientOfFriction) {
        this.coefficientOfFriction = coefficientOfFriction;
    }
}

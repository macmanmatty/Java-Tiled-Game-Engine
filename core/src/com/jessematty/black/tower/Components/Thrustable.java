package com.jessematty.black.tower.Components;

import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;

public class Thrustable implements ActionableComponent {

    private float distanceMoved;
    private Direction direction;
    private boolean isThrusting;
    private float thrustSpeed;
    private float lengthToThrustPerTurn;
    private float  maxThrustDistance;


    public float getDistanceMoved() {
        return distanceMoved;
    }

    public void setDistanceMoved(float distanceMoved) {
        this.distanceMoved = distanceMoved;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isThrusting() {
        return isThrusting;
    }

    public void setThrusting(boolean thrusting) {
        isThrusting = thrusting;
    }

    public float getThrustSpeed() {
        return thrustSpeed;
    }

    public void setThrustSpeed(float thrustSpeed) {
        this.thrustSpeed = thrustSpeed;
    }

    public float getLengthToThrustPerTurn() {
        return lengthToThrustPerTurn;
    }

    public void setLengthToThrustPerTurn(float lengthToThrustPerTurn) {
        this.lengthToThrustPerTurn = lengthToThrustPerTurn;
    }

    public float getMaxThrustDistance() {
        return maxThrustDistance;
    }

    public void setMaxThrustDistance(float maxThrustDistance) {
        this.maxThrustDistance = maxThrustDistance;
    }
}


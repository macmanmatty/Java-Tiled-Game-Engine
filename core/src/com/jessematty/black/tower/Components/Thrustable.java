package com.jessematty.black.tower.Components;

import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.Components.Actions.ActionableComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;

public class Thrustable implements ActionableComponent {

    private float distanceMoved=0f;
    private Direction direction;
    private boolean isThrusting;
    private float thrustSpeed=3f;
    private float lengthToThrustPerTurn;
    private Vector2 startPosition= new Vector2();
    private  float maxDistance;


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

    public Vector2 getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Vector2 startPosition) {
        this.startPosition = startPosition;
    }

    public float getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(float maxDistance) {
        this.maxDistance = maxDistance;
    }
}


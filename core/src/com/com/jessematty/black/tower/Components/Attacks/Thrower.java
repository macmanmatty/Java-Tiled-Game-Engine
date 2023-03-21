package com.jessematty.black.tower.Components.Attacks;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;

public class Thrower implements Component {
    private float currentThrowSpeed;
    private float throwAngle;
    private float targetAngle;
    private Direction throwDirection;






    public float getCurrentThrowSpeed() {
        return currentThrowSpeed;
    }

    public void setCurrentThrowSpeed(float currentThrowSpeed) {
        this.currentThrowSpeed = currentThrowSpeed;
    }

    public float getThrowAngle() {
        return throwAngle;
    }

    public void setThrowAngle(float throwAngle) {
        this.throwAngle = throwAngle;
    }

    public float getTargetAngle() {
        return targetAngle;
    }

    public void setTargetAngle(float targetAngle) {
        this.targetAngle = targetAngle;
    }



    public Direction getThrowDirection() {
        return throwDirection;
    }

    public void setThrowDirection(Direction throwDirection) {
        this.throwDirection = throwDirection;
    }
}

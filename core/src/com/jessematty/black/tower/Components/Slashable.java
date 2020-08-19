package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

public class Slashable implements ActionableComponent {

    private float minRotationDegrees=-90f;
    private float currentRotation=0f;
    private float slashSpeed=5f;
    private float maxRotationDegrees=180f;
    private boolean slashing;
    private float degreesToRotatePerTurn;



    public float getCurrentRotation() {
        return currentRotation;
    }

    public void setCurrentRotation(float currentRotation) {
        this.currentRotation = currentRotation;
    }

    public float getSlashSpeed() {
        return slashSpeed;
    }

    public void setSlashSpeed(float slashSpeed) {
        this.slashSpeed = slashSpeed;
    }

    public float getMaxRotationDegrees() {
        return maxRotationDegrees;
    }

    public void setMaxRotationDegrees(float maxRotationDegrees) {
        this.maxRotationDegrees = maxRotationDegrees;
    }

    public boolean isSlashing() {
        return slashing;
    }

    public void setSlashing(boolean slashing) {
        this.slashing = slashing;
    }

    public float getDegreesToRotatePerTurn() {
        return degreesToRotatePerTurn;
    }

    public void setDegreesToRotatePerTurn(float degreesToRotatePerTurn) {
        this.degreesToRotatePerTurn = degreesToRotatePerTurn;
    }

    public float getMinRotationDegrees() {
        return minRotationDegrees;
    }

    public void setMinRotationDegrees(float minRotationDegrees) {
        this.minRotationDegrees = minRotationDegrees;
    }
}

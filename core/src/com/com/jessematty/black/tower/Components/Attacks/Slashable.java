package com.jessematty.black.tower.Components.Attacks;

import com.jessematty.black.tower.Components.Actions.ActionableComponent;

public class Slashable implements ActionableComponent {

    private float minRotationDegrees=45f;
    private float currentRotation=0f;
    private float slashSpeed=3f;
    private float maxRotationDegrees=90f;
    private boolean slashing;
    private float degreesToRotatePerTurn;
    private float degreesToSlashTo;
    private float  startDegrees;



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

    public float getDegreesToSlashTo() {
        return degreesToSlashTo;
    }

    public void setDegreesToSlashTo(float degreesToSlashTo) {
        this.degreesToSlashTo = degreesToSlashTo;
    }

    public float getStartDegrees() {
        return startDegrees;
    }

    public void setStartDegrees(float startDegrees) {
        this.startDegrees = startDegrees;
    }
}

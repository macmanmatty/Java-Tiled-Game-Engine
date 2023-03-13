package com.jessematty.black.tower.Components.Attacks;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;

public class Launchable implements Component {
  private NumericStat launchSpeed= new NumericStat("Projectile Speed");

  private float launchAngle;
  private Direction launchDirection;
  private float maxHeight;
  private boolean ignoresGravity;


    public Launchable(NumericStats numericStats) {
        numericStats.addStat(launchSpeed);
    }


    public Launchable() {
    }

    public float getLaunchSpeed() {
        return launchSpeed.getFloatValue();
    }

    public void setLaunchSpeed(float launchSpeed) {
        this.launchSpeed.setValue(launchSpeed);
    }

    public float getLaunchAngle() {
        return launchAngle;
    }

    public void setLaunchAngle(float launchAngle) {
        this.launchAngle = launchAngle;
    }

    public Direction getLaunchDirection() {
        return launchDirection;
    }

    public void setLaunchDirection(Direction launchDirection) {
        this.launchDirection = launchDirection;
    }

    public float getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(float maxHeight) {
        this.maxHeight = maxHeight;
    }

    public boolean isIgnoresGravity() {
        return ignoresGravity;
    }

    public void setIgnoresGravity(boolean ignoresGravity) {
        this.ignoresGravity = ignoresGravity;
    }
}

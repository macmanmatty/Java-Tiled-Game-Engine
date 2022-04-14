package com.jessematty.black.tower.Components;

import com.jessematty.black.tower.Components.Stats.NumericStat;

public class Glow  extends com.jessematty.black.tower.Components.Stats.NumericStat {


    private float increase;
    private boolean decreaseBrightness;


    public Glow(boolean displayable,  float  value, float increase, float min, float max) {
        super(displayable, "glow", value, min, max);
        this.increase=increase;

    }

    public Glow() {
    }

    public Glow(NumericStat other) {
        super(other);
    }

    public void setIncrease(float increase) {
        this.increase = increase;
    }

    public float getIncrease() {
        return increase;
    }

    public boolean isDecreaseBrightness() {
        return decreaseBrightness;
    }

    public void setDecreaseBrightness(boolean decreaseBrightness) {
        this.decreaseBrightness = decreaseBrightness;
    }
}

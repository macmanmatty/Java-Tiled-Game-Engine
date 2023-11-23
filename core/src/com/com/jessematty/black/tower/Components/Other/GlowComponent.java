package com.jessematty.black.tower.Components.Other;

import com.jessematty.black.tower.Components.Stats.NumericStat;

public class GlowComponent extends com.jessematty.black.tower.Components.Stats.NumericStat {


    private float increase;
    private boolean decreaseBrightness;


    public GlowComponent(boolean displayable, float  value, float increase, float min, float max) {
        super(displayable, "glow", value, min, max);
        this.increase=increase;

    }

    public GlowComponent() {
    }

    public GlowComponent(NumericStat other) {
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

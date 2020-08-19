package com.jessematty.black.tower.Components;

import com.jessematty.black.tower.Components.Stats.NumericStat;

public class Ripeness extends com.jessematty.black.tower.Components.Stats.NumericStat {


    public Ripeness(boolean displayable, String name, double value) {
        super(displayable, name, value);
    }

    public Ripeness(boolean displayable, String name, double value, double minValue, double maxValue) {
        super(displayable, name, value, minValue, maxValue);
    }

    public Ripeness() {
    }

    public Ripeness(NumericStat other) {
        super(other);
    }
}


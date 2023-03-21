package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.Components.Stats.NumericStat;

public class Explodable implements Component {

    private NumericStat radius= new NumericStat();
    private String blastActionName;



    public float getRadius() {
        return radius.getFloatValue();
    }

    public void setRadius(float radius) {
        this.radius.setValue(radius);
    }

    public String getBlastActionName() {
        return blastActionName;
    }

    public void setBlastActionName(String blastActionName) {
        this.blastActionName = blastActionName;
    }
}

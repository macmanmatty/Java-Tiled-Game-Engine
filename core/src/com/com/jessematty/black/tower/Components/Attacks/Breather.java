package com.jessematty.black.tower.Components.Attacks;

import com.badlogic.ashley.core.Component;

public class Breather implements Component {

    private String gasToBreath;

    public String getGasToBreath() {
        return gasToBreath;
    }

    public void setGasToBreath(String gasToBreath) {
        this.gasToBreath = gasToBreath;
    }
}





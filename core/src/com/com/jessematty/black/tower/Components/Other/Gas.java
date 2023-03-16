package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;

public class Gas  implements Component {
    private  int  numberOfParticales;


    public int getNumberOfParticales() {
        return numberOfParticales;
    }

    public void setNumberOfParticales(int numberOfParticales) {
        this.numberOfParticales = numberOfParticales;
    }
}

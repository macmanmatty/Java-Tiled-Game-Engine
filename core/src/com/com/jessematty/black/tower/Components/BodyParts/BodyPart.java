package com.jessematty.black.tower.Components.BodyParts;

import com.badlogic.ashley.core.Component;

public class BodyPart implements Component {

    private  int size;


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}


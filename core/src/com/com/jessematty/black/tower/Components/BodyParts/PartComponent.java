package com.jessematty.black.tower.Components.BodyParts;

import com.badlogic.ashley.core.Component;

public class PartComponent implements Component {
    private  int size;

    private String partClass;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPartClass() {
        return partClass;
    }

    public void setPartClass(String partClass) {
        this.partClass = partClass;
    }
}


package com.jessematty.black.tower.Components.BodyParts;

import com.badlogic.ashley.core.Component;

public class PartComponent implements Component {
    private  int size;

    private String partName;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }
}


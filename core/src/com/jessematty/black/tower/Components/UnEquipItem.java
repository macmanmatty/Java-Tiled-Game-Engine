package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

public class UnEquipItem implements Component {
   private  DetachItemMode detachItemMode;

    public UnEquipItem(DetachItemMode detachItemMode) {
        this.detachItemMode = detachItemMode;
    }

    public DetachItemMode getDetachItemMode() {
        return detachItemMode;
    }
}

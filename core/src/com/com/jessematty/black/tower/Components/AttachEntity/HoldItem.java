package com.jessematty.black.tower.Components.AttachEntity;

import com.badlogic.ashley.core.Component;

public class HoldItem implements Component {
    private String itemToHoldId;

    public String getItemToHoldId() {
        return itemToHoldId;
    }

    public void setItemToHoldId(String itemToHoldId) {
        this.itemToHoldId = itemToHoldId;
    }

}

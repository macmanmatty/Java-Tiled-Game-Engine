package com.jessematty.black.tower.Components.Item.ItemAction;

import com.badlogic.ashley.core.Component;

public class HoldItemComponent implements Component {

    public HoldItemComponent(String holderId) {
        this.holderId = holderId;
    }

    private String holderId;

    public String getHolderId() {
        return holderId;
    }

    public void setHolderId(String holderId) {
        this.holderId = holderId;
    }

}

package com.jessematty.black.tower.Components.AttachEntity;

import com.badlogic.ashley.core.Component;

public class HoldItem implements Component {

    public HoldItem(String holderId) {
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

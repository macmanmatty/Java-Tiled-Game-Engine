package com.jessematty.black.tower.Components.AttachEntity;

import com.badlogic.ashley.core.Component;

public class AddOwnerComponent implements Component {

    private String ownerId;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}

package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

public class RemoveItemFromContainerComponent implements Component {

    private String entityContainerId;

    public String getEntityContainerId() {
        return entityContainerId;
    }

    public void setEntityContainerId(String entityContainerId) {
        this.entityContainerId = entityContainerId;
    }
}

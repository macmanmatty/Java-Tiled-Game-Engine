package com.jessematty.black.tower.Components.Actions.ActionComponentMarkers;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ItemActionComponent;

public class AddItemToContainerComponent extends ItemActionComponent {
    private String entityContainerId;

    public String getEntityContainerId() {
        return entityContainerId;
    }

    public void setEntityContainerId(String entityContainerId) {
        this.entityContainerId = entityContainerId;
    }
}

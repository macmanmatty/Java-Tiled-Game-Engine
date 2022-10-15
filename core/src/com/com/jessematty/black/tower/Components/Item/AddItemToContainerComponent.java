package com.jessematty.black.tower.Components.Item;

import com.jessematty.black.tower.Components.Item.ItemActionComponent;

public class AddItemToContainerComponent extends ItemActionComponent {
    private String entityContainerId;

    public String getEntityContainerId() {
        return entityContainerId;
    }

    public void setEntityContainerId(String entityContainerId) {
        this.entityContainerId = entityContainerId;
    }
}

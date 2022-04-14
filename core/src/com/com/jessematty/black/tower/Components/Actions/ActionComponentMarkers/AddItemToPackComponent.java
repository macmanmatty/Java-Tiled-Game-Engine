package com.jessematty.black.tower.Components.Actions.ActionComponentMarkers;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ItemActionComponent;

public class AddItemToPackComponent extends ItemActionComponent {


    private String itemToAddId;

    public String getItemToAddId() {
        return itemToAddId;
    }

    public void setItemToAddId(String itemToAddId) {
        this.itemToAddId = itemToAddId;
    }
}

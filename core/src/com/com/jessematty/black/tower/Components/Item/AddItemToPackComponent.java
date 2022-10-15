package com.jessematty.black.tower.Components.Item;

import com.jessematty.black.tower.Components.Item.ItemActionComponent;

public class AddItemToPackComponent extends ItemActionComponent {


    private String itemToAddId;

    public String getItemToAddId() {
        return itemToAddId;
    }

    public void setItemToAddId(String itemToAddId) {
        this.itemToAddId = itemToAddId;
    }
}

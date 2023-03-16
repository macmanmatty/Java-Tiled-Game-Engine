package com.jessematty.black.tower.Components.EventComponents;


import com.jessematty.black.tower.Components.Item.ItemActionComponent;

/**
 * event component  for adding an item to container
 */
public class AddItemToContainer extends ItemActionComponent implements EventComponent  {
    /**
     *  the  id of item to add to the container
      */
 private    String itemId;
    /**
     *  the id of the container to add the item to
     */
 private  String containerId;

    public AddItemToContainer(String itemId, String containerId) {
        this.itemId = itemId;
        this.containerId = containerId;
    }

    public AddItemToContainer() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }
}

package com.jessematty.black.tower.Components.EventComponents;


/**
 * event component  for removing an item from a container
 */
public class RemoveItemFromContainer implements EventComponent{ /**
     *  the id of the container to pull the item from
     */
 private  String containerId;
    /**
     * the id of  the item to be removed from the container;
     */
 private  String itemId;

    /**
     if true the entity will detached from the owner container on removal
     */
 private boolean detachOnRemove;



    public RemoveItemFromContainer(String containerId, String itemId) {
        this.containerId = containerId;
        this.itemId = itemId;
    }

    public RemoveItemFromContainer() {
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

    public boolean isDetachOnRemove() {
        return detachOnRemove;
    }

    public void setDetachOnRemove(boolean detachOnRemove) {
        this.detachOnRemove = detachOnRemove;
    }
}

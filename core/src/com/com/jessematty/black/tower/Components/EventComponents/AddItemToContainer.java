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

    /**
     * if this flag is true the container will become  owner of the Entity
     * The Entity will get ann Owned component  and the Enity will be added to the list of
     * owned components in the Owner Component
     */
 private boolean setContainerAsOwner=true;

    /**
     * if this flag is the items collision  bounds will be set to 0,0,0 on adding the item to the container;
     *
     *
     */
    private boolean removeItemBoundsOnAdd=true;


    public AddItemToContainer(String itemId, String containerId) {
        this.itemId = itemId;
        this.containerId = containerId;
    }

    public AddItemToContainer(String itemId, String containerId, boolean setContainerAsOwner) {
        this.itemId = itemId;
        this.containerId = containerId;
        this.setContainerAsOwner = setContainerAsOwner;
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

    public boolean isSetContainerAsOwner() {
        return setContainerAsOwner;
    }

    public void setSetContainerAsOwner(boolean setContainerAsOwner) {
        this.setContainerAsOwner = setContainerAsOwner;
    }

    public boolean isRemoveItemBoundsOnAdd() {
        return removeItemBoundsOnAdd;
    }

    public void setRemoveItemBoundsOnAdd(boolean removeItemBoundsOnAdd) {
        this.removeItemBoundsOnAdd = removeItemBoundsOnAdd;
    }
}

package com.jessematty.black.tower.Components.EventComponents;


/**
 * event component  for removing an item from a container
 */
public class RemoveItemFromContainer implements EventComponent{
    /**
     *  the  number of item in the array remove from the container component
     *  if the item number is less than zero the first item in the array will be pulled
     * @See ContainerComponent
      */
 private    int itemNumber;
    /**
     *  the id of the container to pull the item from
     */
 private  String containerId;

    public RemoveItemFromContainer(int itemNumber, String containerId) {
        this.itemNumber = itemNumber;
        this.containerId = containerId;
    }

    public RemoveItemFromContainer() {
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }
}

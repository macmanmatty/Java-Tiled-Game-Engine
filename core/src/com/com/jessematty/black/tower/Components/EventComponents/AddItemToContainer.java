package com.jessematty.black.tower.Components.EventComponents;


import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Interfaces.Transient;
import com.jessematty.black.tower.Components.Item.ItemAction.ItemActionComponent;


/**
 * event component  for adding an item to container
 */
@Transient
public class AddItemToContainer extends ItemActionComponent implements EventComponent  {
    /**
     *  the the container to add the item to
     */
 private Entity container;

    /**
     * if this flag is true the container will become  owner of the Entity
     * The Entity will get an Owned component  and the Enity will be added to the list of
     * owned components in the Owner Component
     */
 private boolean setContainerAsOwner=true;

    /**
     * if this flag is the items collision  bounds will be set to 0,0,0 on adding the item to the container;
     *
     *
     */
    private boolean removeItemBoundsOnAdd=true;


    public AddItemToContainer(Entity container) {
        this.container=container;
    }

    public AddItemToContainer(Entity container, boolean setContainerAsOwner) {
        this.container=container;
        this.setContainerAsOwner = setContainerAsOwner;
    }

    public AddItemToContainer() {
    }

    public Entity getContainer() {
        return container;
    }

    public void setContainer(Entity container) {
        this.container = container;
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

package com.jessematty.black.tower.Components.Item.ItemAction;

import com.jessematty.black.tower.Components.Item.ItemAction.ItemActionComponent;

public class PickUpItemComponent extends ItemActionComponent {
   private  String entityToPickUpId;

    public PickUpItemComponent() {
    }

    public PickUpItemComponent(String entityToPickUpId) {
        this.entityToPickUpId = entityToPickUpId;
    }

    public String getEntityToPickUpId() {
        return entityToPickUpId;
    }

    public void setEntityToPickUpId(String entityToPickUpId) {
        this.entityToPickUpId = entityToPickUpId;
    }


}



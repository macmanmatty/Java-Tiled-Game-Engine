package com.jessematty.black.tower.Components.Actions.ActionComponentMarkers;

import com.jessematty.black.tower.Components.Markers.MarkerComponent;

public class PickUp extends ItemActionComponent {
   private  String entityToPickUpId;
   private boolean setEntityActionToOwner=true;
   private boolean setEntityPositionToOwner=true;


    public String getEntityToPickUpId() {
        return entityToPickUpId;
    }

    public void setEntityToPickUpId(String entityToPickUpId) {
        this.entityToPickUpId = entityToPickUpId;
    }

    public boolean isSetEntityActionToOwner() {
        return setEntityActionToOwner;
    }

    public void setSetEntityActionToOwner(boolean setEntityActionToOwner) {
        this.setEntityActionToOwner = setEntityActionToOwner;
    }

    public boolean isSetEntityPositionToOwner() {
        return setEntityPositionToOwner;
    }

    public void setSetEntityPositionToOwner(boolean setEntityPositionToOwner) {
        this.setEntityPositionToOwner = setEntityPositionToOwner;
    }
}



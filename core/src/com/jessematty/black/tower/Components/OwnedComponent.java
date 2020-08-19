package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

public class OwnedComponent implements Component {

    private String ownerEntityID;
    private  boolean setEntityPositionToOwner=true;
    private boolean setEntityActionToOwner=true;
    private boolean attached=true;

    public OwnedComponent() {
    }

    public OwnedComponent(String ownerEntityID, boolean setEntityPositionToOwner, boolean setEntityActionToOwner, boolean attached) {
        this.ownerEntityID = ownerEntityID;
        this.setEntityPositionToOwner = setEntityPositionToOwner;
        this.setEntityActionToOwner = setEntityActionToOwner;
        this.attached = attached;
    }

    public OwnedComponent(String ownerEntityID, boolean setEntityPositionToOwner, boolean setEntityActionToOwner) {
        this.ownerEntityID = ownerEntityID;
        this.setEntityPositionToOwner = setEntityPositionToOwner;
        this.setEntityActionToOwner = setEntityActionToOwner;
    }

    public String getOwnerEntityID() {
        return ownerEntityID;
    }

    public void setOwnerEntityID(String ownerEntityID) {
        this.ownerEntityID = ownerEntityID;
    }

    public boolean isSetEntityPositionToOwner() {
        return setEntityPositionToOwner;
    }

    public void setSetEntityPositionToOwner(boolean setEntityPositionToOwner) {
        this.setEntityPositionToOwner = setEntityPositionToOwner;
    }

    public boolean isSetEntityActionToOwner() {
        return setEntityActionToOwner;
    }

    public void setSetEntityActionToOwner(boolean setEntityActionToOwner) {
        this.setEntityActionToOwner = setEntityActionToOwner;
    }

    public boolean isAttached() {
        return attached;
    }

    public void setAttached(boolean attached) {
        this.attached = attached;
    }
}

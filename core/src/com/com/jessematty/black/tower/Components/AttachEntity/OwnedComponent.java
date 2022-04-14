package com.jessematty.black.tower.Components.AttachEntity;

import com.badlogic.ashley.core.Component;

public class OwnedComponent implements Component {

    private String ownerEntityID;
    private  boolean setEntityPositionToOwner=true;
    private boolean setEntityActionToOwner=true;
    private boolean setOwnerActionToEntity=false;
    private boolean setOwnerPositionToEntity=false;
    private boolean removeEntityFromEngineOnOwnerRemoval=true;

    private boolean attached=true;
    private String rootOwnerID;

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

    public String getRootOwnerID() {
        return rootOwnerID;
    }

    public void setRootOwnerID(String rootOwnerID) {
        this.rootOwnerID = rootOwnerID;
    }

    public boolean isSetOwnerActionToEntity() {
        return setOwnerActionToEntity;
    }

    public void setSetOwnerActionToEntity(boolean setOwnerActionToEntity) {
        this.setOwnerActionToEntity = setOwnerActionToEntity;
    }

    public boolean isSetOwnerPositionToEntity() {
        return setOwnerPositionToEntity;
    }

    public void setSetOwnerPositionToEntity(boolean setOwnerPositionToEntity) {
        this.setOwnerPositionToEntity = setOwnerPositionToEntity;
    }

    public boolean isRemoveEntityFromEngineOnOwnerRemoval() {
        return removeEntityFromEngineOnOwnerRemoval;
    }

    public void setRemoveEntityFromEngineOnOwnerRemoval(boolean removeEntityFromEngineOnOwnerRemoval) {
        this.removeEntityFromEngineOnOwnerRemoval = removeEntityFromEngineOnOwnerRemoval;
    }
}

package com.jessematty.black.tower.Components.AttachEntity;

import com.badlogic.ashley.core.Component;

/**
 * component for an Entity that is attached to another entity
 */
public class OwnedComponent implements Component {
    /**
     * the id  of the entity who owns this entity
     */
    private String ownerEntityID;
    /**
     * whether or not to set the  owned entities  position to the position  of the owner entity;
     * if this is true the owned entities position will mirror the owners position on the map
     */
    private  boolean setEntityPositionToOwner=true;
    /**
     * whether or not to set the  owned entities action to the action   of the owner entity;
     * if this is true the owned entities action  will mirror the action owner entity
     */
    private boolean setEntityActionToOwner=true;
    /**
     * whether or not to set the  owner entity's action  to the action    of the owned entity;
     * if this is true the owner entities action  will mirror the action of the owned entity
     */
    private boolean setOwnerActionToEntity=false;
    /**
     * whether or not to set the  owner entity's position on the map  to the position on the map   of the owned entity;
     * if this is true the owner entities position  will mirror the position of the owned entity
     */
    private boolean setOwnerPositionToEntity=false;

    /**
     * whether or not to remove the owned from entity  from the engine on removal of the owner entity;
     * if this is true the owned will be removed from the engine on removal of the owner entity
     * if not  this component will be removed from the entity  and all of the entities   other components will remain intact
     */
    private boolean removeEntityFromEngineOnOwnerRemoval=true;

    /**
     * whether  or not the entity is physically attached to the owner entity;
     */
    private boolean attached=true;
    /**
     *  the id of the top level owner in the  node tree of owners
     */
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

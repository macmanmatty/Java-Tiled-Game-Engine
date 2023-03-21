package com.jessematty.black.tower.Components.AttachEntity;

import com.badlogic.ashley.core.Component;

/**
 * class for an entity attaching event
 */

public class AttachEntityEvent implements Component {

    /**
     * the id of the entity to attach to
     */
    private String ownerId;
    /**
     * the id of the entity to attach
     */
    private String ownedId;


    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnedId() {
        return ownedId;
    }

    public void setOwnedId(String ownedId) {
        this.ownedId = ownedId;
    }
}

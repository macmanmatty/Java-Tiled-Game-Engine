package com.jessematty.black.tower.Components.AttachEntity;

import com.badlogic.ashley.core.Component;

public class RemoveOwnerComponent implements Component {

    private String entityToRemoveID="";

    public String getEntityToRemoveID() {
        return entityToRemoveID;
    }

    public void setEntityToRemoveID(String entityToRemoveID) {
        this.entityToRemoveID = entityToRemoveID;
    }
}

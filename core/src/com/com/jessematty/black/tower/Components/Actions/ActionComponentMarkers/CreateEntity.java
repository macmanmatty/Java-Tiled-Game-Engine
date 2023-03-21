package com.jessematty.black.tower.Components.Actions.ActionComponentMarkers;

import com.badlogic.ashley.core.Component;

public class CreateEntity implements Component {
    private String entityName;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}

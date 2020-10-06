package com.jessematty.black.tower.Components.CreateEntity;

import com.badlogic.gdx.math.Vector3;

public class ParticleEntity {
    private String entityId="";
    private Vector3 createOffsets= new Vector3();

    public String getEntityId() {
        return entityId;
    }

    public Vector3 getCreateOffsets() {
        return createOffsets;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public void setCreateOffsets(Vector3 createOffsets) {
        this.createOffsets = createOffsets;
    }
}

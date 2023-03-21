package com.jessematty.black.tower.Components.Base;

import com.badlogic.ashley.core.Component;

import java.util.UUID;

/**
 * component for  an entities unique   id. The is is generated by a java  UUID  all  entities are referenced by this in the world;
 * once created  and ID CANNOT be changed
 */
public class EntityId implements Component {
    /**
     *  the entities id;
     */
    private  String id= UUID.randomUUID().toString();

    public String getId() {
        return id;
    }
}

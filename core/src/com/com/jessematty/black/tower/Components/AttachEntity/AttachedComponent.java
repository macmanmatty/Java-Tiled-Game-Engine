package com.jessematty.black.tower.Components.AttachEntity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class AttachedComponent implements Component {
    private int maxAttachedEntities;
    private ObjectMap<String, String> attachedEntities= new ObjectMap<>();
    private int counter;

    public int getMaxAttachedEntities() {
        return maxAttachedEntities;
    }

    public void setMaxAttachedEntities(int maxAttachedEntities) {
        this.maxAttachedEntities = maxAttachedEntities;
    }

    public ObjectMap<String, String> getAttachedEntities() {
        return attachedEntities;
    }

    public boolean addEntity( String name, String id){

        if(attachedEntities.size<maxAttachedEntities){
            attachedEntities.put(name, id);
            return  true;
        }

        return  false;


    }


}

package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;
import java.util.List;

public class Factory implements Component {

    private List<Entity> entitiesToCreate= new  ArrayList<Entity>();



    public List<Entity> getEntitiesToCreate() {
        return entitiesToCreate;
    }

    public void setEntitiesToCreate(List<Entity> entitiesToCreate) {
        this.entitiesToCreate = entitiesToCreate;
    }
}


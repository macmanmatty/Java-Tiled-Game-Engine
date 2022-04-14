package com.jessematty.black.tower.GameBaseClasses.Crafting;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class Craft {

     private String name;

    private Array<Entity> entities = new Array<>();

    public Craft(String name) {
        this.name = name;
    }

    public Craft(String name, Array<Entity> entities) {
        this.name = name;
        this.entities = entities;
    }

    public Array<Entity> getEntities() {
        return entities;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

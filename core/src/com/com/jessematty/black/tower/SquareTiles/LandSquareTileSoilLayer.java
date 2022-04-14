package com.jessematty.black.tower.SquareTiles;

import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;

public class LandSquareTileSoilLayer {
   transient  ArrayList<Entity> items= new ArrayList<Entity>();

    public LandSquareTileSoilLayer(int size) {
        for(int count=0; count<size; count++){


        }
    }

    public ArrayList<Entity> getEntities() {
        return items;
    }

    public void setEntity(ArrayList<Entity> items) {
        this.items = items;
    }

    public void addItem(Entity item){

        items.add(item);

    }

    public void removeItem(Entity item){

        items.remove(item);

    }
}

package com.jessematty.black.tower.Components.Tiles;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.SquareTiles.LandSquareTileSoilLayer;


public class Tile implements Component {


    private boolean solid;
    private  boolean enterable=true;
    protected boolean unchangeable=false;
    protected boolean checked;
    protected boolean inArea;
    protected Array<LandSquareTileSoilLayer> soilLayers = new Array<LandSquareTileSoilLayer>();
    protected transient Array<Entity> entities= new Array<Entity>(); // list of entities on ths tile
    protected String atlasName;
    protected  boolean entered;



    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }


    public boolean isEnterable() {
        return enterable;
    }

    public void setEnterable(boolean enterable) {
        this.enterable = enterable;
    }


    public Entity getLastEntity(){


        return  entities.get(entities.size-1);

    }




    public Array<LandSquareTileSoilLayer> getSoilLayers() {
        return soilLayers;
    }

    public void setSoilLayers(Array<LandSquareTileSoilLayer> soilLayers) {
        this.soilLayers = soilLayers;
    }

    public Array<Entity> getEntities() {
        return entities;
    }

    public void setEntities(Array<Entity> entities) {
        this.entities = entities;
    }

    public String getAtlasName() {
        return atlasName;
    }

    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }



    public boolean isUnchangeable() {
        return unchangeable;
    }

    public void setUnchangeable(boolean unchangeable) {
        this.unchangeable = unchangeable;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isInArea() {
        return inArea;
    }

    public void setInArea(boolean inArea) {
        this.inArea = inArea;
    }

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean entered) {
        this.entered = entered;
    }
}


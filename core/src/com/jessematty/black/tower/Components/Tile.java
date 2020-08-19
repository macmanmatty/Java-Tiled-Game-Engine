package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Entity.Transient;
import com.jessematty.black.tower.Maps.Area;
import com.jessematty.black.tower.SquareTiles.LandSquareTileSoilLayer;

import java.util.ArrayList;
import java.util.List;

@Transient
public class Tile implements Component {


    private boolean solid;
    private transient  Area area;
    private  boolean enterable;
    protected boolean unchangeable=false;
    protected boolean checked;
    protected boolean inArea;
    protected List<LandSquareTileSoilLayer> soilLayers = new ArrayList<LandSquareTileSoilLayer>();
    protected transient Array<Entity> entities= new Array<Entity>(); // list of entities on ths tile
    protected String atlasName;
    protected  transient GameAssets assetts;
    protected transient TiledMapTile mapTile;



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




    public List<LandSquareTileSoilLayer> getSoilLayers() {
        return soilLayers;
    }

    public void setSoilLayers(List<LandSquareTileSoilLayer> soilLayers) {
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

    public GameAssets getAssetts() {
        return assetts;
    }

    public void setAssetts(GameAssets assetts) {
        this.assetts = assetts;
    }

    public TiledMapTile getMapTile() {
        return mapTile;
    }

    public void setMapTile(TiledMapTile mapTile) {
        this.mapTile = mapTile;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
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


}


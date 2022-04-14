package com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jessematty.black.tower.Components.EditorImageComponent;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasAnimatedTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.Maps.Buildings.Building;

public abstract  class ClipBoardActor extends Actor {


    protected  float locationX;
    protected  float locationY;
    protected float rotation;
    public float getLocationX() {
        return locationX;
    }

    public void setLocations(float locationX, float locationY) {
        this.locationX = locationX;
        this.locationY=locationY;
       setColor(NamedColor.WHITE);
    }

    public float getLocationY() {
        return locationY;
    }


    public void setLocationX(float locationX) {
        this.locationX = locationX;
    }

    public void setLocationY(float locationY) {
        this.locationY = locationY;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

}

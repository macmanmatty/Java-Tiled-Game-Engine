package com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;

public abstract  class ClipBoardActor extends Actor {


    protected  float locationX;
    protected  float locationY;
    protected Color color= NamedColor.WHITE;
    protected float rotation;
    public float getLocationX() {
        return locationX;
    }

    public void setLocations(float locationX, float locationY) {
        this.locationX = locationX;
        this.locationY=locationY;
    }

    public float getLocationY() {
        return locationY;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
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

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}

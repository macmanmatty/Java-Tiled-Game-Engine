package com.jessematty.black.tower.Editor.EditMode.Brushes.BrushActors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;

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

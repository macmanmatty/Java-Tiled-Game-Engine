package com.jessematty.black.tower.GameBaseClasses.Lights;

import com.badlogic.gdx.graphics.Color;

public class LightSource  {
  protected float diameter;
  protected float brightness; // 0 total light 1=total dark
  protected boolean flicker;
  protected float lightCenterX;
  protected float lightCenterY;
  protected float tileSize;
  protected boolean isLight;
  protected float screenLocationX;
  protected float screenLocationY;
  protected int rays=20;
  protected float xOffset=0;
  protected float yOffest=0;
  protected Color color= new Color(1,0,0,.5f);
  protected LightKind lightKind;
  protected float lightAngle;
  protected float lightDegrees;










    public LightSource() {
    }

    public LightSource(float diameter, float brightness, boolean flicker, float lightCenterX, float lightCenterY) {
        this.diameter = diameter;
        this.brightness = brightness;
        this.flicker = flicker;
        this.lightCenterX = lightCenterX;
        this.lightCenterY = lightCenterY;
    }







    public float getDiameter() {
        return diameter;
    }

    public void setDiameter(float diameter) {
        this.diameter = diameter;
    }

    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public boolean isFlicker() {
        return flicker;
    }

    public void setFlicker(boolean flicker) {
        this.flicker = flicker;
    }

    public float getLightCenterX() {
        return lightCenterX;
    }

    public void setLightCenterX(float lightCenterX) {
        this.lightCenterX = lightCenterX;
    }

    public float getLightCenterY() {
        return lightCenterY;
    }

    public void setLightCenterY(float lightCenterY) {
        this.lightCenterY = lightCenterY;
    }


    public boolean isLight() {
        return isLight;
    }

    public void setLight(boolean light) {
        isLight = light;
    }

    public float getTileSize() {
        return tileSize;
    }

    public void setTileSize(float tileSize) {
        this.tileSize = tileSize;
    }



    public void setScreenLocation(float x, float  y){

        screenLocationX=x;
        screenLocationY=y;

    }


    public int getRays() {
        return rays;
    }

    public void setRays(int rays) {
        this.rays = rays;
    }

    public float getScreenLocationX() {
        return screenLocationX+xOffset;
    }



    public float getScreenLocationY() {
        return screenLocationY+yOffest;
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffest() {
        return yOffest;
    }

    public void setyOffest(float yOffest) {
        this.yOffest = yOffest;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public LightKind getLightKind() {
        return lightKind;
    }

    public void setLightKind(LightKind lightKind) {
        this.lightKind = lightKind;
    }


    public float getLightAngle() {
        return lightAngle;
    }

    public void setLightAngle(float lightAngle) {
        this.lightAngle = lightAngle;
    }


    public float getLightDegrees() {
        return lightDegrees;
    }

    public void setLightDegrees(float lightDegrees) {
        this.lightDegrees = lightDegrees;
    }
}

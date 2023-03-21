package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Light implements Component {

  private   float brightness=1;
   private  Color color= new Color(1, 1,1 ,1 );
   private  float drawOffsetX;
   private  float drawOffsetY;
   private TextureRegion textureRegion;
   private String name;



    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getDrawOffsetX() {
        return drawOffsetX;
    }

    public void setDrawOffsetX(float drawOffsetX) {
        this.drawOffsetX = drawOffsetX;
    }

    public float getDrawOffsetY() {
        return drawOffsetY;
    }

    public void setDrawOffsetY(float drawOffsetY) {
        this.drawOffsetY = drawOffsetY;
    }


    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }


}


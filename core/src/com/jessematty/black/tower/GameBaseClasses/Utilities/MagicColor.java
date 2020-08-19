package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.gdx.graphics.Color;

public class MagicColor {

   protected  Color color;
  protected   float brightness;
   protected  boolean glows;
   protected  float brightnessIncrease;

    public MagicColor(float fire, float earth, float water, float wind,  float brightness, boolean glows, float brightnessIncrease) {

        float r= fire*.0001f;

        float g=earth*.0001f;

        float b=water*.0001f;
        float a=1;
        if(wind!=0) {
         a = 1 / wind;
        }


        this.color =new Color(r, g, b, a);

        this.brightness = brightness;
        this.glows = glows;
        this.brightnessIncrease = brightnessIncrease;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(float r, float g, float b, float a) {

        color=new Color(r, g, b, a);


    }

    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public boolean isGlows() {
        return glows;
    }

    public void setGlows(boolean glows) {
        this.glows = glows;
    }

    public float getBrightnessIncrease() {
        return brightnessIncrease;
    }

    public void setBrightnessIncrease(float brightnessIncrease) {
        this.brightnessIncrease = brightnessIncrease;
    }
}

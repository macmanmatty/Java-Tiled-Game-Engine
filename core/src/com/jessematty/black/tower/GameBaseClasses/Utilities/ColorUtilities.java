package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.gdx.graphics.Color;

public class ColorUtilities {

    private ColorUtilities(){}

    RandomNumbers value= new RandomNumbers();


    protected Color randomColor(boolean randomB, boolean randomG, boolean randomR){
        float r;
        float g;
        float b;
        if (randomB==true){
            b=value.getRandomNumber(1,1000)*.001f;
        }
        else{
            b=1;
        }
        if (randomG==true){
            g=value.getRandomNumber(1,1000)*.001f;
        }
        else{
            g=1;
        }
        if (randomR==true){
            r=value.getRandomNumber(1,1000)*.001f;
        }
        else{
            r=1;
        }
        Color color= new Color(r,g,b,1);
        return color;
    }
}

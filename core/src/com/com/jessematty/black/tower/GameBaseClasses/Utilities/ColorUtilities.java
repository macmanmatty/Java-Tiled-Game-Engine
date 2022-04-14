package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.gdx.graphics.Color;

public class ColorUtilities {

    private ColorUtilities(){}

     private static RandomNumbers value= new RandomNumbers();


    public   static Color randomColor(float r, float g, float b, float a, boolean randomB, boolean randomG, boolean randomR, boolean randomA){


        if (randomB==true){
            b=value.getRandomNumber(1,1000)*.001f;
        }

        if (randomG==true){
            g=value.getRandomNumber(1,1000)*.001f;
        }

        if (randomR==true){
            r=value.getRandomNumber(1,1000)*.001f;
        }

        if (randomA==true){
            a=value.getRandomNumber(1,1000)*.001f;
        }
        Color color= new Color(r,g,b,a);
        return color;
    }


    public  static boolean isWhite(Color color){
        if(color.a==1 && color.b==1 && color.g==1 && color.r==1){

            return true;
        }


        return false;

    }

    public static  boolean isEqual(Color color1,  Color color2){
        if(color1.a==color2.a && color1.b==color2.b && color1.g==color2.g && color1.r==color2.r){

            return true;
        }


        return false;

    }






}

package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Fonts {


    public static BitmapFont closterBlackLight(int size, int spaceX, int spaceY, int shadowX, int shadowY, float borderGamma, float gamma,  Color color, Color shadowColor, int border, boolean straight) {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/font2.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size=size;
        parameter.borderWidth=border;
        parameter.color=color;
        parameter.borderStraight=straight;
        parameter.spaceX=spaceX;
        parameter.spaceY=spaceY;
        parameter.shadowOffsetX=shadowX;
        parameter.shadowOffsetY=shadowY;
        parameter.borderGamma=borderGamma;
        parameter.gamma=gamma;
        parameter.shadowColor=shadowColor;
        BitmapFont font=fontGenerator.generateFont(parameter);
        return font;





    }


    public static BitmapFont closterBlackLight(int size, int spaceX, int spaceY,  Color color) {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/font2.ttff"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size=size;
        parameter.color=color;
        parameter.spaceX=spaceX;
        parameter.spaceY=spaceY;
        BitmapFont font=fontGenerator.generateFont(parameter);
        return font;





    }

    public static BitmapFont closterBlackLight(int size,  Color color) {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/font2.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size=size;
        parameter.color=color;
        BitmapFont font=fontGenerator.generateFont(parameter);
        return font;





    }

    public static BitmapFont closterBlackLight(int size) {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/font2.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size=size;
        BitmapFont font=fontGenerator.generateFont(parameter);
        return font;





    }

}

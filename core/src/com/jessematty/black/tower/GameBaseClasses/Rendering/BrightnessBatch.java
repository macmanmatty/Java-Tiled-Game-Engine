package com.jessematty.black.tower.GameBaseClasses.Rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class BrightnessBatch  extends SpriteBatch {
    ShaderProgram shader;

    public BrightnessBatch() {
        ShaderProgram.pedantic=false;

        shader= new ShaderProgram(Gdx.files.internal("Shaders/BrightnessVertexShader.vsh"), Gdx.files.internal("Shaders/BrightnessFragmentShader.fsh"));
        shader.begin();
        shader.setUniformf("bright", 1);
        shader.end();

        setShader(shader);
    }



    public void setColor(float r, float g, float b, float a, float brightness) {
        super.setColor(r, g, b, a);
        shader.setUniformf("bright", brightness);



    }


    public void setBrightness(float brightness){
        shader.setUniformf("bright", brightness);


    }


    public  void setColor(Color color, float brightness){
        super.setColor(color);
        shader.setUniformf("bright", brightness);


    }




}

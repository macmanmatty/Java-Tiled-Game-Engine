package com.jessematty.black.tower.GameBaseClasses.Rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
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



    public void setTiledMapView (OrthographicCamera camera) {
       setProjectionMatrix(camera.combined);
        float width = camera.viewportWidth * camera.zoom;
        float height = camera.viewportHeight * camera.zoom;
        float w = width * Math.abs(camera.up.y) + height * Math.abs(camera.up.x);
        float h = height * Math.abs(camera.up.y) + width * Math.abs(camera.up.x);

    }





}

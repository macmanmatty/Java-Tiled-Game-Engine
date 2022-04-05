package com.jessematty.black.tower.GameBaseClasses.Rendering;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * class that extends sprite batch to add brightness to sprites
 * use custom openGL shaders to achieve this
 */
public class BrightnessBatch  extends SpriteBatch {
    /**
     * the custom openGL shader
     */
    ShaderProgram shader;
    public BrightnessBatch() {
        ShaderProgram.pedantic=false;
        shader= new ShaderProgram(Gdx.files.internal("Shaders/BrightnessVertexShader.vsh"), Gdx.files.internal("Shaders/BrightnessFragmentShader.fsh"));
        shader.begin();
        shader.setUniformf("bright", 1);
        shader.end();
        setShader(shader);
    }
    /**
     * sets the current brightness level for the shader and color using RGBA float values
     * @param brightness the brightness from 0f to 2f 0=dark 2=bright white
     */
    public void setColor(float r, float g, float b, float a, float brightness) {
        super.setColor(r, g, b, a);
        shader.setUniformf("bright", brightness);
    }

    /**
     * sets the current brightness level for the shader
     * @param brightness the brightness from 0f to 2f 0=dark 2=bright white
     */
    public void setBrightness(float brightness){
        shader.setUniformf("bright", brightness);
    }
    /**
     * sets the current brightness and color level for the shader
     * @param brightness the brightness from 0f to 2f 0=dark black  2=bright white
     */
    public  void setColor(Color color, float brightness){
        super.setColor(color);
        shader.setUniformf("bright", brightness);
    }

}

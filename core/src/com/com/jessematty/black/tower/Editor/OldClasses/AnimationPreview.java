package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AnimationPreview extends Actor { // class to drawItemAtLocation an animation preview

    AtlasRegion[] frames= new AtlasRegion[1];
    Color textureColor; // the currentColor to drawItemAtLocation the preview animation may be null for no coloeration
    int frameNumber;
    int time;
    int screenLocationX;
    int screenLocationY;
    int numberOfFrames=1;



    public AnimationPreview(int screenLocationX, int screenLocationY) {
        this.screenLocationX = screenLocationX;
        this.screenLocationY = screenLocationY;
    }

    public AnimationPreview(AtlasRegion[] frames, int screenLocationX, int screenLocationY) {
        this.frames = frames;
        this.numberOfFrames=frames.length;

        this.screenLocationX = screenLocationX;
        this.screenLocationY = screenLocationY;
    }

    public AnimationPreview(AtlasRegion[] frames, Color textureColor, int screenLocationX, int screenLocationY) {
        this.frames = frames;
        this.numberOfFrames=frames.length;

        this.textureColor = textureColor;
        this.screenLocationX = screenLocationX;
        this.screenLocationY = screenLocationY;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if (textureColor!=null){

            batch.setColor(textureColor);
        }
        if (frames[frameNumber]!=null) {
            batch.draw(frames[frameNumber], screenLocationX, screenLocationY);
        }

        time++;
        if(time%5==0){

            frameNumber++;

        }
        if (frameNumber>numberOfFrames){

            frameNumber=0;

        }



    }

    public AtlasRegion[] getFrames() {
        return frames;
    }

    public void setFrames(AtlasRegion[] frames) {
        this.frames = frames;
        this.numberOfFrames=frames.length;

    }


    public Color getTextureColor() {
        return textureColor;
    }

    public void setTextureColor(Color textureColor) {
        this.textureColor = textureColor;
    }
}





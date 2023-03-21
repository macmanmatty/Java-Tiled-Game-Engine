package com.jessematty.black.tower.Components.Animation;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;

public class DirectionalAnimation {

    private AtlasRegion []  animationFrames;
    private Direction direction;
    private Vector2 [] animationOffsets;
    private int [] subLayerNumbers;
    private String [] animationFrameNames;



    public AtlasRegion[] getAnimationFrames() {
        return animationFrames;
    }


    public Direction getDirection() {
        return direction;
    }

    public Vector2[] getAnimationOffsets() {
        return animationOffsets;
    }

    public int[] getSubLayerNumbers() {
        return subLayerNumbers;
    }

    public String[] getAnimationFrameNames() {
        return animationFrameNames;
    }


    public void setAnimationFrames(AtlasRegion[] animationFrames) {
        this.animationFrames = animationFrames;

        for(int count=0; count<animationFrames.length; count++){
            animationFrameNames[count]=animationFrames[count].name;
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setAnimationOffsets(Vector2[] animationOffsets) {
        this.animationOffsets = animationOffsets;
    }

    public void setSubLayerNumbers(int[] subLayerNumbers) {
        this.subLayerNumbers = subLayerNumbers;
    }


}

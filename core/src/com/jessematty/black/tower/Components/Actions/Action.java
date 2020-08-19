package com.jessematty.black.tower.Components.Actions;

import com.jessematty.black.tower.Components.Stats.StringStat;

public class Action extends StringStat {
    private boolean acting;
    private int animationFrames=1;
    private int currentFrame=0;
    private int frameRate=1;



    public Action() {
        setStat("rest");

    }

    public boolean isActing() {
        return acting;
    }

    public void setActing(boolean acting) {
        this.acting = acting;
    }

    public int getAnimationFrames() {
        return animationFrames;
    }

    public void setAnimationFrames(int animationFrames) {
        this.animationFrames = animationFrames;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

    public void stopAction(){
        acting=false;
        setStat("rest");
        currentFrame=0;
        frameRate=1;


    }
}

package com.jessematty.black.tower.Components.Actions;

import com.jessematty.black.tower.Components.Stats.StringStat;

public class ActionComponent extends StringStat {

    /**
     * whether or not the action is action is acting;
     */
    private boolean acting;
    private int animationFrames=1;
    private int currentFrame=0;
    private int frameRate=1;
    /**
     * the number of turns
     * the actions takes to complete;
     * -1 means continuous action
     */
    private int turnsToCompletion =-1;
    /**
     * the number of turns passed in the game;
     */
    private int turns;




    public ActionComponent() {
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
        turns=0;
    }

    public int getTurnsToCompletion() {
        return turnsToCompletion;
    }

    public void setTurnsToCompletion(int turnsToCompletion) {
        this.turnsToCompletion = turnsToCompletion;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }
    public void addTurn(){
        this.turns++;
    }
}

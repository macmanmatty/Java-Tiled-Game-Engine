package com.jessematty.black.tower.GameBaseClasses.Sound;

import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.SoundComponent;

public class Sound {

    private com.badlogic.gdx.audio.Sound sound;// the current  sound to play
    private String soundFilePaths =""; // the sound file path
    private float decibels=1; // how loud the sound is
    private int  timesToPlay=0; // amounts of times to play sound for  0<= loop sound

    private float minFrequency;
    private float maxFrequency;
    private String action;


    public com.badlogic.gdx.audio.Sound getSound() {
        return sound;
    }

    public void setSound(com.badlogic.gdx.audio.Sound sound) {
        this.sound = sound;
    }

    public String getSoundFilePath() {
        return soundFilePaths;
    }

    public void setSoundFilePaths(String soundFilePaths) {
        this.soundFilePaths = soundFilePaths;
    }

    public float getDecibels() {
        return decibels;
    }

    public void setDecibels(float decibels) {
        this.decibels = decibels;
    }

    public int getTimesToPlay() {
        return timesToPlay;
    }

    public void setTimesToPlay(int timesToPlay) {
        this.timesToPlay = timesToPlay;
    }

    public float getMinFrequency() {
        return minFrequency;
    }

    public void setMinFrequency(float minFrequency) {
        this.minFrequency = minFrequency;
    }

    public float getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(float maxFrequency) {
        this.maxFrequency = maxFrequency;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

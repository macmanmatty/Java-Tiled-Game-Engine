package com.jessematty.black.tower.Components;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ObjectMap;
public class SoundComponent implements Component {
    private Sound sound;// the current  sound to play
   private ObjectMap<String, com.jessematty.black.tower.GameBaseClasses.Sound.Sound> sounds = new ObjectMap<>(); // the sound file path
   
    private int timesPlayed;
  private String currentAction;
  private boolean playSound=false;
    public SoundComponent() {// used for deserialization
    }
    public SoundComponent(String soundFilePath) {
       sound = Gdx.audio.newSound(Gdx.files.internal(soundFilePath));
    }
    public Sound getCurrentSound() {
        sound=null;
        String soundFilePtah=sounds.get(currentAction).getSoundFilePath();
        if(soundFilePtah!=null) {
            if(Gdx.files.internal(soundFilePtah).exists()) {
                sound = Gdx.audio.newSound(Gdx.files.internal(soundFilePtah));
            }

        }
        return  sound;
    }
    public float getCurrentSoundDecibels() {
        return sounds.get(currentAction).getDecibels();
    }
    public int  getCurrentSoundTimesToPlay() {
        return sounds.get(currentAction).getTimesToPlay();
    }
  
    public float getCurrentSoundMinFrequency() {
        return sounds.get(currentAction).getMinFrequency();
    }
    public float getCurrentSoundMaxFrequency() {
        return sounds.get(currentAction).getMaxFrequency();
    }
    public void tick(){
        timesPlayed++;
    }
    public int getTimesPlayed() {
        return timesPlayed;
    }
    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }
    public String getCurrentAction() {
        return currentAction;
    }
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }
    public void resetCounter() {
        timesPlayed=0;
    }

    public boolean isPlaySound() {
        return playSound;
    }

    public void setPlaySound(boolean playSound) {
        this.playSound = playSound;
    }
}

package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.ObjectMap;

public class TalkComponent implements Component {

   private ObjectMap<String , String> messages= new ObjectMap<>();
   private  boolean playSound;
   private ObjectMap<String, SoundComponent> sounds= new ObjectMap<>();




    public boolean isPlaySound() {
        return playSound;
    }

    public void setPlaySound(boolean playSound) {
        this.playSound = playSound;
    }

    public ObjectMap<String, String> getMessages() {
        return messages;
    }

    public ObjectMap<String, SoundComponent> getSounds() {
        return sounds;
    }
}

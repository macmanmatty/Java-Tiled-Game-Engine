package com.jessematty.black.tower.Components.Actions.ActionComponentMarkers;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


// interface  that denotes a  component as an action component that can be preformed

// components that implement this  must hae no args constructor!
public class ItemActionImageComponent implements Component {
  private   Image buttonDownImage;
   private Image buttonUpImage;
   private Sound sound;

    public Image getButtonDownImage() {
        return buttonDownImage;
    }

    public void setButtonDownImage(Image buttonDownImage) {
        this.buttonDownImage = buttonDownImage;
    }

    public Image getButtonUpImage() {
        return buttonUpImage;
    }

    public void setButtonUpImage(Image buttonUpImage) {
        this.buttonUpImage = buttonUpImage;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }
}

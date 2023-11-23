package com.jessematty.black.tower.Components.Item.ItemAction;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jessematty.black.tower.Components.Actions.ItemAction;

public class ItemActionComponent implements Component {
    String actionName="action";
    ItemAction itemAction;

    private Image buttonDownImage;
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

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public ItemAction getItemAction() {
        return itemAction;
    }

    public void setItemAction(ItemAction itemAction) {
        this.itemAction = itemAction;
    }
}

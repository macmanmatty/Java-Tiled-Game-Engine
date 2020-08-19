package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ItemDisplayWindow  extends GameWindow {
    public ItemDisplayWindow(String title, Skin skin) {
        this(title, skin, "default");
    }

    public ItemDisplayWindow(String title, Skin skin, String styleName) {
        super(title, skin, styleName);
    }
}

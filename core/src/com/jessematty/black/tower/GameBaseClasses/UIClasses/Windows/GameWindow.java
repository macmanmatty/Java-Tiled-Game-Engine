package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.ClosebleWindow;

public class GameWindow extends ClosebleWindow {
    public GameWindow(String title, Skin skin) {
        this(title, skin, "default");
    }

    public GameWindow(String title, Skin skin, String styleName) {
        super(title, skin, styleName);
    }
}

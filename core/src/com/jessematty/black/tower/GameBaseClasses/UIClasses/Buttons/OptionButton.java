package com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class OptionButton extends TextButton {
    int option;
    public OptionButton(String text, Skin skin, int option) {
        super(text, skin);

        this.option=option;
    }

    public OptionButton(String text, Skin skin, String styleName, int option) {
        super(text, skin, styleName);

        this.option=option;
    }

    public int getOption() {
        return option;
    }
}

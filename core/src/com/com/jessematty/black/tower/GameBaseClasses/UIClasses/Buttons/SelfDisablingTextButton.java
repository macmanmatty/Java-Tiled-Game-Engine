package com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class SelfDisablingTextButton extends TextButton {

    public SelfDisablingTextButton(String text, Skin skin) {
        super(text, skin);
    }

    public SelfDisablingTextButton(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }

    public SelfDisablingTextButton(String text, TextButtonStyle style) {
        super(text, style);
    }
}

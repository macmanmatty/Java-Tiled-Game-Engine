package com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class SelfDisablingImageButton extends ImageButton {
    public SelfDisablingImageButton(Skin skin) {
        super(skin);
    }

    public SelfDisablingImageButton(Skin skin, String styleName) {
        super(skin, styleName);
    }

    public SelfDisablingImageButton(ImageButtonStyle style) {
        super(style);
    }

    public SelfDisablingImageButton(Drawable imageUp) {
        super(imageUp);
    }

    public SelfDisablingImageButton(Drawable imageUp, Drawable imageDown) {
        super(imageUp, imageDown);
    }

    public SelfDisablingImageButton(Drawable imageUp, Drawable imageDown, Drawable imageChecked) {
        super(imageUp, imageDown, imageChecked);
    }
}

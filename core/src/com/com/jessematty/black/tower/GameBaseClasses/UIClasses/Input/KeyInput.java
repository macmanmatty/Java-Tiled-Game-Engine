package com.jessematty.black.tower.GameBaseClasses.UIClasses.Input;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;

public class KeyInput extends HorizontalGroup {

    private TextButton setKeys;
    private InputKeyCombo inputKeyCombo;
    private Skin skin;

    public KeyInput(InputKeyCombo inputKeyCombo, Skin skin) {
        this.inputKeyCombo = inputKeyCombo;
        this.skin = skin;
        setKeys= new TextButton("Change Keys", skin);
        Label label= new Label(inputKeyCombo.getName(), skin);
        addActor(label);
        addActor(setKeys);
    }
}

package com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindow;


public class WindowOptionPane extends Dialog {




    // option that displays a new window on accept
    public WindowOptionPane(Skin skin, String title, String text, String buttonText, String button2Text, Image image, final GameWindow window) {
        super(title, skin);
        text(text);
        TextButton button = new TextButton(buttonText, skin);
        TextButton button2 = new TextButton(button2Text, skin);
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hide();
                getStage().addActor(window);
                window.setVisible(true);
                return true;
            }
        });
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hide();
                return true;
            }
        });
        addActor(image);
        add(button);
        add(button2);
        setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        setSize(getPrefWidth(), getPrefHeight());

    }}

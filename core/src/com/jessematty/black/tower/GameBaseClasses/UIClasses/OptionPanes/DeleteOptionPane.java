package com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;

public class DeleteOptionPane extends Dialog {
    /**
     * creates an option pane the confrims deleting an element from an libGDX array of items
     * @param skin the libGDX skin for the UI elements
     * @param items the array of items
     * @param item the item to delete
     */
    public DeleteOptionPane(Skin skin, Array items, Object item) {
        super("Conform Delete", skin);
        text("Delete "+item.toString()+" ?");
        TextButton button = new TextButton("Delete Item", skin);
        TextButton button2 = new TextButton("Cancel", skin);
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hide();
                items.removeValue(item, true);
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
        add(button);
        add(button2);
        setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        setSize(getPrefWidth(), getPrefHeight());

    }
}

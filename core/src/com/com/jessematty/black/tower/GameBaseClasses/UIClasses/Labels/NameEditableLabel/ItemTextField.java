package com.jessematty.black.tower.GameBaseClasses.UIClasses.Labels.NameEditableLabel;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class ItemTextField<T extends Nameable> extends TextField {

    T item;
    public ItemTextField(String text, Skin skin) {
        super(text, skin);
    }

    public ItemTextField(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }

    public ItemTextField(String text, TextFieldStyle style) {
        super(text, style);
    }




    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}

package com.jessematty.black.tower.GameBaseClasses.UIClasses.Input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

public class KeyImages extends HorizontalGroup {

    Array<Keys> keys= new Array<>();
    GameAssets assets;

    public KeyImages(Array<Keys> keys) {
        this.keys = keys;
    }

    public void showKeys(){




    }

    public Array<Keys> getKeys() {
        return keys;
    }

    public void setKeys(Array<Keys> keys) {
        this.keys = keys;
    }
}

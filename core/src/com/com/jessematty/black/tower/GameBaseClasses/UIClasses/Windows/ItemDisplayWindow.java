package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class ItemDisplayWindow  extends GameWindow {
    public ItemDisplayWindow(String title, Skin skin, MapDraw mapDraw) {
        this(title, skin, "default", mapDraw);
    }

    public ItemDisplayWindow(String title, Skin skin, String styleName, MapDraw mapDraw) {
        super(title, skin, styleName, mapDraw.getGameAssets());
    }
}

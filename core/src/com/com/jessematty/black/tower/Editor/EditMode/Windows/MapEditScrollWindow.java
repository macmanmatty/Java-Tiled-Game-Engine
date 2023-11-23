package com.jessematty.black.tower.Editor.EditMode.Windows;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
public  abstract class MapEditScrollWindow extends EditWindow {
    private ScrollPane scrollPane;
    public MapEditScrollWindow(GameAssets gameAssets, String title , Skin skin, String style) {
        super( gameAssets,title, skin, style);
   scrollPane = new ScrollPane(this);
    }
    public ScrollPane getScrollPane() {
        return scrollPane;
    }
}

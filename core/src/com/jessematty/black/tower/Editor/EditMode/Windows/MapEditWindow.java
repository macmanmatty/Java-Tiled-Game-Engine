package com.jessematty.black.tower.Editor.EditMode.Windows;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

public  abstract class MapEditWindow extends EditWindow {

    private final com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen mapEditScreen;
    private final GameAssets gameAssets;


    public MapEditWindow(MapEditScreen mapEditScreen, String title , Skin skin, String style) {
        super(mapEditScreen, title, skin, style);
        this.mapEditScreen = mapEditScreen;
        this.gameAssets= mapEditScreen.getGameAssets();


    }


    public MapEditScreen getMapEditScreen() {
        return mapEditScreen;
    }

    public GameAssets getGameAssets() {
        return gameAssets;
    }
}

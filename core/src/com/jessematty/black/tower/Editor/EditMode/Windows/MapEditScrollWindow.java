package com.jessematty.black.tower.Editor.EditMode.Windows;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;

public  abstract class MapEditScrollWindow extends EditWindow {

    private final com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen mapEditScreen;
    private final GameAssets gameAssets;
    private final ScrollPane scrollPane;


    public MapEditScrollWindow(com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen mapEditScreen, String title , Skin skin, String style) {
        super( mapEditScreen,title, skin, style);
        this.mapEditScreen = mapEditScreen;
        this.gameAssets= mapEditScreen.getGameAssets();
        scrollPane = new ScrollPane(this);



    }




    public MapEditScreen getMapEditScreen() {
        return mapEditScreen;
    }

    public GameAssets getGameAssets() {
        return gameAssets;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }
}

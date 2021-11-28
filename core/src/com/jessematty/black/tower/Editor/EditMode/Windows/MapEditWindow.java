package com.jessematty.black.tower.Editor.EditMode.Windows;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.MapSettable;

public  abstract class MapEditWindow extends EditWindow  implements MapSettable {
    private GameMap map;
    public MapEditWindow(GameAssets gameAssets, String title , Skin skin, String style) {
        super( gameAssets, title, skin, style);
    }
    @Override
    public GameMap getMap() {
        return map;
    }
}

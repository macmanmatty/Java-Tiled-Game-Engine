package com.jessematty.black.tower.Editor.EditMode.Windows;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Editor.EditMode.World.WorldObjects;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Maps.WorldSettable;

public  abstract class ObjectEditWindow extends EditWindow  implements WorldSettable {
    private final GameAssets gameAssets;
    private WorldObjects worldObjects;

    public ObjectEditWindow(GameAssets gameAssets , String title , Skin skin, String style) {
        super( gameAssets,  title, skin, style);
        this.gameAssets= gameAssets;
    }
    public GameAssets getGameAssets() {
        return gameAssets;
    }


}

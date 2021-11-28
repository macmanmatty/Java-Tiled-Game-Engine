package com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapWindows;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.EditWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

public class AnimatedTileEditWindow extends EditWindow {
    public AnimatedTileEditWindow(GameAssets assets,  Skin skin) {
        super(assets,  "Animated Tile Edit", skin, "default");
    }

    @Override
    public void makeWindow() {

    }
}

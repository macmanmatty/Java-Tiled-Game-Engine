package com.jessematty.black.tower.Editor.EditMode.Windows;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Maps.MapSettable;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Maps.WorldSettable;

public  abstract class WorldEditWindow extends EditWindow  implements WorldSettable {
    private final GameAssets gameAssets;
    private  World world;
    public WorldEditWindow( GameAssets gameAssets , String title , Skin skin, String style) {
        super( gameAssets,  title, skin, style);
        this.gameAssets= gameAssets;
    }
    public GameAssets getGameAssets() {
        return gameAssets;
    }

    @Override
    public World getWorld() {
        return world;
    }
}

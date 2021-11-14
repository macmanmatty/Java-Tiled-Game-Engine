package com.jessematty.black.tower.Editor.EditMode.Windows.TiledTileMapEdit;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoardChangeListener;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.ColoredTiledMapTile;

public class TiledMapTileEditWindow extends MapEditWindow implements ClipBoardChangeListener {

    ColoredTiledMapTile tiledMapTile;

    public TiledMapTileEditWindow(MapEditScreen mapEditScreen, String title, Skin skin, String style) {
        super(mapEditScreen, title, skin, style);
    }

    @Override
    public void objectChanged(Object object) {
        if(object instanceof ColoredTiledMapTile ){

            setTiledMapTile((ColoredTiledMapTile) object);
        }

        else{

            noInformation();

        }

    }

    public ColoredTiledMapTile getTiledMapTile() {
        return tiledMapTile;
    }

    public void setTiledMapTile(ColoredTiledMapTile tiledMapTile) {
        this.tiledMapTile = tiledMapTile;
        makeWindow();
    }

    public void noInformation(){

    }


    @Override
    public void makeWindow() {

    }
}

package com.jessematty.black.tower.Editor.EditMode.Windows.TiledTileMapEdit;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoardChangeListener;
import com.jessematty.black.tower.Editor.EditMode.Windows.EditWindow;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.ColoredTiledMapTile;

public class TiledMapTileEditWindow extends EditWindow implements ClipBoardChangeListener {

    ColoredTiledMapTile tiledMapTile;

    public TiledMapTileEditWindow(GameAssets assets, String title, Skin skin, String style) {
        super(assets, title, skin, style);
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

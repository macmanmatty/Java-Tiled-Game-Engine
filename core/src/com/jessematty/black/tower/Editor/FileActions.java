package com.jessematty.black.tower.Editor;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.TiledMapEdit.TiledMapTools;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileAction;

import java.io.File;

public class FileActions {
    public static MapEditScreen mapEditScreen;

    public FileActions(MapEditScreen mapEditScreen) {
        this.mapEditScreen = mapEditScreen;
    }

    public static FileAction loadTMXMAP= new FileAction() {
        @Override
        public void act(File file) throws Exception {
            TiledMap map= mapEditScreen.getGameAssets().loadExternalTMXMap(file.getPath());
          TiledMap convertedMap = TiledMapTools.convertToAtlasBasedTiledMap(  map, mapEditScreen.getCurrentMap().getMapName(), mapEditScreen.getWorld().getWorldTextureAtlas(),  "");
            mapEditScreen.getTiledMapEdit().setCurrentTiledMap(convertedMap);



        }
    };

    public static FileAction loadGame= new FileAction() {
        @Override
        public void act(File file) throws Exception {

        }
    };
    public static FileAction saveGame= new FileAction() {
        @Override
        public void act(File file) throws Exception {

        }
    };
}

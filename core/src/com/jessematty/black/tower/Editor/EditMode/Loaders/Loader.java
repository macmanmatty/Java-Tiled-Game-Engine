package com.jessematty.black.tower.Editor.EditMode.Loaders;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

public class Loader {

    private MapEditScreen mapEditScreen;
    private GameAssets gameAssetts;

    public Loader(MapEditScreen mapEditScreen) {
        this.mapEditScreen = mapEditScreen;
        gameAssetts=mapEditScreen.getGameAssets();
    }

    private  void  loadAtlas( String path){
        TextureAtlas textureAtlas=gameAssetts.loadExternalTextureAtlas(path);

       Array<AtlasRegion>  regions= textureAtlas.getRegions();
       int size=regions.size;
       for(int count=0; count<size; count++){

           addRegion(regions.get(count));

       }



    }
    private  void addRegion(AtlasRegion atlasNamedAtlasRegion){


    }


}

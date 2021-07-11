package com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.Editor.Tools.MapTools.TiledMapTools;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

public class TiledMapSerializer extends Serializer<TiledMap> {

    private final GameAssets gameAssets;
    private boolean fast;

    public TiledMapSerializer( boolean fast , GameAssets gameAssets) {
        this.gameAssets = gameAssets;
        this.fast=fast;
        this.fast=true;


    }

    @Override
    public void write(Kryo kryo, Output output, TiledMap tiledMap) {


        TiledMapSaver tiledMapSaver=null;



        if(fast==true) {

        tiledMapSaver=new FastTiledMapSaver();
        }
        else{
           tiledMapSaver= new MemoryEfficentTiledMapSaver();

        }
        try {
            tiledMapSaver.saveMap(tiledMap, "assetts");
        } catch (MapLoadingExeception mapLoadingExeception) {
            mapLoadingExeception.printStackTrace();
        }
        kryo.writeClassAndObject(output, tiledMapSaver);







    }

    @Override
    public TiledMap read(Kryo kryo, Input input, Class<TiledMap> type) {
        TiledMapSaver tiledMapSaver=null;
        if(fast==true){
            tiledMapSaver= (FastTiledMapSaver) kryo.readClassAndObject(input);


        }


        else {
            tiledMapSaver = (MemoryEfficentTiledMapSaver) kryo.readClassAndObject(input);

        }



     TiledMap map = tiledMapSaver.loadMap(gameAssets);

        return map;




    }
}

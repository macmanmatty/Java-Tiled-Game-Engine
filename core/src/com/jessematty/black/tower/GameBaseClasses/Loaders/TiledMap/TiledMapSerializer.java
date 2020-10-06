package com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.Animation;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MemoryEfficentTiledMapSaver;

public class TiledMapSerializer extends Serializer<TiledMap> {

    private final GameAssets gameAssets;
    private boolean fast;

    public TiledMapSerializer( boolean fast , GameAssets gameAssets) {
        this.gameAssets = gameAssets;
        this.fast=fast;


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
        tiledMapSaver.saveMap(tiledMap, "assetts");
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



       return  tiledMapSaver.loadMap(gameAssets);




    }
}

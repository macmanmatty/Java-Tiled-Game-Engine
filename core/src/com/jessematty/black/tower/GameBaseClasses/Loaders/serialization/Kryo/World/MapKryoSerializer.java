package com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Kryo.World;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.LoadingException;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.TransientChecker;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.Settings.GameMapSettings;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class MapKryoSerializer extends Serializer<LandMap> {

    private final GameAssets gameAssets;
    private final TransientChecker transientChecker = new TransientChecker();


    public MapKryoSerializer(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
    }

    @Override
    public void write(Kryo kryo, Output output, LandMap map) {
        kryo.writeClassAndObject(output, map.getGameMapSettings());
        kryo.writeClassAndObject(output, map.getMap());
        kryo.writeClassAndObject(output, map.getTiledMap());






    }

    @Override
    public LandMap read(Kryo kryo, Input input, Class<LandMap> type) {

        LandMap map= new LandMap();
        GameMapSettings gameMapSettings= (GameMapSettings) kryo.readClassAndObject(input);
        LandSquareTile [] [] mapTiles= (LandSquareTile[][]) kryo.readClassAndObject(input);
        TiledMap tiledMap= (TiledMap) kryo.readClassAndObject(input);
        map.setMap(mapTiles);
        map.setTiledMap(tiledMap);
        map.setGameMapSettings(gameMapSettings);
        map.setTileSize(gameMapSettings.getTileSizeX(), gameMapSettings.getTileSizeY());




            return  map;





    }
}

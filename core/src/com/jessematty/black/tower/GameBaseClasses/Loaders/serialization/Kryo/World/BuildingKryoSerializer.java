package com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Kryo.World;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.TransientChecker;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.Maps.Settings.GameMapSettings;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class BuildingKryoSerializer extends Serializer<Building> {

    private final GameAssets gameAssets;


    public BuildingKryoSerializer(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
    }

    @Override
    public void write(Kryo kryo, Output output, Building map) {
        kryo.writeClassAndObject(output, map.getGameMapSettings());
        kryo.writeClassAndObject(output, map.getMap());
        kryo.writeClassAndObject(output, map.getTiledMap());






    }

    @Override
    public Building read(Kryo kryo, Input input, Class<Building> type) {

        Building map= new Building();
        GameMapSettings gameMapSettings= (GameMapSettings) kryo.readClassAndObject(input);
        LandSquareTile [] [] mapTiles= (LandSquareTile[][]) kryo.readClassAndObject(input);
        TiledMap tiledMap= (TiledMap) kryo.readClassAndObject(input);
        map.setMap(mapTiles);
        map.setTiledMap(tiledMap);
        map.setGameMapSettings(gameMapSettings);
        map.setTileSize(gameMapSettings.getSimpleSetting("tileWidth", Integer.class), gameMapSettings.getSimpleSetting("tileHeight", Integer.class));




            return  map;





    }
}

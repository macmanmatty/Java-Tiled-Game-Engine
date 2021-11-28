package com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.World;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.Settings.GameMapSettings;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
public class MapKryoSerializer extends Serializer<LandMap> {
    private final GameAssets gameAssets;
    public MapKryoSerializer(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
    }
    @Override
    public void write(Kryo kryo, Output output, LandMap map) {
        // write the game map settings
        kryo.writeClassAndObject(output, map.getGameMapSettings());
        // write the map of tiles
        kryo.writeClassAndObject(output, map.getMap());
        // uses a standard tiled tmx map file don't write tiled map
        if(!map.getGameMapSettings().getSimpleSetting("usesTMXMap", Boolean.class)) {
            kryo.writeClassAndObject(output, map.getTiledMap());
        }
    }
    @Override
    public LandMap read(Kryo kryo, Input input, Class<LandMap> type) {
        LandMap map= new LandMap();
        GameMapSettings gameMapSettings= (GameMapSettings) kryo.readClassAndObject(input);
        LandSquareTile [] [] mapTiles= (LandSquareTile[][]) kryo.readClassAndObject(input);
        if(!gameMapSettings.getSimpleSetting("usesTMXMap", Boolean.class)) {
            TiledMap tiledMap = (TiledMap) kryo.readClassAndObject(input);
            map.setTiledMap(tiledMap);
        }
        else{
            TiledMap tiledMap = gameAssets.loadExternalTMXMap(gameMapSettings.getSimpleSetting("tiledMapPath", String.class));
            map.setTiledMap(tiledMap);
        }
        map.setMap(mapTiles);
        map.setGameMapSettings(gameMapSettings);
        map.setTileSize(gameMapSettings.getSimpleSetting("tileWidth", Integer.class), gameMapSettings.getSimpleSetting("tileHeight", Integer.class));
            return  map;
    }
}

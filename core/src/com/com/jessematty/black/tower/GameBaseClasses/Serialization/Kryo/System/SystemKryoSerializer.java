package com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.System;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.Components.Interfaces.NewComponent;
import com.jessematty.black.tower.Components.Interfaces.Transient;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MapLoadingException;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.Settings.GameMapSettings;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
public class SystemKryoSerializer extends Serializer<LandMap> {
    private final GameAssets gameAssets;
    public SystemKryoSerializer(GameAssets gameAssets) {
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
        try {
            removeUnserializableComponentsFromEntities(map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (MapLoadingException mapLoadingException) {
            mapLoadingException.printStackTrace();
        }
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
    /**
     *  removes  all non-serializable components  from the map tiles in the game before saving them
     *
     * @param map the map  to serialize the entities
     * @return
     */
    public void removeUnserializableComponentsFromEntities(LandMap map) throws IllegalAccessException, InstantiationException, MapLoadingException {
        LandSquareTile [] [] tiles=map.getMap();
        if(tiles==null){
            throw new MapLoadingException("Map Must Not Be Null");
        }
        int xTiles=tiles.length;
        int yTiles=tiles[0].length;
        for(int countx=0; countx<xTiles; countx++){
            for(int county=0; county<yTiles; county++) {
                Entity entity=tiles[countx][county];
                ImmutableArray<Component> componentArray=entity.getComponents();
                for (int count = 0; count < componentArray.size(); count++) {
                    Component component = componentArray.get(count);
                    if(component.getClass().isAnnotationPresent(NewComponent.class)){
                        component=component.getClass().newInstance();
                    }
                    if (component.getClass().isAnnotationPresent(Transient.class)) {
                        entity.remove(component.getClass());
                        count--;
                    }
                }
            }
            }
        }
}

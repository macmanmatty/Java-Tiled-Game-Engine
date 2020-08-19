package com.jessematty.black.tower.GameBaseClasses.Loaders.World;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.GameSettings.GameStartSettings;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.JsonLoader;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.TextureAtlas.AtlasNamedAtlasRegionSerializer;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Entity.EntitySerializer;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MapLoadingExeception;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.Settings.GameMapSettings;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class WorldReader {
    private final JsonLoader jsonLoader;
    private final GameAssets assetts;
    private final Engine engine;
    private Map<String, Entity> entitiesInWorld;
    private TextureAtlas atlas;
    private String fileSeperator;
    private final World world;
    ComponentMapper<Position> positionComponentMapper = ComponentMapper.getFor(Position.class);

    public WorldReader(GameAssets assetts) {
        this.assetts = assetts;
        jsonLoader = assetts.getJsonLoader();
        fileSeperator = FileUtilities.getFileSeparator();
        world = new World();
        engine = world.getEngine();
        jsonLoader.getJson().setSerializer(Entity.class, new EntitySerializer(assetts));
        jsonLoader.getJson().setSerializer(AtlasNamedAtlasRegion.class, new AtlasNamedAtlasRegionSerializer(assetts));




    }

    public World loadWorld(String path) throws com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MapLoadingExeception { // loads  game  from the maps json and tmx files ina given path
        String startSettingsPath = path + "GameStartSettings.json";
        File gameStartSettingsFile = new File(startSettingsPath);
        if (gameStartSettingsFile.exists() == false) { // if game has no settings file  return  maps as game cannot be properly loaded
            throw new com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MapLoadingExeception("File " + startSettingsPath + " does not exist");
        }
        GameStartSettings gameStartSettingsObject = (GameStartSettings) jsonLoader.loadObject(GameStartSettings.class, path + "GameStartSettings.json");
        entitiesInWorld = jsonLoader.loadObject(ObjectMap.class, path + "Entities.json");
        world.deserialize(assetts, gameStartSettingsObject);
        int xMaps = gameStartSettingsObject.getXMaps();
        int yMaps = gameStartSettingsObject.getYMaps();
        for (int counterx = 0; counterx < xMaps; counterx++) {
            for (int countery = 0; countery < yMaps; countery++) {
                LandMap map = (LandMap) loadMap(LandMap.class, path, "landMapX" + counterx + "Y" + countery);
                if (map.isCurrentMap()) {
                    world.setCurrentMap(map);
                }
                world.placeMap(map, counterx, countery);
                int buildings = map.getSettings().getNumberOfBuildings();
                for (int count = 0; count < buildings; count++) {
                    Building building = (Building) loadMap(Building.class, path, "landMapX" + counterx + "Y" + countery + "building" + buildings);
                    if (building != null) {
                        map.addBuilding(building);
                        if (building.isCurrentMap()) {
                            world.setCurrentMap(map);
                        }
                    }
                }
            }
        }

        Set<String> keys = entitiesInWorld.keySet();
        for (Iterator<String> ids = keys.iterator(); ids.hasNext(); ) {
            String id = ids.next();
            Entity entity = entitiesInWorld.get(id);
            Position position = positionComponentMapper.get(entity);
            if(position==null){ // entity has no position remove it from game
                entitiesInWorld.remove(id);
                continue;

            }
            GameMap map = world.getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY());
            if (map == null) { // entity is not ona valid map remove it from the game
                entitiesInWorld.remove(id);
                continue;
            }
            world.addEntity(entity);
        }


        return world;
    }

    private GameMap loadMap(Class<? extends GameMap> mapClass, String path, String name) throws com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MapLoadingExeception { // loads WoodWand given game landSquareTileMap and deserializes  all tiles and things on the landSquareTileMap
        GameMap map = null;
        try {
            map = mapClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String fullPath = path + fileSeperator + name + fileSeperator;
        File landSquareTilesFile = new File(fullPath + "LandSquareTileMap.json");
        File tiledMapFile = new File(fullPath + "TiledMap.tmx");
        File mapSettingsFile = new File(fullPath + "Settings.json");
        if (mapSettingsFile.exists() == false) {
            // if all required parts of landSquareTileMap don't exist throw exeception
            throw new com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MapLoadingExeception("File " + mapSettingsFile.getAbsolutePath() + "Does not Exist");
        }
        if (tiledMapFile.exists() == false) {
            throw new com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MapLoadingExeception("File " + tiledMapFile.getAbsolutePath() + "Does not Exist");
        }
        if (landSquareTilesFile.exists() == false) {
            throw new MapLoadingExeception("File " + landSquareTilesFile.getAbsolutePath() + "Does not Exist");
        }

        LandSquareTile[][] landSquareTileMap = (LandSquareTile[][]) jsonLoader.loadObject(LandSquareTile[][].class, fullPath + "LandSquareTileMap.json");
        GameMapSettings settings = (GameMapSettings) jsonLoader.loadObject(GameMapSettings.class, fullPath + "Settings.josn");
        TiledMap tiledMap = assetts.loadExternalTMXMap(fullPath + "tiledMap.tmx");
        map.setTiledMap(tiledMap);
        map.deSerialize(assetts, settings);
        map.setMap(landSquareTileMap);
        int sizex = landSquareTileMap.length;
        int sizey = landSquareTileMap[0].length;
        // add tiles to  ECS engine
        for (int countx = 0; countx < sizex; countx++) {
            for (int county = 0; county < sizey; county++) {
                engine.addEntity(landSquareTileMap[countx][county]);
            }
        }
        int xSize = landSquareTileMap.length;
        int ySize = landSquareTileMap[0].length;


        return map;
    }
}


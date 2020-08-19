package com.jessematty.black.tower.GameBaseClasses.Loaders.World;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.GameSettings.GameStartSettings;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.JsonLoader;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TextureAtlas.TextureAtlasPacker;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.TextureAtlas.AtlasNamedAtlasRegionSerializer;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Entity.EntitySerializer;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasAnimatedTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;

import java.io.IOException;

public class WorldWriter {
private  final JsonLoader jsonLoader;
private final GameAssets assetts;
private String fileSeperator;
private final  World world;
private boolean packAssetts;
private int packWidth;
private int packHeight;
    public WorldWriter(GameAssets assetts, World world) {
        this.world=world;
        this.assetts=assetts;


        jsonLoader = assetts.getJsonLoader();
        fileSeperator= FileUtilities.getFileSeparator();
        jsonLoader.getJson().setSerializer(Entity.class, new EntitySerializer(assetts));
        jsonLoader.getJson().setSerializer(AtlasNamedAtlasRegion.class, new AtlasNamedAtlasRegionSerializer(assetts));


    }

    private  void  saveMap(GameMap map,  String path, String name){
        path=path+fileSeperator+name+fileSeperator;
        jsonLoader.writeObjectToFile(map.getMap(), path+name+"LandSquareTileMap.json", false);
        System.out.println("Tiles Saves!");
        jsonLoader.writeObjectToFile(map.getSettings(), path+name+"Settings.json" ,false );
        jsonLoader.saveTiledMap(map.getTiledMap(), map.getXSize(), map.getYSize(),path+name+"tiledMap.tmx",  map.getTiledMapAtlasName(), assetts);
    }
    public void saveWorld(String path){
        GameStartSettings settings=world.getGameStartSettings();
        jsonLoader.writeObjectToFile(settings, path+"GameStartSettings.json", false);
        jsonLoader.writeObjectToFile(world.getEntitiesInWorld(), path+"GameEntities.json", false);
       int mapX =world.getXMaps();
       int mapY=world.getYMaps();
        for(int countx=0; countx<mapX; countx++){
            for(int county=0; county<mapY; county++) {
                LandMap map = world.getMap(countx, county);
                saveMap(map, path, "landMapX" + countx+"Y"+county);
                ObjectMap<String, Building> buildings = map.getBuildings();
                Keys<String> keys= buildings.keys();
                int counter=0;
                while(keys.hasNext){
                    counter++;
                    saveMap(buildings.get(keys.next()), path, "landMapX" + countx+"Y"+county+"building" + counter);
                }
            }
        }
    }
    private  void packMapTilesToAtlas(Array<AtlasNamedAtlasRegion> regions, String saveDirectory, String mapName, int packWidth, int packHeight, int padding) throws IOException {

        String fullName=mapName+"tiledMap";
        new TextureAtlasPacker().packRegions( regions, packWidth, packHeight, padding);


    }

    private void saveTiledMapRegions(TiledMap tiledMap){
        MapProperties mapProperties=tiledMap.getProperties();
        Integer width= (Integer) mapProperties.get("width");
        Integer height= (Integer) mapProperties.get("height");

        MapLayers layers=tiledMap.getLayers();
        int numberOfLayers=layers.size();




        Array<AtlasNamedAtlasRegion> regions= new Array<>();

        for(int countLayers=0; countLayers<numberOfLayers; countLayers++) {


            TiledMapTileLayer tiledMapTileLayer= (TiledMapTileLayer) layers.get(countLayers);

            for (int countx = 0; countx < width; countx++) {

                for (int county = 0; county < width; county++) {

                   Cell cell= tiledMapTileLayer.getCell(countx, county);
                   if(cell!=null){

                       TiledMapTile tile=cell.getTile();

                       if(tile instanceof AtlasStaticTiledMapTile) {


                           AtlasNamedAtlasRegion atlasRegion = (AtlasNamedAtlasRegion) tile.getTextureRegion();
                           if(atlasRegion!=null){

                               regions.add(atlasRegion);
                           }

                       }

                       else if(tile instanceof AtlasAnimatedTiledMapTile){

                       }



                   }


                }

            }
        }



    }


    public JsonLoader getJsonLoader() {
        return jsonLoader;
    }

    public boolean isPackAssetts() {
        return packAssetts;
    }

    public void setPackAssetts(boolean packAssetts) {
        this.packAssetts = packAssetts;
    }

    public int getPackWidth() {
        return packWidth;
    }

    public void setPackWidth(int packWidth) {
        this.packWidth = packWidth;
    }

    public int getPackHeight() {
        return packHeight;
    }

    public void setPackHeight(int packHeight) {
        this.packHeight = packHeight;
    }
}

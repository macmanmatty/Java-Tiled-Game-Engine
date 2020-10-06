package com.jessematty.black.tower.GameBaseClasses.Loaders.MapLoaders;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.JsonLoader;
import com.jessematty.black.tower.GameBaseClasses.Loaders.LoadingException;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MemoryEfficentTiledMapSaver;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.TiledMapSaver;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.EntitySerializer;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.TextureAtlas.AtlasNamedAtlasRegionSerializer;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileAction;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;

import java.io.File;

public class MapLoader {

   private  GameAssets assetts;
   private JsonLoader jsonLoader;
   private String fileSeperator;
   private World world;
   private Engine engine;
   private TiledMapSaver tiledMapSaver;
    public MapLoader(GameAssets assetts) {

        this.assetts = assetts;
        jsonLoader = assetts.getJsonLoader();
        fileSeperator = FileUtilities.getFileSeparator();
        world = new World();
        engine = world.getEngine();
        jsonLoader.getJson().setSerializer(Entity.class, new EntitySerializer(assetts));
        jsonLoader.getJson().setSerializer(AtlasNamedAtlasRegion.class, new AtlasNamedAtlasRegionSerializer(assetts));

    }
   public   void  loadMaps(String path) throws LoadingException {

       FileAction loadEntity= new FileAction() {
           @Override
           public void act(File file) throws LoadingException {
               if(FileUtilities.getExtensionOfFile(file).equals("map")) {
                   SavedLandMap savedMap = jsonLoader.loadObject(SavedLandMap.class, file.getAbsolutePath());
                   if(savedMap==null){
                       throw  new LoadingException();

                   }

                   loadMap(savedMap);
               }
               if(FileUtilities.getExtensionOfFile(file).equals("maps")) {
                   SavedLandMap [] savedMaps = jsonLoader.loadObject(SavedLandMap[].class, file.getAbsolutePath());

                   int size=savedMaps.length;
                   for(int count=0; count<size; count++) {
                       if(savedMaps[count]==null){

                               throw  new LoadingException();



                       }
                       loadMap(savedMaps[count]);
                   }

               }


           }
       };

       FileUtilities.actOnFiles(path, loadEntity);





   }

   private void loadMap(SavedLandMap savedMap) throws LoadingException {

       SavedMap savedLandMap=savedMap.getLandMap();
       if(savedMap==null){
           throw  new LoadingException();

       }



       MemoryEfficentTiledMapSaver memoryEfficentTiledMapSaver=savedLandMap.getTiledMapSaver();
       if(memoryEfficentTiledMapSaver==null){

               throw  new LoadingException();



       }
       TiledMap tiledMap= memoryEfficentTiledMapSaver.loadMap(assetts);
       LandMap map= (LandMap) savedLandMap.getMap();
       if(map==null){
           throw  new LoadingException();

       }

       map.setTiledMap(tiledMap);
       Array<SavedMap> buildings=savedMap.getBuildings();
       int size=buildings.size;

       for(int count=0; count<size; count++){

           SavedMap building=buildings.get(count);
           TiledMap buildingTiledMap=building.getTiledMapSaver().loadMap(assetts);
           Building buildingObject= (Building) building.getMap();
           buildingObject.setTiledMap(buildingTiledMap);
           map.addBuilding(buildingObject);

       }


       world.placeMap(map, map.getWorldX(), map.getWorldY());





   }



    private  SavedMap createSaveMap(GameMap map){
        SavedMap savedMap= new SavedMap();
        savedMap.setMap(map);
        MemoryEfficentTiledMapSaver memoryEfficentTiledMapSaver=new MemoryEfficentTiledMapSaver();
        memoryEfficentTiledMapSaver.saveMap(map.getTiledMap(),  map.getGameMapSettings().getTiledMapAtlasName());
        savedMap.setTiledMapSaver(memoryEfficentTiledMapSaver);


        return  savedMap;


    }
    public  void saveMap(LandMap map, String directory){

        SavedLandMap savedLandMap= new  SavedLandMap();
        savedLandMap.setLandMap(createSaveMap(map));

        Values<Building> buildings=map.getBuildings().values();
        while(buildings.hasNext()){

            Building building=buildings.next();
            SavedMap savedMap=createSaveMap(building);
            savedLandMap.getBuildings().add(savedMap);
        }

        directory=directory+FileUtilities.getFileSeparator()+map.getMapName()+".map";


        assetts.saveObject(savedLandMap, directory, false);


    }








}

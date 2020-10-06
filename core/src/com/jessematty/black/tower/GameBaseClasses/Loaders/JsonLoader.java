package com.jessematty.black.tower.GameBaseClasses.Loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Json;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.FastTiledMapSaver;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MemoryEfficentTiledMapSaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonLoader {
   private  final  Json json;
   private  boolean fastSaveTiledMaps;
    public JsonLoader() {
        this.json =new Json();


    }


    public void writeObjectToFile(Object object, String path, boolean append){
        String objectJson= json.prettyPrint(object);
        FileHandle file = Gdx.files.absolute(path);
        file.writeString(objectJson, append);
    }
    public <T>  T loadObject(Class thingClass , String path){
        FileHandle file = Gdx.files.absolute(path);
        String object2= file.readString();
        T  object  = (T) json.fromJson(thingClass, object2);
        return object;
    }
    public <T> List<T> loadArrayFromFile(Class<T> typeClass, String path){
        FileHandle file = Gdx.files.absolute(path);
        String object2= file.readString();
        ArrayList<T > object  = (ArrayList<T>) json.fromJson(ArrayList.class,  typeClass, object2);
        return object;
    }

    public <T, T2> Map<T, T2> loadMapFromFile(Class<T2> type2Class, String path){
        FileHandle file = Gdx.files.absolute(path);
        String object2= file.readString();
        HashMap<T , T2> object  = (HashMap<T, T2>) json.fromJson(HashMap.class,  type2Class,  object2);
        return object;
    }
    public void saveTiledMap(  TiledMap map, int xSize, int ySize,  String path, String atlasName, GameAssets assetts ){ //saves tiled map using the map saver class
        if(fastSaveTiledMaps ==true) {
            FastTiledMapSaver saver = new FastTiledMapSaver(assetts);
            saver.saveMap(map,  atlasName);
        }
        else{
            MemoryEfficentTiledMapSaver saver = new MemoryEfficentTiledMapSaver();
            saver.saveMap(map,  atlasName);
            writeObjectToFile(saver, path, false);

        }


    }
    public TiledMap loadTiledMap(  String path, GameAssets assetts ){
        TiledMap map=null;
        if(fastSaveTiledMaps ==true) {
            FastTiledMapSaver saver = loadObject(FastTiledMapSaver.class, path);
            map = saver.loadMap(assetts);
        }

        else{

            MemoryEfficentTiledMapSaver saver = loadObject(MemoryEfficentTiledMapSaver.class, path);
            map = saver.loadMap(assetts);
        }

       return map;
    }

       public <T>  T copyObject(Object object,  Class<T> objectClass){
        writeObjectToFile(object,"/temp.json" ,false );
        T objectCopy= loadObject(objectClass, "/temp.json");
        return  objectCopy;




    }



    public Json getJson() {
        return json;
    }

    public boolean isFastSaveTiledMaps() {
        return fastSaveTiledMaps;
    }

    public void setFastSaveTiledMaps(boolean fastSaveTiledMaps) {
        this.fastSaveTiledMaps = fastSaveTiledMaps;
    }
}

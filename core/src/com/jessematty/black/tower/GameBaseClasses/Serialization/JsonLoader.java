package com.jessematty.black.tower.GameBaseClasses.Serialization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Json;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.FastTiledMapSaver;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MapLoadingException;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MemoryEfficentTiledMapSaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonLoader {
    /**
     * class for saving objects to json format
     */
   private  final  Json json;
   private  boolean fastSaveTiledMaps=true;
    public JsonLoader() {
        this.json =new Json();


    }

    /**
     * writes an object to a file in json format
     * @param object the object to write
     * @param path the file path to save to
     * @param append // whether or not to over the json file or just append it
     */
    public void writeObjectToFile(Object object, String path, boolean append){
        String objectJson= json.prettyPrint(object);
        FileHandle file = Gdx.files.absolute(path);
        file.writeString(objectJson, append);
    }
    /**
     * reads an object to a file in json format
     * @param thingClass the class of the object to read
     * @param path the file path to read from
     */
    public <T>  T loadObject(Class thingClass , String path){
        FileHandle file = Gdx.files.absolute(path);
        String object2= file.readString();
        T  object  = (T) json.fromJson(thingClass, object2);
        return object;
    }

    /**
     * reads a json  array of objects  from a file
     * @param typeClass
     * @param path
     * @param <T>
     * @return
     */
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
    public void saveTiledMap(  TiledMap map, String path) throws MapLoadingException { //saves tiled map using the map saver class
        if(fastSaveTiledMaps ==true) {
            com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.FastTiledMapSaver saver = new com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.FastTiledMapSaver();
            saver.saveMap(map);
        }
        else{
            com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MemoryEfficentTiledMapSaver saver = new com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MemoryEfficentTiledMapSaver();
            saver.saveMap(map);
            writeObjectToFile(saver, path, false);

        }


    }
    public TiledMap loadTiledMap(  String path, GameAssets assetts ){
        TiledMap map=null;
        if(fastSaveTiledMaps ==true) {
            com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.FastTiledMapSaver saver = loadObject(FastTiledMapSaver.class, path);
            map = saver.loadMap(assetts);
        }

        else{

            com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MemoryEfficentTiledMapSaver saver = loadObject(MemoryEfficentTiledMapSaver.class, path);
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

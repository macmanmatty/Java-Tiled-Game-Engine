package com.jessematty.black.tower.GameBaseClasses.Serialization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.FastTiledMapSaver;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MemoryEfficientTiledMapSaver;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileHandleAction;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonLoader {
    /**
     * class for saving objects to json format
     */
   private  final  Json json;
   private  boolean fastSaveTiledMaps=true;

   private Array<Object> loadedObjects= new Array<Object>();

   private FileHandleAction fileAction= new FileHandleAction() {
       @Override
       public void act(FileHandle file) throws Exception {
           String object2= file.readString();
           loadedObjects.addAll(json.fromJson(ArrayList.class,  object2));
       }
   };
    public JsonLoader() {
        this.json =new Json();
    }
    /**
     * writes an object to a file in json format
     * @param object the object to write
     * @param path the file path to save to
     * @param append // whether or not to over the json file or just append it
     */
    public void writeObjectToFile(Object object, String path, boolean append, FileUtilities.FileHandleType fileHandleType){
        json.setUsePrototypes(false);
        String objectJson= json.prettyPrint(object);
      FileHandle file = FileUtilities.getFileHandle(path, fileHandleType);
        file.writeString(objectJson, append);
    }
    /**
     * reads an object to a file in json format
     * @param thingClass the class of the object to read
     * @param path the file path to read from
     */
    public <T>  T loadObject(Class thingClass , String path, FileUtilities.FileHandleType fileHandleType){
        FileHandle file = FileUtilities.getFileHandle(path, fileHandleType);
        String object2= file.readString();
        T  object  = (T) json.fromJson(thingClass, object2);
        return object;
    }
    /**
     * reads an internal  object from android/assets to a file in json format
     * @param thingClass the class of the object to read
     * @param path the file path to read from
     */
    public <T>  T loadInternalObject(Class thingClass , String path){
        FileHandle file = Gdx.files.internal(path);
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
    public <T> Array<T> loadArrayFromFile(Class<T> typeClass, String path, FileUtilities.FileHandleType fileHandleType){
        FileHandle file = FileUtilities.getFileHandle(path, fileHandleType);
        if(file.isDirectory()) {
        return  loadArrayFromDirectory(typeClass, file);
        }
        String object2= file.readString();
        Array<T > object  = (Array<T>) json.fromJson(Array.class,  typeClass, object2);
        return object;
    }

    private<T> Array<T> loadArrayFromDirectory(Class<T> type, FileHandle fileHandle){
        return  loadArrayFromDirectoryInternal(new Array<T>(), type,  fileHandle);
    }
    public<T>  Array<T> loadArrayFromDirectoryInternal(Array<T> objects,  Class <T>  type, FileHandle fileHandle){
      FileHandle [] fileHandles=  fileHandle.list();
      if(fileHandles.length==0){
          return objects;
      }
        for(FileHandle fileHandle1 : fileHandles) {
            if (fileHandle1.isDirectory()) {
                loadArrayFromDirectoryInternal(objects, type, fileHandle1);
            }
            else if (fileHandle1.extension().equals("json")){
                String object2= fileHandle1.readString();
                Array<T > objectsLoaded  = (Array<T>) json.fromJson(Array.class,  type, object2);
                objects.addAll(objectsLoaded);
            }
        }
        return objects;
    }

    public <T, T2> Map<T, T2> loadMapFromFile(Class<T2> type2Class, String path){
        FileHandle file = Gdx.files.absolute(path);
        String object2= file.readString();
        HashMap<T , T2> object  = (HashMap<T, T2>) json.fromJson(HashMap.class,  type2Class,  object2);
        return object;
    }
    public void saveTiledMap(  TiledMap map, String path)  { //saves tiled map using the map saver class
        if(fastSaveTiledMaps ==true) {
            com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.FastTiledMapSaver saver = new com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.FastTiledMapSaver();
            saver.saveMap(map);
        }
        else{
            MemoryEfficientTiledMapSaver saver = new MemoryEfficientTiledMapSaver();
            saver.saveMap(map);
            writeObjectToFile(saver, path, false, FileUtilities.FileHandleType.INTERNAL);
        }
    }
    public TiledMap loadTiledMap(  String path, GameAssets assetts ){
        TiledMap map=null;
        if(fastSaveTiledMaps ==true) {
            com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.FastTiledMapSaver saver = loadObject(FastTiledMapSaver.class, path, FileUtilities.FileHandleType.INTERNAL);
            map = saver.loadMap(assetts);
        }
        else{
            MemoryEfficientTiledMapSaver saver = loadObject(MemoryEfficientTiledMapSaver.class, path, FileUtilities.FileHandleType.INTERNAL);
            map = saver.loadMap(assetts);
        }
       return map;
    }
       public <T>  T copyObject(Object object,  Class<T> objectClass){
        writeObjectToFile(object,"/temp.json" ,false, FileUtilities.FileHandleType.INTERNAL );
        T objectCopy= loadObject(objectClass, "/temp.json", FileUtilities.FileHandleType.INTERNAL);
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

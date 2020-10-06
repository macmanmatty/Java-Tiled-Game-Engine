package com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.World;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializer;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.TagResolver;

public class ObjectMapSerializer<K,  V> implements Serializer<ObjectMap<K, V>> { // serializer for the libgdx atlas region class


    public ObjectMapSerializer() {

    }





    @Override
    public void write(Json json, ObjectMap<K, V> map, Class aClass) {
        String tag = TagResolver.getTag(json, aClass);
        json.writeObjectStart();
        json.writeObjectStart(tag);

        Keys<K> entrySet = map.keys();
        for(K key : entrySet){


            writeKey(json, key);
            V value=map.get(key);
            writeValue(json, value);


        }
        json.writeObjectEnd();
        json.writeObjectEnd();

    }




    @Override
    public ObjectMap read(Json json, JsonValue jsonValue, Class aClass) {

        String tag = TagResolver.getTag(json, ObjectMap.class);

        ObjectMap<K, V> map = new ObjectMap<K, V>();
        K key = null;
        try {
            key = readKey(json, jsonValue);

        V value = readValue(json, jsonValue);
            map.put(key, value);

        }

        catch (ReflectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }



        return map;
    }

    private K readKey(Json json, JsonValue jsonValue) throws ReflectionException, IllegalAccessException, InstantiationException {

        String classNme=jsonValue.name();

        Class<K> keyType = TagResolver.getClass(json,  classNme);
        K kObject=keyType.newInstance();
        json.readFields(kObject, jsonValue);




        return kObject;

    }


    private V readValue(Json json, JsonValue jsonValue) throws ReflectionException, IllegalAccessException, InstantiationException {

        String classNme=jsonValue.name();

        Class<V> valueType = TagResolver.getClass(json,  classNme);
        V vObject=valueType.newInstance();
        json.readFields(vObject, jsonValue);




        return vObject;

    }

    private void writeKey( Json json,  K key){

        Class type = key.getClass();
        String tag = TagResolver.getTag(json, type);
        json.writeObjectStart(tag);
        json.writeFields(key);
        json.writeObjectEnd();


    }


    private void writeValue( Json json,  V value){

        Class type = value.getClass();
        String tag = TagResolver.getTag(json, type);
        json.writeObjectStart(tag);
        json.writeFields(value);
        json.writeObjectEnd();


    }




}


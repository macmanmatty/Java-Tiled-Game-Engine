package com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.ObjectMap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * serializer  that serializes  a libGDX ObjectMap by write the keys  and the values
 * of the object map and then reading the keys and values back into a new ObjectMap instance
 *
 */
public class ObjectMapSerializer extends Serializer<ObjectMap> {
    public ObjectMapSerializer() {

    }

    /**
     * writes the  object map by saving they key and values pairs as arrays
     * @param kryo the kryo object
     * @param output the Kryo Output class
     * @param map the ObjectMap to save
     */
    @Override
    public void write(Kryo kryo, Output output, ObjectMap map) {
      Array keys=map.keys().toArray();
        Array values=map.values().toArray();
        kryo.writeClassAndObject(output, keys);
        kryo.writeClassAndObject(output, values);

    }

    /**
     * reads an  ObjectMap from a saved file as the key and value arrays
     * @param kryo the Kryo reader
     * @param input the Kryo Input
     * @param type the type of class to read in this case ObjectMap.class
     * @return
     */
    @Override
    public ObjectMap read(Kryo kryo, Input input, Class<ObjectMap> type) {
            ObjectMap  objectMap= new ObjectMap();
            Array keys= (Array) kryo.readClassAndObject(input);
             Array values= (Array) kryo.readClassAndObject(input);
            if(keys==null || values==null){
            return  objectMap;
             }
             int size=keys.size;

             for(int count=0; count<size; count++){
                 if(keys.get(count)==null){
                     continue;
                 }
                 objectMap.put(keys.get(count),values.get(count) );
             }
        return  objectMap;

    }
}

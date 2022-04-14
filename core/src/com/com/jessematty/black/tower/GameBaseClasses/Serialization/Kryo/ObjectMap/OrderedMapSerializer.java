package com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.ObjectMap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

/**
 * serializer  that serializes  a libGDX OrderedMap by write the keys  and the values
 * of the object map and then reading the keys and values back into a new OrderedMap instance
 *
 */
public class OrderedMapSerializer extends Serializer<OrderedMap> {
    public OrderedMapSerializer() {

    }

    /**
     * writes the  OrderedMap by saving they key and values pairs as arrays
     * @param kryo the kryo object
     * @param output the Kryo Output class
     * @param map the ObjectMap to save
     */
    @Override
    public void write(Kryo kryo, Output output, OrderedMap map) {
      Array keys=map.keys().toArray();
        Array values=map.values().toArray();
        kryo.writeClassAndObject(output, keys);
        kryo.writeClassAndObject(output, values);

    }

    /**
     * reads an  OrderedMap from a saved file as the key and value arrays
     * @param kryo the Kryo reader
     * @param input the Kryo Input
     * @param type the type of class to read in this case ObjectMap.class
     * @return
     */
    @Override
    public OrderedMap read(Kryo kryo, Input input, Class<OrderedMap> type) {
            OrderedMap  orderedMap= new OrderedMap();
            Array keys= (Array) kryo.readClassAndObject(input);
             Array values= (Array) kryo.readClassAndObject(input);
             if(keys==null || values==null){
                 return  orderedMap;
             }
             int size=keys.size;
             for(int count=0; count<size; count++){
                 if(keys.get(count)==null){
                     continue;
                 }
                 orderedMap.put(keys.get(count),values.get(count) );
             }
        return  orderedMap;

    }
}

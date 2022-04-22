package com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.Entity;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.Components.NewComponent;
import com.jessematty.black.tower.Components.SerializableComponet;
import com.jessematty.black.tower.Components.Transient;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

/**
 * serializer  for libGDX Ashley Entities and there Components
 */
public class EntityKryoSerializer extends Serializer<Entity> {
    private final GameAssets gameAssets;
    public EntityKryoSerializer(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
    }

    /**
     * writes te entities object
     * @param kryo the kryo object
     * @param output the Kryo Output class
     * @param entity the Entity to save
     */
    @Override
    public void write(Kryo kryo, Output output, Entity entity) {
        Array<Component> components= new Array<>();
        for (Component component : entity.getComponents()) {
            if(component.getClass().isAnnotationPresent(NewComponent.class)){
                try {
                    component=component.getClass().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if(component.getClass().isAnnotationPresent(Transient.class)){
                continue;
            }
            if(component instanceof SerializableComponet){
                ((SerializableComponet) component).serialize();
            }
            components.add(component);
            
        }
        kryo.writeClassAndObject(output, components);

    }

    /**
     * reads an Entity Object from a saved file
     * @param kryo the Kryo reader
     * @param input the Kryo Input
     * @param type the type of class to read in this case Entity.class
     * @return
     */
    @Override
    public Entity read(Kryo kryo, Input input, Class<Entity> type) {
            Entity entity = new Entity();
            Array<Component> components= (Array<Component>) kryo.readClassAndObject(input);
            int size=components.size;
            for(int count=0; count<size; count++) {
                Component component=components.get(count);
                if(components.get(count) instanceof  SerializableComponet){
                    ((SerializableComponet) component).deSerialize(gameAssets);
                }
                entity.add(components.get(count));
            }
            return  entity;

    }
}
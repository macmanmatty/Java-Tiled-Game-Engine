package com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.TextureAtlas;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.Components.SerializableComponet;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.TransientChecker;

public class AtlasNamedAtlasRegionKryoSerializer extends Serializer<Entity> {

    private final GameAssets gameAssets;
    private final TransientChecker transientChecker = new TransientChecker();


    public AtlasNamedAtlasRegionKryoSerializer(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
    }

    @Override
    public void write(Kryo kryo, Output output, Entity entity) {

        Array<Component> components= new Array<>();
        for (Component component : entity.getComponents()) {

            if (transientChecker.isTransient(component.getClass())) {
                continue;
            }
            if(component instanceof SerializableComponet){
                ((SerializableComponet) component).serialize();
            }
            components.add(component);



        }
        kryo.writeClassAndObject(output, components);






    }

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

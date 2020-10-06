package com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.jessematty.black.tower.Components.SerializableComponet;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

/**
 * Handles serialization and deserialization of entities and their
 * components to and from Json.
 *
 * @author David Saltares
 */
public class LandSquareTileSerializer implements Json.Serializer<LandSquareTile> {
    private final TransientChecker transientChecker = new TransientChecker();
    private final  GameAssets gameAssets;

    public LandSquareTileSerializer(GameAssets assetts) {

        this.gameAssets=assetts;
    }



    @Override
    public void write(Json json, LandSquareTile entity, Class knownType) {
        json.writeObjectStart();
        json.writeObjectStart("components");
        for (Component component : entity.getComponents()) {

            if (transientChecker.isTransient(component.getClass())) {
                continue;
            }
            if(component instanceof SerializableComponet){
                ((SerializableComponet) component).serialize();
            }

            write(json, component);
        }

        json.writeObjectEnd();
        json.writeObjectEnd();

    }

    @Override
    public LandSquareTile  read(Json json, JsonValue jsonData, Class type) {
        try {
            LandSquareTile entity = new LandSquareTile();

            JsonValue components = jsonData.get("components");
            for (JsonValue componentValue : components) {

                Component component = read(json, componentValue);
                if(component instanceof SerializableComponet){
                    ((SerializableComponet) component).deSerialize(gameAssets);
                }
                entity.add(component);


            }

            return entity;
        } catch (ReflectionException e) {
            return null;
        }
    }

    private Component read(Json json, JsonValue componentValue) throws ReflectionException {
        String className = componentValue.name();
        Class componentType = TagResolver.getClass(json, className);
        Component component = createComponent(componentType);
        json.readFields(component, componentValue);
        return component;
    }

    private void write(Json json, Component component) {
        Class type = component.getClass();
        String tag = TagResolver.getTag(json, type);
        json.writeObjectStart(tag);
        json.writeFields(component);
        json.writeObjectEnd();
    }

    private Entity createEntity() {

            return new Entity();

    }

    public <T extends Component> T createComponent (Class<T> type) throws ReflectionException {

            return ClassReflection.newInstance(type);

    }
}

package com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.World;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jessematty.black.tower.Maps.LandMap;

/**
 * Handles serialization and deserialization of entities and their
 * components to and from Json.
 *
 * @author David Saltares
 */
public class LandMapSerializer implements Json.Serializer<LandMap> {


    @Override
    public void write(Json json, LandMap landMap, Class aClass) {

    }

    @Override
    public LandMap read(Json json, JsonValue jsonValue, Class aClass) {
        return null;
    }
}

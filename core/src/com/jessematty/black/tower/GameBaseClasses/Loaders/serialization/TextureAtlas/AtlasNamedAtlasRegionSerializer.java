package com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.TextureAtlas;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;

public class AtlasNamedAtlasRegionSerializer implements Json.Serializer<AtlasNamedAtlasRegion> { // serializer for the libgdx atlas region class
    GameAssets assetts;


    public AtlasNamedAtlasRegionSerializer(GameAssets assetts) {
        this.assetts = assetts;

    }

    @Override
    public void write(Json json, AtlasNamedAtlasRegion region, Class aClass) {
        json.writeObjectStart();
       json.writeField(region, "name", "name"); // write the json name
        json.writeField(region, "atlasName", "atlasName"); // write the atlas name
       json.writeObjectEnd();

    }

    @Override
    public AtlasNamedAtlasRegion read(Json json, JsonValue jsonValue, Class aClass) {

        String name=json.readValue("name", String.class, jsonValue); // read the texture regionName;
        String atlasName=json.readValue("atlasName", String.class, jsonValue); // read the atlas name


        return assetts.getAtlasRegionByName(name, atlasName); // get texture region from atlas

    }
}


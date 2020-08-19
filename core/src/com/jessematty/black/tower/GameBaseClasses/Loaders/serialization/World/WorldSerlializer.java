package com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.World;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Entity.EngineSerializer;
import com.jessematty.black.tower.Maps.World;

public class WorldSerlializer implements Json.Serializer<World> {
    GameAssets assetts;


    com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Entity.EngineSerializer engineSerializer;

    public WorldSerlializer(GameAssets assetts) {
        this.assetts = assetts;

        engineSerializer=new EngineSerializer();
    }

    @Override
    public void write(Json json, World world, Class aClass) {

    }

    @Override
    public World read(Json json, JsonValue jsonValue, Class aClass) {
        return null;
    }
}


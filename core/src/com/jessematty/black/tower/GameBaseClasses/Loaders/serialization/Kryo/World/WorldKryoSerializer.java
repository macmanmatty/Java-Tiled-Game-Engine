package com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Kryo.World;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.TransientChecker;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;

public class WorldKryoSerializer extends Serializer<World> {

    private final GameAssets gameAssets;
    private final TransientChecker transientChecker = new TransientChecker();


    public WorldKryoSerializer(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
    }

    @Override
    public void write(Kryo kryo, Output output, World world) {
        kryo.writeClassAndObject(output, world.getEntitiesInWorld());
        kryo.writeClassAndObject(output, world.getWorldMap());
            kryo.writeObject(output, world.getCurrentMapX());
        kryo.writeObject(output, world.getCurrentMapY());
        kryo.writeClassAndObject(output, world.getPlayer());








    }

    // reads World  Object using kryo
    @Override
    public World read(Kryo kryo, Input input, Class<World> type) {

            World world = new World();
        ObjectMap<String, Entity> entityObjectMap= (ObjectMap<String, Entity>) kryo.readClassAndObject(input);
        LandMap [] [] maps= (LandMap[][]) kryo.readClassAndObject(input);
        world.setWorldMap(maps);
        int mapX=kryo.readObject(input, Integer.class);
        int mapY=kryo.readObject(input, Integer.class);
        Entity player= (Entity) kryo.readClassAndObject(input);
        Values<Entity> entities=entityObjectMap.values();
        while (entities.hasNext){
            world.addEntityToWorld(entities.next());
        }
        world.setCurrentMap(mapX, mapY);
        world.setPlayer(player);
        gameAssets.finishLoading();

        return  world;





    }
}

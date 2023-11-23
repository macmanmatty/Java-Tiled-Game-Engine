package com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.World;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.Settings.WorldSettings;
import com.jessematty.black.tower.Maps.World;
/**
 * class for serializing / de-serializing a world object using kryo
 */
public class WorldKryoSerializer extends Serializer<World> {
    private final GameAssets gameAssets;
    public WorldKryoSerializer(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
    }
    /**
    writes the world object using kryo
     */
    @Override
    public void write(Kryo kryo, Output output, World world) {
        kryo.writeClassAndObject(output, world.getWorldSettings());
        kryo.writeClassAndObject(output, world.getWorldMaps());
       ObjectMap<String, Entity>  entities= world.getEntitiesInWorld();
        kryo.writeClassAndObject(output, entities);
        kryo.writeClassAndObject(output, world.getPlayer());
    }


    /**
     *  reads World  Object using kryo
      */
    @Override
    public World read(Kryo kryo, Input input, Class<World> type) {
            World world = new World();
        WorldSettings worldSettings= (WorldSettings) kryo.readClassAndObject(input);
        world.setWorldSettings(worldSettings);
        String assetsPath=(String) worldSettings.getSettings().get("textureAtlasPath");
        TextureAtlas  atlas=gameAssets.loadExternalTextureAtlas(assetsPath);
        gameAssets.setCurrentTextureAtlas(atlas);
        gameAssets.finishLoading();
        ObjectMap<String, GameMap> maps= (ObjectMap<String, GameMap>) kryo.readClassAndObject(input);
        world.setWorldMap(maps);
        ObjectMap<String, Entity> entityObjectMap= (ObjectMap<String, Entity>) kryo.readClassAndObject(input);
        Entity player= (Entity) kryo.readClassAndObject(input);
        Values<Entity> entities=entityObjectMap.values();
        while (entities.hasNext){
            world.addEntityToWorld(entities.next());
        }
        world.setCurrentMap((String)worldSettings.getSettings().get("currentMapId"));
        world.setPlayer(player);
        return  world;
    }
}

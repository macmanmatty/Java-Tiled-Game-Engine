package com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.World;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Maps.LandMap;
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
        kryo.writeClassAndObject(output, world.getWorldMaps());
        kryo.writeClassAndObject(output, world.getWorldSettings());
       ObjectMap<String, Entity>  entities= null;
        entities = removeUnserializableComponentsFromEntities(world);
        kryo.writeClassAndObject(output, entities);
        kryo.writeClassAndObject(output, world.getPlayer());
    }
    /**
     * removes all non serializable components from an entity
     * @param world the world object to get the entities from
     * @return a new ObjectMap of entities  that no longer contain  non-serializable components
     */
  
   private ObjectMap<String, Entity> removeUnserializableComponentsFromEntities(World world)  {
       ObjectMap<String, Entity> entityMap=world.getEntitiesInWorld();
       ObjectMap<String, Entity> newEntityMap= new ObjectMap<>();
       newEntityMap.putAll(entityMap);
       return newEntityMap;
    }
    /**
     *  reads World  Object using kryo
      */
    @Override
    public World read(Kryo kryo, Input input, Class<World> type) {
            World world = new World();
       ObjectMap<String, LandMap> maps= (ObjectMap<String, LandMap>) kryo.readClassAndObject(input);
        world.setWorldMap(maps);
        WorldSettings worldSettings= (WorldSettings) kryo.readClassAndObject(input);
        world.setWorldSettings(worldSettings);
        String assetsPath=(String) worldSettings.getSettings().get("textureAtlasPath");
           gameAssets.loadExternalTextureAtlas(assetsPath);
           gameAssets.finishLoading();
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

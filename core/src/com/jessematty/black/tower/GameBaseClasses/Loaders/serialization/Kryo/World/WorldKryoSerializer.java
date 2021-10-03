package com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Kryo.World;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.badlogic.gdx.utils.OrderedMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.Settings.WorldSettings;
import com.jessematty.black.tower.Maps.World;
public class WorldKryoSerializer extends Serializer<World> {
    private final GameAssets gameAssets;
    public WorldKryoSerializer(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
    }
    @Override
    public void write(Kryo kryo, Output output, World world) {
        kryo.writeClassAndObject(output, world.getWorldSettings());
        OrderedMap<String, Entity>  entities= null;
        try {
            entities = removeUnserializableComponentsFromEntities(world);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        kryo.writeClassAndObject(output, entities);
        kryo.writeClassAndObject(output, world.getWorldMap());
        kryo.writeClassAndObject(output, world.getPlayer());

    }
  
   public OrderedMap<String, Entity> removeUnserializableComponentsFromEntities(World world) throws IllegalAccessException, InstantiationException {
       OrderedMap<String, Entity> entityMap=world.getEntitiesInWorld();
       OrderedMap<String, Entity> newEntityMap= new OrderedMap<>();
       newEntityMap.putAll(entityMap);
       return newEntityMap;
    }
    // reads World  Object using kryo
    @Override
    public World read(Kryo kryo, Input input, Class<World> type) {
            World world = new World();
        WorldSettings worldSettings= (WorldSettings) kryo.readClassAndObject(input);
        world.setWorldSettings(worldSettings);
        String assetsPath=(String) worldSettings.getSettings().get("assetsPath");
           gameAssets.loadExternalTextureAtlas(assetsPath);

        OrderedMap<String, Entity> entityObjectMap= (OrderedMap<String, Entity>) kryo.readClassAndObject(input);
        LandMap [] [] maps= (LandMap[][]) kryo.readClassAndObject(input);
        world.setWorldMap(maps);
        Entity player= (Entity) kryo.readClassAndObject(input);
        Values<Entity> entities=entityObjectMap.values();
        while (entities.hasNext){
            world.addEntityToWorld(entities.next());
        }
        world.setCurrentMap((Integer)worldSettings.getSettings().get("currentMapX") , (Integer)worldSettings.getSettings().get("currentMapY"));
        world.setPlayer(player);
        gameAssets.finishLoading();
        return  world;
    }
}

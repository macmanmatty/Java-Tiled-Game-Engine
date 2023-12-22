package com.jessematty.black.tower.ZRPGTest;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.BodyParts.BodyComponent;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Other.SolidObject;
import com.jessematty.black.tower.Components.Position.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Editor.EditMode.TiledMapEdit.TiledMapConverter;
import com.jessematty.black.tower.Editor.Tools.MapTools.MapTools;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.JsonLoader;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.Entity.EntityLoadingException;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MapLoadingException;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.Generators.GameGenerator.TiledMapGameLoader;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCObjectGenerator;
import com.jessematty.black.tower.Generators.MapGenerators.LandMapGenerator;
import com.jessematty.black.tower.Generators.MapGenerators.LandMapSpecs;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.io.IOException;

public class TestMap {
    GameAssets assetts;
    public TestMap(GameAssets assets) {
        this.assetts = assets;
    }
    public void testMap(){
        JsonLoader jsonLoader= new JsonLoader();
        jsonLoader.writeObjectToFile(TestEntities.getAll(), "/android/assets/Entities/testEntities.json", false, FileUtilities.FileHandleType.LOCAL);
        TiledMap map =assetts.loadExternalTMXMap("/Users/jessematty/AndroidStudioProjects/Java-Tiled-Game-Engine2/android/assets/maps/testMap.tmx");
        TextureAtlas atlas= assetts.loadInternalTextureAtlas("textureAtlases/testAssets/testAssets.atlas");
        assetts.finishLoading();
        LandMapSpecs specs= (LandMapSpecs) assetts.getJsonLoader().loadInternalObject(LandMapSpecs.class, "maps/mapLandSpecs1.json");
        Skin skin=assetts.getDefaultSkin();
        LandMapGenerator generator2= new LandMapGenerator(assetts, specs);
        generator2.makeTiledMap();
        LandMap map2= (LandMap) generator2.makeMap();
        map2.setTileSize(32, 32);
        World world= new World();
        world.addMap(map2);
        world.setWorldTextureAtlas(atlas, "textureAtlases/testAssets/testAssets.atlas");

        LPCObjectGenerator lpcObjectGenerator = new LPCObjectGenerator(assetts);
        ObjectMap<String, EntityBag> entityBagObjectMap=lpcObjectGenerator.loadEntities("/android/assets/Entities/testEntities.json",false , FileUtilities.FileHandleType.LOCAL);
        EntityBag lizard= entityBagObjectMap.get("lizard");
        // Entity entity2=new CopyObject(assetts).copyObject(entity, Entity.class);
        //entity2.add(player);
        PositionComponent position =lizard.getOwner().getComponent(PositionComponent.class);
        position.setLocationX(66);
        position.setLocationY(1000);
        position.setMapID(map2.getId());
        position.setLocationX(66);
        position.setLocationY(600);
        position.setBounds(32, 48);
        position.setBoundsXOffset(16);
        position.setHeight(10);
        position.setMapID(map2.getId());
        MovableComponent movableComponent= lizard.getOwner().getComponent(MovableComponent.class);
        movableComponent.setCurrentSpeed(100000);
        NumericStats numericStats=lizard.getOwner().getComponent(NumericStats.class);
        numericStats.getNumericStat("speed").setMinValue(10);
        numericStats.getNumericStat("speed").setMaxValue(10000);
        numericStats.getNumericStat("speed").setValue(100);
        int size=lizard.getEntities().size;
        for(int count=0; count<size; count++){
            lizard.getEntities().get(count).getComponent(PositionComponent.class).setMapID(map2.getId());
        }
        world.addEntityToWorld(lizard);
      EntityBag tree= entityBagObjectMap.get("tree");
        tree.getOwner().add(new SolidObject());
       PositionComponent position2 =tree.getOwner().getComponent(PositionComponent.class);
        position2.setBounds(192, 320);
        position2.setHeight(100);
        position2.setLocationX(198);
        position2.setLocationY(195);
        position2.setMapID(map2.getId());
        position2.setMapID(map2.getId());
        EntityBag pack=entityBagObjectMap.get("pack");
        BodyComponent body=lizard.getOwner().getComponent(BodyComponent.class);
        String id=body.getBodyParts().get("torso");
        Entity torso=world.getEntity(id);

     String attached=   EntityUtilities.attachPart(torso, pack.getOwner());
        world.addEntityToWorld(pack.getOwner());


        world.setPlayer( lizard.getOwner());
       world.setWorldTextureAtlas(assetts.getAssetManager().get("textureAtlases/testAssets/testAssets.atlas", TextureAtlas.class),"textureAtlases/testAssets/testAssets.atlas");
        world.addEntityToWorld(tree);
        map2.setTiledMap(map);
        TiledMap  tiledMap=null;
        try {
       tiledMap = TiledMapConverter.convertToAtlasBasedTiledMap(map2.getTiledMap(), "tiledMap", world.getWorldTextureAtlas(), "textureAtlases/testAssets/testAssets.atlas");
         map2.setTiledMap(tiledMap);
        } catch (MapLoadingException mapLoadingException) {
            mapLoadingException.printStackTrace();
        }

        //Boolean hold= EntityUtilities.holdItem(world,  entityBag.getEntities().get(1), sword);
        for(int count = 33; count<map2.getXTiles(); count++){
            LandSquareTile landSquareTile= map2.getTile(count, 15);
            PhysicalObjectComponent physicalObjectComponent=new PhysicalObjectComponent();
            physicalObjectComponent.setMass(Float.MAX_VALUE);
            physicalObjectComponent.setVolume(100);
            landSquareTile.add(physicalObjectComponent);
            landSquareTile.getComponent(PositionComponent.class).setBounds(32, 64);
        }

        world.setStartMap(map2.getId());
        world.setName("game");
        LandMap map4= MapTools.newLandMap(9.8, "map", 20, 20, 32, 32);
        world.addMap(map4);
        map4.setTiledMap(tiledMap);

      try {
       assetts.saveGameWithAssets(world, "~/world", 2048, 2048);
       } catch (IOException e) {
        e.printStackTrace();
        }
       World newWorld=assetts.loadGame("~/world/game.bin");
       newWorld.getMap(map2.getId()).setSkin(assetts.getDefaultSkin());
       assetts.setWorld(newWorld);
        assetts.getMapDraw().setDrawEntityDebugBounds(true);
        assetts.showGame();
    }
    public void createMapByTMXFile(){
        TiledMap map =assetts.loadExternalTMXMap("/Users/jessematty/AndroidStudioProjects/Java-Tiled-Game-Engine2/android/assets/maps/testMap.tmx");
        assetts.loadInternalTextureAtlas("textureAtlases/testAssets/testAssets.atlas");
        assetts.finishLoading();
        JsonLoader jsonLoader= new JsonLoader();

        jsonLoader.writeObjectToFile(TestEntities.getAll(), "/android/assets/Entities/testEntitiesTmx.json", false, FileUtilities.FileHandleType.LOCAL);
        jsonLoader.writeObjectToFile(TestAnimations.potion,"/android/assets/Animations/potion.json", false,  FileUtilities.FileHandleType.LOCAL);
        jsonLoader.writeObjectToFile(TestAnimations.sword,"/android/assets/Animations/sword.json", false,  FileUtilities.FileHandleType.LOCAL );

        World world= new World();
        world.setWorldTextureAtlas(assetts.getAssetManager().get("textureAtlases/testAssets/testAssets.atlas", TextureAtlas.class),"textureAtlases/testAssets/testAssets.atlas");
        world.getWorldSettings().getSettings().put("textureAtlasPath", "textureAtlases/testAssets/testAssets.atlas");
        TiledMapGameLoader tiledMapLoader= new TiledMapGameLoader(assetts, world);

        try {
            Array<TiledMap> maps= new Array<>();
            maps.add(map);
           tiledMapLoader.createGameFromTmxMaps(maps, FileUtilities.FileHandleType.ABSOLUTE);
        } catch (MapLoadingException e) {
            throw new RuntimeException(e);
        } catch (EntityLoadingException e) {
            throw new RuntimeException(e);
        }
        //EntityBag pack=entityBagObjectMap.get("pack");
     EntityBag lizard=tiledMapLoader.getEntityBagArray().get("player");
        NumericStats numericStats=lizard.getOwner().getComponent(NumericStats.class);
        numericStats.getNumericStat("speed").setMinValue(10);
        numericStats.getNumericStat("speed").setMaxValue(10000);
        numericStats.getNumericStat("speed").setValue(100);
//        BodyComponent body=lizard.getOwner().getComponent(BodyComponent.class);
//        String id=body.getBodyParts().get("torso");
//        Entity torso=world.getEntity(id);

      //  String attached=   EntityUtilities.attachPart(torso, pack.getOwner());
       // world.addEntityToWorld(pack.getOwner());
        world.setPlayer( lizard.getOwner());

        world.setStartMap(world.getWorldMaps().values().toArray().get(0).getId());
        world.setName("game");

        try {
            assetts.saveGameWithAssets(world, "~/world", 2048, 2048);
        } catch (IOException e) {
            e.printStackTrace();
        }
        World newWorld=assetts.loadGame("~/world/game.bin");
        newWorld.getMap(world.getWorldMaps().values().toArray().get(0).getId());
        assetts.setWorld(newWorld);
        assetts.getMapDraw().setDrawEntityDebugBounds(true);
        assetts.showGame();
    }


}

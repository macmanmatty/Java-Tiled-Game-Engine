package com.jessematty.black.tower.ZRPGTest;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Other.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Other.SolidObject;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Editor.EditMode.TiledMapEdit.TiledMapTools;
import com.jessematty.black.tower.Editor.Tools.MapTools.MapTools;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.JsonLoader;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MapLoadingException;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCObjectGenerator;
import com.jessematty.black.tower.Generators.MapGenerators.LandMapGenerator;
import com.jessematty.black.tower.Generators.MapGenerators.LandMapSpecs;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.io.IOException;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class TestMap {
    GameAssets assetts;
    public TestMap(GameAssets assets) {
        this.assetts = assets;
    }
    public void testMap(){
        JsonLoader jsonLoader= new JsonLoader();
        jsonLoader.writeObjectToFile(TestEntities.getAll(), "/Users/jessematty/AndroidStudioProjects/Java-Tiled-Game-Engine2/android/assets/Entities/testEntities.json", false);

        //assetts.loadInternalTextureAtlas("swordWalk");
       TiledMap map =assetts.loadExternalTMXMap("/Users/jessematty/AndroidStudioProjects/Java-Tiled-Game-Engine2/android/assets/maps/testMap.tmx");
       //assetts.loadExternalTextureAtlas("/world/worldAssetts.atlas");
       //assetts.loadExternalTextureAtlas("/world/gameAssets.atlas");

        TextureAtlas atlas= assetts.loadInternalTextureAtlas("textureAtlases/testAssets/testAssets.atlas");
        assetts.finishLoading();
        LandMapSpecs specs= (LandMapSpecs) assetts.loadInternalObject("maps/mapLandSpecs1.json", LandMapSpecs.class);
        Skin skin=assetts.getDefaultSkin();
        LandMapGenerator generator2= new LandMapGenerator(assetts, specs);
        generator2.makeTiledMap();
        LandMap map2= (LandMap) generator2.makeMap();
        map2.setTileSize(32, 32);
        World world= new World();
        world.addMap(map2);
        world.setWorldTextureAtlas(atlas, "textureAtlases/testAssets/testAssets.atlas");
        LPCObjectGenerator lpcObjectGenerator = new LPCObjectGenerator(assetts, world);
        ObjectMap<String, EntityBag> entityBagObjectMap=lpcObjectGenerator.loadEntities("android/assets/Entities/testEntities.json",false, false );

        EntityBag entityBag= entityBagObjectMap.get("lizard");
        // Entity entity2=new CopyObject(assetts).copyObject(entity, Entity.class);
        //entity2.add(player);
        PositionComponent position =entityBag.getOwner().getComponent(PositionComponent.class);
        position.setLocationX(66);
        position.setLocationY(1000);
        position.setMapID(map2.getId());
        position.setLocationX(66);
        position.setLocationY(600);
        position.setBounds(32, 48);
        position.setBoundsXOffset(16);
        position.setHeight(10);
        position.setMapID(map2.getId());
        MovableComponent movableComponent= entityBag.getOwner().getComponent(MovableComponent.class);
        movableComponent.setCurrentSpeed(100000);
        NumericStats numericStats=entityBag.getOwner().getComponent(NumericStats.class);
        numericStats.getNumericStat("speed").setMinValue(10);
        numericStats.getNumericStat("speed").setMaxValue(10000);
        numericStats.getNumericStat("speed").setValue(100);

        int size=entityBag.getEntities().size;
        for(int count=0; count<size; count++){
            entityBag.getEntities().get(count).getComponent(PositionComponent.class).setMapID(map2.getId());
        }
        world.addEntityToWorld(entityBag);

      EntityBag entity1= entityBagObjectMap.get("tree");
        entity1.getOwner().add(new SolidObject());
       PositionComponent position2 =entity1.getOwner().getComponent(PositionComponent.class);
        position2.setBounds(192, 320);
        position2.setHeight(100);
        position2.setLocationX(198);
        position2.setLocationY(195);
        position2.setMapID(map2.getId());
        position2.setMapID(map2.getId());
        world.setPlayer( entityBag.getOwner());
       world.setWorldTextureAtlas(assetts.getAssetManager().get("textureAtlases/testAssets/testAssets.atlas", TextureAtlas.class),"textureAtlases/testAssets/testAssets.atlas");
        //assetts.setWorld(world);

//        Entity hood=lpcActorGenerator.generateArmor("assetts.atlas", "hoodClothMale", "name", "armor", true,  true, true, new Color(1,1,1,1), 1, 100,100,100,100,100,100,100,new NumericStatsChangable(), new BooleanStatsChangable());
        //map2.addEntity(hood);
         //Entity sword=lpcActorGenerator.generateMeeleWeapon("spearMale", "assets", "spear", "is a spear", false,  true, true, true, NamedColor.WHITE, 1, 100,100,100,80,10,true, 100,100,new NumericStatsChangeable(), new BooleanStatsChangeable());
    //    Entity wings=lpcActorGenerator.makeWings("assetts" , "wingsLizardMale", entity, "wings", entity.getComponent(Body.class), new NamedColor(.1f, 1, 0, 1),  1.1f, 4, 12, 12);
        world.addEntityToWorld(entity1);
       // world.addEntity(wings);
         //world.addEntityToWorld(sword);
        map2.setTiledMap(map);
        TiledMap  tiledMap=null;
        try {
       tiledMap =TiledMapTools.convertToAtlasBasedTiledMap(map2.getTiledMap(), "tiledMap", world.getWorldTextureAtlas(), "textureAtlases/testAssets/testAssets.atlas");
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

}

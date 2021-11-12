package com.jessematty.black.tower.GameBaseClasses;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Editor.Tools.MapTools.TiledMapTools;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MapLoadingExeception;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCActorGeneratorLPC;
import com.jessematty.black.tower.Generators.MapGenerators.LandMapGenerator;
import com.jessematty.black.tower.Generators.MapGenerators.LandMapSpecs;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
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
        
        //assetts.loadInternalTextureAtlas("swordWalk");
       TiledMap map =assetts.loadExternalTMXMap("/testMap.tmx");
       assetts.loadExternalTextureAtlas("/world/worldAssetts.atlas");

        assetts.finishLoading();
        LandMapSpecs specs= (LandMapSpecs) assetts.loadObject("/Users/jessematty/AndroidStudioProjects/BlackTowerHTML/android/assets/maps/mapLandSpecs1.json", LandMapSpecs.class);
        Skin skin=assetts.getDefaultSkin();
        LandMapGenerator generator2= new LandMapGenerator(assetts, specs);
        generator2.makeTiledMap();
        LandMap map2= (LandMap) generator2.makeMap();
        map2.setTileSize(32, 32);


        World world= new World(1, 1);
        world.placeMap(map2, 0, 0);
        world.setLoadPath("/world/");
        LPCActorGeneratorLPC lpcActorGenerator= new LPCActorGeneratorLPC(assetts, world);
        EntityBag entityBag=lpcActorGenerator.generateLPCCharacter( "/world/worldAssetts.atlas", "lizardMale", "lizard", "lizard", .67f,new NamedColor(0, 1, .1f, 1),100, 100,32,64,100, 100,100,100,100,100,100,100,true,true);
        // Entity entity2=new CopyObject(assetts).copyObject(entity, Entity.class);
        //entity2.add(player);
        PositionComponent position =entityBag.getEntities().get(0).getComponent(PositionComponent.class);
        position.setLocationX(66);
        position.setLocationY(1000);
        position.setMapWorldLocationX(0);
        position.setMapWorldLocationY(0);
        position.setLocationX(66);
        position.setLocationY(600);
        position.setBounds(32, 48);
        position.setBoundsXOffset(16);
        position.setHeight(10);
        entityBag.getEntities().get(0).getComponent(NumericStats.class).getNumericStats().get("speed").setMaxValue(1000);
        entityBag.getEntities().get(0).getComponent(NumericStats.class).getNumericStats().get("speed").setValue(1000);
        world.addEntityToWorld(entityBag);
        Entity entity1= lpcActorGenerator.generateObject("/world/worldAssetts.atlas", "tree114", "tree");
       PositionComponent position2 =entity1.getComponent(PositionComponent.class);
        position2.setBounds(200, 500);
        position2.setHeight(10);
        position2.setLocationX(66);
        position2.setLocationY(900);
        position2.setMapWorldLocationX(map2.getWorldX());
        position2.setMapWorldLocationY(map2.getWorldY());
        world.setPlayer( entityBag.getEntities().get(0));
       world.setWorldTextureAtlas(assetts.getTextureAtlas("/world/worldAssetts.atlas"),"/world/worldAssetts.atlas");
        assetts.setWorld(world);

//        Entity hood=lpcActorGenerator.generateArmor("assetts.atlas", "hoodClothMale", "name", "armor", true,  true, true, new Color(1,1,1,1), 1, 100,100,100,100,100,100,100,new NumericStatsChangable(), new BooleanStatsChangable());
        //map2.addEntity(hood);
         //Entity sword=lpcActorGenerator.generateMeeleWeapon("spearMale", "assets", "spear", "is a spear", false,  true, true, true, NamedColor.WHITE, 1, 100,100,100,80,10,true, 100,100,new NumericStatsChangeable(), new BooleanStatsChangeable());
    //    Entity wings=lpcActorGenerator.makeWings("assetts" , "wingsLizardMale", entity, "wings", entity.getComponent(Body.class), new NamedColor(.1f, 1, 0, 1),  1.1f, 4, 12, 12);
        world.addEntityToWorld(entity1);
       // world.addEntity(wings);
         //world.addEntityToWorld(sword);
        map2.setTiledMap(map);

        try {
         map=  new TiledMapTools(world.getWorldTextureAtlas(), "/world/worldAssetts.atlas").convertToAtlasBasedTiledMap(map2.getTiledMap(), "tiledMap");
         map2.setTiledMap(map);
        } catch (MapLoadingExeception mapLoadingExeception) {
            mapLoadingExeception.printStackTrace();
        }

        //Boolean hold= EntityUtilities.holdItem(world,  entityBag.getEntities().get(1), sword);
        for(int count = 0; count<map2.getXTiles(); count++){
            LandSquareTile landSquareTile= map2.getMapSquare(count, 10);
            PhysicalObjectComponent physicalObjectComponent=new PhysicalObjectComponent();
            physicalObjectComponent.setMass(Float.MAX_VALUE);
            physicalObjectComponent.setVolume(100);
            landSquareTile.add(physicalObjectComponent);
            landSquareTile.getComponent(PositionComponent.class).setBounds(32, 32);
        }
        world.setStartMap(0, 0);

      try {
           assetts.saveGameWithAssets(world, "/world","worldAssetts", 2048, 2048);
        } catch (IOException e) {
          e.printStackTrace();
        }
       world.setLoadPath("/world/");
           World newWorld=assetts.loadGame("/world/game.bin");
            newWorld.getMap(0, 0).setSkin(assetts.getDefaultSkin());
          assetts.setWorld(newWorld);
        assetts.getMapDraw().setDrawEntityDebugBounds(true);
        assetts.showGame();
    }





}

package com.jessematty.black.tower.GameBaseClasses;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.SolidObject;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Editor.EditMode.TiledMapEdit.TiledMapTools;
import com.jessematty.black.tower.Editor.Tools.MapTools.MapTools;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MapLoadingException;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCActorGeneratorLPC;
import com.jessematty.black.tower.Generators.MapGenerators.LandMapGenerator;
import com.jessematty.black.tower.Generators.MapGenerators.LandMapSpecs;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
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
    public void testMap() {

        //assetts.loadInternalTextureAtlas("swordWalk");
        TiledMap map = assetts.loadExternalTMXMap("/testMap.tmx");
        // assetts.loadExternalTextureAtlas("/world/worldAssetts.atlas");
        // TextureAtlas atlas=   assetts.loadExternalTextureAtlas("/world/gameAssets.atlas");
        TextureAtlas atlas = assetts.loadInternalTextureAtlas("testAssets");
        assetts.finishLoading();
        LandMapSpecs specs = (LandMapSpecs) assetts.loadObject("/Users/jessematty/AndroidStudioProjects/BlackTowerHTML/android/assets/maps/mapLandSpecs1.json", LandMapSpecs.class);
        LandMapGenerator generator2 = new LandMapGenerator(assetts, specs);
        generator2.makeTiledMap();
        LandMap gameMap = (LandMap) generator2.makeMap();
        gameMap.setTileSize(32, 32);
        World world = new World();
        world.addMap(gameMap);
        world.setWorldTextureAtlas(atlas, "/textureAtlases/TestAssets.atlas");
        addActorToMap(map, world, gameMap);
    }
    public void addActorToMap(TiledMap map, World world, GameMap gameMap) {
        LPCActorGeneratorLPC lpcActorGenerator = new LPCActorGeneratorLPC(assetts, world);
        EntityBag entityBag = lpcActorGenerator.generateLPCCharacter("/world/worldAssetts.atlas", "lizardMale", "lizard", "lizard", .67f, new NamedColor(0, 1, .1f, 1), 100, 100, 32, 64, 100, 100, 100, 100, 100, 100, 100, 100, true, true);
        // Entity entity2=new CopyObject(assetts).copyObject(entity, Entity.class);
        //entity2.add(player);
        PositionComponent position = entityBag.getEntities().get(0).getComponent(PositionComponent.class);
        position.setLocationX(66);
        position.setLocationY(1000);
        position.setMapID(gameMap.getId());
        position.setLocationX(66);
        position.setLocationY(600);
        position.setBounds(32, 48);
        position.setBoundsXOffset(16);
        position.setHeight(10);
        position.setMapID(gameMap.getId());
        entityBag.getEntities().get(0).getComponent(NumericStats.class).getNumericStats().get("speed").setMaxValue(70);
        entityBag.getEntities().get(0).getComponent(NumericStats.class).getNumericStats().get("speed").setValue(70);
        int size = entityBag.getEntities().size;
        for (int count = 0; count < size; count++) {
            entityBag.getEntities().get(count).getComponent(PositionComponent.class).setMapID(gameMap.getId());
        }
        world.addEntityToWorld(entityBag);

        Entity entity1 = lpcActorGenerator.generateObject("/world/worldAssetts.atlas", "tree114", "tree");
        entity1.add(new SolidObject());
        PositionComponent position2 = entity1.getComponent(PositionComponent.class);
        position2.setBounds(200, 300);
        position2.setHeight(10);
        position2.setLocationX(200);
        position2.setLocationY(200);
        position2.setMapID(gameMap.getId());
        position2.setMapID(gameMap.getId());
        world.setPlayer(entityBag.getEntities().get(0));
        world.setWorldTextureAtlas(assetts.getAssetManager().get("/world/worldAssetts.atlas", TextureAtlas.class), "/world/worldAssetts.atlas");


//        Entity hood=lpcActorGenerator.generateArmor("assetts.atlas", "hoodClothMale", "name", "armor", true,  true, true, new Color(1,1,1,1), 1, 100,100,100,100,100,100,100,new NumericStatsChangable(), new BooleanStatsChangable());
        //map2.addEntity(hood);
        //Entity sword=lpcActorGenerator.generateMeeleWeapon("spearMale", "assets", "spear", "is a spear", false,  true, true, true, NamedColor.WHITE, 1, 100,100,100,80,10,true, 100,100,new NumericStatsChangeable(), new BooleanStatsChangeable());
        //    Entity wings=lpcActorGenerator.makeWings("assetts" , "wingsLizardMale", entity, "wings", entity.getComponent(Body.class), new NamedColor(.1f, 1, 0, 1),  1.1f, 4, 12, 12);
        world.addEntityToWorld(entity1);
        // world.addEntity(wings);
        //world.addEntityToWorld(sword);
        gameMap.setTiledMap(map);
        TiledMap tiledMap = null;
        try {
            tiledMap = TiledMapTools.convertToAtlasBasedTiledMap(gameMap.getTiledMap(), "tiledMap", world.getWorldTextureAtlas(), "/world/worldAssetts.atlas");
            gameMap.setTiledMap(tiledMap);
        } catch (MapLoadingException mapLoadingException) {
            mapLoadingException.printStackTrace();
        }

        //Boolean hold= EntityUtilities.holdItem(world,  entityBag.getEntities().get(1), sword);
        for (int count = 0; count < gameMap.getXTiles(); count++) {
            LandSquareTile landSquareTile = gameMap.getTile(count, 10);
            PhysicalObjectComponent physicalObjectComponent = new PhysicalObjectComponent();
            physicalObjectComponent.setMass(Float.MAX_VALUE);
            physicalObjectComponent.setVolume(100);
            landSquareTile.add(physicalObjectComponent);
            landSquareTile.getComponent(PositionComponent.class).setBounds(32, 32);
        }
        world.setStartMap(gameMap.getId());
        world.setName("game");
        LandMap map4 = MapTools.newLandMap(9.8, "map", 20, 20, 32, 32);
        world.addMap(map4);
        map4.setTiledMap(tiledMap);

        saveWorld(world);

    }

    public void saveWorld(World world) {

      try {
       assetts.saveGameWithAssets(world, "/world", 2048, 2048);
       } catch (IOException e) {
        e.printStackTrace();
        }
       World newWorld=assetts.loadGame("/world/game.bin");
       assetts.setWorld(newWorld);
        assetts.getMapDraw().setDrawEntityDebugBounds(true);
        assetts.showGame();
    }

}

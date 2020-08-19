package com.jessematty.black.tower.GameBaseClasses;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Components.BodyParts.Body;
import com.jessematty.black.tower.Components.Holder;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.OwnedComponent;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.SelfChangableNumericStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangingStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsSelfChangable;
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.GameBaseClasses.Generators.Entity.LPCGenerator.LPCActorGenerator;
import com.jessematty.black.tower.GameBaseClasses.Generators.MapGenerators.LandMapGenerator;
import com.jessematty.black.tower.GameBaseClasses.Generators.MapGenerators.LandMapSpecs;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;

public class TestMap {


    GameAssets assetts;

    public TestMap(GameAssets assets) {
        this.assetts = assets;
    }

    public void testMap(){




        assetts.loadInternalTextureAtlas("swordWalk");

        assetts.finishLoading();

        LandMapSpecs specs= (LandMapSpecs) assetts.loadObject("/Users/jessematty/AndroidStudioProjects/BlackTowerHTML/android/assets/maps/mapLandSpecs1.json", LandMapSpecs.class);


        System.out.println(specs);







        Skin skin=assetts.getDefaultSkin();


        LandMap map;







        LandMapGenerator generator= new LandMapGenerator(assetts, specs);
        generator.makeTiledMap();

        map= (LandMap) generator.makeMap();
        LandMapGenerator generator2= new LandMapGenerator(assetts, specs);

        generator2.makeTiledMap();
        LandMap map2= (LandMap) generator2.makeMap();
        map.setMapName("First Map");
        map2.setMapName("second Map");



        int xSize=map.getXSize();
        int ySize=map.getYSize()-1;

        World world= new World(1, 1);
        world.placeMap(map2, 0, 0);
        world.setLoadPath("/world/");




        LPCActorGenerator lpcActorGenerator= new LPCActorGenerator( world, assetts);
        Entity entity=lpcActorGenerator.generateLPCCharacter( "assetts", "lizardMale", "lizard", "lizard", .67f,new NamedColor(0, 1, .1f, 1),100, 100,32,64,100, 100,100,100,100,100,100,100,true,true);

        assetts.setWorld(world);


        // Entity entity2=new CopyObject(assetts).copyObject(entity, Entity.class);


        ZRPGPlayer player= new ZRPGPlayer(world, entity);
        //entity2.add(player);
        Position position =entity.getComponent(Position.class);
        position.setScreenLocationX(66);
        position.setScreenLocationY(1000);
        position.setMapWorldLocationX(map2.getWorldX());
        position.setMapWorldLocationY(map2.getWorldY());

        position.setScreenLocationX(66);
        position.setScreenLocationY(1000);
        position.setBounds(32, 48);
        position.setBoundsXOffset(16);
        position.setHeight(10);









        Entity entity1= lpcActorGenerator.generateObject("assetts", "tree114");

        position =entity1.getComponent(Position.class);
        position.setScreenLocationX(66);
        position.setScreenLocationY(50);
        position.setBounds(100, 100);
        position.setHeight(10);


        position.setScreenLocationX(66);
        position.setScreenLocationY(50);

        position.setMapWorldLocationX(map2.getWorldX());
        position.setMapWorldLocationY(map2.getWorldY());




//        Entity hood=lpcActorGenerator.generateArmor("assetts.atlas", "hoodClothMale", "name", "armor", true,  true, true, new Color(1,1,1,1), 1, 100,100,100,100,100,100,100,new NumericStatsChangable(), new BooleanStatsChangable());

        //map2.addEntity(hood);


         Entity sword=lpcActorGenerator.generateMeeleWeapon("longSword", "swordWalk", "name", "armor", false,  false, true, NamedColor.WHITE, 1, 100,100,100,100,100,true, 100,100,new NumericStatsChangable(), new BooleanStatsChangable());
       Holder holder=  new Holder();
       entity.add(holder);


       sword.add(new Position());
       sword.add(new OwnedComponent(entity.getComponent(ID.class).getId(), true, true, true));







        assetts.showGame(player);
        assetts.getMapDraw().setDrawEntityDebugBounds(true);


    //    Entity wings=lpcActorGenerator.makeWings("assetts" , "wingsLizardMale", entity, "wings", entity.getComponent(Body.class), new NamedColor(.1f, 1, 0, 1),  1.1f, 4, 12, 12);
        world.addEntity(entity);
       // world.addEntity(wings);
         world.addEntity(sword);
         holder.setItemToHoldId(sword.getComponent(ID.class).getId());


















    }

}

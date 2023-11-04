package com.jessematty.black.tower.GameBaseClasses.ZRPGTest;


import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCObjectGeneratorDTO;

public class TestEntities {

    static LPCObjectGeneratorDTO lizard = new LPCObjectGeneratorDTO();
    static LPCObjectGeneratorDTO tree = new LPCObjectGeneratorDTO();
    static LPCObjectGeneratorDTO pack = new LPCObjectGeneratorDTO();
    static LPCObjectGeneratorDTO sword= new LPCObjectGeneratorDTO();

    static LPCObjectGeneratorDTO spear= new LPCObjectGeneratorDTO();

    static LPCObjectGeneratorDTO apple= new LPCObjectGeneratorDTO();


    static LPCObjectGeneratorDTO potion= new LPCObjectGeneratorDTO();

    static LPCObjectGeneratorDTO potion2= new LPCObjectGeneratorDTO();


    static LPCObjectGeneratorDTO scroll= new LPCObjectGeneratorDTO();

    static LPCObjectGeneratorDTO bow= new LPCObjectGeneratorDTO();

    static LPCObjectGeneratorDTO shovel= new LPCObjectGeneratorDTO();

    static LPCObjectGeneratorDTO axe= new LPCObjectGeneratorDTO();

   static NumericStat health= new NumericStat(true, "health", 100, 0, 100);

      static NumericStat condition= new NumericStat(true, "condition", 100, 0, 100);

  static BooleanStat on= new BooleanStat(true, "on", true);
    static StringStat classuz= new StringStat(true, "class", "normal");





    static  {
        health.getChangeGroups().add("all");
        lizard.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        lizard.setBody("lizard");
        lizard.setSex("Male");
        lizard.setName("lizard");
        lizard.setBrightness( .67f);
        lizard.setColorA(1);
        lizard.setColorB(.1f);
        lizard.setColorR(0);
        lizard.setColorG(1);
        lizard.setBoundsX(32);
        lizard.setBoundsY(64);
        lizard.setHealth(100);
        lizard.setMass(100);
        lizard.setVolume(100);
        lizard.setMoveable(true);
        lizard.setAnimated(true);
        lizard.setDrawable(true);
        lizard.getStats().add(health, on, classuz);
        tree.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        tree.setImageName("tree");
        tree.setBody("tree");
        tree.setHasImage(true);
        tree.setDrawable(true);
        tree.setDrawOnStart(true);
        tree.getStats().add(health, on, classuz);


        pack.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        pack.setBody("backPack");
        pack.setName("pack");
        pack.setBrightness( .67f);
        pack.setColorA(1);
        pack.setColorB(.1f);
        pack.setColorR(1);
        pack.setColorG(0);
        pack.setBoundsX(32);
        pack.setBoundsY(64);
        pack.setHealth(100);
        pack.setMass(100);
        pack.setVolume(100);
        pack.setAnimated(true);
        pack.setDrawable(true);
        pack.getStats().add(condition, on, classuz);




        apple.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        apple.setName("apple");
        apple.setImageName("apple1");
        apple.setAnimated(false);
        apple.setBrightness( .67f);
        apple.setColorA(1);
        apple.setColorB(.1f);
        apple.setColorR(1);
        apple.setColorG(0);
        apple.setBoundsX(32);
        apple.setBoundsY(64);
        apple.setHealth(100);
        apple.setMass(100);
        apple.setVolume(100);
        apple.setAnimated(true);
        apple.setDrawable(true);

        potion.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        potion.setName("potion");
        potion.setImageName("potion1");
        potion.setAnimated(false);
        potion.setBrightness( .67f);
        potion.setColorA(1);
        potion.setColorB(.1f);
        potion.setColorR(1);
        potion.setColorG(0);
        potion.setBoundsX(32);
        potion.setBoundsY(64);
        potion.setHealth(100);
        potion.setMass(100);
        potion.setVolume(100);
        potion.setAnimated(true);
        potion.setDrawable(true);
        pack.getStats().add(condition,health,  on, classuz);



        scroll.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        scroll.setName("scroll");
        scroll.setImageName("scroll1");
        scroll.setAnimated(false);
        scroll.setBrightness( .67f);
        scroll.setColorA(1);
        scroll.setColorB(.1f);
        scroll.setColorR(1);
        scroll.setColorG(0);
        scroll.setBoundsX(32);
        scroll.setBoundsY(64);
        scroll.setHealth(100);
        scroll.setMass(100);
        scroll.setVolume(100);
        scroll.setAnimated(true);
        scroll.setDrawable(true);
        scroll.getStats().add(condition, on, classuz);



        sword.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        sword.setBody("swordMale");
        sword.setName("sword");
        sword.setImageName("sword1");
        sword.setBrightness( .67f);
        sword.setColorA(1);
        sword.setColorB(.1f);
        sword.setColorR(1);
        sword.setColorG(0);
        sword.setBoundsX(32);
        sword.setBoundsY(64);
        sword.setHealth(100);
        sword.setMass(100);
        sword.setVolume(100);
        sword.setAnimated(true);
        sword.setDrawable(true);


        spear.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        spear.setBody("spear");
        spear.setName("spear");
        spear.setImageName("spear1");
        spear.setBrightness( .67f);
        spear.setColorA(1);
        spear.setColorB(.1f);
        spear.setColorR(1);
        spear.setColorG(0);
        spear.setBoundsX(32);
        spear.setHasEatFrames(false);
        spear.setHasShootFrames(false);
        spear.setHasSpellCastFrames(false);
        spear.setHasWalkFrames(true);
        spear.setHasThrustFrames(true);
        spear.setHashSlashFrames(true);
        spear.setHasDieFrames(false);
        spear.setHasPickupFrames(false);
        spear.setHasShootFrames(false);
        spear.setBoundsY(64);
        spear.setHealth(100);
        spear.setMass(100);
        spear.setVolume(100);
        spear.setAnimated(true);
        spear.setDrawable(true);


        axe.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        axe.setBody("axe");
        axe.setName("axe");
        axe.setImageName("axe1");
        axe.setBrightness( .67f);
        axe.setColorA(1);
        axe.setColorB(.1f);
        axe.setColorR(1);
        axe.setColorG(0);
        axe.setBoundsX(32);
        axe.setBoundsY(64);
        axe.setHealth(100);
        axe.setMass(100);
        axe.setVolume(100);
        axe.setAnimated(true);
        axe.setDrawable(true);


        shovel.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        shovel.setBody("shovel");
        shovel.setName("shovel");
        shovel.setImageName("shovel1");
        shovel.setBrightness( .67f);
        shovel.setColorA(1);
        shovel.setColorB(.1f);
        shovel.setColorR(1);
        shovel.setColorG(0);
        shovel.setBoundsX(32);
        shovel.setBoundsY(64);
        shovel.setHealth(100);
        shovel.setMass(100);
        shovel.setVolume(100);
        shovel.setAnimated(true);
        shovel.setDrawable(true);



        bow.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        bow.setBody("bow");
        bow.setName("bow");
        bow.setImageName("bow1");
        bow.setBrightness( .67f);
        bow.setColorA(1);
        bow.setColorB(.1f);
        bow.setColorR(1);
        bow.setColorG(0);
        bow.setBoundsX(32);
        bow.setBoundsY(64);
        bow.setHealth(100);
        bow.setMass(100);
        bow.setVolume(100);
        bow.setAnimated(true);
        bow.setDrawable(true);


    }

    public TestEntities() {


    }

    public static LPCObjectGeneratorDTO getLizard() {
        return lizard;
    }

    public static Array<LPCObjectGeneratorDTO> getAll(){
        Array<LPCObjectGeneratorDTO> lpcObjectGeneratorDTOS= new Array<>( );
        lpcObjectGeneratorDTOS.add(bow);
        lpcObjectGeneratorDTOS.add(lizard);
        lpcObjectGeneratorDTOS.add(scroll);
        lpcObjectGeneratorDTOS.add(shovel);
        lpcObjectGeneratorDTOS.add(sword);
        lpcObjectGeneratorDTOS.add(spear);
        lpcObjectGeneratorDTOS.add(apple);
        lpcObjectGeneratorDTOS.add(potion);
        lpcObjectGeneratorDTOS.add(pack);
        lpcObjectGeneratorDTOS.add(tree);
        lpcObjectGeneratorDTOS.add(axe);



                  return  lpcObjectGeneratorDTOS;
    }

}

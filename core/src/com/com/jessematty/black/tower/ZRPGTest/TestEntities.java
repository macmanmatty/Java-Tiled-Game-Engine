package com.jessematty.black.tower.ZRPGTest;


import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCObjectGeneratorDTO;

public class TestEntities {

    static LPCObjectGeneratorDTO lizard = new LPCObjectGeneratorDTO();

    static LPCObjectGeneratorDTO leftHand = new LPCObjectGeneratorDTO();
    static LPCObjectGeneratorDTO rightHand = new LPCObjectGeneratorDTO();

    static LPCObjectGeneratorDTO leftFoot = new LPCObjectGeneratorDTO();

    static LPCObjectGeneratorDTO rightFoot = new LPCObjectGeneratorDTO();

    static LPCObjectGeneratorDTO torso= new LPCObjectGeneratorDTO();

    static LPCObjectGeneratorDTO head = new LPCObjectGeneratorDTO();




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

    static LPCObjectGeneratorDTO tail= new LPCObjectGeneratorDTO();

    static LPCObjectGeneratorDTO axe= new LPCObjectGeneratorDTO();

   static NumericStat health= new NumericStat(true, "health", 100, 0, 100);
    static NumericStat price= new NumericStat(true, "health", 100, 0, 100);
    static NumericStat condition= new NumericStat(true, "condition", 100, 0, 100);
    static BooleanStat on= new BooleanStat(true, "on", true);
    static StringStat classuz= new StringStat(true, "class", "lizard");


    static  {

        health.getChangeGroups().add("all");
        lizard.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        lizard.setAnimatableBodyName("lizard");
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
        lizard.setMass(.0001f);
        lizard.setVolume(100);
        lizard.setMoveable(true);
        lizard.setAnimated(true);
        lizard.setLpcActorAnimated(true);
        lizard.setDrawable(true);
        lizard.getStats().add(health, on, classuz);
        lizard.getAttachedEntities().addAll("1", "2", "3", "4","5", "6");
        lizard.getAttachedEntityDTOs().addAll(leftFoot, rightFoot, rightHand, leftHand, head, torso, tail);
        lizard.setBody(true);
        lizard.getAttachableParts().addAll("leftHand", "rightHand", "leftFoot", "rightFoot", "torso", "head", "wings", "tail", "horns");

        tail.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        tail.setAnimatableBodyName("wingsLizard");
        tail.setSex("Male");
        tail.setName("wing");
        tail.setBrightness( .67f);
        tail.setColorA(1);
        tail.setColorB(.1f);
        tail.setColorR(0);
        tail.setColorG(1);
        tail.setBoundsX(0);
        tail.setBoundsY(0);
        tail.setHealth(100);
        tail.setMass(10);
        tail.setVolume(100);
        tail.setMoveable(true);
        tail.setAnimated(true);
        tail.setLpcActorAnimated(true);
        tail.setDrawable(true);
        tail.getStats().add(health, on, classuz);
        tail.getAttachedEntities().addAll("1", "2", "3", "4","5", "6");
        tail.getAttachedEntityDTOs().addAll(leftFoot, rightFoot, rightHand, leftHand, head, torso);
        tail.setBody(true);
        tail.setUpLayerOffset(-1);
        tail.setDownLayerOffset(1);
        tail.setRightLayerOffset(1);
        tail.setLeftLayerOffset(1);
        tail.getAttachableParts().addAll("ring");



        leftHand.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        leftHand.setAnimatableBodyName("leftHand");
        leftHand.setSex("Male");
        leftHand.setName("leftHand");
        leftHand.setBrightness( .67f);
        leftHand.setColorA(1);
        leftHand.setColorB(.1f);
        leftHand.setColorR(0);
        leftHand.setColorG(1);
        leftHand.setBoundsX(0);
        leftHand.setBoundsY(0);
        leftHand.setHealth(100);
        leftHand.setMass(1);
        leftHand.setVolume(1);
        leftHand.setMoveable(true);
        leftHand.setAnimated(false);
        leftHand.setDrawable(false);
        leftHand.getStats().add(health, on, classuz);
        leftHand.setPart(true);
        leftHand.setHolder(true);
        leftHand.setHumanLikeCharacter(true);
        leftHand.setTmxObjectId("1");
        leftHand.setPart(true);
        leftHand.setPartClass("leftHand");
        leftHand.setBody(true);
        leftHand.getAttachableParts().addAll("ring", "glove", "bracelet");




        rightHand.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        rightHand.setAnimatableBodyName("rightHand");
        rightHand.setSex("Male");
        rightHand.setName("rightHand");
        rightHand.setBrightness( .67f);
        rightHand.setColorA(1);
        rightHand.setColorB(.1f);
        rightHand.setColorR(0);
        rightHand.setColorG(1);
        rightHand.setBoundsX(0);
        rightHand.setBoundsY(0);
        rightHand.setHealth(100);
        rightHand.setMass(1);
        rightHand.setVolume(1);
        rightHand.setMoveable(true);
        rightHand.setAnimated(false);
        rightHand.setDrawable(false);
        rightHand.getStats().add(health, on, classuz);
        rightHand.setHolder(true);
        rightHand.setPart(true);
        rightHand.setHumanLikeCharacter(true);
        rightHand.setTmxObjectId("2");
        rightHand.setPart(true);
        rightHand.setPartClass("rightHand");
        rightHand.setBody(true);
        rightHand.getAttachableParts().addAll("ring", "glove", "bracelet");



        rightFoot.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        rightFoot.setAnimatableBodyName("rightFoot");
        rightFoot.setSex("Male");
        rightFoot.setName("rightFoot");
        rightFoot.setBrightness( .67f);
        rightFoot.setColorA(1);
        rightFoot.setColorB(.1f);
        rightFoot.setColorR(0);
        rightFoot.setColorG(1);
        rightFoot.setBoundsX(0);
        rightFoot.setBoundsY(0);
        rightFoot.setHealth(100);
        rightFoot.setMass(1);
        rightFoot.setVolume(1);
        rightFoot.setMoveable(true);
        rightFoot.setAnimated(false);
        rightFoot.setDrawable(false);
        rightFoot.getStats().add(health, on, classuz);
        rightFoot.setHolder(true);
        rightFoot.setPart(true);
        rightFoot.setHumanLikeCharacter(true);
        rightFoot.setTmxObjectId("3");
        rightFoot.setPart(true);
        rightFoot.setPartClass("rightFoot");
        rightFoot.setBody(true);
        rightFoot.getAttachableParts().addAll("ring", "shoe", "bracelet");





        leftFoot.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        leftFoot.setAnimatableBodyName("leftFoot");
        leftFoot.setSex("Male");
        leftFoot.setName("leftFoot");
        leftFoot.setBrightness( .67f);
        leftFoot.setColorA(1);
        leftFoot.setColorB(.1f);
        leftFoot.setColorR(0);
        leftFoot.setColorG(1);
        leftFoot.setBoundsX(0);
        leftFoot.setBoundsY(0);
        leftFoot.setHealth(100);
        leftFoot.setMass(1);
        leftFoot.setVolume(1);
        leftFoot.setMoveable(true);
        leftFoot.setAnimated(false);
        leftFoot.setDrawable(false);
        leftFoot.getStats().add(health, on, classuz);
        leftFoot.setHolder(true);
        leftFoot.setPart(true);
        leftFoot.setPart(true);
        leftFoot.setPartClass("leftFoot");
        leftFoot.setBody(true);
        leftFoot.getAttachableParts().addAll("ring", "shoe", "bracelet");




        torso.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        torso.setAnimatableBodyName("torso");
        torso.setSex("Male");
        torso.setName("torso");
        torso.setBrightness( .67f);
        torso.setColorA(1);
        torso.setColorB(.1f);
        torso.setColorR(0);
        torso.setColorG(1);
        torso.setBoundsX(0);
        torso.setBoundsY(0);
        torso.setHealth(100);
        torso.setMass(1);
        torso.setVolume(1);
        torso.setMoveable(true);
        torso.setAnimated(false);
        torso.setDrawable(false);
        torso.getStats().add(health, on, classuz);
        torso.setHolder(true);
        torso.setPart(true);
        torso.setHumanLikeCharacter(true);
        torso.setTmxObjectId("5");
        torso.setPart(true);
        torso.setBody(true);
        torso.getAttachableParts().addAll("armor", "shirt", "belt", "pants", "vest", "backPack", "pouch", "quiver");
        torso.setPartClass("torso");




        head.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        head.setAnimatableBodyName("head");
        head.setSex("Male");
        head.setName("head");
        head.setBrightness( .67f);
        head.setColorA(1);
        head.setColorB(.1f);
        head.setColorR(0);
        head.setColorG(1);
        head.setBoundsX(0);
        head.setBoundsY(0);
        head.setHealth(100);
        head.setMass(1);
        head.setVolume(1);
        head.setMoveable(true);
        head.setAnimated(false);
        head.setDrawable(false);
        head.getStats().add(health, on, classuz);
        head.setHolder(true);
        head.setPart(true);
        head.setHumanLikeCharacter(true);
        head.setTmxObjectId("6");
        head.setPart(true);
        head.setPartClass("head");
        head.getAttachableParts().addAll("hat", "helmet", "mask", "glasses");
        head.setPartClass("torso");


        tree.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        tree.setImageName("tree");
        tree.setName("tree");
        tree.setAnimatableBodyName("tree");
        tree.setHasImage(true);
        tree.setDrawable(true);
        tree.setDrawOnStart(true);
        tree.getStats().add(health, on, classuz);


        pack.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        pack.setAnimatableBodyName("backPack");
        pack.setSex("");
        pack.setName("pack");
        pack.setBrightness( .67f);
        pack.setColorA(1);
        pack.setColorB(.1f);
        pack.setColorR(1);
        pack.setColorG(0);
        pack.setBoundsX(32);
        pack.setBoundsY(64);
        pack.setHealth(100);
        pack.setMass(1);
        pack.setVolume(100);
        pack.setAnimated(true);
        pack.setDrawable(true);
        pack.setLpcActorAnimated(true);
        pack.getStats().add(condition, on, classuz);
        pack.setLoad(true);
        pack.setPart(true);
        pack.setPartClass("backPack");




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
        apple.setDrawable(false);

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
        potion.setAnimated(false);
        potion.setDrawable(false);
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
        scroll.setAnimated(false);
        scroll.setDrawable(false);
        scroll.getStats().add(condition, on, classuz);



        sword.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        sword.setAnimatableBodyName("swordMale");
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
        sword.setAnimated(false);
        sword.setDrawable(false);
        sword.getStats().add(condition, price, classuz, on);


        spear.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        spear.setAnimatableBodyName("spear");
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
        spear.setAnimated(false);
        spear.setDrawable(false);


        axe.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        axe.setAnimatableBodyName("axe");
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
        axe.setAnimated(false);
        axe.setDrawable(false);


        shovel.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        shovel.setAnimatableBodyName("shovel");
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
        shovel.setAnimated(false);
        shovel.setDrawable(false);



        bow.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        bow.setAnimatableBodyName("bow");
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
        bow.setAnimated(false);
        bow.setDrawable(false);


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

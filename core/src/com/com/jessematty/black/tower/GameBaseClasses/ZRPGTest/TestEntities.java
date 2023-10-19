package com.jessematty.black.tower.GameBaseClasses.ZRPGTest;

public class TestEntities {

    static  LPCActorGeneratorDTO lizard = new LPCActorGeneratorDTO();
   static  {
        lizard.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        lizard.setBodyName("lizardMale");
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
    }

    public TestEntities() {


    }

    public static LPCActorGeneratorDTO getLizard() {
        return lizard;
    }

}

package com.jessematty.black.tower.GameBaseClasses.ZRPGTest;


import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCObjectGeneratorDTO;

public class TestEntities {

    static LPCObjectGeneratorDTO lizard = new LPCObjectGeneratorDTO();
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
        lizard.setMoveable(true);
        lizard.setAnimated(true);
        lizard.setDrawable(true);
    }

    public TestEntities() {


    }

    public static LPCObjectGeneratorDTO getLizard() {
        return lizard;
    }

}

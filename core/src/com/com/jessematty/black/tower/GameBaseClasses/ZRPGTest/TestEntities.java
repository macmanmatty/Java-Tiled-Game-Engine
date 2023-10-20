package com.jessematty.black.tower.GameBaseClasses.ZRPGTest;


import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCObjectGeneratorDTO;

public class TestEntities {

    static LPCObjectGeneratorDTO lizard = new LPCObjectGeneratorDTO();
    static LPCObjectGeneratorDTO tree = new LPCObjectGeneratorDTO();
    static LPCObjectGeneratorDTO pack = new LPCObjectGeneratorDTO();

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
        tree.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        tree.setImageName("tree");
        tree.setBodyName("tree");
        tree.setHasImage(true);
        tree.setDrawable(true);
        tree.setDrawOnStart(true);

        pack.setAtlasName( "textureAtlases/testAssets/testAssets.atlas");
        pack.setBodyName("backPack");
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

    }

    public TestEntities() {


    }

    public static LPCObjectGeneratorDTO getLizard() {
        return lizard;
    }

}

package com.jessematty.black.tower.Generators.MapGenerators.BuildingSpriteGenerator;

import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Generators.MapGenerators.LandMapGenerator;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMask;

public abstract  class BuildingSpriteGenerator {

   protected GameAssets assetts;
  protected LandMapGenerator mapGenerator;
   protected BitMask bitMask= new BitMask();
    protected int stories;
    protected int innerYSize;
    protected int innerXSize;
    protected int numberOfRooms;
    protected int locationX;
    protected int locationY;
    RandomNumbers value= new RandomNumbers();




    public BuildingSpriteGenerator(GameAssets assetts, LandMapGenerator map) {
        this.assetts = assetts;
        this.mapGenerator = map;
    }

    abstract  boolean makeBuilding();


}

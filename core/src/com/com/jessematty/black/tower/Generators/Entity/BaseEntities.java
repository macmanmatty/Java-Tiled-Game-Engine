package com.jessematty.black.tower.Generators.Entity;

import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCObjectGeneratorDTO;

public class BaseEntities {

    /**
     * use to create empty bounds
     */

  static   LPCObjectGeneratorDTO wall = new LPCObjectGeneratorDTO();

   static  {
        wall.setName("unenterable");
        wall.setHasImage(false);
        wall.setDrawable(false);
        wall.setDrawOnStart(false);
        wall.setMass(Float.MAX_VALUE);
        wall.setHeight(Float.MAX_VALUE);
        wall.setVolume(Float.MAX_VALUE);
    }
}
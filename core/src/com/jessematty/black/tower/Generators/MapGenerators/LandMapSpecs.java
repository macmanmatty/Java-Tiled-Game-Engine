package com.jessematty.black.tower.Generators.MapGenerators;

import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TerrainSet;

import java.util.ArrayList;

public class LandMapSpecs extends MapSpecs {

   ArrayList<String> preMadeTmxMapSectionPaths = new ArrayList<String>();
   Array<NumericStat> tileNumericStats= new Array<>();
   Array<TerrainSet> tileSets= new Array<>();






    public  void setSpecs(){

        super.setSpecs();
        xSize=1;
        ySize=1;

        enemiesRegenerate =true;

        tileNumericStats.add(new NumericStat(true, "Temperature", 0, -100, 200));
        tileNumericStats.add(new NumericStat(true, "Temperature", 0, -100, 200));











    }

    public Array<NumericStat> getTileNumericStats() {
        return tileNumericStats;
    }

    public Array<TerrainSet> getTileSets() {
        return tileSets;
    }
}

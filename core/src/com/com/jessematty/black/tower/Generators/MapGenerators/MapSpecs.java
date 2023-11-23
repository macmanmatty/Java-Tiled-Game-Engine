package com.jessematty.black.tower.Generators.MapGenerators;

import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TileSet;

import java.util.ArrayList;

public class MapSpecs {
    int xSize;
    int ySize;
    boolean enemiesRegenerate;
    ArrayList<String> preMadeTmxMapSectionPaths = new ArrayList<String>();
    Array<NumericStat> tileNumericStats= new Array<>();
    Array<TileSet> tileSets= new Array<>();
    String thingsToLoadJsonFilePath;
     String fightersToLoadJsonFilePath;






                
              public  void setSpecs(){

                   xSize=1;
                   ySize=1;

                enemiesRegenerate =true;

              }



    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }



    public boolean isEnemiesRegenerate() {
        return enemiesRegenerate;
    }

    public void setEnemiesRegenerate(boolean enemiesRegenerate) {
        this.enemiesRegenerate = enemiesRegenerate;
    }



    public String getThingsToLoadJsonFilePath() {
        return thingsToLoadJsonFilePath;
    }

    public void setThingsToLoadJsonFilePath(String thingsToLoadJsonFilePath) {
        this.thingsToLoadJsonFilePath = thingsToLoadJsonFilePath;
    }


    public String getFightersToLoadJsonFilePath() {
        return fightersToLoadJsonFilePath;
    }

    public void setFightersToLoadJsonFilePath(String fightersToLoadJsonFilePath) {
        this.fightersToLoadJsonFilePath = fightersToLoadJsonFilePath;
    }


}

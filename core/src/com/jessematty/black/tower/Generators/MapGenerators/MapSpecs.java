package com.jessematty.black.tower.Generators.MapGenerators;

import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.GameBaseClasses.BitMask.TileSet;
import com.jessematty.black.tower.Maps.Area;

import java.util.ArrayList;

public class MapSpecs {
    int xSize;
    int ySize;
    int maxNumberOfFighters;
    int minNumberOfFighters;
    boolean fightersRegenerate;
    ArrayList<String> preMadeTmxMapSectionPaths = new ArrayList<String>();
    Array<NumericStat> tileNumericStats= new Array<>();
    Array<TileSet> tileSets= new Array<>();
    String thingsToLoadJsonFilePath;
     String fightersToLoadJsonFilePath;
     ArrayList<Area> areas= new ArrayList<Area>();
    Area defaultArea;






                
              public  void setSpecs(){

                   xSize=1;
                   ySize=1;
                   maxNumberOfFighters=1;
                   minNumberOfFighters=1;
                fightersRegenerate=true;
                 defaultArea= new Area();
                 areas.add(defaultArea);












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

    public int getMaxNumberOfFighters() {
        return maxNumberOfFighters;
    }

    public void setMaxNumberOfFighters(int maxNumberOfFighters) {
        this.maxNumberOfFighters = maxNumberOfFighters;
    }

    public int getMinNumberOfFighters() {
        return minNumberOfFighters;
    }

    public void setMinNumberOfFighters(int minNumberOfFighters) {
        this.minNumberOfFighters = minNumberOfFighters;
    }

    public boolean isFightersRegenerate() {
        return fightersRegenerate;
    }

    public void setFightersRegenerate(boolean fightersRegenerate) {
        this.fightersRegenerate = fightersRegenerate;
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

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }

    public Area getDefaultArea() {
        return defaultArea;
    }

    public void setDefaultArea(Area defaultArea) {
        this.defaultArea = defaultArea;
    }

}

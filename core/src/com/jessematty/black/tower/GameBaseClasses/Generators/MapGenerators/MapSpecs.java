package com.jessematty.black.tower.GameBaseClasses.Generators.MapGenerators;

import com.jessematty.black.tower.Maps.Area;

import java.util.ArrayList;

public class MapSpecs {
    int xSize;
    int ySize;
    int maxNumberOfFighters;
    int minNumberOfFighters;
    boolean fightersRegenerate;
    int maxFireMagic;
    int minFireMagic;
    int maxWindMagic;
    int minWindMagic;
    int maxEarthMagic;
    int minEarthMagic;
    int maxWaterMagic;
    int minWaterMagic;
    int minLightMagic;
    int maxLightMagic;
    int minDarkMagic;
    int maxDarkMagic;
    int magicSmoothness;
    int heightSmoothness;

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
                   maxFireMagic=1;
                   minFireMagic=1;
                   maxWindMagic=1;
                   minWindMagic=1;
                   maxEarthMagic=1;
                   minEarthMagic=1;
                   maxWaterMagic=1;
                   minWaterMagic=1;
                   minLightMagic=1;
                   maxLightMagic=1;
                   minDarkMagic=1;
                   maxDarkMagic=1;
                   magicSmoothness=1;

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

    public int getMaxFireMagic() {
        return maxFireMagic;
    }

    public void setMaxFireMagic(int maxFireMagic) {
        this.maxFireMagic = maxFireMagic;
    }

    public int getMinFireMagic() {
        return minFireMagic;
    }

    public void setMinFireMagic(int minFireMagic) {
        this.minFireMagic = minFireMagic;
    }

    public int getMaxWindMagic() {
        return maxWindMagic;
    }

    public void setMaxWindMagic(int maxWindMagic) {
        this.maxWindMagic = maxWindMagic;
    }

    public int getMinWindMagic() {
        return minWindMagic;
    }

    public void setMinWindMagic(int minWindMagic) {
        this.minWindMagic = minWindMagic;
    }

    public int getMaxEarthMagic() {
        return maxEarthMagic;
    }

    public void setMaxEarthMagic(int maxEarthMagic) {
        this.maxEarthMagic = maxEarthMagic;
    }

    public int getMinEarthMagic() {
        return minEarthMagic;
    }

    public void setMinEarthMagic(int minEarthMagic) {
        this.minEarthMagic = minEarthMagic;
    }

    public int getMaxWaterMagic() {
        return maxWaterMagic;
    }

    public void setMaxWaterMagic(int maxWaterMagic) {
        this.maxWaterMagic = maxWaterMagic;
    }

    public int getMinWaterMagic() {
        return minWaterMagic;
    }

    public void setMinWaterMagic(int minWaterMagic) {
        this.minWaterMagic = minWaterMagic;
    }

    public int getMinLightMagic() {
        return minLightMagic;
    }

    public void setMinLightMagic(int minLightMagic) {
        this.minLightMagic = minLightMagic;
    }

    public int getMaxLightMagic() {
        return maxLightMagic;
    }

    public void setMaxLightMagic(int maxLightMagic) {
        this.maxLightMagic = maxLightMagic;
    }

    public int getMinDarkMagic() {
        return minDarkMagic;
    }

    public void setMinDarkMagic(int minDarkMagic) {
        this.minDarkMagic = minDarkMagic;
    }

    public int getMaxDarkMagic() {
        return maxDarkMagic;
    }

    public void setMaxDarkMagic(int maxDarkMagic) {
        this.maxDarkMagic = maxDarkMagic;
    }



    public int getMagicSmoothness() {
        return magicSmoothness;
    }

    public void setMagicSmoothness(int magicSmoothness) {
        this.magicSmoothness = magicSmoothness;
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

    public int getHeightSmoothness() {
        return heightSmoothness;
    }

    public void setHeightSmoothness(int heightSmoothness) {
        this.heightSmoothness = heightSmoothness;
    }
}

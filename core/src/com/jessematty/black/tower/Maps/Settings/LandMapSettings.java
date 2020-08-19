package com.jessematty.black.tower.Maps.Settings;

import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.TextureAtlasRegionNames;

import java.util.ArrayList;

public class LandMapSettings extends GameMapSettings {


    public LandMapSettings(int xSize, int ySize, String tiledMapPath, String mapName, String skinName, double gravity, boolean hasEnemies, ArrayList<TextureAtlasRegionNames> atlasNames, boolean currentMap, boolean hasWeather, int numberOfBuildings) {
        super(xSize, ySize, tiledMapPath, mapName, skinName, gravity, hasEnemies, currentMap, hasWeather, numberOfBuildings);
    }

    public LandMapSettings() {
    }
}

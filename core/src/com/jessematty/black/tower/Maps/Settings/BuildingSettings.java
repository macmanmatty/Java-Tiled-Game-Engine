package com.jessematty.black.tower.Maps.Settings;

import com.jessematty.black.tower.Maps.Settings.GameMapSettings;

public class BuildingSettings extends GameMapSettings {
    public BuildingSettings(int xSize, int ySize, String tiledMapPath, String mapName, String skinName, double gravity, boolean hasEnemies, boolean currentMap, boolean hasWeather, int numberOfBuildings) {
        super(xSize, ySize, tiledMapPath, mapName, skinName, gravity, hasEnemies,  currentMap, hasWeather, numberOfBuildings);
    }
}

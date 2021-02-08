package com.jessematty.black.tower.Maps.Settings;

import com.jessematty.black.tower.GameBaseClasses.Settings.Settings;

public   class WorldSettings extends Settings {


    public WorldSettings() {
        settings.put("width", 320);
        settings.put("height", 320);
        settings.put("tileWidth", 32);
        settings.put("tileHeight", 32);
        settings.put("gravity", -9.8);
        settings.put("worldX", 0);
        settings.put("worldY", 0);
        settings.put("name", "Map"+hashCode());
        settings.put("skinName", "skin");
        settings.put("skinAtlas", "skin");
        settings.put("textureAtlasPath", "");
        settings.put("lightChanges", false);
        settings.put("worldX", 0);
        settings.put("worldY", 0);
        settings.put("dayLightAmount", 0.0f);
        settings.put("gettingBrighter", true);
        settings.put("newMap", true);
        settings.put("tiledMapAtlasName", "");
        settings.put("tiledMapPath", "");
        settings.put("currentMap", false);
        settings.put("xTiles", 10);
        settings.put("yTiles", 10);
    }


    public void setTiles(int tilesX, int tilesY){

        settings.put("xTiles", tilesX);
        settings.put("yTiles", tilesY);

        settings.put("width", tilesX*getSetting("tileWidth", Integer.class));
        settings.put("height", tilesY*getSetting("tileHeight", Integer.class));


    }





}

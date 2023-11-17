package com.jessematty.black.tower.Maps.Settings;

import com.jessematty.black.tower.GameBaseClasses.Settings.Settings;

public   class WorldSettings extends Settings {


    public WorldSettings() {
        settings.put("width", 3200);
        settings.put("height", 3200);
        settings.put("name", "World"+hashCode());
        settings.put("skinName", "skin");
        settings.put("skinAtlas", "skin");
        settings.put("textureAtlasPath", "");
        settings.put("gettingBrighter", true);
        settings.put("newGame", true);
        settings.put("xMaps", 10);
        settings.put("yMaps", 10);
        settings.put("startMapX", 0);
        settings.put("startMapY", 0);
        settings.put("currentMapX", 0);
        settings.put("currentMapY", 0);
        settings.put("playerId", "id");
        settings.put("textureAtlasPath", "/worldAssets.atlas");
    }

}

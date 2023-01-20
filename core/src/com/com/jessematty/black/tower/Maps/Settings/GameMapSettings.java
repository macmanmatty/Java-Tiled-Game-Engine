package com.jessematty.black.tower.Maps.Settings;
import com.jessematty.black.tower.GameBaseClasses.Settings.Settings;

/**
 * Map of settings for a  GameMap class
 */
public   class GameMapSettings extends Settings {
    /**
     * default constructor with required settings added to the map settings
     */
    public GameMapSettings() {
        settings.put("width", 320);
        settings.put("height", 320);
        settings.put("tileWidth", 32);
        settings.put("tileHeight", 32);
        settings.put("gravity", -9.8);
        settings.put("name", "Map"+hashCode());
        settings.put("skinName", "skin");
        settings.put("skinAtlas", "skin");
        settings.put("skinStyle", "default");
        settings.put("textureAtlasPath", "");
        settings.put("lightChanges", false);
        settings.put("worldX", 0);
        settings.put("worldY", 0);
        settings.put("dayLightAmount", 0.0f);
        settings.put("gettingBrighter", true);
        settings.put("newMap", true);
        settings.put("tiledMapAtlasName", "");
        settings.put("tiledMapPath", "");
        settings.put("usesTMXMap", false);
        settings.put("currentMap", false);
        settings.put("xTiles", 10);
        settings.put("yTiles", 10);
    }

    /**
     *  sets the tle based attributes for the map
     * @param width the  maps width in tiles
     * @param height the maps height in tiles
     */
    public void setTiles(int width, int height){
        settings.put("xTiles", width);
        settings.put("yTiles", height);
        settings.put("width", width* getSimpleSetting("tileWidth", Integer.class));
        settings.put("height", height* getSimpleSetting("tileHeight", Integer.class));
    }

}

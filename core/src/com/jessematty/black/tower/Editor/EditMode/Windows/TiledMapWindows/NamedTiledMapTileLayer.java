package com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapWindows;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class NamedTiledMapTileLayer extends TiledMapTileLayer {
    /**
     * Creates TiledMap layer
     *
     * @param width      layer width in tiles
     * @param height     layer height in tiles
     * @param tileWidth  tile width in pixels
     * @param tileHeight tile height in pixels
     */

    public NamedTiledMapTileLayer(int width, int height, int tileWidth, int tileHeight) {
        super(width, height, tileWidth, tileHeight);
    }


    // returns name  used with libGDX list to display name
    @Override
    public String toString() {
        return getName();
    }

    public String getDisplayName() {
        return getName();
    }

    public void setDisplayName(String name) {
        setName(name);
    }
}

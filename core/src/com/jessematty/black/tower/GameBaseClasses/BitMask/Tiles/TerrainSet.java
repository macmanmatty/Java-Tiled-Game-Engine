package com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles;


import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Generators.Sets.MaskMode;
 // class that holds an array of tile sets
public class TerrainSet {
    private MaskMode maskMode=MaskMode.FULL_WANG_SET;
    private Array<TileSet> tileSets= new Array<>();

    public Array<TileSet> getTileSets() {
        return tileSets;
    }

    public MaskMode getMaskMode() {
        return maskMode;
    }

    public void setMaskMode(MaskMode maskMode) {
        this.maskMode = maskMode;
    }
}

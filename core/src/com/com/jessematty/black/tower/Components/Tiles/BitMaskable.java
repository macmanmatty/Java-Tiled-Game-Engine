package com.jessematty.black.tower.Components.Tiles;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.Components.Stats.Stat;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;

import java.util.HashMap;
import java.util.Map;

public class BitMaskable implements Component {

   private  int bitNumber;
   boolean bitNumberChanged;
   private transient  Map<Integer, AtlasNamedAtlasRegion> textureRegion= new HashMap<Integer, AtlasNamedAtlasRegion>();
   private Stat statToMaskOn;

    public int getBitNumber() {
        return bitNumber;
    }

    public void setBitNumber(int bitNumber) {
        this.bitNumber = bitNumber;
        bitNumberChanged=true;
    }

    public Map<Integer, AtlasNamedAtlasRegion> getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(Map<Integer, AtlasNamedAtlasRegion> textureRegion) {
        this.textureRegion = textureRegion;
    }

    public boolean isBitNumberChanged() {
        return bitNumberChanged;
    }

    public void setBitNumberChanged(boolean bitNumberChanged) {
        this.bitNumberChanged = bitNumberChanged;
    }

    public void setStatToMaskOn(Stat statToMaskOn) {
        this.statToMaskOn = statToMaskOn;
    }
}

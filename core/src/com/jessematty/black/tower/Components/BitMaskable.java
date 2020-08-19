package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;

import java.util.HashMap;
import java.util.Map;

public class BitMaskable implements Component {

   private  int bitNumber;
   boolean bitNumberChanged;

   private Map<Integer, AtlasNamedAtlasRegion> textureRegion= new HashMap<Integer, AtlasNamedAtlasRegion>();

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
}

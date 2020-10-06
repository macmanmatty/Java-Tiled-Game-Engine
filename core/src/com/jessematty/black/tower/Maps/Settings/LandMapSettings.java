package com.jessematty.black.tower.Maps.Settings;

import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.TextureAtlasRegionNames;

import java.util.ArrayList;

public class LandMapSettings extends GameMapSettings {


   private  Array<String> buildingNames=new Array();



    public LandMapSettings() {
    }

    public Array<String> getBuildingNames() {
        return buildingNames;
    }

    public void setBuildingNames(Array<String> buildingNames) {
        this.buildingNames = buildingNames;
    }
}

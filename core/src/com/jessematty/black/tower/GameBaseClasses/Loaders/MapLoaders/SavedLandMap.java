package com.jessematty.black.tower.GameBaseClasses.Loaders.MapLoaders;

import com.badlogic.gdx.utils.Array;

public class SavedLandMap {
  private SavedMap landMap;
  private Array<SavedMap> buildings= new Array<>();


    public SavedMap getLandMap() {
        return landMap;
    }

    public void setLandMap(SavedMap landMap) {
        this.landMap = landMap;
    }

    public Array<SavedMap> getBuildings() {
        return buildings;
    }


}

package com.jessematty.black.tower.Generators;

import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Maps.GameMap;

public class WeaponGenerator {


    GameAssets assetts;
    GameMap map;

    ItemGenerator itemGenerator;

    public WeaponGenerator(GameAssets assetts, GameMap map, ItemGenerator itemGenerator) {
        this.assetts = assetts;
        this.map = map;
        this.itemGenerator=itemGenerator;

    }



}

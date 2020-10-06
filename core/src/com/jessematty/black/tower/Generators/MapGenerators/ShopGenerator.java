package com.jessematty.black.tower.Generators.MapGenerators;

import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Maps.Buildings.Shops.Shop;
import com.jessematty.black.tower.Maps.GameMap;

public class ShopGenerator extends MapGenerator {

    public ShopGenerator(GameAssets assetts, int xSize, int ySize) {
        super(assetts, xSize, ySize);

        makeTiles();


    }

    @Override
    public GameMap makeMap() {
        return null;
    }
}

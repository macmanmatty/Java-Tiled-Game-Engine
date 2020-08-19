package com.jessematty.black.tower.GameBaseClasses.Generators.MapGenerators;

import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Maps.Buildings.Shops.Shop;
import com.jessematty.black.tower.Maps.GameMap;

public class ShopGenerator extends MapGenerator {

    public ShopGenerator(GameAssets assetts, int xSize, int ySize) {
        super(assetts, xSize, ySize);

        map= new Shop( xSize, ySize, assetts);
        makeTiles();


    }

    @Override
    public GameMap makeMap() {
        return null;
    }
}

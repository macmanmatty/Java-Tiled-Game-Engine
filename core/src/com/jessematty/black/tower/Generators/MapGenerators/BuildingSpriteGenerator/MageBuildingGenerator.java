package com.jessematty.black.tower.Generators.MapGenerators.BuildingSpriteGenerator;

import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Generators.MapGenerators.LandMapGenerator;

public class MageBuildingGenerator extends BuildingSpriteGenerator {
    public MageBuildingGenerator(GameAssets assetts, LandMapGenerator map) {
        super(assetts, map);
    }

    @Override
    public boolean  makeBuilding() {
        return true;

    }
}

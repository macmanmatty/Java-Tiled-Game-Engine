package com.jessematty.black.tower.Generators.MapGenerators;

import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatChangeable;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeable;
import com.jessematty.black.tower.Components.Tiles.TileComponent;
import com.jessematty.black.tower.Components.Tiles.TileWeatherChangableNumericStatChangeable;
import com.jessematty.black.tower.Components.Tiles.TileWeatherNumericStatsChangable;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class TileGenerator {


    public LandSquareTile createBasicTile(int locationX, int locationY, GameMap map) {
        LandSquareTile tile= new LandSquareTile();
        float screenLocationX = (locationX + 1) * 32;
        float screenLocationY = (map.getYTiles() - locationY - 1) * 32;
        PositionComponent position = new PositionComponent();
        position.removeBounds();
        position.setTileLocationX(locationX);
        position.setTileLocationY(locationY);
        position.setMapID(map.getId());

        tile.add(position);
        position.setPosition(screenLocationX, screenLocationY);
        TileComponent tileComponent = new TileComponent();
        NumericStats numericStats = new NumericStats();
        BooleanStats booleanStats = new BooleanStats();
        StringStats stringStats = new StringStats();
        NumericStatsChangeable numericStatsChangeable = new NumericStatsChangeable();
        TileWeatherNumericStatsChangable tileWeatherNumericStatsChangable = new TileWeatherNumericStatsChangable();
        BooleanStatsChangeable booleanStatsChangeable = new BooleanStatsChangeable();
        StringStatsChangeable stringStatsChangeable = new StringStatsChangeable();
        tile.add(numericStats);
        tile.add(booleanStats);
        tile.add(stringStats);
        tile.add(booleanStatsChangeable);
        tile.add(numericStatsChangeable);
       tile. add(stringStatsChangeable);
       tile. add(tileWeatherNumericStatsChangable);
        tile.add(tileComponent);
        tileWeatherNumericStatsChangable.addStatToChange(new TileWeatherChangableNumericStatChangeable(true, "temperature", 70, -275, 20000));
        numericStats.addStat(new NumericStat(false,"COF", 1, 0, 10));
        numericStatsChangeable.addStatToChange(new NumericStatChangeable(false, "temperature", 0, 0, 3, 0));

        tile.add(new NameComponent(true,  toString()));

        return  tile;

    }


}

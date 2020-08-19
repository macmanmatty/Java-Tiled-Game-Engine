package com.jessematty.black.tower.GameBaseClasses.Generators.MapGenerators;

import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangable;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangable;
import com.jessematty.black.tower.Components.Tile;
import com.jessematty.black.tower.Components.TileWeatherChangableNumericStat;
import com.jessematty.black.tower.Components.TileWeatherNumericStatsChangable;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class TileGenerator {


    public LandSquareTile createBasicTile(int locationX, int locationY, GameMap map) {
        LandSquareTile tile= new LandSquareTile();
        float screenLocationX = (locationX + 1) * 32;
        float screenLocationY = (map.getYSize() - locationY - 1) * 32;
        Position position = new Position();
        position.removeBounds();
        position.setTileLocationX(locationX);
        position.setTileLocationY(locationY);
        position.setMapWorldLocationX(map.getWorldX());
        position.setMapWorldLocationY(map.getWorldY());

        tile.add(position);
        position.setSetScreenLocations(screenLocationX, screenLocationY);
        Tile tileComponent = new Tile();
        tileComponent.setAtlasName("assetts.atlas");
        NumericStats numericStats = new NumericStats();
        BooleanStats booleanStats = new BooleanStats();
        StringStats stringStats = new StringStats();
        NumericStatsChangable numericStatsChangable = new NumericStatsChangable();
        TileWeatherNumericStatsChangable tileWeatherNumericStatsChangable = new TileWeatherNumericStatsChangable();
        BooleanStatsChangable booleanStatsChangable = new BooleanStatsChangable();
        StringStatsChangable stringStatsChangable = new StringStatsChangable();
        tile.add(numericStats);
        tile.add(booleanStats);
        tile.add(stringStats);
        tile.add(booleanStatsChangable);
        tile.add(numericStatsChangable);
       tile. add(stringStatsChangable);
       tile. add(tileWeatherNumericStatsChangable);
        tile.add(tileComponent);
        tileWeatherNumericStatsChangable.addStatToChange(new TileWeatherChangableNumericStat(true, "temperature", 70, -275, 20000));
        numericStats.addStat(new NumericStat(false,"COF", 1, 0, 10));
        numericStatsChangable.addStatToChange(new ChangableNumericStat(false, "temperature", 0, 0, 3, 0));

        tile.add(new Name(true,  toString()));

        return  tile;

    }


}

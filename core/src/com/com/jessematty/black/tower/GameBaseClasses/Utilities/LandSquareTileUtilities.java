package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeable;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Tiles.TileComponent;
import com.jessematty.black.tower.Components.Tiles.TileWeatherChangableNumericStatChangeable;
import com.jessematty.black.tower.Components.Tiles.TileWeatherNumericStatsChangable;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class LandSquareTileUtilities {

    /**
     *Takes two tiles and determines the direction one would have to travel to get from
     * tile one to tile two
     * @param tile the tile you are traveling from
     * @param tile2 the tile you are you traveilng to
     * @return
     */
    public static  Direction getDirection(LandSquareTile tile, LandSquareTile tile2) {
        int tileX = tile.getLocationX();
        int tileY = tile.getLocationY();
        int tile2X = tile2.getLocationX();
        int tile2Y = tile2.getLocationY();
        int locationXDifference = tileX - tile2X;
        int locationYDifference = tileY - tile2Y;
        Direction direction=Direction.SAME;
        if (locationYDifference > 0 && locationXDifference == 0) {
            direction=Direction.UP;
        } else if (locationYDifference < 0 && locationXDifference == 0) {
            direction=Direction.DOWN;
        } else if (locationXDifference < 0 && locationYDifference == 0) {
            direction=Direction.RIGHT;
        } else if (locationXDifference > 0 && locationYDifference == 0) {
            direction=Direction.LEFT;
        } else if (locationYDifference < 0 && locationXDifference > 0) {
            direction=Direction.LEFTDOWN;
        } else if (locationYDifference > 0 && locationXDifference > 0) {
            direction=Direction.LEFTUP;
        } else if (locationYDifference < 0 && locationXDifference < 0) {
            direction=Direction.RIGHTDOWN;
        } else if (locationYDifference > 0 && locationXDifference < 0) {
            direction=Direction.RIGHTUP;
        } ;
        return  direction;
    }

    /**
     * creates a new land square tile
     * with all the required components
     * @return the newly created  LandSquareTile
     */
   public static  LandSquareTile createTile(){
        LandSquareTile  tile= new LandSquareTile();
        NumericStats numericStats= new NumericStats();
        BooleanStats booleanStats= new BooleanStats();
        StringStats stringStats=new StringStats();
        NumericStatsChangeable numericStatsChangeable = new NumericStatsChangeable();
        TileWeatherNumericStatsChangable tileWeatherNumericStatsChangable= new TileWeatherNumericStatsChangable();
        BooleanStatsChangeable booleanStatsChangeable = new BooleanStatsChangeable();
        StringStatsChangeable stringStatsChangeable = new StringStatsChangeable();
        GroupsComponent groupsComponent = new GroupsComponent();
        Array<String> stringGroups= groupsComponent.getGroups();
        TileComponent tileComponent= new TileComponent();
        stringGroups.add("entity");
        stringGroups.add("tile");
        tile.add(numericStats);
        tile.add(booleanStats);
        tile.add(stringStats);
        tile.add(booleanStatsChangeable);
        tile.add(numericStatsChangeable);
        tile.add(stringStatsChangeable);
        tile.add(tileWeatherNumericStatsChangable);
        tile.add(tileComponent);
        tile.add(groupsComponent);
        tileWeatherNumericStatsChangable.addStatToChange(new TileWeatherChangableNumericStatChangeable(true,"temperature", 70,-275, 20000));
        numericStats.addStat(new NumericStat(false,"COF" ,1,0,10));
        numericStatsChangeable.addStatToChange(new NumericStatChangeable(false,"temperature",  0,0,3, 0));
        PositionComponent position= new PositionComponent();
        position.getTiles().add(tile);
        position.removeBounds();
        tile.add(position);
        tile.add(new NameComponent(true, tile.toString()));
        return tile;
    }

    public static LandSquareTile createTile(int x, int y, GameMap map){
        LandSquareTile tile= createTile();
        int yTiles=map.getYTiles();
        PositionComponent position=  new PositionComponent();
        position.setTileLocationX(x);
        position.setTileLocationY(y);
        tile.add(position);
        float screenLocationX = (position.getTileLocationX() ) * map.getTileWidth();
        float screenLocationY =  (yTiles - position.getTileLocationY()) *map.getTileHeight();
        position.setPosition(screenLocationX, screenLocationY);
        return  tile;

    }
}

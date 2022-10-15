package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class LandSquareTileUtilities {

    /**
     *Takes two tile and determines the direction one would have to travel to get from
     * tile one to tile two
     * @param tile the tile you are traveling from
     * @param tile2 teh tile you are you traveilng to
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


}

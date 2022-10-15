package com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.Validation;

import com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.AStar;

/**
 * functional interface   that contains   the isValid(x,y) method to determine if a give tile is valid movement for path finding
 * used with Astar8  and Astar4 pathfinding classes
 * @see AStar
 * @see  com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.Astar4
 */
public interface IsValid {

    boolean isValid(int x, int y);

}

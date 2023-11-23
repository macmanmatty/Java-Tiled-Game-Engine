package com.jessematty.black.tower.GameBaseClasses.Direction;

import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MathUtilities;

/**
 *  enum that represents a direction
 */
public enum Direction {
    UP("North", 0, 0),
    RIGHT("East", 90, 1.5708f),
    DOWN("South", 180, (float)Math.PI ),
    LEFT("West", 270, 4.71239f),
    RIGHTUP("NorthEast", 45.5f, 0.7941248f),
    RIGHTDOWN("SouthEast", 135.5f, 2.3649211f ),
    LEFTUP("NorthWest",351.5f, 5.5065138f),
    LEFTDOWN("SouthWest", 225.5f, 3.9357175f),
    SAME("Location", -1f, -1f);
    String compassDirection;
    float angleDegrees;
    float angleRadians;
    Direction(String compassDirection, float angleDegrees, float angleRadians) {
        this.compassDirection = compassDirection;
        this.angleDegrees = angleDegrees;
        this.angleRadians = angleRadians;
    }
    public String getCompassDirection() {
        return compassDirection;
    }
    public float getAngleDegrees() {
        return angleDegrees;
    }
    public float getAngleRadians() {
        return angleRadians;
    }

    /**
     *  returns the direction of point 2 from point 1
     * @param x the x value of point one
     * @param y the y value of point one
     * @param x2 the x value of point 2
     * @param y2 the y value of point 2
     * @return the directional point 2 is from point one
     */
    public static Direction getDirection(int x, int y, int x2, int y2) {
        if (x2 < x && y == y2) {
            return LEFT;
        } else if (x2 > x && y == y2) {
            return RIGHT;
        } else if (x == x2 && y2 > y) {
            return DOWN;
        }
        else if (x2 < x && y2 > y) {
            return LEFTDOWN;
        }
        else if (x2 == x && y2 < y) {
            return UP;
        } else if (x2 < x && y2 < y) {
            return LEFTUP;
        } else if (x2 > x && y2 < y) {
            return RIGHTUP;
        } else if (x2 > x && y2 > y) {
            return RIGHTDOWN;
        }
        return SAME;
    }
    /**
     *  returns the direction of point 2 from point 1
     * @param x the x value of point one
     * @param y the y value of point one
     * @param x2 the x value of point 2
     * @param y2 the y value of point 2
     * @return the directional point 2 is from point one
     */
    public static Direction getDirection(float x, float y, float x2, float y2) {
        if (x2 < x && y2 > y) {
            return LEFTDOWN;
        } else if (x2 < x && y == y2) {
            return LEFT;
        } else if (x2 > x && y == y2) {
            return RIGHT;
        } else if (x == x2 && y2 < y) {
            return DOWN;
        } else if (x2 == x && y2 > y) {
            return UP;
        } else if (x2 < x && y2 > y) {
            return LEFTUP;
        } else if (x2 > x && y2 > y) {
            return RIGHTUP;
        } else if (x2 > x && y2 < y) {
            return RIGHTDOWN;
        }
        return SAME;
    }
    public static Vector2 getTileVector(Direction direction) {
        switch (direction) {
            case UP:
                return new Vector2(0, -1);
            case DOWN:
                return new Vector2(0, 1);
            case LEFT:
                return new Vector2(-1, 0);
            case RIGHT:
                return new Vector2(1, 0);
            case LEFTUP:
                return new Vector2(-1, -1);
            case LEFTDOWN:
                return new Vector2(-1, 1);
            case RIGHTUP:
                return new Vector2(1, -1);
            case RIGHTDOWN:
                return new Vector2(1, 1);
            case SAME:
                return new Vector2(0, 0);
        }
        return new Vector2(0, 0);
    }
    public static Direction getOppositeDirection(Direction direction) {
        switch (direction) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
            case LEFTUP:
                return Direction.RIGHTDOWN;
            case LEFTDOWN:
                return Direction.RIGHTUP;
            case RIGHTUP:
                return Direction.LEFTDOWN;
            case RIGHTDOWN:
                return Direction.LEFTUP;
            case SAME:
                return SAME;
        }
        return SAME;
    }

    /**
     * returns the base direction  IE UP , DOWN LEFT OR RIGHT for a passed in direction
     * @param direction the direction to check
     * @return the  base Direction
     */
    public  static Direction getBaseDirection(Direction direction) {
        switch (direction){
            case UP:
                return Direction.UP;
            case RIGHT:
            case RIGHTUP:
            case RIGHTDOWN:
                return Direction.RIGHT;
            case DOWN:
                return Direction.DOWN;
            case LEFT:
            case LEFTUP:
            case LEFTDOWN:
                return Direction.LEFT;
            case SAME:
                break;
        }
        return  Direction.SAME;
    }

    /**
     * returns  an angle in degrees for given direction
     * assuming that North or up is zero
     * @param direction
     * @return the angle of the direction
     */
    public static float getAngel(Direction direction) { // get the clockwise degrees assuming that up is 0 or 360 degrees
      switch(direction)
    {
        case UP:
            return 0;
        case DOWN:
            return 180;
        case LEFT:
            return 270;
        case RIGHT:
            return 90;
        case LEFTUP:
            return 315.5f;
        case LEFTDOWN:
            return 225.5f;
        case RIGHTUP:
            return 45.5f;
        case RIGHTDOWN:
            return 135.5f;
        case SAME:
            return -1f;
    }
            return -1;
}
    /**
     * returns  an angle  in radians  for  a given direction
     * assuming that North or up is zero
     * @param direction
     * @return the angle of the direction
     */
    public static float getAngelInRadians(Direction direction) { // get the clockwise degrees assuming that up is 0 or 360 degrees
        switch(direction)
        {
            case UP:
                return 0f;
            case DOWN:
                return (float) Math.PI;
            case LEFT:
                return (float) 4.71239;
            case RIGHT:
               return  (float) 1.5708;
            case LEFTUP:
                return (float) 5.5065138;
            case LEFTDOWN:
                return (float) 3.9357175;
            case RIGHTUP:
                return (float) 0.7941248;
            case RIGHTDOWN:
                return (float) 2.3649211;
            case SAME:
                return 0;
        }
        return 0;
    }
    public static float getSwingAngle(Direction directionTo, Direction directionFrom) { // returns the angle between two directions
        switch (directionTo) {
            case UP:
                switch (directionFrom) {
                    case UP:
                       return 360;
                    case DOWN:
                       return 180;
                    case LEFT:
                        return 270;
                    case RIGHT:
                         return 90;
                    case LEFTUP:
                        return 315.5f;
                    case LEFTDOWN:
                        return 225.5f;
                    case RIGHTUP:
                       return  45.5f;
                    case RIGHTDOWN:
                       return 135.5f;
                    case SAME:
                        return 0;
                }
                    break;
            case DOWN:
                switch (directionFrom) {
                    case UP:
                        return 180;
                    case DOWN:
                        return 360;
                    case LEFT:
                        return 90;
                    case RIGHT:
                        return 270;
                    case LEFTUP:
                        return 135.5f;
                    case LEFTDOWN:
                        return 45.5f;
                    case RIGHTUP:
                        return  225.5f;
                    case RIGHTDOWN:
                        return 315.5f;
                    case SAME:
                        return 0;
                }
                break;
            case LEFT:
                switch (directionFrom) {
                    case UP:
                        return 90;
                    case DOWN:
                        return 270;
                    case LEFT:
                        return 360;
                    case RIGHT:
                        return 180;
                    case LEFTUP:
                        return 45.5f;
                    case LEFTDOWN:
                        return 315.5f;
                    case RIGHTUP:
                        return  135.5f;
                    case RIGHTDOWN:
                        return 225.5f;
                    case SAME:
                        return 0;
                }
                break;
            case RIGHT:
                switch (directionFrom) {
                    case UP:
                        return 270;
                    case DOWN:
                        return 90;
                    case LEFT:
                        return 180;
                    case RIGHT:
                        return 360;
                    case LEFTUP:
                        return 222.5f;
                    case LEFTDOWN:
                        return 135.5f;
                    case RIGHTUP:
                        return  315.5f;
                    case RIGHTDOWN:
                        return 45.5f;
                    case SAME:
                        return 0;
                }
                break;
            case LEFTUP:
                switch (directionFrom) {
                    case UP:
                        return 45.5f;
                    case DOWN:
                        return 225.5f;
                    case LEFT:
                        return 315.5f;
                    case RIGHT:
                        return 135.5f;
                    case LEFTUP:
                        return 360f;
                    case LEFTDOWN:
                        return 270;
                    case RIGHTUP:
                        return  90;
                    case RIGHTDOWN:
                        return 180f;
                    case SAME:
                        return 0;
                }
                break;
            case LEFTDOWN:
                switch (directionFrom) {
                    case UP:
                        return 135.5f ;
                    case DOWN:
                        return 315.5f;
                    case LEFT:
                        return 45.5f ;
                    case RIGHT:
                        return 225.5f ;
                    case LEFTUP:
                        return 90 ;
                    case LEFTDOWN:
                        return 360;
                    case RIGHTUP:
                        return 180  ;
                    case RIGHTDOWN:
                        return 270f;
                    case SAME:
                        return 0;
                }
                break;
            case RIGHTUP:
                switch (directionFrom) {
                    case UP:
                        return 315.5f ;
                    case DOWN:
                        return 135.5f ;
                    case LEFT:
                        return 225.5f ;
                    case RIGHT:
                        return 45.5f ;
                    case LEFTUP:
                        return 270  ;
                    case LEFTDOWN:
                        return 180 ;
                    case RIGHTUP:
                        return 360f ;
                    case RIGHTDOWN:
                        return 90 ;
                    case SAME:
                        return 0;
                }
                break;
            case RIGHTDOWN:
                switch (directionFrom) {
                    case UP:
                        return 225.5f ;
                    case DOWN:
                        return 45.5f;
                    case LEFT:
                        return 135.5f ;
                    case RIGHT:
                        return 315.5f ;
                    case LEFTUP:
                        return 180 ;
                    case LEFTDOWN:
                        return 90 ;
                    case RIGHTUP:
                        return 270  ;
                    case RIGHTDOWN:
                        return 360f;
                    case SAME:
                        return 0;
                }
                break;
            case SAME:
                break;
        }
        return 0;
    }
    public static  Direction getDirection( double angle){
       angle= MathUtilities.radiansToDegrees(angle);
        if (angle>337  || angle<22){
            return Direction.UP;
        }
        else if (angle>=22 && angle<=68){
            return Direction.RIGHTUP;
        }
        else if (angle>68 && angle<113){
            return Direction.RIGHT;
        }
        else if (angle>=113 && angle<=158){
            return Direction.RIGHTDOWN;
        }
        else if (angle>158 && angle<203){
            return Direction.DOWN;
        }
        else if (angle>=203 && angle<=248){
            return Direction.LEFTDOWN;
        }
        else if (angle>248 && angle<293){
            return Direction.LEFT;
        }
        else if (angle>=293 && angle<=337){
            return Direction.LEFTUP;
        }
        else{
            return SAME;
        }
    }
    public static Direction getDirection(String direction){
        if(direction.equalsIgnoreCase("left")){
            return Direction.LEFT;
        }
        else if(direction.equalsIgnoreCase("right")){
            return Direction.RIGHT;
        }
         else if(direction.equalsIgnoreCase("down")){
            return Direction.DOWN;
        }
        else if(direction.equalsIgnoreCase("up")){
            return Direction.UP;
        }
        else if(direction.equalsIgnoreCase("rightdown")){
            return Direction.RIGHTDOWN;
        }
        else if(direction.equalsIgnoreCase("leftdown")){
            return Direction.LEFTDOWN;
        }
        else if(direction.equalsIgnoreCase("leftup")){
            return Direction.LEFTUP;
        }
        else if(direction.equalsIgnoreCase("rightup")){
            return Direction.RIGHTUP;
        }
        return  Direction.SAME;
    }
    
    
    
}
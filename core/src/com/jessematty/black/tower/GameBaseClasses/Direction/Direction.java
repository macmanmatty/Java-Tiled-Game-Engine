package com.jessematty.black.tower.GameBaseClasses.Direction;
import com.badlogic.gdx.math.Vector2;
public enum Direction {
    UP, RIGHT, DOWN, LEFT, RIGHTUP, RIGHTDOWN, LEFTUP, LEFTDOWN, SAME;
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
    public static  Direction getBaseDirection(Direction direction){
        switch (direction) {
            case UP:
                return Direction.UP;
            case DOWN:
                return Direction.DOWN;
            case LEFT:
                return Direction.LEFT;
            case RIGHT:
                return Direction.RIGHT;
            case LEFTUP:
                return Direction.LEFT;
            case LEFTDOWN:
                return Direction.LEFT;
            case RIGHTUP:
                return Direction.RIGHT;
            case RIGHTDOWN:
                return Direction.RIGHT;
            case SAME:
                return SAME;
        }
        return SAME;
    }
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


    public static float getAngelInRadians(Direction direction) { // get the clockwise degrees assuming that up is 0 or 360 degrees
        switch(direction)
        {
            case UP:
                return 0;
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
    public static  Direction getMajorDirection(Direction direction) {
        switch (direction) {
            case LEFTUP:
            case LEFTDOWN: {
                return Direction.LEFT;
            }
            case RIGHTUP:
            case RIGHTDOWN: {
                return Direction.RIGHT;
            }
            default:
                return SAME;
        }
    }
    public static  Direction getDirection( double angle){
        angle=Math.toDegrees(angle);
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
        if(direction.equals("left")){
            return Direction.LEFT;
        }
        else if(direction.equals("right")){
            return Direction.RIGHT;
        }
         else if(direction.equals("down")){
            return Direction.DOWN;
        }
        else if(direction.equals("up")){
            return Direction.UP;
        }
        else if(direction.equals("rightdown")){
            return Direction.RIGHTDOWN;
        }
        else if(direction.equals("leftdown")){
            return Direction.LEFTDOWN;
        }
        else if(direction.equals("leftup")){
            return Direction.LEFTUP;
        }
        else if(direction.equals("rightup")){
            return Direction.RIGHTUP;
        }
        return  Direction.SAME;
    }
    
    
    
}
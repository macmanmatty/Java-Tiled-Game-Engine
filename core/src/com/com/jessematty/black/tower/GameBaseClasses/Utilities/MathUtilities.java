package com.jessematty.black.tower.GameBaseClasses.Utilities;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;

public class MathUtilities {
    private  MathUtilities() {
    }
    public static  double calculateMinScreenDistance(PositionComponent position1 , PositionComponent position2){
        Rectangle position2BoundingRectangle=position2.getBounds().getBoundingRectangle();
        Rectangle position1BoundingRectangle=position1.getBounds().getBoundingRectangle();
        double position1X=position1BoundingRectangle.x-(.5*position1BoundingRectangle.width);
        double position1Y=position1BoundingRectangle.y-(.5*position1BoundingRectangle.height);
        double position2X=position2BoundingRectangle.x-(.5*position2BoundingRectangle.width);
        double position2Y=position2BoundingRectangle.y-(.5*position2BoundingRectangle.height);
        return Math.abs(Math.sqrt(Math.pow((position1X - position2X), 2) + Math.pow((position1Y- position2Y), 2)));
    }
    public static  float findDistance(float speed, float gravity, float angle){
        float speedZ= (float) (Math.sin(angle)*speed);
        float  time= speedZ/gravity;
        float distance= (float) (Math.cos(angle)*speed*time);
        return distance;
    }
    public static  double roundPowerOfTwo(long number){
        double log = Math.log(number) / Math.log(2);
        long roundLog = Math.round(log);
        double powerOfTwo = Math.pow(2, roundLog);
        return  powerOfTwo;
    }
        public static Vector2 findVelocityCoefficientsForPoints(float speed, float x1, float y1, float x2, float y2){
        float run=x2-x1;
        float rise=y2-y1;
        float angle= (float) Math.atan2(rise, run);
        float  xCofficent=(float)(Math.abs(Math.cos(angle)));
        float  yCofficent=(float)(Math.abs(Math.sin(angle)));
        return new Vector2(xCofficent,yCofficent);
    }
    public  static boolean overLaps(double min1,  double max1, double min2, double max2) {
        if(min1>=max1 && min1<max2){
            return true;
        }
        if(max2<=min1 && max2>max1){
            return true;
        }
        return false;
    }

    /**
     * converts a double in radians to degrees
     * if degrees>360 subtracts 360 from the number to get the compass circle degrees
     * @param radians
     * @return
     */
    public static  double radiansToDegrees( double radians){
       double   degrees=Math.toDegrees(radians);
       if(degrees>360){
           degrees=degrees-360;
       }

       return degrees;


    }

    /**
     * calculates the directional difference between two points  point 1 and point 2
     * @param point1X
     * @param point1Y
     * @param point2X
     * @param point2Y
     * @return
     */
    public static Direction findDirectionBetweenPoints(int point1X, int point1Y, int point2X, int point2Y){
    int pointsXDifference = point1X - point2X;
     int pointsYDifference = point1Y - point2Y;
    Direction direction=Direction.SAME;
        if (pointsYDifference > 0 && pointsXDifference == 0) {
        direction=Direction.UP;
    } else if (pointsYDifference < 0 && pointsXDifference == 0) {
        direction=Direction.DOWN;
    } else if (pointsXDifference < 0 && pointsYDifference == 0) {
        direction=Direction.RIGHT;
    } else if (pointsXDifference > 0 && pointsYDifference == 0) {
        direction=Direction.LEFT;
    } else if (pointsYDifference < 0 && pointsXDifference > 0) {
        direction=Direction.LEFTDOWN;
    } else if (pointsYDifference > 0 && pointsXDifference > 0) {
        direction=Direction.LEFTUP;
    } else if (pointsYDifference < 0 && pointsXDifference < 0) {
        direction=Direction.RIGHTDOWN;
    } else if (pointsYDifference > 0 && pointsXDifference < 0) {
        direction=Direction.RIGHTUP;
    } ;
        return  direction;
}
}

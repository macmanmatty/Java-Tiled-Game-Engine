package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.Maps.World;

public class PositionUtils {

    private  PositionUtils() {
    }

    public static Vector2 getFacingEdge(PositionComponent position){
        float x=0;
        float y=0;
         Direction direction=position.getDirection();
         float screenLocationY=position.getLocationY();
         float screenLocationX=position.getLocationX();
         Rectangle boundingBox=position.getBoundsBoundingRectangle();

        switch (direction){
            case UP:
                x=screenLocationX+(boundingBox.x/2);
                y=screenLocationY+boundingBox.y;

                break;
            case RIGHT:
                x=screenLocationX+(boundingBox.x);
                y=screenLocationY+(boundingBox.y/2);
                break;
            case DOWN:
                x=screenLocationX+(boundingBox.x/2);
                y=screenLocationY;
                break;
            case LEFT:
                x=screenLocationX;
                y=screenLocationY+(boundingBox.y/2);
                break;
            case RIGHTUP:
                x=screenLocationX+(boundingBox.x);
                y=screenLocationY+boundingBox.y;
                break;
            case RIGHTDOWN:
                x=screenLocationX+(boundingBox.x/2);
                y=screenLocationY;
                break;
            case LEFTUP:
                x=screenLocationX;
                y=screenLocationY+boundingBox.y;
                break;
            case LEFTDOWN:
                x=screenLocationX;
                y=screenLocationY;
                break;
            case SAME:
                x=screenLocationX;
                y=screenLocationY;
        }


        return  new Vector2(x,y);
    }


    public PositionComponent getStartingPosition(World world, PositionComponent creatingEntityPosition, PositionComponent createdEntityPosition, Direction direction){


        Rectangle parentEntityBounds=creatingEntityPosition.getBoundsBoundingRectangle();
        float parentEntityScreenLocationX=creatingEntityPosition.getLocationX();
        float parentEntityScreenLocationY=creatingEntityPosition.getLocationY();
        float parentEntityHeight=creatingEntityPosition.getHeight();
        float parentEntityHeightFromGround=creatingEntityPosition.getHeightFromGround();
        return createdEntityPosition;





    }


}

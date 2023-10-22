package com.jessematty.black.tower.GameBaseClasses.Entity.Functions;

import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Moved;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Other.ZRPGCharacter;

public class CharacterMoveFunctions {

    /**
     * stops all player movement
     * by removing the MovingOnGround Component
     * and setting speed to zero
     * also sets action to rest
     * @param player
     */
    public static void stop(ZRPGCharacter player) {
        if (player.getMovableComponent().getCurrentSpeed() > 0) {
            player.getPlayerEntity().remove(MovingOnGroundComponent.class);
            player.getPlayerEntity().remove(Moved.class);
            player.getActionComponent().setStat("rest");
            player.getMovableComponent().setMoved(false);
            player.getMovableComponent().setCurrentSpeed(0);
        }
        player.getZRPGBrainComponen().getZrpgAIActions().clear();


    }

    /**
     * moves player right
     * @param player
     */
    public static void moveRight (ZRPGCharacter player){
            player.getPlayerEntity().add(new MovingOnGroundComponent());
            player.getActionComponent().setStat("move");
            player.getMovableComponent().moveRight();
        }


    /**
     * moves player left
     * @param player
     */
        public static void moveLeft (ZRPGCharacter player){
     
            player.getPlayerEntity().add(new MovingOnGroundComponent());
            player.getActionComponent().setStat("move");
            player.getMovableComponent().moveLeft();
        }
    /**
     * moves player up
     * @param player
     */
        public static void moveUp (ZRPGCharacter player){
     
            player.getPlayerEntity().add(new MovingOnGroundComponent());
            player.getActionComponent().setStat("move");
            player.getMovableComponent().moveUp();
        }
    /**
     * moves player down
     * @param player
     */
        public static void moveDown (ZRPGCharacter player){
     
            player.getPlayerEntity().add(new MovingOnGroundComponent());
            player.getActionComponent().setStat("move");
            player.getMovableComponent().moveDown();
        }
        public static void moveRightUp (ZRPGCharacter player){
     
            player.getPlayerEntity().add(new MovingOnGroundComponent());
            player.getActionComponent().setStat("move");
            player.getMovableComponent().moveRightUp();
        }
        public static void moveLeftUp (ZRPGCharacter player){
     
            player.getPlayerEntity().add(new MovingOnGroundComponent());
            player.getActionComponent().setStat("move");
            player.getMovableComponent().moveLeftUp();
        }
        public static void moveLeftDown (ZRPGCharacter player){
     
            player.getPlayerEntity().add(new MovingOnGroundComponent());
            player.getActionComponent().setStat("move");
            player.getMovableComponent().moveLeftDown();
        }
        public static void moveRightDown (ZRPGCharacter player){
     
            player.getPlayerEntity().add(new MovingOnGroundComponent());
            player.getActionComponent().setStat("move");
            player.getMovableComponent().moveRightDown();
        }

    /**
     * increases player move speed
     * by adding one to the current speed value
     * @param player
     */
    public static void increaseSpeed (ZRPGCharacter player){
     
            NumericStat speed= player.getNumericStats().getNumericStat("speed");
            speed.addValue(1);
        }

    /**
     * decreases player move speed
     * @param player
     */
    public static void decreaseSpeed (ZRPGCharacter player){
     
            NumericStat speed= player.getNumericStats().getNumericStat("speed");
            speed.subtractValue(1);
        }

    /**
     * method that moves a RPG Character  object to given x, y location on the map
     *
     * @param x the x location to move to
     * @param y the y location to move to
     * @return a boolean that stats whether or not  the move can be completed
     */
    public static boolean moveToLocation(float x, float y){



        return false;

        }

}

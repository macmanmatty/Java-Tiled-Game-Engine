package com.jessematty.black.tower.AI;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Other.ZRPGCharacter;

/**
 * base class for zelda style rpg actions
 */
public abstract class ZRPGAIAction {
    /**
     * the rpg Character to act on
     */
    protected  ZRPGCharacter zrpgCharacter;
    /**
     * the Character's Entity Object
     */
    protected Entity entity;
    /**
     * the Character's Brain Object
     */
   protected ZRPGBrainComponent zrpgBrainComponent;
    /**
     * whether or not to allow more tha one instance  of this action in a
     * Characters Brain
     */
   protected   boolean allowDuplicateActions;

    /**
     * if true this action is locked and can't be removed from the queue and
     * must stay at position zero
     */
   protected boolean locked;

    /**
     *
     * the counter for the number of turns;
     */
    int turnCounter;

    /**
     *
     * the number of turns it takes to preform this action
     */
      int turns;


    public ZRPGAIAction(ZRPGCharacter zrpgCharacter) {
        this.zrpgCharacter = zrpgCharacter;
        zrpgBrainComponent =zrpgCharacter.getZRPGBrainComponen();
        this.entity=zrpgCharacter.getPlayerEntity();
    }

    /**
     * the internal  act method called each loop
     * returns an int that specifies what position in the Queue that the
     * AI method should be placed after acting;
     * -1 means to permanently remove this action from the list of actions
     * @param deltaTime libGDX render method  delta time
     * @return
     */
    protected  abstract int act(float deltaTime);

    public void actAI(float deltaTime){
        turnCounter++;
        int place= act(deltaTime);
       if(place<0 && !locked) {
           zrpgBrainComponent.getZrpgAIActions().removeValue(this, false);
       }
       else if(!locked){
           Array<ZRPGAIAction> zrpgaiActions=zrpgBrainComponent.getZrpgAIActions();
           if(place>=zrpgaiActions.size){
               place=zrpgaiActions.size-1;
           }
           zrpgaiActions.set(place, this);


       }
       else{
           zrpgBrainComponent.getZrpgAIActions().set(0, this);
       }

    }

    public boolean isAllowDuplicateActions() {
        return allowDuplicateActions;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }
}

package com.jessematty.black.tower.AI;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.ZRPGCharacter;

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
   protected  Brain brain;
    /**
     * whether or not to allow more tha one instance  of this action in a
     * Characters Brain
     */
   protected   boolean allowDuplicateActions;
    public ZRPGAIAction(ZRPGCharacter zrpgCharacter) {
        this.zrpgCharacter = zrpgCharacter;
        brain =zrpgCharacter.getBrain();
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
       int place= act(deltaTime);
        brain.getZrpgaiActions().removeValue(this, false);
        if(place>-1) {
            brain.getZrpgaiActions().insert(place, this);
        }

    }

    public boolean isAllowDuplicateActions() {
        return allowDuplicateActions;
    }


}

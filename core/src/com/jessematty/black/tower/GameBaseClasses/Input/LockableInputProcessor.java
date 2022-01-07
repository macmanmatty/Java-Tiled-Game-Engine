package com.jessematty.black.tower.GameBaseClasses.Input;

import com.badlogic.gdx.InputProcessor;
/**
interface for locking input to allow only one input processor to be used at  a time
 **/
public interface LockableInputProcessor extends InputProcessor {
    /**
     *
     * getters and setters for the locking boolean variables
     */
    boolean isMouseInputLocked();
    void setMouseInputLocked(boolean locked);
    boolean isKeyInputLocked();
    void setKeyInputLocked(boolean locked);

}


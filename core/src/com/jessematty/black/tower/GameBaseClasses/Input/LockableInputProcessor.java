package com.jessematty.black.tower.GameBaseClasses.Input;

import com.badlogic.gdx.InputProcessor;
// interface for locking input to prevent usage
public interface LockableInputProcessor extends InputProcessor {
    boolean isMouseInputLocked();
    void setMouseInputLocked(boolean locked);
    boolean isKeyInputLocked();
    void setKeyInputLocked(boolean locked);

}


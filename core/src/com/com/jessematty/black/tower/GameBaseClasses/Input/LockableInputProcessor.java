package com.jessematty.black.tower.GameBaseClasses.Input;

import com.badlogic.gdx.InputProcessor;
/**
interface for locking input to allow only one input processor to be used at  a time
 when a processor is locked  none of it's locked input methods will be processed
 * you can lock either the mouse input key input or both
 * only one input mode is locked the other  input mode will still be processed
 * any LockableInputProcessor will automatically have it's  key input locked
 * when a TextField  or other text entering field has the keyboard focus of a libGDX Stage
 * @see LockableInputMultiplexer
 **/
public interface LockableInputProcessor extends InputProcessor {
    /**
     *
     * getters and setters for the locking boolean variables
     *
     */
    boolean isMouseInputLocked();
    void setMouseInputLocked(boolean locked);
    boolean isTouchUpMouseInputLocked();
    void setTouchDownMouseInputLocked(boolean locked);
    boolean isTouchDownMouseInputLocked();
    void setTouchUpMouseInputLocked(boolean locked);
    boolean isMouseMovedMouseInputLocked();
    void setMouseMovedMouseInputLocked(boolean locked);
    boolean isScrolledMouseInputLocked();
    void setScrolledMouseInputLocked(boolean locked);
    boolean isTouchDraggedMouseInputLocked();
    void setTouchDraggedMouseInputLocked(boolean locked);
    boolean isKeyInputLocked();
    void setKeyInputLocked(boolean locked);
    boolean isKeyTypedKeyInputLocked();
    void setKeyTypedKeyInputLocked(boolean keyTypedKeyInputLocked);
    boolean isKeyUpKeyInputLocked();
    void setKeyUpKeyInputLocked(boolean keyUpKeyInputLocked);
    boolean isKeyDownKeyInputLocked();
    void setKeyDownKeyInputLocked(boolean keyDownKeyInputLocked);



}


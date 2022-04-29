package com.jessematty.black.tower.GameBaseClasses.Input;

/**
 * base abstract class for  a locking  Input Processor  with  the locking booleans implemented
 */
public abstract   class BaseLockingInputProcessor implements LockableInputProcessor {

    protected boolean mouseInputLocked;
    protected boolean keyInputLocked;
    protected boolean  touchUpMouseInputLocked;
    protected boolean touchDownMouseInputLocked;
    protected boolean scrolledMouseInputLocked;
    protected boolean mouseMovedMouseInputLocked;
    protected boolean touchDraggedMouseInputLocked;
    private boolean keyTypedKeyInputLocked;
    private boolean keyUpKeyInputLocked;
    private boolean keyDownKeyInputLocked;
    @Override
    public boolean isMouseInputLocked() {
        return mouseInputLocked;
    }

    @Override
    public void setMouseInputLocked(boolean mouseInputLocked) {
        this.mouseInputLocked = mouseInputLocked;
    }

    @Override
    public boolean isKeyInputLocked() {
        return keyInputLocked;
    }

    @Override
    public void setKeyInputLocked(boolean keyInputLocked) {
        this.keyInputLocked = keyInputLocked;
    }

    @Override
    public boolean isTouchUpMouseInputLocked() {
        return touchUpMouseInputLocked;
    }

    @Override
    public void setTouchUpMouseInputLocked(boolean touchUpMouseInputLocked) {
        this.touchUpMouseInputLocked = touchUpMouseInputLocked;
    }

    @Override
    public boolean isTouchDownMouseInputLocked() {
        return touchDownMouseInputLocked;
    }

    @Override
    public void setTouchDownMouseInputLocked(boolean touchDownMouseInputLocked) {
        this.touchDownMouseInputLocked = touchDownMouseInputLocked;
    }

    @Override
    public boolean isScrolledMouseInputLocked() {
        return scrolledMouseInputLocked;
    }

    @Override
    public void setScrolledMouseInputLocked(boolean scrolledMouseInputLocked) {
        this.scrolledMouseInputLocked = scrolledMouseInputLocked;
    }

    @Override
    public boolean isMouseMovedMouseInputLocked() {
        return mouseMovedMouseInputLocked;
    }

    @Override
    public void setMouseMovedMouseInputLocked(boolean mouseMovedMouseInputLocked) {
        this.mouseMovedMouseInputLocked = mouseMovedMouseInputLocked;
    }

    @Override
    public boolean isTouchDraggedMouseInputLocked() {
        return touchDraggedMouseInputLocked;
    }

    @Override
    public void setTouchDraggedMouseInputLocked(boolean touchDraggedMouseInputLocked) {
        this.touchDraggedMouseInputLocked = touchDraggedMouseInputLocked;
    }

    @Override
    public boolean isKeyTypedKeyInputLocked() {
        return keyTypedKeyInputLocked;
    }

    @Override
    public void setKeyTypedKeyInputLocked(boolean keyTypedKeyInputLocked) {
        this.keyTypedKeyInputLocked = keyTypedKeyInputLocked;
    }

    @Override
    public boolean isKeyUpKeyInputLocked() {
        return keyUpKeyInputLocked;
    }

    @Override
    public void setKeyUpKeyInputLocked(boolean keyUpKeyInputLocked) {
        this.keyUpKeyInputLocked = keyUpKeyInputLocked;
    }

    @Override
    public boolean isKeyDownKeyInputLocked() {
        return keyDownKeyInputLocked;
    }

    @Override
    public void setKeyDownKeyInputLocked(boolean keyDownKeyInputLocked) {
        this.keyDownKeyInputLocked = keyDownKeyInputLocked;
    }


}

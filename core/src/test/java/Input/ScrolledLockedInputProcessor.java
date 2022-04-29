package Input;

import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputProcessor;

public class ScrolledLockedInputProcessor implements LockableInputProcessor {
    String text="";



    @Override
    public boolean isMouseInputLocked() {
        return false;
    }

    @Override
    public void setMouseInputLocked(boolean locked) {

    }

    @Override
    public boolean isTouchUpMouseInputLocked() {
        return false;
    }

    @Override
    public void setTouchDownMouseInputLocked(boolean locked) {

    }

    @Override
    public boolean isTouchDownMouseInputLocked() {
        return false;
    }

    @Override
    public void setTouchUpMouseInputLocked(boolean locked) {

    }

    @Override
    public boolean isMouseMovedMouseInputLocked() {
        return true;
    }

    @Override
    public void setMouseMovedMouseInputLocked(boolean locked) {

    }

    @Override
    public boolean isScrolledMouseInputLocked() {
        return false;
    }

    @Override
    public void setScrolledMouseInputLocked(boolean locked) {

    }

    @Override
    public boolean isTouchDraggedMouseInputLocked() {
        return false;
    }

    @Override
    public void setTouchDraggedMouseInputLocked(boolean locked) {

    }

    @Override
    public boolean isKeyInputLocked() {
        return false;
    }

    @Override
    public void setKeyInputLocked(boolean locked) {

    }

    @Override
    public boolean isKeyTypedKeyInputLocked() {
        return false;
    }

    @Override
    public void setKeyTypedKeyInputLocked(boolean keyTypedKeyInputLocked) {

    }

    @Override
    public boolean isKeyUpKeyInputLocked() {
        return false;
    }

    @Override
    public void setKeyUpKeyInputLocked(boolean keyUpKeyInputLocked) {

    }

    @Override
    public boolean isKeyDownKeyInputLocked() {
        return false;
    }

    @Override
    public void setKeyDownKeyInputLocked(boolean keyDownKeyInputLocked) {

    }

    @Override
    public boolean keyDown(int keycode) {

        text="Key Down";
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        text="Key Up";

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        text="Key  Typed";

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        text="Touch Down";

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        text="Touch Up";

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        text="Touch Dragged";

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        text="Moused Moved";

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        text="Scrolled";

        return false;
    }

    public String getText() {
        return text;
    }


}

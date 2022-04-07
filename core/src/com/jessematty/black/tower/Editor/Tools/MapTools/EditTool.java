package com.jessematty.black.tower.Editor.Tools.MapTools;

import com.badlogic.gdx.InputProcessor;
import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputProcessor;

public  abstract class EditTool implements LockableInputProcessor {
    boolean mouseInputLocked;
    boolean keyInputLocked;


    @Override
    public boolean isMouseInputLocked() {
        return mouseInputLocked;
    }

    @Override
    public void setMouseInputLocked(boolean locked) {
        this.mouseInputLocked=locked;

    }

    @Override
    public boolean isKeyInputLocked() {
        return keyInputLocked;
    }

    @Override
    public void setKeyInputLocked(boolean locked) {

        this.keyInputLocked=locked;
    }
}

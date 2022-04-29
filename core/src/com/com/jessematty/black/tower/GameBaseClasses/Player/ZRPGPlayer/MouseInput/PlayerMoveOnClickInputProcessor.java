package com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer.MouseInput;

import com.jessematty.black.tower.Components.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Input.BaseLockingInputProcessor;
import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputProcessor;

public   class PlayerMoveOnClickInputProcessor extends BaseLockingInputProcessor {

    private ZRPGCharacter player;

    public PlayerMoveOnClickInputProcessor(ZRPGCharacter player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

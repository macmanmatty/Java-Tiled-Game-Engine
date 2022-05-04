package com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer.MouseInput;

import com.badlogic.gdx.Game;
import com.jessematty.black.tower.AI.Movement.MoveToLocation;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Input.BaseLockingInputProcessor;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MathUtilities;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public   class PlayerMoveOnClickInputProcessor extends BaseLockingInputProcessor {

    private ZRPGCharacter player;
    private GameMap map;


    public PlayerMoveOnClickInputProcessor(ZRPGCharacter player, GameMap map) {
        this.player = player;
        this.map=map;
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
        LandSquareTile tile= map.screenToTile(screenX, screenY);
        player.getBrain().addAction(new MoveToLocation(player, tile));
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

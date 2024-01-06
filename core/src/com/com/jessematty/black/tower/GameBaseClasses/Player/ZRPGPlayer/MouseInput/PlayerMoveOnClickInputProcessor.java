package com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer.MouseInput;

import com.jessematty.black.tower.AI.Movement.MoveToLocation;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Input.BaseLockingInputProcessor;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public   class PlayerMoveOnClickInputProcessor extends BaseLockingInputProcessor {

    private ZRPGCharacter player;
    private MapDraw mapDraw;
    private int clicks;
    private MoveToLocation moveToLocation;


    public PlayerMoveOnClickInputProcessor(ZRPGCharacter player, MapDraw mapDraw) {
        this.player = player;
        this.mapDraw = mapDraw;
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
            LandSquareTile tile = mapDraw.screenToTile(screenX, screenY);
            if(moveToLocation!=null){
                player.getZrpgBrainComponent().removeAction(moveToLocation);
            }
            moveToLocation = new MoveToLocation(player, mapDraw.getCurrentMap(), tile);
            player.getZrpgBrainComponent().addAction(moveToLocation);
            player.getMovableComponent().setCurrentSpeed(20);
            player.getPlayerEntity().add(new MovingOnGroundComponent());


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
        LandSquareTile tile= mapDraw.screenToTile(screenX, screenY);



        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }


    public boolean scrolled(int amount) {
        return false;
    }
}

package com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer.MouseInput;

import com.jessematty.black.tower.AI.Movement.MoveToLocation;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Entity.Functions.CharacterMoveFunctions;
import com.jessematty.black.tower.GameBaseClasses.Input.BaseLockingInputProcessor;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.Print;
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
                player.getBrain().removeAction(moveToLocation);
            }
            moveToLocation = new MoveToLocation(player, mapDraw.getCurrentMap(), tile);
            player.getBrain().addAction(moveToLocation);
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

        if(tile!=null) {
            Print.printXY("tile location ", tile.getLocationX(), tile.getLocationY());
        }
        else{
            System.out.println("Tile is  Null!!!!???");
        }

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

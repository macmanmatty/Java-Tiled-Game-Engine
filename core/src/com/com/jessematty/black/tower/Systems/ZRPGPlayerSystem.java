package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Moved;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGround;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.AttachEntity.Holdable;
import com.jessematty.black.tower.Components.Ingestable;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.AttachEntity.Loadable;
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.Components.Readable;
import com.jessematty.black.tower.Components.Shootable;
import com.jessematty.black.tower.Components.Slashable;
import com.jessematty.black.tower.Components.SpellCastable;
import com.jessematty.black.tower.Components.Throwable;
import com.jessematty.black.tower.Components.Thrustable;
import com.jessematty.black.tower.Components.ZRPGPlayerButtonModes;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeys;
import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputProcessor;
import com.jessematty.black.tower.GameBaseClasses.Settings.GameSettings.GameInputKeys;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer.ZRPGPlayerFunctions;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Actors.ZRPGActors;


public class ZRPGPlayerSystem extends GameEntitySystem  implements  LockableInputProcessor, InputKeys {
    private ZRPGPlayer player;
   private GameInputKeys gameInputKeys;
   private int handNumber=1;
   private Entity [] items= new Entity[2];
   boolean mouseLocked;
   boolean keyLocked;
   private ComponentMapper<Thrustable > thrustableComponentMapper;
   private ComponentMapper<Ingestable> ingestableComponentMapper;
   private ComponentMapper<Throwable> throwableComponentMapper;
   private ComponentMapper<Readable> readableComponentMapper;
   private ComponentMapper<Slashable> slashableComponentMapper;
   private ComponentMapper<Shootable> shootableComponentMapper;
   private ComponentMapper<SpellCastable> spellCastableComponentMapper;
   private ComponentMapper <Loadable> loadableComponentMapper;
   private ComponentMapper<Item> itemComponentMapper;
   private ComponentMapper<Holdable> holdableComponentMapper;
   private ComponentMapper<Attachable> attachableComponentMapper;
   private ZRPGPlayerFunctions playerFunctions;
   private ZRPGActors zrpgActors;

   private Vector3 target;
    public ZRPGPlayerSystem(MapDraw draw) {
        super(draw);
        this.gameInputKeys=draw.getGameAssets().getSettings().getGameInputKeys();

        zrpgActors=new ZRPGActors(draw);


    }


    @Override
    public void addedToEngine(Engine engine) {
        slashableComponentMapper=GameComponentMapper.getSlashableComponentMapper();
        shootableComponentMapper=GameComponentMapper.getShootableComponentMapper();
        thrustableComponentMapper=GameComponentMapper.getThrustableComponentMapper();
        ingestableComponentMapper=GameComponentMapper.getIngestableComponentMapper();
        readableComponentMapper=GameComponentMapper.getReadableComponentMapper();
        spellCastableComponentMapper=GameComponentMapper.getSpellCastableComponentMapper();
        itemComponentMapper=GameComponentMapper.getItemComponentMapper();
        holdableComponentMapper=GameComponentMapper.getHoldableComponentMapper();
        attachableComponentMapper=GameComponentMapper.getAttachableComponentMapper();
        throwableComponentMapper=GameComponentMapper.getThrowableComponentMapper();
        loadableComponentMapper=GameComponentMapper.getLoadableComponentMapper();
    }

    @Override
    public void update(float deltaTime) {



    }



    @Override
    public boolean keyDown(int keycode) {


        if (keycode == gameInputKeys.getMoveRightKey()&& player.getAction().isActing()==false   ) {
          playerFunctions.moveRight();



        } else if (keycode == gameInputKeys.getMoveLeftKey() && player.getAction().isActing()==false) {

            playerFunctions.moveLeft();





        } else if (keycode == gameInputKeys.getMoveUpKey() && player.getAction().isActing()==false) {
          playerFunctions.moveUp();




        } else if (keycode == gameInputKeys.getMoveDownKey() && player.getAction().isActing()==false) {
          playerFunctions.moveDown();


        } else if (keycode == gameInputKeys.getMoveRightUpKey() && player.getAction().isActing()==false) {
          playerFunctions.moveRightUp();


        } else if (keycode == gameInputKeys.getMoveLeftUpKey() && player.getAction().isActing()==false) {
            playerFunctions.moveLeftUp();


        } else if (keycode == gameInputKeys.getMoveLeftDownKey() && player.getAction().isActing()==false) {
            playerFunctions.moveLeftDown();



        } else if (keycode == gameInputKeys.getMoveRightDownKey() && player.getAction().isActing()==false) {
           playerFunctions.moveRightDown();



        }
        else if (keycode == gameInputKeys.getEatKey() && player.getAction().isActing()==false) {


           playerFunctions.eatItem(player.getHandToUse());

        }
        else if (keycode == gameInputKeys.getThrowKey() && player.getAction().isActing()==false) {
           playerFunctions.throwItem(player.getHandToUse());

        }
        else if (keycode == gameInputKeys.getSlashKey() && player.getAction().isActing()==false) {
            playerFunctions.slashItem(handNumber);
            System.out.println("slashed");

        }

        else if (keycode == gameInputKeys.getThrustKey() && player.getAction().isActing()==false) {
            playerFunctions.thrustItem(handNumber);

        }
        else if (keycode == gameInputKeys.getShootKey() && player.getAction().isActing()==false) {
            playerFunctions.shootItem(handNumber);

        }





        else if (keycode == gameInputKeys.getPickUpItemKey() && player.getAction().isActing()==false ) {
            playerFunctions.pickupItem();



        }
        else if (keycode == gameInputKeys.getChangeHoldPositionKey() && player.getAction().isActing()==false ) {
            playerFunctions.changeHoldPosition(handNumber);
            System.out.println("Hold Position Changed");



        }


        else if (keycode == gameInputKeys.getChangeHandNumberKey() && player.getAction().isActing()==false) {

            if(handNumber==1) {
                handNumber = 0;
            }
            else{
                handNumber=1;
            }
            System.out.println("Current Hand Number: "+handNumber);

        }

        else if (keycode == gameInputKeys.getIncreaseSpeedKey()) {
            player.getSpeed().addValues(1, 0, 0);


        } else if (keycode ==gameInputKeys.getDecreaseSpeedKey() ){
            player.getSpeed().addValues(-1, 0, 0);


        }

        return true;
    }
    
    




         

  
    @Override
    public boolean keyUp(int keycode) {

       if ( gameInputKeys.isAMoveKey(keycode)) {
            player.getPlayerEntity().remove(MovingOnGround.class);
           player.getPlayerEntity().remove(Moved.class);

           player.getAction().setStat("rest");
            player.getMovableComponent().setMoved(false);
            System.out.println("Key up!!");




        }

        return true;
    }



    @Override
    public boolean keyTyped(char c) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector3 unProjectedCoordinates = getDraw().getGameCamera().unproject(new Vector3(screenX, screenY, 0));

        if(button== Buttons.LEFT){


        }

        if(button==Buttons.RIGHT) {

            ZRPGPlayerButtonModes buttonModes=player.getButtonMode();
            switch(buttonModes){


                case TARGET:
                    playerFunctions.target(unProjectedCoordinates.x, unProjectedCoordinates.y);
                    break;
                case INFO:
                    break;
                case SELECT:
                    break;
            }
        }



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

        if(false) {
            Image targetImage = zrpgActors.getTargetImage();

            if (player.getButtonMode() == ZRPGPlayerButtonModes.TARGET) {
                targetImage.setPosition(screenX, screenY);
                targetImage.setVisible(true);

            } else {
                targetImage.setVisible(false);

            }
        }



        return  true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public ZRPGPlayer getPlayer() {
        return player;
    }

    public void setPlayer(ZRPGPlayer player) {
        this.player = player;
        playerFunctions= new ZRPGPlayerFunctions(player, getDraw());


    }

    @Override
    public boolean isMouseInputLocked() {
        return mouseLocked;
    }

    @Override
    public void setMouseInputLocked(boolean locked) {

        this.mouseLocked =locked;
    }

    @Override
    public boolean isKeyInputLocked() {
        return keyLocked;
    }

    @Override
    public void setKeyInputLocked(boolean locked) {

        this.keyLocked=locked;

    }

    @Override
    public Array<InputKeyCombo> getKeys() {
        return playerFunctions.getPlayerControlFunctions();

    }
}

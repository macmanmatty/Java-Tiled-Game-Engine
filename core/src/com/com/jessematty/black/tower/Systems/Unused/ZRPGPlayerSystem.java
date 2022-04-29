package com.jessematty.black.tower.Systems.Unused;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Moved;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.AttachEntity.Holdable;
import com.jessematty.black.tower.Components.Ingestable;
import com.jessematty.black.tower.Components.AttachEntity.Loadable;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.ZRPGCharacter;
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
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class ZRPGPlayerSystem extends GameEntitySystem implements  LockableInputProcessor, InputKeys {
    private ZRPGCharacter player;
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
   private ComponentMapper<ItemComponent> itemComponentMapper;
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
//        if (keycode == gameInputKeys.getMoveRightKey()&& player.getActionComponent().isActing()==false   ) {
//         // playerFunctions.moveRight();
//        } else if (keycode == gameInputKeys.getMoveLeftKey() && player.getActionComponent().isActing()==false) {
//           // playerFunctions.moveLeft();
//        } else if (keycode == gameInputKeys.getMoveUpKey() && player.getActionComponent().isActing()==false) {
//          //playerFunctions.moveUp();
//        } else if (keycode == gameInputKeys.getMoveDownKey() && player.getActionComponent().isActing()==false) {
//          //playerFunctions.moveDown();
//        } else if (keycode == gameInputKeys.getMoveRightUpKey() && player.getActionComponent().isActing()==false) {
//          //playerFunctions.moveRightUp();
//        } else if (keycode == gameInputKeys.getMoveLeftUpKey() && player.getActionComponent().isActing()==false) {
//           // playerFunctions.moveLeftUp();
//        } else if (keycode == gameInputKeys.getMoveLeftDownKey() && player.getActionComponent().isActing()==false) {
//           // playerFunctions.moveLeftDown();
//        } else if (keycode == gameInputKeys.getMoveRightDownKey() && player.getActionComponent().isActing()==false) {
//           //playerFunctions.moveRightDown();
//        }
//        else if (keycode == gameInputKeys.getEatKey() && player.getActionComponent().isActing()==false) {
//           playerFunctions.eatItem(player.getHandToUse());
//        }
//        else if (keycode == gameInputKeys.getThrowKey() && player.getActionComponent().isActing()==false) {
//           playerFunctions.throwItem(player.getHandToUse());
//        }
//        else if (keycode == gameInputKeys.getSlashKey() && player.getActionComponent().isActing()==false) {
//            playerFunctions.slashItem(handNumber);
//            System.out.println("slashed");
//        }
//        else if (keycode == gameInputKeys.getThrustKey() && player.getActionComponent().isActing()==false) {
//            playerFunctions.thrustItem(handNumber);
//        }
//        else if (keycode == gameInputKeys.getShootKey() && player.getActionComponent().isActing()==false) {
//            playerFunctions.shootItem(handNumber);
//        }
//        else if (keycode == gameInputKeys.getPickUpItemKey() && player.getActionComponent().isActing()==false ) {
//            playerFunctions.pickupItem();
//        }
//        else if (keycode == gameInputKeys.getChangeHoldPositionKey() && player.getActionComponent().isActing()==false ) {
//            playerFunctions.changeHoldPosition(handNumber);
//            System.out.println("Hold Position Changed");
//        }
//        else if (keycode == gameInputKeys.getChangeHandNumberKey() && player.getActionComponent().isActing()==false) {
//            if(handNumber==1) {
//                handNumber = 0;
//            }
//            else{
//                handNumber=1;
//            }
//            System.out.println("Current Hand Number: "+handNumber);
//        }
//        else if (keycode == gameInputKeys.getIncreaseSpeedKey()) {
//            player.getSpeed().addValues(10, 0, 0);
//        } else if (keycode ==gameInputKeys.getDecreaseSpeedKey() ){
//            player.getSpeed().addValues(-10, 0, 0);
//        }
        return true;
    }
    
    
         
  
    @Override
    public boolean keyUp(int keycode) {
       if ( gameInputKeys.isAMoveKey(keycode)) {
            player.getPlayerEntity().remove(MovingOnGroundComponent.class);
           player.getPlayerEntity().remove(Moved.class);
           player.getActionComponent().setStat("rest");
            player.getMovableComponent().setMoved(false);
            System.out.println("Key up!!");
        }
        return true;
    }
    @Override
    public boolean keyTyped(char c) {
        return keyDown(c);
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        Vector3 unProjectedCoordinates = getDraw().getGameCamera().unproject(new Vector3(screenX, screenY, 0));
//        if(button== Buttons.LEFT){
//        }
//        if(button==Buttons.RIGHT) {
//            ZRPGPlayerButtonModes buttonModes=player.getButtonMode();
//            switch(buttonModes){
//                case TARGET:
//                    playerFunctions.target(unProjectedCoordinates.x, unProjectedCoordinates.y);
//                    break;
//                case INFO:
//                    break;
//                case SELECT:
//                    break;
//            }
//        }
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
    public ZRPGCharacter getPlayer() {
        return player;
    }
    public void setPlayer(ZRPGCharacter player) {
        this.player = player;
        playerFunctions= new ZRPGPlayerFunctions(player);
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
        return false;
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
        return keyLocked;
    }
    @Override
    public void setKeyInputLocked(boolean locked) {
        this.keyLocked=locked;
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
    public Array<InputKeyCombo> getKeys() {
        return playerFunctions.getPlayerControlFunctions();
    }
}
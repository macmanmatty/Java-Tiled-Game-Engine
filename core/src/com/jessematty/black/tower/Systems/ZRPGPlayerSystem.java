package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGround;
import com.jessematty.black.tower.Components.Attachable;
import com.jessematty.black.tower.Components.Holdable;
import com.jessematty.black.tower.Components.Ingestable;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.Loadable;
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.Components.Readable;
import com.jessematty.black.tower.Components.Shootable;
import com.jessematty.black.tower.Components.Slashable;
import com.jessematty.black.tower.Components.SpellCastable;
import com.jessematty.black.tower.Components.Throwable;
import com.jessematty.black.tower.Components.Thrustable;
import com.jessematty.black.tower.Components.ZRPGPlayerButtonModes;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameSettings.GameInputKeys;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer.ZRPGPlayerFunctions;


public class ZRPGPlayerSystem extends GameEntitySystem  implements InputProcessor {



    private ZRPGPlayer player;
   private GameInputKeys gameInputKeys = new GameInputKeys();
   private int handNumber;
   private Entity [] items= new Entity[2];
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
    public ZRPGPlayerSystem(MapDraw draw) {
        super(draw);
    }


    @Override
    public void addedToEngine(Engine engine) {

        GameComponentMapper gameComponentMapper=getGameComponentMapper();
        slashableComponentMapper=gameComponentMapper.getSlashableComponentMapper();
        shootableComponentMapper=gameComponentMapper.getShootableComponentMapper();
        thrustableComponentMapper=gameComponentMapper.getThrustableComponentMapper();
        ingestableComponentMapper=gameComponentMapper.getIngestableComponentMapper();
        readableComponentMapper=gameComponentMapper.getReadableComponentMapper();
        spellCastableComponentMapper=gameComponentMapper.getSpellCastableComponentMapper();
        itemComponentMapper=getGameComponentMapper().getItemComponentMapper();
        holdableComponentMapper=getGameComponentMapper().getHoldableComponentMapper();
        attachableComponentMapper=getGameComponentMapper().getAttachableComponentMapper();
        throwableComponentMapper=getGameComponentMapper().getThrowableComponentMapper();
        loadableComponentMapper=getGameComponentMapper().getLoadableComponentMapper();
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



        } else if (keycode == gameInputKeys.getAttackKey() && player.getAction().isActing()==false) {





        } else if (keycode == gameInputKeys.getEatKey() && player.getAction().isActing()==false) {


           playerFunctions.eatItem(player.getHandToUse());

        } else if (keycode == gameInputKeys.getChangeAttackModeKey() && player.getAction().isActing()==false) {
            if(items[handNumber]!=null){

                if(items[handNumber].getComponent(Throwable.class)!=null){


                }


            }



        } else if (keycode == gameInputKeys.getThrowKey() && player.getAction().isActing()==false) {
           playerFunctions.throwItem(player.getHandToUse());

        }
        else if (keycode == gameInputKeys.getChangeHoldPositionKey() && player.getAction().isActing()==false) {


        }
        else if (keycode == gameInputKeys.getPickUpItemKey() && player.getAction().isActing()==false ) {
            playerFunctions.pickupItem();



        }


        else if (keycode == gameInputKeys.getChangeHandNumberKey() && player.getAction().isActing()==false) {
          playerFunctions.changeHandNumber();

        }

        else if (keycode == Keys.CONTROL_RIGHT) {
            player.getSpeed().addValues(1, 0, 0);


        } else if (keycode == Keys.ALT_RIGHT) {
            player.getSpeed().addValues(-1, 0, 0);


        }




         else if (keycode == Keys.Q) {




        } else if (keycode == Keys.B) {


        }


        return true;
    }
    
    




         

  
    @Override
    public boolean keyUp(int keycode) {
       if ( gameInputKeys.isAMoveKey(keycode)) {
            player.getPlayerEntity().remove(MovingOnGround.class);
            player.getAction().setStat("rest");
            player.getMovable().setMoved(false);
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
        Vector3 unProjectedCoordinates = getDraw().getCamera().unproject(new Vector3(screenX, screenY, 0));

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
        return false;
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
}

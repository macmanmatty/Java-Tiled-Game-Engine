package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Moved;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.AttachEntity.Holdable;
import com.jessematty.black.tower.Components.AttachEntity.Loadable;
import com.jessematty.black.tower.Components.Ingestable;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.Readable;
import com.jessematty.black.tower.Components.Shootable;
import com.jessematty.black.tower.Components.Slashable;
import com.jessematty.black.tower.Components.SpellCastable;
import com.jessematty.black.tower.Components.Throwable;
import com.jessematty.black.tower.Components.Thrustable;
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.Components.ZRPGPlayerButtonModes;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Settings.GameSettings.GameInputKeys;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer.ZRPGPlayerFunctions;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Actors.ZRPGActors;
public class PlayerSystem extends GameEntitySystem  implements InputProcessor {
    private ZRPGPlayer player;
   private GameInputKeys gameInputKeys;
   private int handNumber=1;
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
   private ZRPGActors zrpgActors;
   private Vector3 target;
    public PlayerSystem(MapDraw draw) {
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
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 unProjectedCoordinates = getDraw().getGameCamera().unproject(new Vector3(screenX, screenY, 0));
        if(button== Buttons.LEFT){
        }
        if(button==Buttons.RIGHT) {

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
        Image targetImage=zrpgActors.getTargetImage();
        if(player.getButtonMode()==ZRPGPlayerButtonModes.TARGET){
            targetImage.setPosition(screenX, screenY);
            targetImage.setVisible(true);
        }
        else{
            targetImage.setVisible(false);
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
}

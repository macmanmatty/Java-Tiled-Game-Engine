package com.jessematty.black.tower.Systems.Player;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.AttachEntity.Holdable;
import com.jessematty.black.tower.Components.Ingestable;
import com.jessematty.black.tower.Components.AttachEntity.Loadable;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.ZRPGCharacter;
import com.jessematty.black.tower.Components.Readable;
import com.jessematty.black.tower.Components.Attacks.Shootable;
import com.jessematty.black.tower.Components.Attacks.Slashable;
import com.jessematty.black.tower.Components.Attacks.SpellCastable;
import com.jessematty.black.tower.Components.Attacks.Throwable;
import com.jessematty.black.tower.Components.Attacks.Thrustable;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputProcessor;
import com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer.MouseInput.PlayerMoveOnClickInputProcessor;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer.ZRPGPlayerFunctions;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Actors.ZRPGActors;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class ZRPGPlayerSystem extends GameEntitySystem   {
    private ZRPGCharacter player;
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
   private ComponentMapper<ItemComponent> itemComponentMapper;
   private ComponentMapper<Holdable> holdableComponentMapper;
   private ComponentMapper<Attachable> attachableComponentMapper;
   private ZRPGPlayerFunctions playerFunctions;
   private ZRPGActors zrpgActors;
   private Vector3 target;
   private LockableInputProcessor mouseProcessor;
    public ZRPGPlayerSystem(MapDraw draw) {
        super(draw);
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
        super.update(deltaTime);
    }

    public ZRPGCharacter getPlayer() {
        return player;
    }

    public void setPlayer(ZRPGCharacter player) {
        this.player = player;
        mouseProcessor= new PlayerMoveOnClickInputProcessor(player, getDraw());
        GameAssets.getGameInput().getLockableInputMultiplexer().addProcessor(mouseProcessor);

    }
}

package com.jessematty.black.tower.Components.ZRPG;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.AI.ZRPGBrainComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Attacks.AttackMode;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.BodyParts.BodyComponent;
import com.jessematty.black.tower.Components.Containers.CharacterPacksComponent;
import com.jessematty.black.tower.Components.Other.AIComponent;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Position.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;

/**
 * class the represents a Zelda Like RPG Character
 * be it a Enemy, Friendly,  NPC, Quest Giver, Shop Owner or the Player
 * with convenience  access to commonly used components;
 */
public class ZRPGCharacter implements Component {
    /**
     * commonly used the components
     */
    private MovableComponent movableComponent;
    private  PositionComponent position;
    private  NumericStats numericStats;
    private  BooleanStats booleanStats;
    private  StringStats stringStats;
    private  OwnerComponent ownerComponent;
    private ZRPGBrainComponent zrpgBrainComponent;
    private String id;
    private PhysicalObjectComponent physicalObject;
    private CharacterPacksComponent packs= new CharacterPacksComponent();
    private  Entity playerEntity;
    private ActionComponent actionComponent;
    private AttackMode attackMode= new AttackMode("slash");
    private BodyComponent bodyComponent;
    private NameComponent nameComponent;
    private ImageComponent imageComponent;
    private boolean showBottomBar;
    private boolean autoPickUpFirstItem;
    private boolean autoAddToFirstPack;
    private boolean useItemWithoutHolding;
    private ZRPGPlayerButtonModes buttonMode;
    private String  currentHand="rightHand";
    public  ZRPGCharacter() {
    }

    public ZRPGCharacter(Entity playerEntity) {
        setFields(playerEntity);

    }

   public void setFields(Entity playerEntity){

        this.playerEntity = playerEntity;
        this.playerEntity.add(new ZRPGCharacterComponent());
        AIComponent aiComponent= GameComponentMapper.getAiComponentMapper().get(playerEntity);
        if (aiComponent==null){
            aiComponent=new AIComponent();
            playerEntity.add(aiComponent);
        }
        this.zrpgBrainComponent =aiComponent.getZRPGBrainComponen();
        this.movableComponent=GameComponentMapper.getMovableComponentMapper().get(playerEntity);
        this.position = GameComponentMapper.getPositionComponentMapper().get(playerEntity);
        this.numericStats = GameComponentMapper.getNumericStatsComponentMapper().get(playerEntity);
        this.booleanStats= GameComponentMapper.getBooleanStatsComponentMapper().get(playerEntity);
        this.stringStats= GameComponentMapper.getStringStatsComponentMapper().get(playerEntity);
        this.physicalObject = GameComponentMapper.getPhysicalObjectComponentMapper().get(playerEntity);
        this.actionComponent = GameComponentMapper.getActionComponentMapper().get(playerEntity);
        this.bodyComponent =GameComponentMapper.getBodyComponentComponentMapper().get(playerEntity);
        this.imageComponent=GameComponentMapper.getImageComponentMapper().get(playerEntity);
        this.ownerComponent= GameComponentMapper.getOwnerComponentComponentMapper().get(playerEntity);
        this.id=GameComponentMapper.getIdComponentMapper().get(playerEntity).getId();
        this.nameComponent = GameComponentMapper.getNameComponentMapper().get(playerEntity);
    }
    public MovableComponent getMovableComponent() {
        return movableComponent;
    }
    public PositionComponent getPositionComponent() {
        return position;
    }
    public NumericStats getNumericStats() {
        return numericStats;
    }
    public PhysicalObjectComponent getPhysicalObject() {
        return physicalObject;
    }
    public Entity getPlayerEntity() {
        return playerEntity;
    }
    public ActionComponent getActionComponent() {
        return actionComponent;
    }
    public AttackMode getAttackMode() {
        return attackMode;
    }
    public BooleanStats getBooleanStats() {
        return booleanStats;
    }
    public StringStats getStringStats() {
        return stringStats;
    }
    public StringStat getNameComponent() {
        return nameComponent;
    }
    public com.jessematty.black.tower.Components.AttachEntity.OwnerComponent getOwnerComponent() {
        return ownerComponent;
    }
    public void setOwnerComponent(OwnerComponent ownerComponent) {
        this.ownerComponent = ownerComponent;
    }
    public boolean isShowBottomBar() {
        return showBottomBar;
    }
    public void setShowBottomBar(boolean showBottomBar) {
        this.showBottomBar = showBottomBar;
    }
    public boolean isAutoPickUpFirstItem() {
        return autoPickUpFirstItem;
    }
    public void setAutoPickUpFirstItem(boolean autoPickUpFirstItem) {
        this.autoPickUpFirstItem = autoPickUpFirstItem;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public boolean isAutoAddToFirstPack() {
        return autoAddToFirstPack;
    }
    public void setAutoAddToFirstPack(boolean autoAddToFirstPack) {
        this.autoAddToFirstPack = autoAddToFirstPack;
    }
    public ZRPGPlayerButtonModes getButtonMode() {
        return buttonMode;
    }
    public void setButtonMode(ZRPGPlayerButtonModes buttonMode) {
        this.buttonMode = buttonMode;
    }
    public ImageComponent getImageComponent() {
        return imageComponent;
    }
    public ZRPGBrainComponent getZrpgBrainComponent() {
        return zrpgBrainComponent;
    }

    public PositionComponent getPosition() {
        return position;
    }


    public CharacterPacksComponent getPacks() {
        return packs;
    }

    public BodyComponent getBody() {
        return bodyComponent;
    }
    public String getCurrentHand() {
        return currentHand;
    }

    public void setCurrentHand(String currentHand) {
        this.currentHand = currentHand;
    }

    public boolean isUseItemWithoutHolding() {
        return useItemWithoutHolding;
    }

    public void setUseItemWithoutHolding(boolean useItemWithoutHolding) {
        this.useItemWithoutHolding = useItemWithoutHolding;
    }

}

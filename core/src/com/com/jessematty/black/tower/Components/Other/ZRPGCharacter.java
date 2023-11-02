package com.jessematty.black.tower.Components.Other;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.AI.ZRPGBrainComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Attacks.AttackMode;
import com.jessematty.black.tower.Components.Attacks.Thrower;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.BodyParts.Body;
import com.jessematty.black.tower.Components.BodyParts.Ears;
import com.jessematty.black.tower.Components.BodyParts.Eyes;
import com.jessematty.black.tower.Components.Containers.ContainerComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Maps.World;

/**
 * class the represents a rpg Character
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
    private ZRPGBrainComponent ZRPGBrainComponen;
    private Ears ears;
    private   Nose nose;
    private Eyes eyes;
    private String id;
    private  PhysicalObjectComponent physicalObject;
    private DrawableComponent drawableComponent;
    private  AnimatableComponent animatable;
    private Thrower thrower;
    private  Entity rightHand;
    private  Holder[]  handHolders= new Holder[2];
    private  Entity leftHand;
    private   Entity leftFoot;
    private  Entity rightFoot;
    private Array<Entity> packs= new Array<>();
    private  Entity playerEntity;
    private ActionComponent actionComponent;
    private AttackMode attackMode= new AttackMode("slash");
    private  Body body;
    private NameComponent nameComponent;
    private  World world;
    private  NumericStat speed;
    private ImageComponent imageComponent;
    private boolean showBottomBar;
    private boolean autoPickUpFirstItem;
    private DominateHand dominateHand;
    private boolean autoAddToFirstPack;
    private ZRPGPlayerButtonModes buttonMode;
    private int handToUse;
    public ZRPGCharacter(World world, Entity playerEntity) {
        this.playerEntity = playerEntity;
        AIComponent aiComponent= new AIComponent();
        this.ZRPGBrainComponen =aiComponent.getZRPGBrainComponen();
        playerEntity.add(aiComponent);
        this.world=world;
        this.movableComponent = playerEntity.getComponent(MovableComponent.class);
        this.position = playerEntity.getComponent(PositionComponent.class);
        this.numericStats = playerEntity.getComponent(com.jessematty.black.tower.Components.Stats.NumericStats.class);
        this.booleanStats= playerEntity.getComponent(com.jessematty.black.tower.Components.Stats.BooleanStats.class);
        this.stringStats= playerEntity.getComponent(com.jessematty.black.tower.Components.Stats.StringStats.class);
        this.physicalObject = playerEntity.getComponent(PhysicalObjectComponent.class);
        this.drawableComponent = playerEntity.getComponent(DrawableComponent.class);
        this.animatable = playerEntity.getComponent(com.jessematty.black.tower.Components.Animation.AnimatableComponent.class);
        this.actionComponent = playerEntity.getComponent(ActionComponent.class);
        this.ears= playerEntity.getComponent(Ears.class);
        this.eyes= playerEntity.getComponent(Eyes.class);
        this.nose= playerEntity.getComponent(Nose.class);
        this.body=playerEntity.getComponent(Body.class);
        this.imageComponent=playerEntity.getComponent(com.jessematty.black.tower.Components.Animation.ImageComponent.class);
        this.ownerComponent= playerEntity.getComponent(com.jessematty.black.tower.Components.AttachEntity.OwnerComponent.class);
        this.id=playerEntity.getComponent(EntityId.class).getId();
        this.rightHand=world.getEntity(body.getBodyParts().get("rightHand"));
        this.leftHand=world.getEntity(body.getBodyParts().get("leftHand"));
        System.out.println ("hands "+leftHand + ", " +rightHand);
        handHolders[1]=rightHand.getComponent(com.jessematty.black.tower.Components.AttachEntity.Holder.class);
        handHolders[0]=leftHand.getComponent(com.jessematty.black.tower.Components.AttachEntity.Holder.class);
        this.rightFoot=world.getEntity(body.getBodyParts().get("rightFoot"));
        this.leftFoot=world.getEntity(body.getBodyParts().get("leftFoot"));
        this.thrower= playerEntity.getComponent(com.jessematty.black.tower.Components.Attacks.Thrower.class);
        this.nameComponent = playerEntity.getComponent(NameComponent.class);
        this.speed=numericStats.getNumericStat("speed");
        Playable playable= playerEntity.getComponent(Playable.class);
        if(playable==null){
            playable= new Playable();
            playerEntity.add(playable);
        }
        playable.setCurrentPlayer(true);
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
    public DrawableComponent getDrawableComponent() {
        return drawableComponent;
    }
    public AnimatableComponent getAnimatable() {
        return animatable;
    }
    public Thrower getThrower() {
        return thrower;
    }
    public com.jessematty.black.tower.Components.AttachEntity.Holder[] getHolders() {
        return handHolders;
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
    public Holder[] getHandHolders() {
        return handHolders;
    }
    public Entity getLeftFoot() {
        return leftFoot;
    }
    public Entity getRightFoot() {
        return rightFoot;
    }
    public StringStat getNameComponent() {
        return nameComponent;
    }
    public Ears getEars() {
        return ears;
    }
    public Nose getNose() {
        return nose;
    }
    public Eyes getEyes() {
        return eyes;
    }
    public NumericStat getSpeed() {
        return speed;
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
    public DominateHand getDominateHand() {
        return dominateHand;
    }
    public void setDominateHand(DominateHand dominateHand) {
        this.dominateHand = dominateHand;
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
    public int getHandToUse() {
        return handToUse;
    }
    public void setHandToUse(int handToUse) {
        this.handToUse = handToUse;
    }
    public ImageComponent getImageComponent() {
        return imageComponent;
    }
    public ZRPGBrainComponent getZRPGBrainComponen() {
        return ZRPGBrainComponen;
    }

    public PositionComponent getPosition() {
        return position;
    }

    public Entity getRightHand() {
        return rightHand;
    }

    public Entity getLeftHand() {
        return leftHand;
    }

    public Array<Entity> getPacks() {
        return packs;
    }

    public Body getBody() {
        return body;
    }

    public World getWorld() {
        return world;
    }

    public Entity getHand(int i) {
        return null;
    }
}

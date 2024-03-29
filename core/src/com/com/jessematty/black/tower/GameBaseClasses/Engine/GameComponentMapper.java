package com.jessematty.black.tower.GameBaseClasses.Engine;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.AI.ZRPGBrainComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.CreateEntity;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Dying;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Ingest;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Shoot;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Slash;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.SpellCast;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Talk;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Thrust;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.AttachEntity.AttachEntityEvent;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.AttachEntity.DetachEntityEvent;
import com.jessematty.black.tower.Components.AttachEntity.EquipItem;
import com.jessematty.black.tower.Components.Item.ItemAction.HoldItemComponent;
import com.jessematty.black.tower.Components.AttachEntity.Holdable;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.AttachEntity.Loadable;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.AttachEntity.UnEquipItem;
import com.jessematty.black.tower.Components.AttachEntity.UnHoldItem;
import com.jessematty.black.tower.Components.Attacks.Breather;
import com.jessematty.black.tower.Components.Attacks.Launchable;
import com.jessematty.black.tower.Components.Attacks.Shootable;
import com.jessematty.black.tower.Components.Attacks.Slashable;
import com.jessematty.black.tower.Components.Attacks.SpellCastable;
import com.jessematty.black.tower.Components.Attacks.Throwable;
import com.jessematty.black.tower.Components.Attacks.Thrustable;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.BodyParts.BodyComponent;
import com.jessematty.black.tower.Components.BodyParts.Ears;
import com.jessematty.black.tower.Components.BodyParts.Eyes;
import com.jessematty.black.tower.Components.BodyParts.PartComponent;
import com.jessematty.black.tower.Components.ChangeImage.ChangeImageOnStatsValueChanges;
import com.jessematty.black.tower.Components.Components.AddComponent;
import com.jessematty.black.tower.Components.Containers.CharacterPacksComponent;
import com.jessematty.black.tower.Components.Containers.ContainerComponent;
import com.jessematty.black.tower.Components.Containers.PackComponent;
import com.jessematty.black.tower.Components.CreateEntity.CreateEntitiesOnBooleanStatChange;
import com.jessematty.black.tower.Components.CreateEntity.CreateEntitiesOnNumericStatChange;
import com.jessematty.black.tower.Components.CreateEntity.CreateEntitiesOnTime;
import com.jessematty.black.tower.Components.CreateEntity.CreateEntityOnAction;
import com.jessematty.black.tower.Components.EventComponents.AddItemToContainer;
import com.jessematty.black.tower.Components.EventComponents.RemoveItemFromContainer;
import com.jessematty.black.tower.Components.FlagComponents.AddedToEngine;
import com.jessematty.black.tower.Components.FlagComponents.BooleanStatChanged;
import com.jessematty.black.tower.Components.FlagComponents.NotAddedToEngine;
import com.jessematty.black.tower.Components.FlagComponents.NumericStatChanged;
import com.jessematty.black.tower.Components.FlagComponents.OnCurrentMap;
import com.jessematty.black.tower.Components.FlagComponents.StatChanged;
import com.jessematty.black.tower.Components.FlagComponents.StringStatChanged;
import com.jessematty.black.tower.Components.FlagComponents.VisibleOnScreen;
import com.jessematty.black.tower.Components.Item.ItemAction.ItemActionComponents;
import com.jessematty.black.tower.Components.Item.ItemAction.PickUpItemComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Other.AIComponent;
import com.jessematty.black.tower.Components.Other.Armor;
import com.jessematty.black.tower.Components.Other.EditorImageComponent;
import com.jessematty.black.tower.Components.Other.ErrorComponent;
import com.jessematty.black.tower.Components.Other.Explodable;
import com.jessematty.black.tower.Components.Other.GlowComponent;
import com.jessematty.black.tower.Components.Other.Growable;
import com.jessematty.black.tower.Components.Other.InfoComponent;
import com.jessematty.black.tower.Components.Other.Ingestable;
import com.jessematty.black.tower.Components.Other.Light;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Other.RandomlyCreateAndPlaceEntity;
import com.jessematty.black.tower.Components.Other.Readable;
import com.jessematty.black.tower.Components.Other.Root;
import com.jessematty.black.tower.Components.Other.SolidObject;
import com.jessematty.black.tower.Components.Other.SoundComponent;
import com.jessematty.black.tower.Components.Other.TalkComponent;
import com.jessematty.black.tower.Components.Other.Target;
import com.jessematty.black.tower.Components.Other.Weapon;
import com.jessematty.black.tower.Components.Position.BoundsChangeableComponent;
import com.jessematty.black.tower.Components.Position.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsSelfChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.TimeChangingStats;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Systems.AddSystemsComponent;
import com.jessematty.black.tower.Components.Systems.RemoveSystemsComponent;
import com.jessematty.black.tower.Components.Tiles.BitMaskable;
import com.jessematty.black.tower.Components.Tiles.OnEnterTileComponent;
import com.jessematty.black.tower.Components.Tiles.OnExitTileComponent;
import com.jessematty.black.tower.Components.Tiles.TileComponent;
import com.jessematty.black.tower.Components.Tiles.TileWeatherNumericStatsChangable;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacterComponent;
import com.jessematty.black.tower.GameBaseClasses.Logging.GameLog;
import com.jessematty.black.tower.Maps.World;

import java.util.HashMap;
import java.util.Map;
/** class that contains all of the component mappers for all of the components in the game
 * 
 */
public class GameComponentMapper {
    private static ComponentMapper<AddedToEngine> addedToEngineComponentMapper = ComponentMapper.getFor(AddedToEngine.class);
    private static ComponentMapper<DrawableComponent> drawableComponentMapper = ComponentMapper.getFor(DrawableComponent.class);
    private static ComponentMapper<AnimatableComponent> animatableComponentMapper = ComponentMapper.getFor(AnimatableComponent.class);
    private static ComponentMapper<ActionComponent> actionComponentMapper = ComponentMapper.getFor(ActionComponent.class);
    private static ComponentMapper<PositionComponent> positionComponentMapper = ComponentMapper.getFor(PositionComponent.class);
    private static ComponentMapper<BitMaskable> bitMaskableComponentMapper = ComponentMapper.getFor(BitMaskable.class);
    private static ComponentMapper<MovableComponent> movableComponentMapper = ComponentMapper.getFor(MovableComponent.class);
    private static ComponentMapper<PhysicalObjectComponent> physicalObjectComponentMapper = ComponentMapper.getFor(PhysicalObjectComponent.class);
    private static ComponentMapper<Light> lightComponentMapper = ComponentMapper.getFor(Light.class);
    private static ComponentMapper<TileComponent> tileComponentMapper = ComponentMapper.getFor(TileComponent.class);
    private static ComponentMapper<Holder> holderComponentMapper = ComponentMapper.getFor(Holder.class);
    private static ComponentMapper<NameComponent> nameComponentMapper = ComponentMapper.getFor(NameComponent.class);
    private static ComponentMapper<Growable> growableComponentMapper = ComponentMapper.getFor(Growable.class);
    private static ComponentMapper<NumericStats> numericStatsComponentMapper = ComponentMapper.getFor(NumericStats.class);
    private static ComponentMapper<StringStats> stringStatsComponentMapper = ComponentMapper.getFor(StringStats.class);
    private static ComponentMapper<BooleanStats> booleanStatsComponentMapper = ComponentMapper.getFor(BooleanStats.class);
    private static ComponentMapper<NumericStatsChangeable> numericStatsChangableComponentMapper = ComponentMapper.getFor(NumericStatsChangeable.class);
    private static ComponentMapper<StringStatsChangeable> stringStatsChangableComponentMapper = ComponentMapper.getFor(StringStatsChangeable.class);
    private static ComponentMapper<BooleanStatsChangeable> booleanStatsChangableComponentMapper = ComponentMapper.getFor(BooleanStatsChangeable.class);
    private static ComponentMapper<Slashable> slashComponentMapper = ComponentMapper.getFor(Slashable.class);
    private static ComponentMapper<Thrustable> thrustComponentMapper = ComponentMapper.getFor(Thrustable.class);
    private static ComponentMapper<Shootable> shootComponentMapper = ComponentMapper.getFor(Shootable.class);
    private static ComponentMapper<Armor> armorComponentMapper = ComponentMapper.getFor(Armor.class);
    private static ComponentMapper<Weapon> weaponComponentMapper = ComponentMapper.getFor(Weapon.class);
    private static ComponentMapper<Breather> breatherComponentMapper = ComponentMapper.getFor(Breather.class);
    private static ComponentMapper<Holdable> holdableComponentMapper = ComponentMapper.getFor(Holdable.class);
    private static ComponentMapper<Attachable> attachableComponentMapper = ComponentMapper.getFor(Attachable.class);
    private static ComponentMapper<VisibleOnScreen> visibleOnScreenComponentMapper = ComponentMapper.getFor(VisibleOnScreen.class);
    private static ComponentMapper<TileWeatherNumericStatsChangable> tileNumericStatsChangableComponentMapper = ComponentMapper.getFor(TileWeatherNumericStatsChangable.class);
    private static ComponentMapper<ImageComponent> imageComponentMapper = ComponentMapper.getFor(ImageComponent.class);
    private static ComponentMapper<EntityId> idComponentMapper = ComponentMapper.getFor(EntityId.class);
    private static ComponentMapper<GlowComponent> glowComponentMapper = ComponentMapper.getFor(GlowComponent.class);
    private static ComponentMapper<NumericStatsSelfChangable> numericStatsSelfChangableComponentMapper = ComponentMapper.getFor(NumericStatsSelfChangable.class);
    private static ComponentMapper<ChangeImageOnStatsValueChanges> changeImageOnStatsValueChangesComponentMapper = ComponentMapper.getFor(ChangeImageOnStatsValueChanges.class);
    private static ComponentMapper<NumericStatChanged> numericStatChangedComponentMapper = ComponentMapper.getFor(NumericStatChanged.class);
    private static ComponentMapper<BooleanStatChanged> booleanStatChangedComponentMapper = ComponentMapper.getFor(BooleanStatChanged.class);
    private static ComponentMapper<StringStatChanged> stringStatChangedComponentMapper = ComponentMapper.getFor(StringStatChanged.class);
    private static ComponentMapper<StatChanged> statChangedComponentMapper = ComponentMapper.getFor(StatChanged.class);
    private static ComponentMapper<ItemActionComponents> actionComponentsComponentMapper = ComponentMapper.getFor(ItemActionComponents.class);
    private static ComponentMapper<InfoComponent> infoComponentMapper = ComponentMapper.getFor(InfoComponent.class);
    private static ComponentMapper<Ingestable> ingestableComponentMapper = ComponentMapper.getFor(Ingestable.class);
    private static ComponentMapper<Ingest> ingestingComponentMapper = ComponentMapper.getFor(Ingest.class);
    private static ComponentMapper<Slashable> slashableComponentMapper = ComponentMapper.getFor(Slashable.class);
    private static ComponentMapper<Slash> slashingComponentMapper = ComponentMapper.getFor(Slash.class);
    private static ComponentMapper<Thrust> thrustingComponentMapper = ComponentMapper.getFor(Thrust.class);
    private static ComponentMapper<Thrustable> thrustableComponentMapper = ComponentMapper.getFor(Thrustable.class);
    private static ComponentMapper<Shoot> shootingComponentMapper = ComponentMapper.getFor(Shoot.class);
    private static ComponentMapper<Shootable> shootableComponentMapper = ComponentMapper.getFor(Shootable.class);
    private static ComponentMapper<SpellCastable> spellCastableComponentMapper = ComponentMapper.getFor(SpellCastable.class);
    private static ComponentMapper<SpellCast> spellCastingComponentMapper = ComponentMapper.getFor(SpellCast.class);
    private static ComponentMapper<NumericStatsChangeComponent> numericStatChangeComponentMapper = ComponentMapper.getFor(NumericStatsChangeComponent.class);
    private static ComponentMapper<BooleanStatsChangeComponent> booleanStatChangeComponentComponentMapper = ComponentMapper.getFor(BooleanStatsChangeComponent.class);
    private static ComponentMapper<StringStatsChangeComponent> stringStatChangeComponentComponentMapper = ComponentMapper.getFor(StringStatsChangeComponent.class);
    private static ComponentMapper<ItemComponent> itemComponentMapper = ComponentMapper.getFor(ItemComponent.class);
    private static ComponentMapper<TimeChangingStats> timeChangingNumericStatsComponentMapper = ComponentMapper.getFor(TimeChangingStats.class);
    private static ComponentMapper<Dying> dyingComponentMapper = ComponentMapper.getFor(Dying.class);
    private static ComponentMapper<TalkComponent> talkComponentComponentMapper = ComponentMapper.getFor(TalkComponent.class);
    private static ComponentMapper<Talk> talkingComponentMapper = ComponentMapper.getFor(Talk.class);
    private static ComponentMapper<AIComponent> aiComponentMapper = ComponentMapper.getFor(AIComponent.class);
    private static ComponentMapper<Launchable> throwComponentMapper = ComponentMapper.getFor(Launchable.class);
    private static ComponentMapper<ContainerComponent> containerComponentMapper = ComponentMapper.getFor(ContainerComponent.class);
    private static ComponentMapper<AttachEntityEvent> addOwnerComponentComponentMapper = ComponentMapper.getFor(AttachEntityEvent.class);
    private static ComponentMapper<DetachEntityEvent> removeOwnerComponentComponentMapper = ComponentMapper.getFor(DetachEntityEvent.class);
    private static ComponentMapper<OwnerComponent> ownerComponentComponentMapper = ComponentMapper.getFor(OwnerComponent.class);
    private static ComponentMapper<OwnedComponent> ownedComponentComponentMapper = ComponentMapper.getFor(OwnedComponent.class);
    private static ComponentMapper<ErrorComponent> errorComponentComponentMapper = ComponentMapper.getFor(ErrorComponent.class);
    private static ComponentMapper<EquipItem> equipItemComponentMapper = ComponentMapper.getFor(EquipItem.class);
    private static ComponentMapper<UnEquipItem> unEquipItemComponentMapperr = ComponentMapper.getFor(UnEquipItem.class);
    private static ComponentMapper<GroupsComponent> groupsComponentMapper = ComponentMapper.getFor(GroupsComponent.class);
    private static ComponentMapper<Throwable> throwableComponentMapper = ComponentMapper.getFor(Throwable.class);
    private static ComponentMapper<Readable> readableComponentMapper = ComponentMapper.getFor(Readable.class);
    private static ComponentMapper<Loadable> loadableComponentMapper = ComponentMapper.getFor(Loadable.class);
    private static ComponentMapper<SoundComponent> soundComponentComponentMapper = ComponentMapper.getFor(SoundComponent.class);
    private static ComponentMapper<Root> rootComponentMapper = ComponentMapper.getFor(Root.class);
    private static ComponentMapper<Explodable> blastableComponentMapper = ComponentMapper.getFor(Explodable.class);
    private static ComponentMapper<AddComponent> addComponentComponentMapper = ComponentMapper.getFor(AddComponent.class);
    private static ComponentMapper<Target> targetComponentMapper = ComponentMapper.getFor(Target.class);
    private static ComponentMapper<CreateEntityOnAction> entityCreateableComponentMapper = ComponentMapper.getFor(CreateEntityOnAction.class);
    private static ComponentMapper<CreateEntity> createEntityComponentMapper = ComponentMapper.getFor(CreateEntity.class);
    private static ComponentMapper<PickUpItemComponent> pickUpComponentMapper = ComponentMapper.getFor(PickUpItemComponent.class);
    private static ComponentMapper<EditorImageComponent> editorImageComponentComponentMapper = ComponentMapper.getFor(EditorImageComponent.class);
    private static ComponentMapper<CreateEntitiesOnTime> createEntitiesOnTimeComponentMapper = ComponentMapper.getFor(CreateEntitiesOnTime.class);
    private static ComponentMapper<CreateEntitiesOnNumericStatChange> createEntitiesOnNumericStatChangeComponentMapper = ComponentMapper.getFor(CreateEntitiesOnNumericStatChange.class);
    private static ComponentMapper<CreateEntitiesOnBooleanStatChange> createEntitiesOnBooleanStatChangeComponentMapper = ComponentMapper.getFor(CreateEntitiesOnBooleanStatChange.class);
    private static ComponentMapper<Eyes> eyesComponentMapper = ComponentMapper.getFor(Eyes.class);
    private static ComponentMapper<Ears> earsComponentMapper = ComponentMapper.getFor(Ears.class);
    private static ComponentMapper<HoldItemComponent> holdItemComponentMapper = ComponentMapper.getFor(HoldItemComponent.class);
    private static ComponentMapper<UnHoldItem> unholdItemComponentMapper = ComponentMapper.getFor(UnHoldItem.class);
    private static ComponentMapper<BoundsChangeableComponent> boundsChangeableComponentMapper = ComponentMapper.getFor(BoundsChangeableComponent.class);
    private static ComponentMapper<OnEnterTileComponent> onEnterTileComponentComponentMapper = ComponentMapper.getFor(OnEnterTileComponent.class);
    private static ComponentMapper<OnExitTileComponent> onExitTileComponentComponentMapper = ComponentMapper.getFor(OnExitTileComponent.class);
    private static ComponentMapper<NotAddedToEngine> notAddedToEngineComponentMapper = ComponentMapper.getFor(NotAddedToEngine.class);
    private static ComponentMapper<RandomlyCreateAndPlaceEntity> randomlyCreateAndPlaceEntityComponentMapper = ComponentMapper.getFor(RandomlyCreateAndPlaceEntity.class);
    private static ComponentMapper<OnCurrentMap> onCurrentMapComponentMapper = ComponentMapper.getFor(OnCurrentMap.class);
    private static ComponentMapper<AddSystemsComponent> addSystemsComponentComponentMapper = ComponentMapper.getFor(AddSystemsComponent.class);
    private static ComponentMapper<RemoveSystemsComponent> removeSystemsComponentComponentMapper = ComponentMapper.getFor(RemoveSystemsComponent.class);
    private static ComponentMapper<ZRPGBrainComponent> brainComponentComponentMapper = ComponentMapper.getFor(ZRPGBrainComponent.class);
    private static ComponentMapper<SolidObject> solidObjectComponentMapper = ComponentMapper.getFor(SolidObject.class);
    private static ComponentMapper<GameLog> logComponentMapper = ComponentMapper.getFor(GameLog.class);
    private static ComponentMapper<RemoveItemFromContainer> removeItemFromContainerComponentComponentMapper = ComponentMapper.getFor(RemoveItemFromContainer.class);
    private static ComponentMapper<AddItemToContainer> addItemToContainerComponentMapper = ComponentMapper.getFor(AddItemToContainer.class);
    private static ComponentMapper<BodyComponent> bodyComponentComponentMapper = ComponentMapper.getFor(BodyComponent.class);
    private static ComponentMapper<PartComponent> partComponentComponentMapper = ComponentMapper.getFor(PartComponent.class);
    private static ComponentMapper<PackComponent> packComponentComponentMapper = ComponentMapper.getFor(PackComponent.class);
    private static ComponentMapper<ZRPGCharacter> zrpgCharacterComponentMapper = ComponentMapper.getFor(ZRPGCharacter.class);
    private static ComponentMapper<ZRPGCharacterComponent> zrpgCharacterComponentComponentMapper = ComponentMapper.getFor(ZRPGCharacterComponent.class);

    private static ComponentMapper<CharacterPacksComponent> characterPacksComponentComponentMapper = ComponentMapper.getFor(CharacterPacksComponent.class);

    private static final Map<Class<? extends Component>, ComponentMapper> componentComponentMapperMap = new HashMap<Class<? extends Component>, ComponentMapper>();
    static {
        componentComponentMapperMap.put(AddedToEngine.class, actionComponentMapper);
        componentComponentMapperMap.put(DrawableComponent.class, drawableComponentMapper);
        componentComponentMapperMap.put(AnimatableComponent.class, animatableComponentMapper);
        componentComponentMapperMap.put(MovableComponent.class, movableComponentMapper);
        componentComponentMapperMap.put(ActionComponent.class, actionComponentMapper);
        componentComponentMapperMap.put(PositionComponent.class, positionComponentMapper);
        componentComponentMapperMap.put(BitMaskable.class, bitMaskableComponentMapper);
        componentComponentMapperMap.put(PhysicalObjectComponent.class, physicalObjectComponentMapper);
        componentComponentMapperMap.put(Light.class, lightComponentMapper);
        componentComponentMapperMap.put(Growable.class, growableComponentMapper);
        componentComponentMapperMap.put(NameComponent.class, nameComponentMapper);
        componentComponentMapperMap.put(NumericStats.class, numericStatsComponentMapper);
        componentComponentMapperMap.put(BooleanStats.class, booleanStatsComponentMapper);
        componentComponentMapperMap.put(StringStats.class, stringStatsComponentMapper);
        componentComponentMapperMap.put(StringStatsChangeable.class, stringStatsChangableComponentMapper);
        componentComponentMapperMap.put(BooleanStatsChangeable.class, booleanStatsChangableComponentMapper);
        componentComponentMapperMap.put(NumericStatsChangeable.class, numericStatsChangableComponentMapper);
        componentComponentMapperMap.put(Slashable.class, slashComponentMapper);
        componentComponentMapperMap.put(Thrustable.class, thrustComponentMapper);
        componentComponentMapperMap.put(Shootable.class, shootComponentMapper);
        componentComponentMapperMap.put(Armor.class, armorComponentMapper);
        componentComponentMapperMap.put(Weapon.class, weaponComponentMapper);
        componentComponentMapperMap.put(ImageComponent.class, imageComponentMapper);
        componentComponentMapperMap.put(Holder.class, holderComponentMapper);
        componentComponentMapperMap.put(TileComponent.class, tileComponentMapper);
        componentComponentMapperMap.put(Breather.class, holdableComponentMapper);
        componentComponentMapperMap.put(Attachable.class, attachableComponentMapper);
        componentComponentMapperMap.put(VisibleOnScreen.class, visibleOnScreenComponentMapper);
        componentComponentMapperMap.put(TileWeatherNumericStatsChangable.class, tileNumericStatsChangableComponentMapper);
        componentComponentMapperMap.put(GlowComponent.class, glowComponentMapper);
        componentComponentMapperMap.put(EntityId.class, idComponentMapper);
        componentComponentMapperMap.put(NumericStatsSelfChangable.class, numericStatsChangableComponentMapper);
        componentComponentMapperMap.put(ChangeImageOnStatsValueChanges.class, changeImageOnStatsValueChangesComponentMapper);
        componentComponentMapperMap.put(StringStatChanged.class, stringStatChangedComponentMapper);
        componentComponentMapperMap.put(NumericStatChanged.class, numericStatChangedComponentMapper);
        componentComponentMapperMap.put(BooleanStatChanged.class, booleanStatChangedComponentMapper);
        componentComponentMapperMap.put(StatChanged.class, statChangedComponentMapper);
        componentComponentMapperMap.put(ItemActionComponents.class, actionComponentsComponentMapper);
        componentComponentMapperMap.put(InfoComponent.class, infoComponentMapper);
        componentComponentMapperMap.put(Ingestable.class, ingestableComponentMapper);
        componentComponentMapperMap.put(Ingest.class, ingestingComponentMapper);
        componentComponentMapperMap.put(Slash.class, slashingComponentMapper);
        componentComponentMapperMap.put(Thrust.class, thrustingComponentMapper);
        componentComponentMapperMap.put(Shoot.class, shootingComponentMapper);
        componentComponentMapperMap.put(SpellCastable.class, spellCastableComponentMapper);
        componentComponentMapperMap.put(SpellCast.class, spellCastingComponentMapper);
        componentComponentMapperMap.put(NumericStatsChangeComponent.class, numericStatChangeComponentMapper);
        componentComponentMapperMap.put(BooleanStatsChangeComponent.class, booleanStatChangeComponentComponentMapper);
        componentComponentMapperMap.put(StringStatsChangeComponent.class, stringStatChangeComponentComponentMapper);
        componentComponentMapperMap.put(ItemComponent.class, itemComponentMapper);
        componentComponentMapperMap.put(TimeChangingStats.class, timeChangingNumericStatsComponentMapper);
        componentComponentMapperMap.put(Dying.class, dyingComponentMapper);
        componentComponentMapperMap.put(TalkComponent.class, talkComponentComponentMapper);
        componentComponentMapperMap.put(Talk.class, talkingComponentMapper);
        componentComponentMapperMap.put(AIComponent.class, aiComponentMapper);
        componentComponentMapperMap.put(Launchable.class, throwComponentMapper);
        componentComponentMapperMap.put(ContainerComponent.class, containerComponentMapper);
        componentComponentMapperMap.put(AttachEntityEvent.class, addOwnerComponentComponentMapper);
        componentComponentMapperMap.put(DetachEntityEvent.class, removeOwnerComponentComponentMapper);
        componentComponentMapperMap.put(OwnedComponent.class, ownedComponentComponentMapper);
        componentComponentMapperMap.put(OwnerComponent.class, ownerComponentComponentMapper);
        componentComponentMapperMap.put(ErrorComponent.class, errorComponentComponentMapper);
        componentComponentMapperMap.put(EquipItem.class, equipItemComponentMapper);
        componentComponentMapperMap.put(UnEquipItem.class, unEquipItemComponentMapperr);
        componentComponentMapperMap.put(GroupsComponent.class, groupsComponentMapper);
        componentComponentMapperMap.put(Loadable.class, loadableComponentMapper);
        componentComponentMapperMap.put(Throwable.class, throwableComponentMapper);
        componentComponentMapperMap.put(Readable.class, readableComponentMapper);
        componentComponentMapperMap.put(SoundComponent.class, soundComponentComponentMapper);
        componentComponentMapperMap.put(Explodable.class, blastableComponentMapper);
        componentComponentMapperMap.put(Root.class, rootComponentMapper);
        componentComponentMapperMap.put(AddComponent.class, addComponentComponentMapper);
        componentComponentMapperMap.put(Target.class, targetComponentMapper);
        componentComponentMapperMap.put(CreateEntityOnAction.class, entityCreateableComponentMapper);
        componentComponentMapperMap.put(CreateEntity.class, createEntityComponentMapper);
        componentComponentMapperMap.put(PickUpItemComponent.class, pickUpComponentMapper);
        componentComponentMapperMap.put(EditorImageComponent.class, equipItemComponentMapper);
        componentComponentMapperMap.put(CreateEntitiesOnTime.class, createEntitiesOnTimeComponentMapper);
        componentComponentMapperMap.put(CreateEntitiesOnNumericStatChange.class, createEntitiesOnNumericStatChangeComponentMapper);
        componentComponentMapperMap.put(CreateEntitiesOnBooleanStatChange.class, createEntitiesOnBooleanStatChangeComponentMapper);
        componentComponentMapperMap.put(Ears.class, earsComponentMapper);
        componentComponentMapperMap.put(Eyes.class, eyesComponentMapper);
        componentComponentMapperMap.put(HoldItemComponent.class, holdItemComponentMapper);
        componentComponentMapperMap.put(UnHoldItem.class, unholdItemComponentMapper);
        componentComponentMapperMap.put(BoundsChangeableComponent.class, boundsChangeableComponentMapper);
        componentComponentMapperMap.put(OnEnterTileComponent.class, onEnterTileComponentComponentMapper);
        componentComponentMapperMap.put(OnExitTileComponent.class, onExitTileComponentComponentMapper);
        componentComponentMapperMap.put(NotAddedToEngine.class, notAddedToEngineComponentMapper);
        componentComponentMapperMap.put(RandomlyCreateAndPlaceEntity.class, randomlyCreateAndPlaceEntityComponentMapper);
        componentComponentMapperMap.put(OnCurrentMap.class, onCurrentMapComponentMapper);
        componentComponentMapperMap.put(AddSystemsComponent.class, addSystemsComponentComponentMapper);
        componentComponentMapperMap.put(RemoveSystemsComponent.class, removeSystemsComponentComponentMapper);
        componentComponentMapperMap.put(SolidObject.class, solidObjectComponentMapper);
        componentComponentMapperMap.put(GameLog.class, logComponentMapper);
        componentComponentMapperMap.put(BodyComponent.class, bodyComponentComponentMapper);
        componentComponentMapperMap.put(PartComponent.class, partComponentComponentMapper);
        componentComponentMapperMap.put(PackComponent.class, packComponentComponentMapper);
        componentComponentMapperMap.put(CharacterPacksComponent.class, characterPacksComponentComponentMapper);
        componentComponentMapperMap.put(ZRPGCharacter.class, zrpgCharacterComponentMapper);
        componentComponentMapperMap.put(ZRPGCharacterComponent.class, zrpgCharacterComponentComponentMapper);

    }

    private GameComponentMapper() {
    }

    public static <T extends Component> void addComponentToMapper(Class<T> componentClass) {
        ComponentMapper<T> componentMapper = ComponentMapper.getFor(componentClass);
        componentComponentMapperMap.put(componentClass, componentMapper);
    }
    public static <T extends Component> ComponentMapper<T> getComponent(Class<T> componentClass) {
        return componentComponentMapperMap.get(componentClass);
    }
    public static <T extends Component> Array<T> getComponentsFromEntitiesWithComponents(Array<Entity> entities, Class<T> componentsClass) {
        Array<T> componentsToReturn = new Array<T>();
        int size = entities.size;
        ComponentMapper<T> componentMapper = ComponentMapper.getFor(componentsClass);
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            T component = componentMapper.get(entity);
            if (component != null) {
                componentsToReturn.add(component);
            }
        }
        return componentsToReturn;
    }

    /**
     * should probably use engine.getFor(); and remove this duplicate method
     * @param entities
     * @param components
     * @return
     */
    public static Array<Entity> getEntitiesWithComponents(Array<Entity> entities, Class<? extends Component>... components) {
        Array<Entity> entitiesToReturn = new Array<Entity>();
        int size = entities.size;
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
                boolean hasComponents = hasComponents(entity, components);
                if (hasComponents == true) {
                    entitiesToReturn.add(entity);
                }
        }
        return entitiesToReturn;
    }
    public static Array<Entity> getEntitiesWithComponentsById(World world, Array<String> entityIDs, Class<? extends Component>... components) {
        Array<Entity> entitiesToReturn = new Array<Entity>();
        int size = entityIDs.size;
        for (int count = 0; count < size; count++) {
            Entity entity = world.getEntity(entityIDs.get(count));
                boolean hasComponents = hasComponents(entity, components);
                if (hasComponents == true) {
                    entitiesToReturn.add(entity);
                }

        }
        return entitiesToReturn;
    }

    /**
     * returns  a libGDX array of entities containing ALL  passed in stats and components
     * @param entities
     * @param numericStatNames  numeric stats to check for
     * @param booleanStatNames boolean to check if exists
     * @param stringStatNames string stats to check if exists
     * @param components components to check if exists
     * @return the libGDX array of entities
     */
    public static Array<Entity> getEntitiesContainingStats(Array<Entity> entities, Array<String> numericStatNames, Array<String> booleanStatNames, Array<String> stringStatNames, Class<? extends Component>... components) {
        Array<Entity> entitiesToReturn = new Array<Entity>();
        int size = entities.size;
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            boolean hasNumericStats = true;
            if (numericStatNames != null) {
                NumericStats numericStats = numericStatsComponentMapper.get(entity);
                if (numericStats != null) {
                    hasNumericStats = hasNumericStats(numericStats, numericStatNames);
                } else {
                    hasNumericStats = false;
                }
            }
            if (hasNumericStats == false) {
                continue;
            }
            boolean hasBooleanStats = true;
            if (booleanStatNames != null) {
                BooleanStats booleanStats = booleanStatsComponentMapper.get(entity);
                if (booleanStats != null) {
                    hasBooleanStats = hasBooleanStats(booleanStats, booleanStatNames);
                } else {
                    hasBooleanStats = false;
                }
            }
            if (hasBooleanStats == false) {
                continue;
            }
            boolean hasStringStats = true;
            if (stringStatNames != null) {
                StringStats stringStats = stringStatsComponentMapper.get(entity);
                if (stringStats != null) {
                    hasStringStats = hasStringStats(stringStats, stringStatNames);
                } else {
                    hasStringStats = false;
                }
            }
            if (hasStringStats == false) {
                continue;
            }
            boolean hasComponents = true;
            if (components.length > 0) {
                hasComponents = hasComponents(entity, components);
            }
            if (hasBooleanStats == true && hasComponents == true && hasNumericStats == true && hasStringStats == true) {
                entitiesToReturn.add(entity);
            }
        }
        return entitiesToReturn;
    }

    /**
     *  check to see if  an entity has  has all of the passed in components
     * @param entity the entity to check for
     * @param components the array of components
     * @return a boolean true if entity has all of the components false if not
     */
    private static boolean hasComponents(Entity entity, Class<? extends Component>... components) {
        if(entity==null){
        throw new NullPointerException("Entity Must Not Be Null");
        }
        int length = components.length;
        for (int count2 = 0; count2 < length; count2++) {
            ComponentMapper component=componentComponentMapperMap.get(components[count2]);
            if(component==null){
                throw new NullPointerException("Component has not been added to componentComponentMapperMap variable  please do so!");
            }
            if (component.get(entity) == null) {
                return false;
            }
        }
        return true;
    }
    /**
     *  checks to see if  an entity has  has all of given numeric stats
     * @param stats the entities stats
     * @param numericStatNames the names of the stats to check
     * @return a boolean true if entity has all of the components false if not
     */
    private static boolean hasNumericStats(NumericStats stats, Array<String> numericStatNames) {
        int size = numericStatNames.size;
        for (int count = 0; count < size; count++) {
            NumericStat stat = stats.getNumericStat(numericStatNames.get(count));
            if (stat == null) {
                return false;
            }
        }
        return true;
    }
    private static boolean hasBooleanStats(BooleanStats stats, Array<String> booleanStatNames) {
        int size = booleanStatNames.size;
        for (int count = 0; count < size; count++) {
            BooleanStat stat = stats.getBooleanStat(booleanStatNames.get(count));
            if (stat == null) {
                return false;
            }
        }
        return true;
    }
    private static boolean hasStringStats(StringStats stats, Array<String> stringStatNames) {
        int size = stringStatNames.size;
        for (int count = 0; count < size; count++) {
            StringStat stat = stats.getStringStat(stringStatNames.get(count));
            if (stat == null) {
                return false;
            }
        }
        return true;
    }
    public static ComponentMapper<AddedToEngine> getAddedToEngineComponentMapper() {
        return addedToEngineComponentMapper;
    }
    public static ComponentMapper<DrawableComponent> getDrawableComponentMapper() {
        return drawableComponentMapper;
    }
    public static ComponentMapper<AnimatableComponent> getAnimatableComponentMapper() {
        return animatableComponentMapper;
    }
    public static ComponentMapper<ActionComponent> getActionComponentMapper() {
        return actionComponentMapper;
    }
    public static ComponentMapper<PositionComponent> getPositionComponentMapper() {
        return positionComponentMapper;
    }
    public static ComponentMapper<BitMaskable> getBitMaskableComponentMapper() {
        return bitMaskableComponentMapper;
    }
    public static ComponentMapper<MovableComponent> getMovableComponentMapper() {
        return movableComponentMapper;
    }
    public static ComponentMapper<PhysicalObjectComponent> getPhysicalObjectComponentMapper() {
        return physicalObjectComponentMapper;
    }
    public static ComponentMapper<Light> getLightComponentMapper() {
        return lightComponentMapper;
    }
    public static ComponentMapper<TileComponent> getTileComponentMapper() {
        return tileComponentMapper;
    }
    public static ComponentMapper<Holder> getHolderComponentMapper() {
        return holderComponentMapper;
    }
    public static ComponentMapper<NameComponent> getNameComponentMapper() {
        return nameComponentMapper;
    }
    public static ComponentMapper<Growable> getGrowableComponentMapper() {
        return growableComponentMapper;
    }
    public static ComponentMapper<NumericStats> getNumericStatsComponentMapper() {
        return numericStatsComponentMapper;
    }
    public static ComponentMapper<StringStats> getStringStatsComponentMapper() {
        return stringStatsComponentMapper;
    }
    public static ComponentMapper<BooleanStats> getBooleanStatsComponentMapper() {
        return booleanStatsComponentMapper;
    }
    public static ComponentMapper<NumericStatsChangeable> getNumericStatsChangableComponentMapper() {
        return numericStatsChangableComponentMapper;
    }
    public static ComponentMapper<StringStatsChangeable> getStringStatsChangableComponentMapper() {
        return stringStatsChangableComponentMapper;
    }
    public static ComponentMapper<BooleanStatsChangeable> getBooleanStatsChangableComponentMapper() {
        return booleanStatsChangableComponentMapper;
    }
    public static ComponentMapper<ImageComponent> getImageComponentMapper() {
        return imageComponentMapper;
    }
    public static ComponentMapper<Breather> getBreatherComponentMapper() {
        return breatherComponentMapper;
    }
    public static ComponentMapper<Holdable> getHoldableComponentMapper() {
        return holdableComponentMapper;
    }
    public static ComponentMapper<TileWeatherNumericStatsChangable> getTileWeatherNumericStatsChangableComponentMapper() {
        return tileNumericStatsChangableComponentMapper;
    }
    public static ComponentMapper<Slashable> getSlashComponentMapper() {
        return slashComponentMapper;
    }
    public static ComponentMapper<Thrustable> getThrustComponentMapper() {
        return thrustComponentMapper;
    }
    public static ComponentMapper<Shootable> getShootComponentMapper() {
        return shootComponentMapper;
    }
    public static ComponentMapper<Armor> getArmorComponentMapper() {
        return armorComponentMapper;
    }
    public static ComponentMapper<Weapon> getWeaponComponentMapper() {
        return weaponComponentMapper;
    }
    public static Map<Class<? extends Component>, ComponentMapper> getComponentComponentMapperMap() {
        return componentComponentMapperMap;
    }
    public static ComponentMapper<VisibleOnScreen> getVisibleOnScreenComponentMapper() {
        return visibleOnScreenComponentMapper;
    }
    public static ComponentMapper<TileWeatherNumericStatsChangable> getTileNumericStatsChangableComponentMapper() {
        return tileNumericStatsChangableComponentMapper;
    }
    public static ComponentMapper<EntityId> getIdComponentMapper() {
        return idComponentMapper;
    }
    public static ComponentMapper<GlowComponent> getGlowComponentMapper() {
        return glowComponentMapper;
    }
    public static ComponentMapper<NumericStatsSelfChangable> getNumericStatsSelfChangableComponentMapper() {
        return numericStatsSelfChangableComponentMapper;
    }
    public static ComponentMapper<ChangeImageOnStatsValueChanges> getChangeImageOnStatsValueChangesComponentMapper() {
        return changeImageOnStatsValueChangesComponentMapper;
    }
    public static ComponentMapper<NumericStatChanged> getNumericStatChangedComponentMapper() {
        return numericStatChangedComponentMapper;
    }
    public static ComponentMapper<BooleanStatChanged> getBooleanStatChangedComponentMapper() {
        return booleanStatChangedComponentMapper;
    }
    public static ComponentMapper<StringStatChanged> getStringStatChangedComponentMapper() {
        return stringStatChangedComponentMapper;
    }
    public static ComponentMapper<StatChanged> getStatChangedComponentMapper() {
        return statChangedComponentMapper;
    }
    public static ComponentMapper<ItemActionComponents> getActionComponentsComponentMapper() {
        return actionComponentsComponentMapper;
    }
    public static ComponentMapper<InfoComponent> getInfoComponentMapper() {
        return infoComponentMapper;
    }
    public static ComponentMapper<Ingestable> getIngestableComponentMapper() {
        return ingestableComponentMapper;
    }
    public static ComponentMapper<Ingest> getIngestingComponentMapper() {
        return ingestingComponentMapper;
    }
    public static ComponentMapper<Slashable> getSlashableComponentMapper() {
        return slashableComponentMapper;
    }
    public static ComponentMapper<Slash> getSlashingComponentMapper() {
        return slashingComponentMapper;
    }
    public static ComponentMapper<Thrust> getThrustingComponentMapper() {
        return thrustingComponentMapper;
    }
    public static ComponentMapper<Thrustable> getThrustableComponentMapper() {
        return thrustableComponentMapper;
    }
    public static ComponentMapper<Shoot> getShootingComponentMapper() {
        return shootingComponentMapper;
    }
    public static ComponentMapper<Shootable> getShootableComponentMapper() {
        return shootableComponentMapper;
    }
    public static ComponentMapper<SpellCastable> getSpellCastableComponentMapper() {
        return spellCastableComponentMapper;
    }
    public static ComponentMapper<SpellCast> getSpellCastingComponentMapper() {
        return spellCastingComponentMapper;
    }
    public static ComponentMapper<NumericStatsChangeComponent> getNumericStatChangeComponentMapper() {
        return numericStatChangeComponentMapper;
    }
    public static ComponentMapper<BooleanStatsChangeComponent> getBooleanStatChangeComponentComponentMapper() {
        return booleanStatChangeComponentComponentMapper;
    }
    public static ComponentMapper<StringStatsChangeComponent> getStringStatChangeComponentComponentMapper() {
        return stringStatChangeComponentComponentMapper;
    }
    public static ComponentMapper<ItemComponent> getItemComponentMapper() {
        return itemComponentMapper;
    }
    public static ComponentMapper<TimeChangingStats> getTimeChangingNumericStatsComponentMapper() {
        return timeChangingNumericStatsComponentMapper;
    }
    public static ComponentMapper<TalkComponent> getTalkComponentComponentMapper() {
        return talkComponentComponentMapper;
    }
    public static ComponentMapper<Attachable> getAttachableComponentMapper() {
        return attachableComponentMapper;
    }
    public static ComponentMapper<Launchable> getThrowComponentMapper() {
        return throwComponentMapper;
    }
    public static ComponentMapper<AIComponent> getAiComponentMapper() {
        return aiComponentMapper;
    }
    public static ComponentMapper<Talk> getTalkingComponentMapper() {
        return talkingComponentMapper;
    }
    public static ComponentMapper<Dying> getDyingComponentMapper() {
        return dyingComponentMapper;
    }


    public static ComponentMapper<ContainerComponent> getContainerComponentMapper() {
        return containerComponentMapper;
    }
    public static ComponentMapper<AttachEntityEvent> getAddOwnerComponentComponentMapper() {
        return addOwnerComponentComponentMapper;
    }
    public static ComponentMapper<OwnerComponent> getOwnerComponentComponentMapper() {
        return ownerComponentComponentMapper;
    }
    public static ComponentMapper<OwnedComponent> getOwnedComponentComponentMapper() {
        return ownedComponentComponentMapper;
    }
    public static ComponentMapper<DetachEntityEvent> getRemoveOwnerComponentComponentMapper() {
        return removeOwnerComponentComponentMapper;
    }
    public static ComponentMapper<ErrorComponent> getErrorComponentComponentMapper() {
        return errorComponentComponentMapper;
    }
    public static ComponentMapper<EquipItem> getEquipItemComponentMapper() {
        return equipItemComponentMapper;
    }
    public static ComponentMapper<UnEquipItem> getUnEquipItemComponentMapperr() {
        return unEquipItemComponentMapperr;
    }
    public static ComponentMapper<Throwable> getThrowableComponentMapper() {
        return throwableComponentMapper;
    }
    public static ComponentMapper<Readable> getReadableComponentMapper() {
        return readableComponentMapper;
    }
    public static ComponentMapper<Loadable> getLoadableComponentMapper() {
        return loadableComponentMapper;
    }
    public static ComponentMapper<SoundComponent> getSoundComponentComponentMapper() {
        return soundComponentComponentMapper;
    }
    public static ComponentMapper<GroupsComponent> getGroupsComponentMapper() {
        return groupsComponentMapper;
    }
    public static ComponentMapper<Explodable> getBlastableComponentMapper() {
        return blastableComponentMapper;
    }
    public static ComponentMapper<Root> getRootComponentMapper() {
        return rootComponentMapper;
    }
    public static ComponentMapper<AddComponent> getAddComponentComponentMapper() {
        return addComponentComponentMapper;
    }
    public static ComponentMapper<Target> getTargetComponentMapper() {
        return targetComponentMapper;
    }
    public static ComponentMapper<CreateEntityOnAction> getEntityCreateableComponentMapper() {
        return entityCreateableComponentMapper;
    }
    public static ComponentMapper<CreateEntity> getCreateEntityComponentMapper() {
        return createEntityComponentMapper;
    }
    public static ComponentMapper<PickUpItemComponent> getPickUpComponentMapper() {
        return pickUpComponentMapper;
    }
    public static ComponentMapper<EditorImageComponent> getEditorImageComponentComponentMapper() {
        return editorImageComponentComponentMapper;
    }
    public static ComponentMapper<CreateEntitiesOnTime> getCreateEntitiesOnTimeComponentMapper() {
        return createEntitiesOnTimeComponentMapper;
    }
    public static ComponentMapper<CreateEntitiesOnNumericStatChange> getCreateEntitiesOnNumericStatChangeComponentMapper() {
        return createEntitiesOnNumericStatChangeComponentMapper;
    }
    public static ComponentMapper<CreateEntitiesOnBooleanStatChange> getCreateEntitiesOnBooleanStatChangeComponentMapper() {
        return createEntitiesOnBooleanStatChangeComponentMapper;
    }
    public static ComponentMapper<Eyes> getEyesComponentMapper() {
        return eyesComponentMapper;
    }
    public static ComponentMapper<Ears> getEarsComponentMapper() {
        return earsComponentMapper;
    }
    public static ComponentMapper<HoldItemComponent> getHoldItemComponentMapper() {
        return holdItemComponentMapper;
    }
    public static ComponentMapper<UnHoldItem> getUnholdItemComponentMapper() {
        return unholdItemComponentMapper;
    }
    public static ComponentMapper<BoundsChangeableComponent> getBoundsChangeableComponentMapper() {
        return boundsChangeableComponentMapper;
    }
    public static ComponentMapper<OnEnterTileComponent> getOnEnterTileComponentComponentMapper() {
        return onEnterTileComponentComponentMapper;
    }
    public static ComponentMapper<OnExitTileComponent> getOnExitTileComponentComponentMapper() {
        return onExitTileComponentComponentMapper;
    }
    public static ComponentMapper<NotAddedToEngine> getNotAddedToEngineComponentMapper() {
        return notAddedToEngineComponentMapper;
    }
    public static ComponentMapper<RandomlyCreateAndPlaceEntity> getRandomlyCreateAndPlaceEntityComponentMapper() {
        return randomlyCreateAndPlaceEntityComponentMapper;
    }
    public static ComponentMapper<OnCurrentMap> getOnCurrentMapComponentMapper() {
        return onCurrentMapComponentMapper;
    }
    public static ComponentMapper<AddSystemsComponent> getAddSystemsComponentComponentMapper() {
        return addSystemsComponentComponentMapper;
    }
    public static ComponentMapper<RemoveSystemsComponent> getRemoveSystemsComponentComponentMapper() {
        return removeSystemsComponentComponentMapper;
    }
    public static ComponentMapper<ZRPGBrainComponent> getBrainComponentComponentMapper() {
        return brainComponentComponentMapper;
    }

    public static ComponentMapper<SolidObject> getSolidObjectComponentMapper() {
        return solidObjectComponentMapper;
    }

    public static ComponentMapper<RemoveItemFromContainer> getRemoveItemFromContainerComponentComponentMapper() {
        return removeItemFromContainerComponentComponentMapper;
    }

    public static ComponentMapper<AddItemToContainer> getAddItemToContainerComponentMapper() {
        return addItemToContainerComponentMapper;
    }

    public static ComponentMapper<GameLog> getLogComponentMapper() {
        return logComponentMapper;
    }

    public static ComponentMapper<BodyComponent> getBodyComponentComponentMapper() {
        return bodyComponentComponentMapper;
    }

    public static ComponentMapper<PartComponent> getPartComponentComponentMapper() {
        return partComponentComponentMapper;
    }

    public static ComponentMapper<PackComponent> getPackComponentComponentMapper() {
        return packComponentComponentMapper;
    }

    public static ComponentMapper<CharacterPacksComponent> getCharacterPacksComponentComponentMapper() {
        return characterPacksComponentComponentMapper;
    }

    public static ComponentMapper<ZRPGCharacter> getZrpgCharacterComponentMapper() {
        return zrpgCharacterComponentMapper;
    }

    public static ComponentMapper<ZRPGCharacterComponent> getZrpgCharacterComponentComponentMapper() {
        return zrpgCharacterComponentComponentMapper;
    }
}

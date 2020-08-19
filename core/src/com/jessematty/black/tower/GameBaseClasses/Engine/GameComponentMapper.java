package com.jessematty.black.tower.GameBaseClasses.Engine;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AIComponent;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.CreateEntity;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Dying;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Ingest;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.PickUp;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Shoot;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Slash;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.SpellCast;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Talk;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Thrust;
import com.jessematty.black.tower.Components.Actions.ActionComponents;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.AddItemToContainerComponent;
import com.jessematty.black.tower.Components.AddComponent;
import com.jessematty.black.tower.Components.AddOwnerComponent;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Armor;
import com.jessematty.black.tower.Components.Attachable;
import com.jessematty.black.tower.Components.BitMaskable;
import com.jessematty.black.tower.Components.EditorImageComponent;
import com.jessematty.black.tower.Components.EntityCreateable;
import com.jessematty.black.tower.Components.Explodable;
import com.jessematty.black.tower.Components.Container;
import com.jessematty.black.tower.Components.EquipItem;
import com.jessematty.black.tower.Components.ErrorComponent;
import com.jessematty.black.tower.Components.Groups;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.Loadable;
import com.jessematty.black.tower.Components.OwnedComponent;
import com.jessematty.black.tower.Components.OwnerComponent;
import com.jessematty.black.tower.Components.Pack;
import com.jessematty.black.tower.Components.Readable;
import com.jessematty.black.tower.Components.RemoveItemFromContainerComponent;
import com.jessematty.black.tower.Components.RemoveOwnerComponent;
import com.jessematty.black.tower.Components.Root;
import com.jessematty.black.tower.Components.SoundComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangable;
import com.jessematty.black.tower.Components.Breather;
import com.jessematty.black.tower.Components.ChangeImage.ChangeImageOnStatsValueChanges;
import com.jessematty.black.tower.Components.Animation.Drawable;
import com.jessematty.black.tower.Components.Glow;
import com.jessematty.black.tower.Components.Growable;
import com.jessematty.black.tower.Components.Holdable;
import com.jessematty.black.tower.Components.Holder;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.ImageComponent;
import com.jessematty.black.tower.Components.Info;
import com.jessematty.black.tower.Components.Ingestable;
import com.jessematty.black.tower.Components.Light;
import com.jessematty.black.tower.Components.Markers.AddedToEngine;
import com.jessematty.black.tower.Components.Markers.BooleanStatChanged;
import com.jessematty.black.tower.Components.Markers.NumericStatChanged;
import com.jessematty.black.tower.Components.Markers.StatChanged;
import com.jessematty.black.tower.Components.Markers.StringStatChanged;
import com.jessematty.black.tower.Components.Markers.VisibleOnScreen;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.TimeChangingStats;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsSelfChangable;
import com.jessematty.black.tower.Components.PhysicalObject;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.Shootable;
import com.jessematty.black.tower.Components.Slashable;
import com.jessematty.black.tower.Components.SpellCastable;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangable;
import com.jessematty.black.tower.Components.TalkComponent;
import com.jessematty.black.tower.Components.Launchable;
import com.jessematty.black.tower.Components.Target;
import com.jessematty.black.tower.Components.Throwable;
import com.jessematty.black.tower.Components.Thrustable;
import com.jessematty.black.tower.Components.Tile;
import com.jessematty.black.tower.Components.TileWeatherNumericStatsChangable;
import com.jessematty.black.tower.Components.UnEquipItem;
import com.jessematty.black.tower.Components.Weapon;
import com.jessematty.black.tower.Maps.World;

import java.util.HashMap;
import java.util.Map;
//  class that contains all of the component mappers for all of the components in the game
public class GameComponentMapper {
    private ComponentMapper<AddedToEngine>  addedToEngineComponentMapper= ComponentMapper.getFor(AddedToEngine.class);
    private ComponentMapper<Drawable> drawableComponentMapper =ComponentMapper.getFor(Drawable.class);
    private ComponentMapper<AnimatableComponent> animatableComponentMapper =ComponentMapper.getFor(AnimatableComponent.class);
    private ComponentMapper<Action> actionComponentMapper=ComponentMapper.getFor(Action.class);
    private ComponentMapper<Position> positionComponentMapper=ComponentMapper.getFor(Position.class);
    private ComponentMapper<BitMaskable> bitMaskableComponentMapper =ComponentMapper.getFor(BitMaskable.class);
    private ComponentMapper<Movable> movableComponentMapper=ComponentMapper.getFor(Movable .class);
    private ComponentMapper<PhysicalObject> physicalObjectComponentMapper=ComponentMapper.getFor(PhysicalObject.class);
    private ComponentMapper<Light> lightComponentMapper= ComponentMapper.getFor(Light.class);
    private ComponentMapper<Tile> tileComponentMapper= ComponentMapper.getFor(Tile.class);
    private ComponentMapper<Holder> holderComponentMapper=ComponentMapper.getFor(Holder.class);
    private ComponentMapper<Name> nameComponentMapper=ComponentMapper.getFor(Name.class);
    private ComponentMapper<Growable> growableComponentMapper= ComponentMapper.getFor(Growable.class);
    private ComponentMapper<NumericStats> numericStatsComponentMapper =ComponentMapper.getFor(NumericStats.class);
    private ComponentMapper<StringStats> stringStatsComponentMapper=ComponentMapper.getFor(StringStats.class);
    private ComponentMapper<BooleanStats> booleanStatsComponentMapper=ComponentMapper.getFor(BooleanStats.class);
    private ComponentMapper<NumericStatsChangable> numericStatsChangableComponentMapper =ComponentMapper.getFor(NumericStatsChangable.class);
    private ComponentMapper<StringStatsChangable> stringStatsChangableComponentMapper =ComponentMapper.getFor(StringStatsChangable.class);
    private ComponentMapper<BooleanStatsChangable> booleanStatsChangableComponentMapper =ComponentMapper.getFor(BooleanStatsChangable.class);
    private ComponentMapper<Slashable> slashComponentMapper=ComponentMapper.getFor(Slashable.class);
    private ComponentMapper<Thrustable> thrustComponentMapper=ComponentMapper.getFor(Thrustable.class);
    private ComponentMapper<Shootable> shootComponentMapper=ComponentMapper.getFor(Shootable.class);
    private ComponentMapper<Armor> armorComponentMapper=ComponentMapper.getFor(Armor.class);
    private ComponentMapper<Weapon> weaponComponentMapper=ComponentMapper.getFor(Weapon.class);
    private ComponentMapper<Breather> breatherComponentMapper=ComponentMapper.getFor(Breather.class);
    private ComponentMapper<Holdable> holdableComponentMapper =ComponentMapper.getFor(Holdable.class);
    private ComponentMapper<Attachable> attachableComponentMapper =ComponentMapper.getFor(Attachable.class);
    private ComponentMapper<VisibleOnScreen> visibleOnScreenComponentMapper =ComponentMapper.getFor(VisibleOnScreen.class);
    private ComponentMapper<TileWeatherNumericStatsChangable> tileNumericStatsChangableComponentMapper =ComponentMapper.getFor(TileWeatherNumericStatsChangable.class);
    private ComponentMapper<ImageComponent> imageComponentMapper=ComponentMapper.getFor(ImageComponent.class);
    private ComponentMapper<ID> idComponentMapper=ComponentMapper.getFor(ID.class);
    private ComponentMapper<Glow> glowComponentMapper=ComponentMapper.getFor(Glow.class);
    private ComponentMapper<NumericStatsSelfChangable> numericStatsSelfChangableComponentMapper =ComponentMapper.getFor(NumericStatsSelfChangable.class);
    private ComponentMapper<ChangeImageOnStatsValueChanges> changeImageOnStatsValueChangesComponentMapper =ComponentMapper.getFor(ChangeImageOnStatsValueChanges.class);
    private ComponentMapper<NumericStatChanged> numericStatChangedComponentMapper =ComponentMapper.getFor(NumericStatChanged.class);
    private ComponentMapper<BooleanStatChanged> booleanStatChangedComponentMapper =ComponentMapper.getFor(BooleanStatChanged.class);
    private ComponentMapper<StringStatChanged> stringStatChangedComponentMapper =ComponentMapper.getFor(StringStatChanged.class);
    private ComponentMapper<StatChanged> statChangedComponentMapper =ComponentMapper.getFor(StatChanged.class);
    private ComponentMapper<ActionComponents> actionComponentsComponentMapper =ComponentMapper.getFor(ActionComponents.class);
    private ComponentMapper<Info> infoComponentMapper =ComponentMapper.getFor(Info.class);
    private ComponentMapper<Ingestable> ingestableComponentMapper= ComponentMapper.getFor(Ingestable.class);
    private ComponentMapper<Ingest> ingestingComponentMapper= ComponentMapper.getFor(Ingest.class);
    private ComponentMapper<Slashable> slashableComponentMapper=ComponentMapper.getFor(Slashable.class);
    private ComponentMapper<Slash> slashingComponentMapper=ComponentMapper.getFor(Slash.class);
    private ComponentMapper<Thrust> thrustingComponentMapper=ComponentMapper.getFor(Thrust.class);
    private ComponentMapper<Thrustable> thrustableComponentMapper=ComponentMapper.getFor(Thrustable.class);
    private ComponentMapper<Shoot> shootingComponentMapper=ComponentMapper.getFor(Shoot.class);
    private ComponentMapper<Shootable> shootableComponentMapper=ComponentMapper.getFor(Shootable.class);
    private ComponentMapper<SpellCastable> spellCastableComponentMapper=ComponentMapper.getFor(SpellCastable.class);
    private ComponentMapper<SpellCast> spellCastingComponentMapper=ComponentMapper.getFor(SpellCast.class);
    private ComponentMapper<NumericStatsChangeComponent> numericStatChangeComponentMapper=ComponentMapper.getFor(NumericStatsChangeComponent.class);
    private ComponentMapper<BooleanStatsChangeComponent> booleanStatChangeComponentComponentMapper =ComponentMapper.getFor(BooleanStatsChangeComponent.class);
    private ComponentMapper<StringStatsChangeComponent> stringStatChangeComponentComponentMapper =ComponentMapper.getFor(StringStatsChangeComponent.class);
    private  ComponentMapper<Item> itemComponentMapper=ComponentMapper.getFor(Item.class);
    private ComponentMapper<TimeChangingStats>timeChangingNumericStatsComponentMapper=ComponentMapper.getFor(TimeChangingStats.class);
    private ComponentMapper<Dying>dyingComponentMapper=ComponentMapper.getFor(Dying.class);
    private ComponentMapper<TalkComponent> talkComponentComponentMapper=ComponentMapper.getFor(TalkComponent.class);
    private ComponentMapper<Talk> talkingComponentMapper=ComponentMapper.getFor(Talk.class);
    private ComponentMapper<AIComponent> aiComponentMapper=ComponentMapper.getFor(AIComponent.class);
    private ComponentMapper<Launchable> throwComponentMapper=ComponentMapper.getFor(Launchable.class);
    private ComponentMapper<Container> containerComponentMapper=ComponentMapper.getFor(Container.class);
    private ComponentMapper <AddItemToContainerComponent> addItemToContainerComponentComponentMapper =ComponentMapper.getFor(AddItemToContainerComponent.class);
    private ComponentMapper <RemoveItemFromContainerComponent> removeItemFromContainerComponentComponentMapper =ComponentMapper.getFor(RemoveItemFromContainerComponent.class);
    private ComponentMapper<AddOwnerComponent> addOwnerComponentComponentMapper=ComponentMapper.getFor(AddOwnerComponent.class);
    private ComponentMapper<RemoveOwnerComponent> removeOwnerComponentComponentMapper=ComponentMapper.getFor(RemoveOwnerComponent.class);
    private ComponentMapper<OwnerComponent> ownerComponentComponentMapper=ComponentMapper.getFor(OwnerComponent.class);
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper=ComponentMapper.getFor(OwnedComponent.class);
    private ComponentMapper<ErrorComponent> errorComponentComponentMapper=ComponentMapper.getFor(ErrorComponent.class);
    private ComponentMapper<EquipItem> equipItemComponentMapper=ComponentMapper.getFor(EquipItem.class);
    private ComponentMapper<UnEquipItem> unEquipItemComponentMapperr=ComponentMapper.getFor(UnEquipItem.class);
    private ComponentMapper<Groups> groupsComponentMapper=ComponentMapper.getFor(Groups.class);
    private ComponentMapper<Pack> packComponentMapper=ComponentMapper.getFor(Pack.class);
    private ComponentMapper<Throwable> throwableComponentMapper=ComponentMapper.getFor(Throwable.class);
    private ComponentMapper<Readable> readableComponentMapper=ComponentMapper.getFor(Readable.class);
    private ComponentMapper<Loadable> loadableComponentMapper=ComponentMapper.getFor(Loadable.class);
    private ComponentMapper<SoundComponent> soundComponentComponentMapper=ComponentMapper.getFor(SoundComponent.class);
    private ComponentMapper<Root> rootComponentMapper=ComponentMapper.getFor(Root.class);
    private ComponentMapper<Explodable> blastableComponentMapper=ComponentMapper.getFor(Explodable.class);
    private ComponentMapper<AddComponent> addComponentComponentMapper=ComponentMapper.getFor(AddComponent.class);
    private ComponentMapper<Target> targetComponentMapper =ComponentMapper.getFor(Target.class);
    private ComponentMapper<EntityCreateable> entityCreateableComponentMapper =ComponentMapper.getFor(EntityCreateable.class);
    private ComponentMapper<CreateEntity> createEntityComponentMapper =ComponentMapper.getFor(CreateEntity.class);
    private ComponentMapper<PickUp> pickUpComponentMapper =ComponentMapper.getFor(PickUp.class);
    private ComponentMapper<EditorImageComponent> editorImageComponentComponentMapper =ComponentMapper.getFor(EditorImageComponent.class);



    private  Map<Class <? extends Component>, ComponentMapper> componentComponentMapperMap= new HashMap<Class<? extends Component>, ComponentMapper>();

    public GameComponentMapper() {
        componentComponentMapperMap.put(AddedToEngine.class, actionComponentMapper);
        componentComponentMapperMap.put(Drawable.class, drawableComponentMapper);
        componentComponentMapperMap.put( AnimatableComponent.class, animatableComponentMapper);
        componentComponentMapperMap.put(Movable.class, movableComponentMapper);
        componentComponentMapperMap.put(Action.class, actionComponentMapper);
        componentComponentMapperMap.put(Position.class, positionComponentMapper);
        componentComponentMapperMap.put( BitMaskable.class, bitMaskableComponentMapper);
        componentComponentMapperMap.put(PhysicalObject.class, physicalObjectComponentMapper);
        componentComponentMapperMap.put(Light.class, lightComponentMapper);
        componentComponentMapperMap.put(Growable.class, growableComponentMapper);
        componentComponentMapperMap.put(Name.class, nameComponentMapper);
        componentComponentMapperMap.put(NumericStats.class, numericStatsComponentMapper);
        componentComponentMapperMap.put(BooleanStats.class, booleanStatsComponentMapper);
        componentComponentMapperMap.put(StringStats.class, stringStatsComponentMapper);
        componentComponentMapperMap.put(StringStatsChangable.class, stringStatsChangableComponentMapper);
        componentComponentMapperMap.put(BooleanStatsChangable.class, booleanStatsChangableComponentMapper);
        componentComponentMapperMap.put(NumericStatsChangable.class, numericStatsChangableComponentMapper);
        componentComponentMapperMap.put(Slashable.class, slashComponentMapper);
        componentComponentMapperMap.put(Thrustable.class, thrustComponentMapper);
        componentComponentMapperMap.put(Shootable.class, shootComponentMapper);
        componentComponentMapperMap.put(Armor.class, armorComponentMapper);
        componentComponentMapperMap.put(Weapon.class, weaponComponentMapper);
        componentComponentMapperMap.put(ImageComponent.class, imageComponentMapper);
        componentComponentMapperMap.put(Holder.class, holderComponentMapper);
        componentComponentMapperMap.put(Tile.class, tileComponentMapper);
        componentComponentMapperMap.put(Breather.class, holdableComponentMapper);
        componentComponentMapperMap.put(Attachable.class, attachableComponentMapper);
        componentComponentMapperMap.put(VisibleOnScreen.class, visibleOnScreenComponentMapper);
        componentComponentMapperMap.put(TileWeatherNumericStatsChangable.class, tileNumericStatsChangableComponentMapper);
        componentComponentMapperMap.put(Glow.class, glowComponentMapper);
        componentComponentMapperMap.put(ID.class, idComponentMapper);
        componentComponentMapperMap.put(NumericStatsSelfChangable.class, numericStatsChangableComponentMapper);
        componentComponentMapperMap.put(ChangeImageOnStatsValueChanges.class, changeImageOnStatsValueChangesComponentMapper);
        componentComponentMapperMap.put(StringStatChanged.class, stringStatChangedComponentMapper);
        componentComponentMapperMap.put(NumericStatChanged.class, numericStatChangedComponentMapper);
        componentComponentMapperMap.put(BooleanStatChanged.class, booleanStatChangedComponentMapper);
        componentComponentMapperMap.put(StatChanged.class, statChangedComponentMapper);
        componentComponentMapperMap.put(ActionComponents.class, actionComponentsComponentMapper);
        componentComponentMapperMap.put(Info.class, infoComponentMapper);
          componentComponentMapperMap.put(Ingestable.class, ingestableComponentMapper);
          componentComponentMapperMap.put(Ingest.class, ingestingComponentMapper);
          componentComponentMapperMap.put(Slashable.class, slashableComponentMapper);
          componentComponentMapperMap.put(Slash.class, slashingComponentMapper);
          componentComponentMapperMap.put(Thrust.class, thrustingComponentMapper);
          componentComponentMapperMap.put(Thrustable.class, thrustableComponentMapper);
          componentComponentMapperMap.put(Shoot.class, shootingComponentMapper);
          componentComponentMapperMap.put(Shootable.class, shootableComponentMapper);
          componentComponentMapperMap.put(SpellCastable.class, spellCastableComponentMapper);
          componentComponentMapperMap.put(SpellCast.class, spellCastingComponentMapper);
          componentComponentMapperMap.put(NumericStatsChangeComponent.class, numericStatChangeComponentMapper);
          componentComponentMapperMap.put(BooleanStatsChangeComponent.class, booleanStatChangeComponentComponentMapper);
          componentComponentMapperMap.put(StringStatsChangeComponent.class, stringStatChangeComponentComponentMapper);
          componentComponentMapperMap.put(Item.class,itemComponentMapper);
          componentComponentMapperMap.put(TimeChangingStats.class, timeChangingNumericStatsComponentMapper);
          componentComponentMapperMap.put(Dying.class, dyingComponentMapper);
        componentComponentMapperMap.put(TalkComponent.class, talkComponentComponentMapper);
        componentComponentMapperMap.put(Talk.class, talkingComponentMapper);
        componentComponentMapperMap.put(AIComponent.class,aiComponentMapper);
        componentComponentMapperMap.put(Launchable.class, throwComponentMapper);
        componentComponentMapperMap.put(Container.class, containerComponentMapper);
        componentComponentMapperMap.put(AddItemToContainerComponent.class, addItemToContainerComponentComponentMapper);
        componentComponentMapperMap.put(RemoveItemFromContainerComponent.class, removeItemFromContainerComponentComponentMapper);
        componentComponentMapperMap.put(AddOwnerComponent.class, addOwnerComponentComponentMapper);
        componentComponentMapperMap.put(RemoveOwnerComponent.class, removeOwnerComponentComponentMapper);
        componentComponentMapperMap.put(OwnedComponent.class, ownedComponentComponentMapper);
        componentComponentMapperMap.put(OwnerComponent.class, ownerComponentComponentMapper);
        componentComponentMapperMap.put(ErrorComponent.class, errorComponentComponentMapper);
        componentComponentMapperMap.put(EquipItem.class, equipItemComponentMapper);
        componentComponentMapperMap.put( UnEquipItem.class, unEquipItemComponentMapperr);
        componentComponentMapperMap.put(Groups.class, groupsComponentMapper);
        componentComponentMapperMap.put(Pack.class, packComponentMapper);
        componentComponentMapperMap.put(Loadable.class, loadableComponentMapper);
        componentComponentMapperMap.put(Throwable.class, throwableComponentMapper);
        componentComponentMapperMap.put(Readable.class, readableComponentMapper);
        componentComponentMapperMap.put(SoundComponent.class, soundComponentComponentMapper);
        componentComponentMapperMap.put(Explodable.class, blastableComponentMapper);
        componentComponentMapperMap.put(Root.class, rootComponentMapper);
        componentComponentMapperMap.put(AddComponent.class, addComponentComponentMapper);
        componentComponentMapperMap.put(Target.class, targetComponentMapper);
        componentComponentMapperMap.put(EntityCreateable.class, entityCreateableComponentMapper);
        componentComponentMapperMap.put(CreateEntity.class, createEntityComponentMapper);
        componentComponentMapperMap.put(PickUp.class, pickUpComponentMapper);
        componentComponentMapperMap.put(EditorImageComponent.class, equipItemComponentMapper);








    }
    public <T extends Component> void addComponentToMapper(Class<T> componentClass){

     ComponentMapper<T> componentMapper=ComponentMapper.getFor(componentClass);

        componentComponentMapperMap.put(componentClass,componentMapper );

    }

    public <T extends Component> ComponentMapper<T> getComponent(Class<T> componentClass){

        return componentComponentMapperMap.get(componentClass);
    }


    public  <T extends  Component> Array<T>   getComponentsFromEntitiesWithComponents(Array<Entity> entities, Class< T >  componentsClass){
        Array<T> componentsToReturn= new Array<T>();
        int size=entities.size;
        ComponentMapper<T> componentMapper=ComponentMapper.getFor(componentsClass);

        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
                T  component=componentMapper.get(entity);

                if(component !=null) {
                    componentsToReturn.add(component);
                }




        }
        return  componentsToReturn;
    }



    public Array<Entity> getEntitiesWithComponents(Array<Entity> entities, Class<? extends  Component> ... components){
        Array<Entity> entitiesToReturn= new Array<Entity>();
        int size=entities.size;
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            int length=components.length;
            for(int count2=0; count2<length; count2++){
                boolean hasComponents=hasComponents(entity, components);
                if(hasComponents==true){
                    entitiesToReturn.add(entity);
                }


            }
        }
        return  entitiesToReturn;
    }

    public Array<Entity> getEntitiesWithComponentsById( World world, Array<String> entityIDs, Class<? extends  Component> ... components){
        Array<Entity> entitiesToReturn= new Array<Entity>();
        int size=entityIDs.size;
        for(int count=0; count<size; count++){
            Entity entity=world.getEntity(entityIDs.get(count));
            int length=components.length;
            for(int count2=0; count2<length; count2++){
                boolean hasComponents=hasComponents(entity, components);
                if(hasComponents==true){
                    entitiesToReturn.add(entity);
                }


            }
        }
        return  entitiesToReturn;
    }
    public Array<Entity> getEntitiesContainingStats(Array<Entity> entities, Array<String> numericStatNames, Array<String> booleanStatNames, Array<String> stringStatNames,Class<? extends  Component> ... components ){
        Array<Entity> entitiesToReturn= new Array<Entity>();
        int size=entities.size;

        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            boolean hasNumericStats=true;
            if(numericStatNames!=null) {
                NumericStats numericStats=numericStatsComponentMapper.get(entity);
                if(numericStats!=null){
                 hasNumericStats = hasNumericStats(numericStats, numericStatNames);
                }
                else{
                    hasNumericStats=false;

                }

            }
            if(hasNumericStats==false){
                continue;
            }


            boolean hasBooleanStats=true;
            if(booleanStatNames!=null) {
                BooleanStats booleanStats=booleanStatsComponentMapper.get(entity);
                if(booleanStats!=null) {
                    hasBooleanStats = hasBooleanStats(booleanStats, booleanStatNames);
                }
                else{
                    hasBooleanStats=false;

                }
            }

            if(hasBooleanStats==false){

                continue;
            }


            boolean hasStringStats=true;
            if(stringStatNames!=null) {
                StringStats stringStats=stringStatsComponentMapper.get(entity);
                if(stringStats!=null) {
                    hasStringStats = hasStringStats(stringStats, stringStatNames);
                }
                else{
                    hasStringStats=false;

                }

            }
            if(hasStringStats==false){

                continue;
            }
            boolean hasComponents=true;

            if(components.length>0){

                hasComponents=hasComponents(entity, components);



            }
            if(hasBooleanStats==true && hasComponents==true && hasNumericStats==true && hasStringStats==true){
                entitiesToReturn.add(entity);

            }

        }
        return  entitiesToReturn;
    }
    private boolean hasComponents(Entity entity, Class<? extends Component>... components){
        int length=components.length;
        for(int count2=0; count2<length; count2++) {
            if (componentComponentMapperMap.get(components[count2]).get(entity) == null) {
                return false;
            }
        }
        
        return true;
    }



    private boolean hasNumericStats(NumericStats stats, Array<String>  numericStatNames){
        
        int size=numericStatNames.size;
        for(int count=0; count<size; count++){
            
            NumericStat stat=stats.getNumericStat(numericStatNames.get(count));
            if(stat==null){
                
                return  false;
            }
            
            
        }
        
        
        return true;
        
    }
    private boolean hasBooleanStats(BooleanStats stats, Array<String>  booleanStatNames){
        int size=booleanStatNames.size;
        for(int count=0; count<size; count++){
            BooleanStat stat=stats.getBooleanStat(booleanStatNames.get(count));
            if(stat==null){
                return  false;
            }
        }
        return true;
    }
    private boolean hasStringStats(StringStats stats, Array<String>  stringStatNames){
        int size=stringStatNames.size;
        for(int count=0; count<size; count++){
            StringStat stat=stats.getStringStat(stringStatNames.get(count));
            if(stat==null){
                return  false;
            }
        }
        return true;
    }
    public ComponentMapper<AddedToEngine> getAddedToEngineComponentMapper() {
        return addedToEngineComponentMapper;
    }
    public ComponentMapper<Drawable> getDrawableComponentMapper() {
        return drawableComponentMapper;
    }
    public ComponentMapper<AnimatableComponent> getAnimatableComponentMapper() {
        return animatableComponentMapper;
    }
    public ComponentMapper<Action> getActionComponentMapper() {
        return actionComponentMapper;
    }
    public ComponentMapper<Position> getPositionComponentMapper() {
        return positionComponentMapper;
    }
    public ComponentMapper<BitMaskable> getBitMaskableComponentMapper() {
        return bitMaskableComponentMapper;
    }
    public ComponentMapper<Movable> getMovableComponentMapper() {
        return movableComponentMapper;
    }
    public ComponentMapper<PhysicalObject> getPhysicalObjectComponentMapper() {
        return physicalObjectComponentMapper;
    }

    public ComponentMapper<Light> getLightComponentMapper() {
        return lightComponentMapper;
    }
    public ComponentMapper<Tile> getTileComponentMapper() {
        return tileComponentMapper;
    }

    public ComponentMapper<Holder> getHolderComponentMapper() {
        return holderComponentMapper;
    }
    public ComponentMapper<Name> getNameComponentMapper() {
        return nameComponentMapper;
    }
    public ComponentMapper<Growable> getGrowableComponentMapper() {
        return growableComponentMapper;
    }
    public ComponentMapper<NumericStats> getNumericStatsComponentMapper() {
        return numericStatsComponentMapper;
    }
    public ComponentMapper<StringStats> getStringStatsComponentMapper() {
        return stringStatsComponentMapper;
    }
    public ComponentMapper<BooleanStats> getBooleanStatsComponentMapper() {
        return booleanStatsComponentMapper;
    }
    public ComponentMapper<NumericStatsChangable> getNumericStatsChangableComponentMapper() {
        return numericStatsChangableComponentMapper;
    }
    public ComponentMapper<StringStatsChangable> getStringStatsChangableComponentMapper() {
        return stringStatsChangableComponentMapper;
    }
    public ComponentMapper<BooleanStatsChangable> getBooleanStatsChangableComponentMapper() {
        return booleanStatsChangableComponentMapper;
    }
    public ComponentMapper<ImageComponent> getImageComponentMapper() {
        return imageComponentMapper;
    }

    public ComponentMapper<Breather> getBreatherComponentMapper() {
        return breatherComponentMapper;
    }

    public ComponentMapper<Holdable> getHoldableComponentMapper() {
        return holdableComponentMapper;
    }


    public ComponentMapper<TileWeatherNumericStatsChangable> getTileWeatherNumericStatsChangableComponentMapper() {
        return tileNumericStatsChangableComponentMapper;
    }

    public ComponentMapper<Slashable> getSlashComponentMapper() {
        return slashComponentMapper;
    }
    public ComponentMapper<Thrustable> getThrustComponentMapper() {
        return thrustComponentMapper;
    }
    public ComponentMapper<Shootable> getShootComponentMapper() {
        return shootComponentMapper;
    }
    public ComponentMapper<Armor> getArmorComponentMapper() {
        return armorComponentMapper;
    }
    public ComponentMapper<Weapon> getWeaponComponentMapper() {
        return weaponComponentMapper;
    }
    public Map<Class<? extends Component>, ComponentMapper> getComponentComponentMapperMap() {
        return componentComponentMapperMap;
    }


    public ComponentMapper<VisibleOnScreen> getVisibleOnScreenComponentMapper() {
        return visibleOnScreenComponentMapper;
    }


    public ComponentMapper<TileWeatherNumericStatsChangable> getTileNumericStatsChangableComponentMapper() {
        return tileNumericStatsChangableComponentMapper;
    }

    public ComponentMapper<ID> getIdComponentMapper() {
        return idComponentMapper;
    }

    public ComponentMapper<Glow> getGlowComponentMapper() {
        return glowComponentMapper;
    }

    public ComponentMapper<NumericStatsSelfChangable> getNumericStatsSelfChangableComponentMapper() {
        return numericStatsSelfChangableComponentMapper;
    }

    public ComponentMapper<ChangeImageOnStatsValueChanges> getChangeImageOnStatsValueChangesComponentMapper() {
        return changeImageOnStatsValueChangesComponentMapper;
    }

    public ComponentMapper<NumericStatChanged> getNumericStatChangedComponentMapper() {
        return numericStatChangedComponentMapper;
    }

    public ComponentMapper<BooleanStatChanged> getBooleanStatChangedComponentMapper() {
        return booleanStatChangedComponentMapper;
    }

    public ComponentMapper<StringStatChanged> getStringStatChangedComponentMapper() {
        return stringStatChangedComponentMapper;
    }

    public ComponentMapper<StatChanged> getStatChangedComponentMapper() {
        return statChangedComponentMapper;
    }

    public ComponentMapper<ActionComponents> getActionComponentsComponentMapper() {
        return actionComponentsComponentMapper;
    }

    public ComponentMapper<Info> getInfoComponentMapper() {
        return infoComponentMapper;
    }

    public ComponentMapper<Ingestable> getIngestableComponentMapper() {
        return ingestableComponentMapper;
    }

    public ComponentMapper<Ingest> getIngestingComponentMapper() {
        return ingestingComponentMapper;
    }

    public ComponentMapper<Slashable> getSlashableComponentMapper() {
        return slashableComponentMapper;
    }

    public ComponentMapper<Slash> getSlashingComponentMapper() {
        return slashingComponentMapper;
    }

    public ComponentMapper<Thrust> getThrustingComponentMapper() {
        return thrustingComponentMapper;
    }

    public ComponentMapper<Thrustable> getThrustableComponentMapper() {
        return thrustableComponentMapper;
    }

    public ComponentMapper<Shoot> getShootingComponentMapper() {
        return shootingComponentMapper;
    }

    public ComponentMapper<Shootable> getShootableComponentMapper() {
        return shootableComponentMapper;
    }

    public ComponentMapper<SpellCastable> getSpellCastableComponentMapper() {
        return spellCastableComponentMapper;
    }

    public ComponentMapper<SpellCast> getSpellCastingComponentMapper() {
        return spellCastingComponentMapper;
    }

    public ComponentMapper<NumericStatsChangeComponent> getNumericStatChangeComponentMapper() {
        return numericStatChangeComponentMapper;
    }

    public ComponentMapper<BooleanStatsChangeComponent> getBooleanStatChangeComponentComponentMapper() {
        return booleanStatChangeComponentComponentMapper;
    }

    public ComponentMapper<StringStatsChangeComponent> getStringStatChangeComponentComponentMapper() {
        return stringStatChangeComponentComponentMapper;
    }

    public ComponentMapper<Item> getItemComponentMapper() {
        return itemComponentMapper;
    }

    public ComponentMapper<TimeChangingStats> getTimeChangingNumericStatsComponentMapper() {
        return timeChangingNumericStatsComponentMapper;
    }

    public ComponentMapper<TalkComponent> getTalkComponentComponentMapper() {
        return talkComponentComponentMapper;
    }

    public ComponentMapper<Attachable> getAttachableComponentMapper() {
        return attachableComponentMapper;
    }

    public ComponentMapper<Launchable> getThrowComponentMapper() {
        return throwComponentMapper;
    }

    public ComponentMapper<AIComponent> getAiComponentMapper() {
        return aiComponentMapper;
    }

    public ComponentMapper<Talk> getTalkingComponentMapper() {
        return talkingComponentMapper;
    }

    public ComponentMapper<Dying> getDyingComponentMapper() {
        return dyingComponentMapper;
    }


    public ComponentMapper<AddItemToContainerComponent> getAddItemToContainerComponentComponentMapper() {
        return addItemToContainerComponentComponentMapper;
    }

    public ComponentMapper<RemoveItemFromContainerComponent> getRemoveItemFromContainerComponentComponentMapper() {
        return removeItemFromContainerComponentComponentMapper;
    }

    public ComponentMapper<Container> getContainerComponentMapper() {
        return containerComponentMapper;
    }

    public ComponentMapper<AddOwnerComponent> getAddOwnerComponentComponentMapper() {
        return addOwnerComponentComponentMapper;
    }

    public ComponentMapper<OwnerComponent> getOwnerComponentComponentMapper() {
        return ownerComponentComponentMapper;
    }

    public ComponentMapper<OwnedComponent> getOwnedComponentComponentMapper() {
        return ownedComponentComponentMapper;
    }

    public ComponentMapper<RemoveOwnerComponent> getRemoveOwnerComponentComponentMapper() {
        return removeOwnerComponentComponentMapper;
    }

    public ComponentMapper<ErrorComponent> getErrorComponentComponentMapper() {
        return errorComponentComponentMapper;
    }

    public ComponentMapper<EquipItem> getEquipItemComponentMapper() {
        return equipItemComponentMapper;
    }

    public ComponentMapper<UnEquipItem> getUnEquipItemComponentMapperr() {
        return unEquipItemComponentMapperr;
    }

    public ComponentMapper<Throwable> getThrowableComponentMapper() {
        return throwableComponentMapper;
    }

    public ComponentMapper<Readable> getReadableComponentMapper() {
        return readableComponentMapper;
    }

    public ComponentMapper<Loadable> getLoadableComponentMapper() {
        return loadableComponentMapper;
    }


    public ComponentMapper<SoundComponent> getSoundComponentComponentMapper() {
        return soundComponentComponentMapper;
    }

    public ComponentMapper<Groups> getGroupsComponentMapper() {
        return groupsComponentMapper;
    }

    public ComponentMapper<Pack> getPackComponentMapper() {
        return packComponentMapper;
    }

    public ComponentMapper<Explodable> getBlastableComponentMapper() {
        return blastableComponentMapper;
    }

    public ComponentMapper<Root> getRootComponentMapper() {
        return rootComponentMapper;
    }

    public ComponentMapper<AddComponent> getAddComponentComponentMapper() {
        return addComponentComponentMapper;
    }

    public ComponentMapper<Target> getTargetComponentMapper() {
        return targetComponentMapper;
    }

    public ComponentMapper<EntityCreateable> getEntityCreateableComponentMapper() {
        return entityCreateableComponentMapper;
    }

    public ComponentMapper<CreateEntity> getCreateEntityComponentMapper() {
        return createEntityComponentMapper;
    }

    public ComponentMapper<PickUp> getPickUpComponentMapper() {
        return pickUpComponentMapper;
    }


    public ComponentMapper<EditorImageComponent> getEditorImageComponentComponentMapper() {
        return editorImageComponentComponentMapper;
    }
}

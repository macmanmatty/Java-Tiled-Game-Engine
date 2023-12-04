package com.jessematty.black.tower.GameBaseClasses.Character;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AttachEntity.HoldItem;
import com.jessematty.black.tower.Components.Containers.ContainerComponent;
import com.jessematty.black.tower.Components.Item.ItemAction.PickUpItemComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.OnSelected;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.OptionPane;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.EntityInfoWindow;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.MultipleEntitySelect.EntitySelectWindow;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MapUtilities;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
public class PlayerItemFunctions {
    private PlayerItemFunctions() {
    }
    private void pickUpItem(MapDraw mapDraw,  ZRPGCharacter zrpgCharacter, int hand) {
        PositionComponent positionComponent = zrpgCharacter.getPositionComponent();
        GameMap map = mapDraw.getWorld().getMap(positionComponent.getMapId());
        LandSquareTile landSquareTile = MapUtilities.getCenterTile(map, positionComponent.getBoundsBoundingRectangle());
        Array<Entity> entities = landSquareTile.getEntities(ItemComponent.class);
        if (entities.size > 1) {
            OnSelected<Entity> onSelected = new OnSelected<Entity>() {
                @Override
                public void onSelected(Entity entity) {
                    pickUpItem(hand, zrpgCharacter, entity);
                }
            };
            EntitySelectWindow entitySelectWindow = new EntitySelectWindow(mapDraw.getGameAssets().getCurrentSkin(), entities, mapDraw.getGameAssets(), onSelected);
            mapDraw.getUiStage().addWindow(entitySelectWindow, ScreenPosition.CENTER);
        }
        pickUpItem(hand, zrpgCharacter, entities.get(0));
    }
        public  void pickUpItem(int hand  , ZRPGCharacter zrpgCharacter, Entity itemToPickUp){
            PickUpItemComponent pickUpItemComponent = new PickUpItemComponent();
            Entity handEntity = zrpgCharacter.getHand(hand);
            String id = GameComponentMapper.getIdComponentMapper().get(handEntity).getId();
            pickUpItemComponent.setEntityToPickUpId(id);
            itemToPickUp.add(pickUpItemComponent);
        }
        public static  void displayPack(MapDraw mapDraw, ZRPGCharacter player) {
            Array<Entity> packs = player.getPacks();
            if (packs.size < 1) {
                OnSelected<Entity> onSelected = new OnSelected<Entity>() {
                    @Override
                    public void onSelected(Entity pack) {
                        displayItems(pack, mapDraw);
                    }
                };
                EntitySelectWindow entitySelectWindow = new EntitySelectWindow(mapDraw.getGameAssets().getDefaultSkin(), packs, mapDraw.getGameAssets(), onSelected);
                mapDraw.getUiStage().addWindow(entitySelectWindow, ScreenPosition.CENTER);
            }
            else if(packs.size==1){
                displayItems(packs.get(0), mapDraw);
            }
            else{
                OptionPane optionPane= new OptionPane(mapDraw.getGameAssets().getCurrentSkin(),"No Packs!",  "You Have No Packs!!", "Ok", "default");
                mapDraw.getUiStage().addWindow(optionPane, ScreenPosition.CENTER);
            }
        }
    /**
     * displays items in a select pack
     * @param pack
     * @param mapDraw
     */
        private static void displayItems(Entity pack, MapDraw mapDraw){
            ContainerComponent packComponent = GameComponentMapper.getContainerComponentMapper().get(pack);
            if (packComponent != null) {
                OnSelected<Entity> onSelected1 = new OnSelected<Entity>() {
                    @Override
                    public void onSelected(Entity entity) {
                        EntityInfoWindow entityInfoWindow = new EntityInfoWindow(mapDraw.getGameAssets().getCurrentSkin(), "default", entity, mapDraw.getGameAssets());
                        mapDraw.getUiStage().addWindow(entityInfoWindow, ScreenPosition.CENTER);
                    }
                };
                World world=mapDraw.getWorld();
                EntitySelectWindow entitySelectWindow = new EntitySelectWindow(mapDraw.getGameAssets().getDefaultSkin(),world.getEntitiesFromEntityIdsArray(packComponent.getEntitiesInContainerIds()) , mapDraw.getGameAssets(), onSelected1);
                mapDraw.getUiStage().addWindow(entitySelectWindow, ScreenPosition.CENTER);
            }
        }

    /**
     * pick ups an item  if only  one item exists will attempt  pick up the item
     * if more than one item exists will display a Entity select window
     * so the user can choose which item they wish to pick up
     * @param zrpgCharacter the player zrpgCharacter
     * @param draw the map draw to add the window to if needed
     */
    public static void pickUpItem(ZRPGCharacter zrpgCharacter, MapDraw draw, int hand) {
        String id = GameComponentMapper.getIdComponentMapper().get(zrpgCharacter.getHand(1)).getId();
        if(zrpgCharacter.getHandHolders()[hand]!=null){
            String text= "Hand Is Not Empty";
            draw.getUiStage().getGameLogger().logInfo(text);
            return;
        }
        Array<Entity> entities = zrpgCharacter.getPositionComponent().getTiles().get(0).getEntities(ItemComponent.class);
        if (entities.size == 1) {
            entities.get(0).add(new HoldItem(id));
        } else if (entities.size > 0) {
            displayPickUpWindow(id, draw, entities);
        }
    }
    /**
     *  in the case of multiple items to pick up
     * adds an entity select window to the stage
     * to select which item you wish to pick up
     * @param handId the id of the hand  entity to pick up the item
     * @param draw the map that holds the UIStage to add the window to.
     * @param entities the array of items that can be picked up.
     */
    
    public static void displayPickUpWindow( String handId, MapDraw draw, Array<Entity> entities){
        OnSelected<Entity> onSelected = new OnSelected<Entity>() {
            @Override
            public void onSelected(Entity item) {
                item.add(new HoldItem(handId));
            }
        };
        String text="Select An Item To Pick Up";
                EntitySelectWindow entitySelectWindow = new EntitySelectWindow(text, draw.getGameAssets().getCurrentSkin(),  entities, draw.getGameAssets(),  "Select",  onSelected);
                draw.getUiStage().addWindow(entitySelectWindow, ScreenPosition.CENTER);
    }
        }

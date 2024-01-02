package com.jessematty.black.tower.GameBaseClasses.Character;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AttachEntity.HoldItem;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.Containers.ContainerComponent;
import com.jessematty.black.tower.Components.EventComponents.AddItemToContainer;
import com.jessematty.black.tower.Components.Item.ItemAction.PickUpItemComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.OnSelected;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.OptionPane;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.EntityInfoWindow;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.MultipleEntitySelect.EntitySelectWindow;
import com.jessematty.black.tower.Maps.World;
public class PlayerItemFunctions {
    private PlayerItemFunctions() {
    }


    public static void displayPack(MapDraw mapDraw, ZRPGCharacter player) {
        Array<Entity> packs = mapDraw.getWorld().getEntitiesFromEntityIdsArray(player.getPacks().getPackEntityIds());
        if (packs.size < 1) {
            OnSelected<Entity> onSelected = new OnSelected<Entity>() {
                @Override
                public void onSelected(Entity pack) {
                    displayItems(pack, mapDraw);
                }
            };
            EntitySelectWindow entitySelectWindow = new EntitySelectWindow(mapDraw.getGameAssets().getDefaultSkin(), packs, mapDraw.getGameAssets(), onSelected);
            mapDraw.getUiStage().addWindow(entitySelectWindow, ScreenPosition.CENTER);
        } else if (packs.size == 1) {
            displayItems(packs.get(0), mapDraw);
        } else {
            OptionPane optionPane = new OptionPane(mapDraw.getGameAssets().getCurrentSkin(), "No Packs!", "You Have No Packs!!", "Ok", "default");
            mapDraw.getUiStage().addWindow(optionPane, ScreenPosition.CENTER);
        }
    }

    /**
     * displays items in a selected pack
     *
     * @param pack
     * @param mapDraw
     */
    private static void displayItems(Entity pack, MapDraw mapDraw) {
        ContainerComponent packComponent = GameComponentMapper.getContainerComponentMapper().get(pack);
        if (packComponent != null) {
            OnSelected<Entity> onSelected1 = new OnSelected<Entity>() {
                @Override
                public void onSelected(Entity entity) {
                    EntityInfoWindow entityInfoWindow = new EntityInfoWindow(mapDraw.getGameAssets().getCurrentSkin(), "default", entity, mapDraw.getGameAssets());
                    mapDraw.getUiStage().addWindow(entityInfoWindow, ScreenPosition.CENTER);
                }
            };
            World world = mapDraw.getWorld();
            EntitySelectWindow entitySelectWindow = new EntitySelectWindow(mapDraw.getGameAssets().getDefaultSkin(), world.getEntitiesFromEntityIdsArray(packComponent.getEntitiesInContainerIds()), mapDraw.getGameAssets(), onSelected1);
            mapDraw.getUiStage().addWindow(entitySelectWindow, ScreenPosition.CENTER);
        }
    }

    /**
     * add an item to a pack from from a players hand
     * if more han one pack exists that can accept a given item
     * a window will pop up asking he user which pack to add
     * @param zrpgCharacter  the player
     * @param draw the map draw used to get the world for  getting entities from  ids
     */
    public static void addItemToPackFromHand(ZRPGCharacter zrpgCharacter, MapDraw draw) {
        Holder hand=zrpgCharacter.getHandHolders()[zrpgCharacter.getCurrentHand()];
        String itemId = hand.getItemToHoldId();
        Entity item = draw.getWorld().getEntity(itemId);
        Array<Entity> packs = draw.getWorld().getEntitiesFromEntityIdsArray(zrpgCharacter.getPacks().getPackEntityIds());
        if (packs.size == 1) {
            item.add(new AddItemToContainer(packs.get(0)));
            GameAssets.getGameLogger().logInfo("Item added to pack!!");

        } else if (packs.size>1) {
            OnSelected<Entity> onSelected = new OnSelected<Entity>() {
                @Override
                public void onSelected(Entity pack) {
                    item.add(new AddItemToContainer(pack));
                    GameAssets.getGameLogger().logInfo("Item added to pack!!");

                }
            };
            EntitySelectWindow entitySelectWindow = new EntitySelectWindow(draw.getGameAssets().getCurrentSkin(), packs, draw.getGameAssets(), onSelected);
            draw.getUiStage().addWindow(entitySelectWindow, ScreenPosition.CENTER);


        }
        else{
            GameAssets.getGameLogger().logInfo("You have no packs to store the item!!");
        }

    }
        /**
         * picks up an item  if only  one item exists will attempt  pick up the item
         * if more than one item exists will display a Entity select window
         * so the user can choose which item they wish to pick up
         * @param zrpgCharacter the player zrpgCharacter
         * @param draw the map draw to add the window to if needed
         */

        public static void pickUpItem (ZRPGCharacter zrpgCharacter, MapDraw draw,int hand){
            String id = GameComponentMapper.getIdComponentMapper().get(zrpgCharacter.getHand(hand)).getId();
            if (zrpgCharacter.getHandHolders()[hand].getItemToHoldId() != null) {
                String text = "Hand Is Not Empty";
                draw.getUiStage().getScreenLogger().logInfo(text);
                return;
            }
            Array<Entity> entities = zrpgCharacter.getPositionComponent().getTiles().get(0).getEntities(ItemComponent.class);
            System.out.println("Picking up item!!! from items "+entities.size);
            if (entities.size == 1) {
                Entity entity=entities.get(0);
               pickupItem(entity, id);
            } else if (entities.size > 0) {
                displayPickUpWindow(id, draw, entities);
            }
        }

    /**
     * add components to picked -up entity
     * @param entity the entity to pick up
     * @param handId the id of the hand to pick up
     */
    private static  void pickupItem(Entity entity, String handId){
            entity.add(new HoldItem(handId));
            entity.add( new PickUpItemComponent());
            GameAssets.getGameLogger().logInfo("picked up", entity);

        }


        /**
         *  in the case of multiple items to pick up
         * adds an entity select window to the stage
         * to select which item you wish to pick up
         * @param handId the id of the hand  entity to pick up the item
         * @param draw the map that holds the UIStage to add the window to.
         * @param entities the array of items that can be picked up.
         */

        public static void displayPickUpWindow (String handId, MapDraw
        draw, Array < Entity > entities){
            OnSelected<Entity> onSelected = new OnSelected<Entity>() {
                @Override
                public void onSelected(Entity item) {
                    pickupItem(item, handId);
                }
            };
            String text = "Select An Item To Pick Up";
            EntitySelectWindow entitySelectWindow = new EntitySelectWindow(text, draw.getGameAssets().getCurrentSkin(), entities, draw.getGameAssets(), "Select", onSelected);
            draw.getUiStage().addWindow(entitySelectWindow, ScreenPosition.CENTER);
        }
    }


package com.jessematty.black.tower.GameBaseClasses.Character;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
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
                EntitySelectWindow entitySelectWindow = new EntitySelectWindow(mapDraw.getGameAssets().getDefaultSkin(),packComponent.getEntitiesInContainer() , mapDraw.getGameAssets(), onSelected1);
                mapDraw.getUiStage().addWindow(entitySelectWindow, ScreenPosition.CENTER);

            }
        }

        }


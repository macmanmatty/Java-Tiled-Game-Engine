package com.jessematty.black.tower.GameBaseClasses.Character;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Item.ItemAction.PickUpItemComponent;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.OnSelected;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
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
            EntitySelectWindow entitySelectWindow = new EntitySelectWindow(mapDraw.getGameAssets().getSkin(), entities, mapDraw.getGameAssets(), onSelected);
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

}

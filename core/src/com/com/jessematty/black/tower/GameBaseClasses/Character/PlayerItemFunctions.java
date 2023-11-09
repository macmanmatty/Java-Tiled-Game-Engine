package com.jessematty.black.tower.GameBaseClasses.Character;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Item.PickUpItemComponent;
import com.jessematty.black.tower.Components.Other.ZRPGCharacter;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.OnSelected;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.MultipleEntitySelect.EntitySelectWindow;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MapUtilities;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class PlayerItemFunctions {

    private PlayerItemFunctions() {
    }

    private void pickUpItem(ZRPGCharacter zrpgCharacter){
        PositionComponent positionComponent=zrpgCharacter.getPositionComponent();
        GameMap map=zrpgCharacter.getWorld().getMap(positionComponent.getMapId());
        LandSquareTile landSquareTile= MapUtilities.getCenterTile(map, positionComponent.getBoundsBoundingRectangle());
        Array<Entity> entities=landSquareTile.getEntities(ItemComponent.class);
        if(entities.size>1){
            OnSelected<Entity> onSelected= new OnSelected<Entity>() {
                @Override
                public void onSelected(Entity entity) {
                    PickUpItemComponent pickUpItemComponent= new PickUpItemComponent();
                    entity.add(pickUpItemComponent);

                }
            };


        }
    }
}

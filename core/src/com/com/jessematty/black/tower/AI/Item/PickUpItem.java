package com.jessematty.black.tower.AI.Item;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.AI.ZRPGAIAction;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class PickUpItem extends ZRPGAIAction {
   private LandSquareTile targetTile;
    private GameMap map;
    public PickUpItem(ZRPGCharacter zrpgCharacter, LandSquareTile targetTile) {
        super(zrpgCharacter);
        this.targetTile = targetTile;
    }


    @Override
    public int  act(float deltaTime) {
       Array<Entity>  entities=targetTile.getEntities(ItemComponent.class);
        Entity entity =entities.get(0);
        ComponentMapper<EntityId> idComponentMapper= GameComponentMapper.getIdComponentMapper();
        return -1;
        }


}

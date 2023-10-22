package com.jessematty.black.tower.AI.Item;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.AI.ZRPGAIAction;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Item.PickUpItemComponent;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Other.ZRPGCharacter;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class PickUpItem extends ZRPGAIAction {
   private LandSquareTile targetTile;
    private GameMap map;
    public PickUpItem(ZRPGCharacter zrpgCharacter, GameMap map) {
        super(zrpgCharacter);
        this.map=map;
    }

    public PickUpItem(ZRPGCharacter zrpgCharacter, GameMap map, LandSquareTile targetTile) {
        this(zrpgCharacter, map);
        this.targetTile = targetTile;
    }


    @Override
    public int  act(float deltaTime) {
       Array<Entity>  entities=targetTile.getEntities(ItemComponent.class);
        Entity entity =entities.get(0);
        ComponentMapper<EntityId> idComponentMapper= GameComponentMapper.getIdComponentMapper();
        entity.add(new PickUpItemComponent(idComponentMapper.get(entity).getId()));
        return -1;
        }


}

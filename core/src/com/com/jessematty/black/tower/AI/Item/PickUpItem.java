package com.jessematty.black.tower.AI.Item;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.AI.ZRPGAIAction;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Other.ZRPGCharacter;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class PickUpItem extends ZRPGAIAction {
   private LandSquareTile targetTile;
  private GameMap map;
   private  int xSize;
    private int ySize;
    private PositionComponent position;
   private MovableComponent movableComponent;
    public PickUpItem(ZRPGCharacter zrpgCharacter, GameMap map) {
        super(zrpgCharacter);
        this.xSize=map.getXTiles();
        this.ySize=map.getYTiles();
        this.map=map;
        position=zrpgCharacter.getPositionComponent();
    }

    public PickUpItem(ZRPGCharacter zrpgCharacter, GameMap map, LandSquareTile targetTile) {
        this(zrpgCharacter, map);
        this.targetTile = targetTile;
    }


    @Override
    public int  act(float deltaTime) {
       Array<Entity>  entities=targetTile.getEntities(ItemComponent.class);


        return -1;

        }


}

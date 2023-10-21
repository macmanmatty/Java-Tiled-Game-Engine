package com.jessematty.black.tower.AI.Base;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.AI.ZRPGAIAction;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Other.ZRPGCharacter;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

/**
 * removes all actions from a ai characters brain
 */
public class ClearQueue extends ZRPGAIAction {
    public ClearQueue(ZRPGCharacter zrpgCharacter) {
        super(zrpgCharacter);

    }

    @Override
    public int  act(float deltaTime) {
        brain.getZrpgAIActions().clear();
        return -1;

        }


}

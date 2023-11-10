package com.jessematty.black.tower.AI.Base;

import com.jessematty.black.tower.AI.ZRPGAIAction;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;

/**
 * removes all actions from a ai characters brain
 */
public class ClearQueue extends ZRPGAIAction {
    public ClearQueue(ZRPGCharacter zrpgCharacter) {
        super(zrpgCharacter);

    }

    @Override
    public int  act(float deltaTime) {
        zrpgBrainComponent.getZrpgAIActions().clear();
        return -1;

        }


}

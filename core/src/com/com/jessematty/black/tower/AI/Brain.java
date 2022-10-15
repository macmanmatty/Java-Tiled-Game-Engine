package com.jessematty.black.tower.AI;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public  class Brain implements Component {

    private Array<ZRPGAIAction> zrpgAIActions =new Array<ZRPGAIAction>();

    public Array<ZRPGAIAction> getZrpgAIActions() {
        return zrpgAIActions;
    }

    public void aiAct(float deltaTime){
        if(zrpgAIActions.size>0) {
            zrpgAIActions.get(0).actAI(deltaTime);

        }
    }

    public void addAction(ZRPGAIAction zrpgaiAction){
        this.zrpgAIActions.add(zrpgaiAction);
    }

    public void removeAction(ZRPGAIAction zrpgaiAction){
        this.zrpgAIActions.removeValue(zrpgaiAction, true);
    }

}

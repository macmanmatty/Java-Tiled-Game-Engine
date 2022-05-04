package com.jessematty.black.tower.AI;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public  class Brain implements Component {

    private Array<ZRPGAIAction> zrpgaiActions =new Array<ZRPGAIAction>();

    public Array<ZRPGAIAction> getZrpgaiActions() {
        return zrpgaiActions;
    }

    public void aiAct(float deltaTime){
        if(zrpgaiActions.size>0) {
            zrpgaiActions.get(0).actAI(deltaTime);

        }
    }

    public void addAction(ZRPGAIAction zrpgaiAction){
        this.zrpgaiActions.add(zrpgaiAction);
    }

    public void removeAction(ZRPGAIAction zrpgaiAction){
        this.zrpgaiActions.removeValue(zrpgaiAction, true);
    }

}

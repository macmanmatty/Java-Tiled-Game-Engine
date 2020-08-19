package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.Components.Actions.Action;

import java.util.ArrayList;
import java.util.List;

public class Weapon implements Component {

  private  List<com.jessematty.black.tower.Components.Actions.Action> attackModes= new ArrayList<com.jessematty.black.tower.Components.Actions.Action>();
  private com.jessematty.black.tower.Components.Actions.Action currentAttackMode;
  private int actionCounter;

    public List<com.jessematty.black.tower.Components.Actions.Action> getAttackModes() {
        return attackModes;
    }


    public void setCurrentAction(){

        if(attackModes.size()>0) {
            currentAttackMode = attackModes.get(actionCounter);
            actionCounter++;
            if (actionCounter == attackModes.size() - 1) {

                actionCounter = 0;

            }

        }

    }



    public Action getCurrentAttackMode() {
        return currentAttackMode;
    }


}


package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.Components.Actions.ActionComponent;

import java.util.ArrayList;
import java.util.List;

public class Weapon implements Component {

  private  List<ActionComponent> attackModes= new ArrayList<ActionComponent>();
  private ActionComponent currentAttackMode;
  private int actionCounter;

    public List<ActionComponent> getAttackModes() {
        return attackModes;
    }

    public ActionComponent getCurrentAttackMode() {
        return currentAttackMode;
    }


}


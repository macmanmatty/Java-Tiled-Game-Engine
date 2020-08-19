package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ActionComponent;

public class Holdable implements ActionableComponent  {

  private   HoldPosition holdPosition;

    public HoldPosition getHoldPosition() {
        return holdPosition;
    }

    public void setHoldPosition(HoldPosition holdPosition) {
        this.holdPosition = holdPosition;
    }
}


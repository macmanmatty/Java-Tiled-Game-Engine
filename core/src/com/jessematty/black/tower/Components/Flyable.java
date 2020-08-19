package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ActionComponent;

public class Flyable implements ActionableComponent  {

    private Entity owner;

    public Entity getOwner() {
        return owner;
    }
}


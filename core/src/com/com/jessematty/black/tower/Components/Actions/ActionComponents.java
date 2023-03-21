package com.jessematty.black.tower.Components.Actions;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ItemActionImageComponent;

// class that holds an array of all action components that an entity has
public class ActionComponents implements Component {

   private Array<ItemActionImageComponent> actionComponents = new Array<>();

    public Array<ItemActionImageComponent> getActionComponents() {
        return actionComponents;
    }
}

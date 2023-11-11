package com.jessematty.black.tower.Components.Item.ItemAction;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

// class that holds an array of all action components that an entity has
public class ItemActionComponents implements Component {

   private Array<ItemActionComponent> actionComponents = new Array<>();

    public Array<ItemActionComponent> getActionComponents() {
        return actionComponents;
    }
}

package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class Leaf implements Component {

    private Entity owner;

    public Entity getOwner() {
        return owner;
    }
}


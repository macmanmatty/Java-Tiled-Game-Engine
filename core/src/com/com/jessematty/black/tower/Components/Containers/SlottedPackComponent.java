package com.jessematty.black.tower.Components.Containers;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class SlottedPackComponent implements Component {

    private Array<PackSlot> slots =new Array();

    public Array<PackSlot> getSlots() {
        return slots;
    }
}

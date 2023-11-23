package com.jessematty.black.tower.Components.Containers;


import com.badlogic.gdx.utils.Array;

public class PackComponent extends ContainerComponent{

    private Array<PackSlot> slots =new Array();

    public Array<PackSlot> getSlots() {
        return slots;
    }
}

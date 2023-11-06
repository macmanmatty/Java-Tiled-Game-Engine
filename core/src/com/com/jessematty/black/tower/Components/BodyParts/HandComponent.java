package com.jessematty.black.tower.Components.BodyParts;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AttachEntity.Holder;

public class HandComponent implements Component {
    private int size;
    private int numberOfFingers;

    private Array<Holder> fingers = new Array<Holder>();

    public HandComponent(int numberOfFingers) {
        this.numberOfFingers = numberOfFingers;
        for(int count=0; count<numberOfFingers; count++){
            Holder holder= new Holder();
            holder.getHoldableItemGroups().add("ring");
            fingers.add(holder);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Array<Holder> getFingers() {
        return fingers;
    }

    public int getNumberOfFingers() {
        return numberOfFingers;
    }

}


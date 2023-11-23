package com.jessematty.black.tower.Components.BodyParts;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

/**
 * component for bopy that has parts attached to it
 */
public class BodyComponent implements Component {

    /**
     * the map of body parts
     * key is name
     * value id the EntityID of the body part Entity
     */
    private  HashMap<String, String> bodyParts = new HashMap<String, String>();

    /**
     * list of names of body parts that can be added.
     */
   private Array<String> partsAddable= new Array<>();
    public BodyComponent() {
    }
    public HashMap<String, String> getBodyParts() {
        return bodyParts;
    }

    public Array<String> getPartsAddable() {
        return partsAddable;
    }

    public void setPartsAddable(Array<String> partsAddable) {
        this.partsAddable = partsAddable;
    }

}
package com.jessematty.black.tower.Components.BodyParts;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
public class BodyComponent implements Component {
   private  HashMap<String, String> bodyParts = new HashMap<String, String>();

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
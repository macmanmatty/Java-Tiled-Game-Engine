package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class Material implements Component {
   private  Array<String> craftModes= new Array<>();


    public Array<String> getCraftModes() {
        return craftModes;
    }
}

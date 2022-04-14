package com.jessematty.black.tower.Components.ChangeImage;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.ObjectMap;

public class ChangeImageOnState implements Component{

    private ObjectMap<String, ChangeImage> imagesAnStates= new ObjectMap<>();

    public ObjectMap<String, ChangeImage> getImagesAnStates() {
        return imagesAnStates;
    }


}

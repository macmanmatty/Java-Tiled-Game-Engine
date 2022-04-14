package com.jessematty.black.tower.Components.ChangeImage;

import com.badlogic.ashley.core.Component;

// component that changes  an  an entities image on a string.
public class ChangeImage implements Component {


    private String imageName;
    private String atlasName;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getAtlasName() {
        return atlasName;
    }

    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }
}

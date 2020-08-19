package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Entity.Transient;

public class ImageComponent implements SerializableComponet {
   private transient Image image;
   private transient AtlasNamedAtlasRegion atlasRegion;
   private String imageName;
   private String atlasName;
   private Color color= new Color(1, 1, 1, 1);
   private float  brightness=1;
    private boolean draw=true;


    public Image getImage() {
        return image;
    }

    public void setImage(AtlasNamedAtlasRegion region) {
        this.image = new Image(region);
        this.atlasName=region.getAtlasName();
        this.imageName=region.name;
        this.atlasRegion=region;

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public String getImageName() {
        return imageName;
    }



    public String getAtlasName() {
        return atlasName;
    }


    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }


    @Override
    public void deSerialize(GameAssets assets) {
        AtlasNamedAtlasRegion atlasNamedAtlasRegion=assets.getAtlasRegionByName(imageName, atlasName);

        this.image=new Image(atlasNamedAtlasRegion);
        this.atlasRegion=atlasNamedAtlasRegion;


    }

    @Override
    public void serialize() {

    }
}

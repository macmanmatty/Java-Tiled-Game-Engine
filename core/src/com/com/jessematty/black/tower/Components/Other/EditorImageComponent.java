package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.jessematty.black.tower.Components.Interfaces.SerializableComponent;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;

public class EditorImageComponent implements SerializableComponent {
   private  transient AtlasNamedAtlasRegion atlasRegion;
   private Color color= new Color(1, 1, 1, 1);
   private float  brightness=1;
    private boolean draw=true;
    private String atlasName;
    private String imageName;




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

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public AtlasNamedAtlasRegion getAtlasRegion() {
        return atlasRegion;
    }

    public void setAtlasRegion(AtlasNamedAtlasRegion atlasRegion) {
        this.atlasRegion = atlasRegion;
        this.atlasName= atlasRegion.getAtlasName();
        this.imageName= atlasRegion.name;
    }

    public String getAtlasName() {
        return atlasName;
    }

    public String getImageName() {
        return imageName;
    }

    @Override
    public void deSerialize(Entity unused, GameAssets assets) {
        AtlasNamedAtlasRegion atlasNamedAtlasRegion=assets.getAtlasRegionByName(imageName, atlasName);


        this.atlasRegion =atlasNamedAtlasRegion;



    }

    @Override
    public void serialize(Entity unused) {

    }


}

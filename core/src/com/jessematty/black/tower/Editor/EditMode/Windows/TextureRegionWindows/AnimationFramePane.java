package com.jessematty.black.tower.Editor.EditMode.Windows.TextureRegionWindows;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveIntegerField;


public class AnimationFramePane {
    private Array<AtlasRegion> regions= new Array<AtlasRegion>();
    private Array<NameBox> imageBoxes= new Array<NameBox>();
    private int frames;
    private PositiveIntegerField framesField;
    private Skin skin;
    private HorizontalGroup horizontalGroup= new HorizontalGroup();


    public AnimationFramePane(int frames, Skin skin) {
        this.frames = frames;
        this.skin = skin;
        makeBoxes();
    }

    public AnimationFramePane(Skin skin) {
        this.skin = skin;
    }



    private void makeBoxes(){


    }

    private void addRegion(TextureRegion region, String name){
        AtlasRegion atlasRegion=new AtlasRegion(region);
        atlasRegion.name=name;
        regions.add(atlasRegion);

    }

    private class NameBox extends VerticalGroup {

        com.jessematty.black.tower.Editor.EditMode.Windows.TextureRegionWindows.TextureRegionImageDragTarget textureRegionImageDragTarget;
        TextField nameField;
        Skin skin;
        int width;
        int height;

        public NameBox(Skin skin, int width, int height) {
            this.skin = skin;
            this.width = width;
            this.height = height;
            Image image= new Image();
            image.setSize(width, height);
            textureRegionImageDragTarget= new TextureRegionImageDragTarget(image);
            nameField= new TextField("enter name", skin);
            addActor(textureRegionImageDragTarget.getActor());
            addActor(nameField);

        }
    }
}

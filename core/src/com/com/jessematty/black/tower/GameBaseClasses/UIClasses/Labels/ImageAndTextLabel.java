package com.jessematty.black.tower.GameBaseClasses.UIClasses.Labels;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ImageAndTextLabel extends HorizontalGroup {

    Image image;
    Label label;
    public ImageAndTextLabel(String text, Skin skin,  TextureRegion region) {
        this( new Image(region), new Label(text, skin));
    }
    public ImageAndTextLabel(String text, Skin skin,  Image region) {
        this( region, new Label(text, skin));
    }

    public ImageAndTextLabel(String text, Skin skin, String style,  Image region) {
        this( region,  new Label(text, skin, style));
    }

    public ImageAndTextLabel(String text, Skin skin, String style,  TextureRegion region) {
        this( new Image(region), new Label(text, skin, style));
    }

    public ImageAndTextLabel(Image image, Label label) {
        this.image = image;
        this.label = label;
        addActor(image);
        addActor(label);
    }
}

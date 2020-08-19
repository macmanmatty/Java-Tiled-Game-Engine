package com.jessematty.black.tower.GameBaseClasses.UIClasses.MessageBoxes;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

public class ImageWithName extends VerticalGroup {

   private  Label name;
   private Image image;

    public ImageWithName(String name, Skin skin , Image image) {

        this(name, skin, "default", image);

    }

    public ImageWithName(String name, Skin skin , String style,   Image image) {

        if(name==null){
            name="name is NUll";
        }
        this.name=new Label(name, skin, style);
        this.image=image;
        if(image!=null) {
            addActor(image);
        }

        addActor(this.name);

    }

}

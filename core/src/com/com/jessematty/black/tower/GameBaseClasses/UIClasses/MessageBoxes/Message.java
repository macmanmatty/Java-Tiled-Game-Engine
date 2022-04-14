package com.jessematty.black.tower.GameBaseClasses.UIClasses.MessageBoxes;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Labels.TextTypeWriterLabel;

public class Message  extends HorizontalGroup {

   private  ImageWithName image;
   private  TextTypeWriterLabel textTypeWriterLabel;

    public Message(Image image, String name,  String text , Skin skin ) {
        this(image, name, text, skin, "default");

    }

    public Message(Image image,  String name, String text, Skin skin, String style ) {
        this.image =  new ImageWithName(name, skin, style,  image);
        this.textTypeWriterLabel = new TextTypeWriterLabel(name, skin, style);
        addActor(image);
        addActor(textTypeWriterLabel);

    }


}

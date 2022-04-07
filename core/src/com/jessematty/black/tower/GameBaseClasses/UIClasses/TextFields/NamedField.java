package com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
/**
 * class for text field with  a label to the left of the text field
 * 
 */
public class NamedField extends HorizontalGroup {
    private TextField field;
    private Label label;
    public NamedField(Label label, TextField field) {
        this.field = field;
        this.label = label;
        addActor(label);
        addActor(field);
        space(4);
    }
    public NamedField(String name , Skin skin, String  style, TextField field) {
        this.field = field;
        this.label=new Label(name, skin, style);
        addActor(label);
        addActor(field);
        space(4);
    }
    public NamedField(String name , Skin skin, TextField field) {
        this.field = field;
        this.label=new Label(name, skin);
        addActor(label);
        addActor(field);
        space(4);
    }
    public TextField getField() {
        return field;
    }
    public Label getLabel() {
        return label;
    }
}

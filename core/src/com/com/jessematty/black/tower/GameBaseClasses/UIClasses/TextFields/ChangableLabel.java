package com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
public class ChangableLabel extends HorizontalGroup {
    
    private  Label label;
     private TextField textField;
    public ChangableLabel(Skin skin) {
        this("",  skin, "default");
        
    }
    public ChangableLabel( String text, Skin skin) {
        this(text,  skin, "default");

    }
    public ChangableLabel(Skin skin, String style) {
        this("", skin, style);
    }
    public ChangableLabel(String text, Skin skin, String style) {
        label= new Label(text, skin);
        label.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                
                changeToTextField();
                
                
            }
        });
        textField=new TextField("", skin);
        textField.addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode== Keys.ENTER){
                    removeActor(textField);
                    label.setText(textField.getText());
                    addActor(label);
                    
                }
                
                return true;
                
            }
        });

    }


    public void changeToTextField(){
        removeActor(label);
        textField.setText(String.valueOf(label.getText()));
        addActor(textField);

    }


    public void setText(String text){
        
        label.setText(text);
    }
    
    public String getText(){
        
        return String.valueOf(label.getText());
        
    
    }
    
    
}

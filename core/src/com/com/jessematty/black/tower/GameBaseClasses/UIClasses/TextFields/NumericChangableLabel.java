package com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
public class NumericChangableLabel extends HorizontalGroup {
    
    private  Label label;
     private FloatField textField;
    public NumericChangableLabel(Skin skin) {
        this("",  skin, "default");
        
    }
    public NumericChangableLabel(Skin skin, String style) {
        this("", skin, style);
    }
    public NumericChangableLabel(String text, Skin skin, String style) {
        label= new Label(text, skin);
        label.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                
                removeActor(label);
                textField.setText(String.valueOf(label.getText()));
                addActor(textField);
                
                
                
            }
        });
        textField=new FloatField("", skin);
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
    
    
    
    
    public void setText(double text){
        
        label.setText(text+"");
    }
    
    
    public double  getDouble(){
        
        return Double.valueOf(String.valueOf(label.getText()));
        
    
    }
    public float getFloat(){
        return Float.valueOf(String.valueOf(label.getText()));
    }
    public int getIntger(){
        return Integer.valueOf(String.valueOf(label.getText()));
    }
    
    
}

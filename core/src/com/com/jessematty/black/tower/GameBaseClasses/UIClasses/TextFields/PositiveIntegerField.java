package com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
public class PositiveIntegerField extends ActionTextField { // a text field that only allows positive integer numbers
    public PositiveIntegerField(String text, Skin skin) {
        super(text, skin);
        setTextFilter();
    }
    public PositiveIntegerField(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
        setTextFilter();
    }
    public PositiveIntegerField(String text, TextFieldStyle style) {
        super(text, style);
        setTextFilter();
    }
    public void setTextFilter(){
        setTextFieldFilter(new TextFieldFilter() {
            @Override
            public boolean acceptChar(TextField textField, char c) {
                return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' ;
            }
        });
    }
    public int getInteger(){
        if( getText()==null|| getText().isEmpty()){
            return 0;
        }
        System.out.println("text: "+getText());
        return Integer.valueOf(getText());
    }
    public void setText(int text){
        setText(Integer.toString(text));
    }
}

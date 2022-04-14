package com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * //a  text field that accepts only integers
 */
public class IntegerField extends ActionTextField {
    public IntegerField(String text, Skin skin) {
        super(text, skin);
        setTextFilter();

    }

    public IntegerField(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
        setTextFilter();

    }

    public IntegerField(String text, TextFieldStyle style) {
        super(text, style);
        setTextFilter();
    }

    public void setTextFilter(){
        setTextFieldFilter(new TextFieldFilter() {
            @Override
            public boolean acceptChar(TextField textField, char c) {
                return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9'  || c == '-';
            }
        });
    }


    public int getInteger(){
        if( getText()==null|| getText().isEmpty()){
            return 0;
        }
        return Integer.valueOf(getText());

    }
    public void setText(int text){
        setText(Integer.toString(text));
    }
}

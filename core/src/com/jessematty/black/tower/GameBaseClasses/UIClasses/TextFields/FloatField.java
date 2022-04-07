package com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * //a  text field that accepts only decimal numbers
 */
public class FloatField extends ActionTextField { //a  text field that accepts only decimal numbers
    public FloatField(String text, Skin skin) {
        super(text, skin);
        setFloatFilter();
    }
    public FloatField(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
        setFloatFilter();
    }
    public FloatField(String text, TextFieldStyle style) {
        super(text, style);
        setFloatFilter();
    }

    /**
     * the filter for the text field to accept any float number positive or negative
     */
    public void setFloatFilter(){
        setTextFieldFilter(new TextFieldFilter() {
            @Override
            public boolean acceptChar(TextField textField, char c) {
                return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '.' || c == '-';
            }
        });
    }

    /**
     * the filter for the text field to accept any float number that is positive
     */
    public void setPositiveFloatFilter(){
        setTextFieldFilter(new TextFieldFilter() {
            @Override
            public boolean acceptChar(TextField textField, char c) {
                return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '.';
            }
        });
    }
    /**
     * the filter for the text field to accept any integer
     */
    public void setIntFilter(){
        setTextFieldFilter(new TextFieldFilter() {
            @Override
            public boolean acceptChar(TextField textField, char c) {
                return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '-';
            }
        });
    }
    /**
     * the filter for the text field to accept any  positive integer
     */
    public void setPositiveIntFilter(){
        setTextFieldFilter(new TextFieldFilter() {
            @Override
            public boolean acceptChar(TextField textField, char c) {
                return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' ;
            }
        });
    }
    public float getFloat(){
        if( getText()==null|| getText().isEmpty()){
            return 0.0f;
        }
        return Float.valueOf(getText());
    }
    public double getDouble(){
        if( getText()==null|| getText().isEmpty()){
            return 0.0d;
        }
        return Double.valueOf(getText());
    }
    public int getInteger(){
        if( getText()==null|| getText().isEmpty()){
            return 0;
        }
        return Integer.valueOf(getText());
    }
    public void setText(double text){
        setText(Double.toString(text));
    }
    public void setText(float text){
        setText(Float.toString(text));
    }
    public void setText(int text){
        setText(Integer.toString(text));
    }
}

package com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
/**
 * a  TextField that has an on enter action linked to it
 */
public class ActionTextField extends TextField {
   
    private TextFieldOnEnterAction textFieldOnEnterAction;
    private float fontScaleX;
    private float  fontScaleY;
    private boolean fontScaleChanged;
    private boolean forceSetWidth;
    private float prefWidth;
    public ActionTextField(String text, Skin skin) {
        super(text, skin);
        addListener();
    }
    public ActionTextField(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
        addListener();
    }
    public ActionTextField(String text, TextFieldStyle style) {
        super(text, style);
        addListener();
    }
    // add the action listener
    private void addListener(){
        addListener( new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(textFieldOnEnterAction !=null && keycode== Keys.ENTER){
                    textFieldOnEnterAction.action();
                }
                return true;
            }
        });
    };
    @Override
    public void act(float delta) {
        BitmapFont font = getStyle().font;
        if (fontScaleChanged==true) {
            font.getData().setScale(fontScaleX, fontScaleY);
            fontScaleChanged=false;
        }
        super.act(delta);
    }
    public TextFieldOnEnterAction getTextFieldOnEnterAction() {
        return textFieldOnEnterAction;
    }
    public void setTextFieldOnEnterAction(TextFieldOnEnterAction textFieldOnEnterAction) {
        this.textFieldOnEnterAction = textFieldOnEnterAction;
    }
    public void setFontScale (float fontScale) {
        setFontScale(fontScale, fontScale);
    }
    public void setFontScale (float fontScaleX, float fontScaleY) {
        fontScaleChanged = true;
        this.fontScaleX = fontScaleX;
        this.fontScaleY = fontScaleY;
        invalidateHierarchy();
    }
    public float getFontScaleX() {
        return fontScaleX;
    }
    public float getFontScaleY() {
        return fontScaleY;
    }
    @Override
    public float getPrefWidth() {
        if(forceSetWidth==false){
        return super.getPrefWidth();}
        else{
            return  prefWidth;
        }
    }
    @Override
    public float getMinWidth() {
        if (forceSetWidth == false) {
            return super.getMinWidth();
        }
        return  prefWidth;
    }
    @Override
    public float getMaxWidth() {
        if(forceSetWidth==false) {
            return super.getMaxWidth();
        }
        return  prefWidth;
    }
    public void forceSetPrefWidth(float prefWidth) {
        this.prefWidth = prefWidth;
        forceSetWidth=true;
    }
    public void  useLayoutWidth(){
        forceSetWidth=false;
    }
}

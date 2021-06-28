package com.jessematty.black.tower.GameBaseClasses.UIClasses.Labels.NameEditableLabel;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ItemLabel<T extends Nameable>  extends Label {
    private T item;
    private TextField textField;
    private boolean displayTextField;
    private boolean changed;
    private boolean textFieldAdded;
    private int clicks;
    private int timeCounter;
    public ItemLabel(Skin skin, final T item) {
        super("", skin);
        textField= new TextField("", skin);
        textField.setVisible(false);
        if(item!=null){
            textField.setText(item.getName());
            setText(item.getName());
        }
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
               clicks++;
               if(clicks>2) {
                   displayTextField = true;
                   setVisible(false);
                   textField.setVisible(true);
                   changed = true;
                   clicks=0;
               }
            }
        });
        textField.addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode== Keys.ENTER){
                    String text=textField.getText();
                    item.setName(text);
                    setText(text);
                    displayTextField=false;
                    setVisible(true);
                    textField.setVisible(false);
                    changed=true;
                }
                return true;
            }
        });
    }
    public T getItem() {
        return item;
    }
    public void setItem(T item) {
        if(item==null){
            throw new IllegalArgumentException("Item Cannot Be Null");
        }
        this.item = item;
        this.textField.setText(item.getName());
        setText(item.getName());
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        if(textFieldAdded==false) {
            getParent().addActor(textField);
            textFieldAdded=true;
        }
        timeCounter++;
        if(timeCounter==100){
            clicks=0;
            timeCounter=0;
        }

    }
    @Override
    public void setX(float x) {
        super.setX(x);
        textField.setX(x);
    }
    @Override
    public void setX(float x, int alignment) {
        super.setX(x, alignment);
        textField.setX(x, alignment);
    }
    @Override
    public void setY(float y) {
        super.setY(y);
        textField.setY(y);
    }
    @Override
    public void setY(float y, int alignment) {
        super.setY(y, alignment);
        textField.setY(y, alignment);
    }
    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        textField.setPosition(x, y);
        setBounds(getX(), getY(), getPrefWidth(), getPrefHeight());
    }
    @Override
    public void setPosition(float x, float y, int alignment) {
        super.setPosition(x, y, alignment);
        textField.setPosition(x, y, alignment);
    }
    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        textField.setWidth(width);
    }
    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        textField.setHeight(height);
    }
    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        textField.setSize(width, height);
    }
    @Override
    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
        textField.setBounds(x, y, width, height);
    }
    @Override
    public void setOriginX(float originX) {
        super.setOriginX(originX);
        textField.setOriginX(originX);
    }
    @Override
    public void setOriginY(float originY) {
        super.setOriginY(originY);
        textField.setOriginY(originY);
    }
    @Override
    public void setOrigin(float originX, float originY) {
        super.setOrigin(originX, originY);
        textField.setOrigin(originX, originY);
    }
    @Override
    public void setOrigin(int alignment) {
        super.setOrigin(alignment);
        textField.setOrigin(alignment);
    }
    @Override
    public void setScaleX(float scaleX) {
        super.setScaleX(scaleX);
        textField.setScaleX(scaleX);
    }
    @Override
    public void setScaleY(float scaleY) {
        super.setScaleY(scaleY);
    }
    @Override
    public void setScale(float scaleXY) {
        super.setScale(scaleXY);
        textField.setScaleY(scaleXY);
    }
    @Override
    public void setScale(float scaleX, float scaleY) {
        super.setScale(scaleX, scaleY);
        textField.setScale(scaleX, scaleY);
    }
    @Override
    public void setRotation(float degrees) {
        super.setRotation(degrees);
        textField.setRotation(degrees);
    }
    @Override
    public void setName(String name) {
        super.setName(name);
        textField.setName(name);
    }
    @Override
    public boolean setZIndex(int index) {
        return textField.setZIndex(index) && super.setZIndex(index);
    }
    @Override
    public void setDebug(boolean enabled) {
        super.setDebug(enabled);
        textField.setDebug(enabled);
    }
    public TextField getTextField() {
        return textField;
    }
}

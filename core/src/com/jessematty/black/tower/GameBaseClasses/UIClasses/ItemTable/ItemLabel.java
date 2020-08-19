package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.FloatField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.IntegerField;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ItemLabel<T >  extends Label {
    private T item;
    private TextField textField;
    private boolean displayTextField;
    private boolean changed;
    private boolean textFieldAdded;
    private int clicks;
    private boolean selected;
    private  ItemListAdapter<T> itemList;
    private  int index;
    private int clickResetCounter;
    private boolean editable;
    private String methodName="";
    private Class itemDataClass=String.class;
    private ItemListLabelStyle itemListLabelStyle;




    public ItemLabel(final ItemListAdapter <T> itemList,   final int index, ItemListLabelStyle itemListLabelStyle, final T item, final boolean editable, String methodName, Class itemDataClass) {
        super("", itemListLabelStyle.labelStyle);

        this.itemList = itemList;
        this.index = index;
        this.editable = editable;
        this.methodName = methodName;
        this.itemDataClass = itemDataClass;
        this.itemListLabelStyle=itemListLabelStyle;



        if(itemDataClass==String.class) {
            textField = new TextField("", itemListLabelStyle.textFieldStyle);
        }
        else if(itemDataClass==Float.class || itemDataClass==Double.class){

            textField = new FloatField("", itemListLabelStyle.textFieldStyle);

        }

        else if(itemDataClass==Long.class || itemDataClass==Integer.class){

            textField = new IntegerField("",itemListLabelStyle.textFieldStyle);

        }
        textField.setVisible(false);
        textField.setLayoutEnabled(false);
        setLayoutEnabled(true);
        this.item=item;


        if(item!=null){
           setText(getItemValue());
        }
        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer,  int button ) {
                clicks++;

                System.out.println(getText()+" clicked  times " + clicks);
               if(clicks==1){
                   if(itemList!=null){
                       System.out.println(getText()+" clicked  times " + clicks);

                       itemList.setSelected(index, true);
                   }
               }
               if(clicks==2 ) {

                   if(editable==true) {
                       displayTextField();
                   }
                   clicks=0;



               }
               return  true;

            }


        });
        textField.addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode== Keys.ENTER){
                        displayLabel();

                }
                return true;
            }
        });
        setBounds(getX(),getY() ,getPrefWidth() ,getPrefHeight() );


    }
    public void displayLabel() {
        if(item!=null) {
            setItemValue();
        }

        setText(textField.getText());
        displayTextField=false;
        setVisible(true);

        invalidate();

        textField.setVisible(false);
        changed=true;


    }

    public void displayTextField(){
        displayTextField = true;
        setVisible(false);
        textField.setVisible(true);
        textField.setText(String.valueOf(getText()));
        textField.setPosition(getX(), getY());
        textField.setSize(getWidth(), getHeight());
        changed = true;

    }

    public void changeBackground(){
    }
    public T getItem() {
        return item;
    }
    public void setItem(T item) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if(item==null){
            throw new IllegalArgumentException("Item Cannot Be Null");
        }
        this.item = item;
       getItemValue();
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        if(textFieldAdded==false) {
            getParent().addActor(textField);
            textFieldAdded=true;
        }
        clickResetCounter++;
        if(clickResetCounter==120000){
            clicks=0;
            clickResetCounter=0;

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

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
        if(selected==true){

            setStyle(itemListLabelStyle.selectedLabelStyle);
        }
        else{

            setStyle(itemListLabelStyle.labelStyle);

        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

   private  ItemLabel <T> getLabel(){

        return this;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }


   public void  setItemValue()  {


       if(item!=null){
           Class<T> cls= (Class<T>) item.getClass();
           Method method = null;
           try {
               method = cls.getDeclaredMethod("set"+methodName, itemDataClass);
           } catch (NoSuchMethodException e) {
               e.printStackTrace();
               throw new IllegalArgumentException("Method Not Found");
           }
           try {
               if(itemDataClass==String.class) {
                   method.invoke(item, textField.getText());
               }
               else if(itemDataClass==Long.class){
                   method.invoke(item, Long.valueOf(textField.getText()));

               }
              else  if(itemDataClass==Integer.class){
                   method.invoke(item, Integer.valueOf(textField.getText()));

               }
               else if(itemDataClass==Float.class){
                   method.invoke(item, Float.valueOf(textField.getText()));

               }
               else  if(itemDataClass==Double.class){
                   method.invoke(item, Double.valueOf(textField.getText()));

               }


           } catch (IllegalAccessException e) {
               e.printStackTrace();

               throw new IllegalArgumentException("Method Not Found");
           } catch (InvocationTargetException e) {
               e.printStackTrace();

               throw new IllegalArgumentException("Method Not Found");
           }
       }
   }

    public String  getItemValue()  {


        String value="";
        if(item!=null){
            Class<T> cls= (Class<T>) item.getClass();
            Method method = null;
            try {
                method = cls.getDeclaredMethod("get"+methodName);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Method " +methodName+ " Not Found");
            }
            try {
                value= String.valueOf(method.invoke(item));
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Method " +methodName+ " Not Found");
            } catch (InvocationTargetException e) {
                throw new IllegalArgumentException("Method " +methodName+ " Not Found");
            }
        }
        return value;
    }



}

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
/**
 *  class for  label that runs into a TextField on double clicking to allow you to edit the contents the 
 *  label returns after hotting enter in the text field.
 *  uses reflection to call a method to get the and set the data to a linked item.
 * @param <T>
 */
public class ItemLabel<T >  extends Label {
    /**
     * the linked item
     */
    private T item;
    /**
     * the TextField
     */
    private TextField textField;
    /**
     * whether for not to display the text field
     */
    private boolean displayTextField;
    
    private boolean changed;
    
    private boolean textFieldAdded;
    /**
     * click counter on 2 clicks  the Label Becomes a TextField
     */
    private int clicks;
    /**
     * is this the selected  label in the liked item list
     */
    private boolean selected;
    /**
     *  the adapter for the linked ItemList
     * @see ItemList
     */
    private  ItemListAdapter<T> itemList;
    /**
     * the position of  the item in the linked ItemLists Array
     */
    private  int index;
    /**
     * the counter in milliseconds  to reset the click counter after 2 seconds the click counter will reset to zero
     */
    private int clickResetCounter;
    /**
     * is the label editable aka will the text appear on double clicking
     */
    private boolean editable;
    /**
     * the method name to call to get the data
     */
    private String methodName="";
    /**
     * the  java Class of the data type supported types are
     * String.class
     * Long.class
     * Integer.class
     * Double.class
     * Float.class
     * Boolean.class
     * method is the format of get+MethodName and set+MethodName  
     * you need ony the part of the  method  name after the get and set portions
     */
    private Class itemDataClass=String.class;
    /**
     *  the custom libGDX Scene 2d style for this  UI class
     */
    private ItemListLabelStyle itemListLabelStyle;



    /**
     *
     * @param itemList
     * @param index
     * @param itemListLabelStyle
     * @param item
     * @param editable
     * @param methodName
     * @param itemDataClass
     */
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
               if(clicks==1){
                   if(itemList!=null){
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
    /**
     * method to display the label
     */
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
    /**
     * method to display the text field 
     */
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
    /**
     * 
     * @return the linked item
     */
    public T getItem() {
        return item;
    }
    /**
     * 
     * @param item sets the linked item
     * @throws IllegalArgumentException if the item is null
     */
    public void setItem(T item) throws IllegalArgumentException {
        if(item==null){
            throw new IllegalArgumentException("Item Cannot Be Null");
        }
        this.item = item;
       getItemValue();
    }
    /**
     * overridden act method  used to swap TextField and Label actors  and count clicks
     * @param delta
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        if(textFieldAdded==false) {
            getParent().addActor(textField);
            textFieldAdded=true;
        }
        clickResetCounter++;
        if(clickResetCounter==120){
            clicks=0;
            clickResetCounter=0;
        }
    }
    /**
     * overidden methods to size the TextField the same as the label
     *
     */
    
    
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
    /**
     * sets whether or not this Label is the selected one  in the list
     * and if so sets it style to the style to the selected style.
     * @param selected
     */
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
    /**
     * method for setting the value uses reflection
     */
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


    /**
     * method for getting the value uses reflection
     */
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

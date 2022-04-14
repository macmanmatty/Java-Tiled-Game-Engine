package com.jessematty.black.tower.GameBaseClasses.UIClasses.MapLists;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.ActionTextField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.TextFieldOnEnterAction;


import java.util.Comparator;

public class StringMapList<K> extends Table {
    private int size;
    private ObjectMap<K, String> items;

    private Skin skin;
    private boolean sortable;
    private boolean trackItems;
    private boolean remakeTable;
    private MapListTitle<String> itemListTitle;
    private float labelWidth =100f;
    private float textFieldWidth=100f;

    private java.lang.String titleName;

   private boolean displayTitle;


    private Comparator<String> itemComparator= new Comparator<String>() {
        boolean descend;
        @Override
        public int compare(String item1, String item2) {
                return (item1).compareTo((item2));
        }
    };
    private Comparator<String> reverseItemComparator= new Comparator<String>() {
        boolean descend;
        @Override
        public int compare(String item1, String item2) {
            return (item2).compareTo((item1));
        }
    };

    public StringMapList(Skin skin, java.lang.String titleName, boolean displayTitle) {
       this(skin,"default", titleName,  displayTitle);
    }
    public StringMapList(Skin skin, java.lang.String styleName, java.lang.String titleName,  boolean displayTitle) {
        this.skin = skin;
        this.displayTitle=displayTitle;


        this.titleName=titleName;


    }
    @Override
    public void act(float delta) {
        super.act(delta);
        if(items!=null){
        if((size!=items.size) || remakeTable==true) {
                size = items.size;


            remakeTable=false;
            makeItems();
        }
    }
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }
    private void makeItems(){
        int size=items.size;
        clear();
        if(displayTitle==true) {
            add(itemListTitle).width(labelWidth);
        }

        row();
        Keys<K> keys=items.keys();
        int count=0;

        while(keys.hasNext){
           final K key= keys.next();
            Label label=new Label(key.toString(), skin);
            final ActionTextField textField= new ActionTextField(items.get(key).toString(), skin);


            textField.setTextFieldOnEnterAction(new TextFieldOnEnterAction() {
                @Override
                public void action() {
                        items.put(key, textField.getText());



                }
            });




           label.setSize(getMaxWidth(), getMaxHeight());

            add(label).width(labelWidth);
            add(textField).width(textFieldWidth);
            row();
            count++;

        }
        invalidateHierarchy();
        setBounds(getX(), getY(), labelWidth, getPrefHeight());

    }

    public ObjectMap<K, String> getItems() {
        return items;
    }

    public void setItems(ObjectMap<K, String> items, boolean trackItems) {
        if(trackItems==true) {
            this.items = items;
        }
        else{
            this.items= new ObjectMap<>(items.size);
            this.items.putAll(items);

        }
        remakeTable=true;
    }

    public void forceRemakeTable() {
        this.remakeTable = true;

    }

    public void sort( boolean descending){
        if(descending==false) {
            sort(true);
        }
        else {

            sort(false);
        }
        remakeTable=true;


        fire(new ChangeEvent());
    }
    public boolean isSortable() {
        return sortable;
    }
    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }


    public float getLabelWidth() {
        return labelWidth;
    }
    public void setLabelWidth(float labelWidth) {
        this.labelWidth = labelWidth;


    }




    public float getTextFieldWidth() {
        return textFieldWidth;
    }

    public void setTextFieldWidth(float textFieldWidth) {
        this.textFieldWidth = textFieldWidth;
    }

    public java.lang.String getTitleName() {
        return titleName;
    }


    @Override
    public Skin getSkin() {
        return skin;
    }



}

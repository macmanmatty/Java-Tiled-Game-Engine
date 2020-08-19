package com.jessematty.black.tower.GameBaseClasses.UIClasses.MapLists;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.FloatField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.TextFieldOnEnterAction;

import java.util.Comparator;

public class NumericMapList<K> extends Table {
    private int size;
    private ObjectMap<K, Double> items;

    private Skin skin;
    private boolean editable;
    private boolean sortable;
    private boolean trackItems;
    private boolean remakeTable;
    private MapListTitle<Double> itemListTitle;
    private float labelWidth =100f;
    private float textFieldWidth=100f;

    private String titleName;

   private boolean displayTitle;


    private Comparator<Double> itemComparator= new Comparator<Double>() {
        boolean descend;
        @Override
        public int compare(Double item1, Double item2) {
                return (item1).compareTo((item2));
        }
    };
    private Comparator<Double> reverseItemComparator= new Comparator<Double>() {
        boolean descend;
        @Override
        public int compare(Double item1, Double item2) {
            return (item2).compareTo((item1));
        }
    };

    public NumericMapList(Skin skin,  String titleName,  boolean displayTitle) {
       this(skin,"default",  titleName,   0, displayTitle);
    }
    public NumericMapList(Skin skin, String styleName, String titleName,   int tableColumnIndex, boolean displayTitle) {
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
            final FloatField textField= new FloatField(items.get(key).toString(), skin);


            textField.setTextFieldOnEnterAction(new TextFieldOnEnterAction() {
                @Override
                public void action() {
                        items.put(key, textField.getDouble());



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

    public ObjectMap<K, Double> getItems() {
        return items;
    }

    public void setItems(ObjectMap<K, Double> items, boolean trackItems) {
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

    public String getTitleName() {
        return titleName;
    }


    @Override
    public Skin getSkin() {
        return skin;
    }



}

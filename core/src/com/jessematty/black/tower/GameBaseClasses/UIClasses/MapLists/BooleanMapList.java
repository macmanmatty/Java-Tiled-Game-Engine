package com.jessematty.black.tower.GameBaseClasses.UIClasses.MapLists;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Keys;

import java.util.Comparator;


public class BooleanMapList<K> extends Table {
    private int size;
    private ObjectMap<K, Boolean> items;

    private Skin skin;
    private boolean editable;
    private boolean sortable;
    private boolean trackItems;
    private boolean remakeTable;
    private MapListTitle<Boolean> itemListTitle;
    private float checkBoxWidth =100f;

    private String titleName;
   private boolean displayTitle;


    private Comparator<Boolean> itemComparator= new Comparator<Boolean>() {
        boolean descend;
        @Override
        public int compare(Boolean item1, Boolean item2) {
                return (item1).compareTo((item2));
        }
    };
    private Comparator<Boolean> reverseItemComparator= new Comparator<Boolean>() {
        boolean descend;
        @Override
        public int compare(Boolean item1, Boolean item2) {
            return (item2).compareTo((item1));
        }
    };

    public BooleanMapList(Skin skin, String titleName, boolean displayTitle) {
       this(skin,"default",  titleName,   0, displayTitle);
    }
    public BooleanMapList(Skin skin, String styleName, String titleName, int tableColumnIndex, boolean displayTitle) {
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
            add(itemListTitle).width(checkBoxWidth);
        }

        row();
        Keys<K> keys=items.keys();
        int count=0;

        while(keys.hasNext){
           final K key= keys.next();
            final CheckBox checkBox= new CheckBox(key.toString(), skin);


            checkBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    boolean newValue=!checkBox.isChecked();
                    items.put(key, newValue);
                    checkBox.setChecked(newValue);

                }
            });






            add(checkBox).width(checkBoxWidth);
            row();
            count++;

        }
        invalidateHierarchy();
        setBounds(getX(), getY(), checkBoxWidth, getPrefHeight());

    }

    public ObjectMap<K, Boolean> getItems() {
        return items;
    }

    public void setItems(ObjectMap<K,Boolean> items, boolean trackItems) {
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



    public float getCheckBoxWidth() {
        return checkBoxWidth;
    }
    public void setCheckBoxWidth(float checkBoxWidth) {
        this.checkBoxWidth = checkBoxWidth;


    }





    public String getTitleName() {
        return titleName;
    }


    @Override
    public Skin getSkin() {
        return skin;
    }



}

package com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists.DragLists;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists.ObservableList;

public class TwoWayDragList<T>  extends Table {
   private Array<T> items1= new Array<T>();
   private  Array<T> items2=  new Array<T>();
   private DragAndDrop dragAndDrop;
   private final  List<T> items1List;
    private final List<T> items2List;
    private float listHeight;
    private float listWidth;
    private ScrollPane scrollPane1;
    private  ScrollPane scrollPane2;

    public TwoWayDragList(Skin skin, Array<T> items1, Array<T> items2, String list1Title , String list2Title) {
        this(new DragAndDrop(), skin, items1, items2, list1Title, list2Title);
    }
    public TwoWayDragList(DragAndDrop dragAndDrop, Skin skin, Array<T> items1, Array<T> items2, String list1Title, String list2Title) {
        this.dragAndDrop=dragAndDrop;
        items1List= new ObservableList<T>(skin);
        items2List= new ObservableList<T>(skin);

        if(items1!=null) {
            this.items1 = items1;
            items1List.setItems(items1);
        }
        if(items2!=null) {
            this.items2 = items2;
            items2List.setItems(items2);

        }

        int maxSize=this.items1.size;
        if(maxSize<this.items2.size){
            maxSize=this.items2.size;
        }


       DragListSource<T> dragListSource1 =null;
        DragListSource<T> dragListSource2 =null;
        DragListTarget<T> dragListTarget1 =null;
        DragListTarget<T> dragListTarget2 =null;
        dragListTarget1 = new DragListTarget<T>( items1List);
        dragListTarget2 = new DragListTarget<T>(items2List);
        dragListSource1 = new DragListSource<>( items2List, skin);
        dragListSource2 = new DragListSource<>( items1List, skin);
        dragAndDrop.addSource(dragListSource1);
        dragAndDrop.addSource(dragListSource2);
        dragAndDrop.addTarget(dragListTarget1);
        dragAndDrop.addTarget(dragListTarget2);
        items1List.setSize(items1List.getPrefWidth(), maxSize*items1List.getItemHeight());
        items2List.setSize(items2List.getPrefWidth(), maxSize*items2List.getItemHeight());



        scrollPane1=new ScrollPane(items1List, skin);
        scrollPane1.setTransform(true);
        scrollPane1.setFadeScrollBars(false);
        scrollPane2=new ScrollPane(items2List, skin);
        scrollPane2.setTransform(true);
        scrollPane2.setScrollbarsVisible(true);
        scrollPane2.setFadeScrollBars(false);

        Label list1TitleLabel= new Label(list1Title, skin);
        add(list1TitleLabel).size(list1TitleLabel.getPrefWidth(), list1TitleLabel.getPrefHeight());

        Label list2TitleLabel= new Label(list2Title, skin);
        add(list2TitleLabel).size(list2TitleLabel.getPrefWidth(), list2TitleLabel.getPrefHeight());




    }

    public float getListHeight() {
        return listHeight;
    }

    public void setListSize(float listHeight, float listWidth) {
        row();
        add(scrollPane1).size(listWidth,listHeight).maxHeight(listHeight);

        add(scrollPane2).size(listWidth, listHeight).maxHeight(listHeight);

        pack();
        validate();

    }

    public float getListWidth() {

        return listWidth;
    }


    public List<T> getItems1List() {
        return items1List;
    }

    public List<T> getItems2List() {
        return items2List;
    }




    public ScrollPane getScrollPane1() {
        return scrollPane1;
    }



    public ScrollPane getScrollPane2() {
        return scrollPane2;
    }


}

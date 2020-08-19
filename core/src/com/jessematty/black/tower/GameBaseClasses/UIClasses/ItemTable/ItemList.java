package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.ItemSettable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;

public class ItemList<T > extends Table implements  ItemListAdapter<T>, ItemSettable<T> {
    private int size;
    private Array<T> items;
    private Array<ItemLabel<T>> itemLabels= new  Array<>();
    private Skin skin;
    private T selectedItem;
    private int selectedIndex;
    private boolean editable;
    private boolean dragToChangeOrder;
    private boolean sortable;
    private boolean trackItems;
    private DragAndDrop dragAndDrop;
    private ItemLabel<T> selectedLabel;
    private boolean remakeTable;
    private String  methodName="";
    private Class itemDataClass=String.class;
    private ItemListTitle<T> itemListTitle;
    private float columnWidth=100f;
    private ItemTable<T> itemTable;
    private int tableColumnIndex;
    private String titleName;
    private ItemListLabelStyle itemListLabelStyle;
    private ItemListTitleStyle itemListTitleStyle;
     private boolean displayTitle;


    private Comparator<T> itemComparator= new Comparator<T>() {
        boolean descend;
        @Override
        public int compare(T item1, T item2) {
                return getItemValue(item1).compareTo(getItemValue(item2));
        }
    };
    private Comparator<T> reverseItemComparator= new Comparator<T>() {
        boolean descend;
        @Override
        public int compare(T item1, T item2) {
            return getItemValue(item2).compareTo(getItemValue(item1));
        }
    };
    public ItemList(Skin skin, String titleName, String methodName, Class itemDataClass, boolean displayTitle) {
       this(skin,"default", titleName, methodName, itemDataClass, null, 0, displayTitle);
    }
    public ItemList(Skin skin, String styleName,  String titleName, String methodName,  Class itemDataClass, ItemTable<T> itemTable, int tableColumnIndex, boolean displayTitle) {
        this.skin = skin;
        this.displayTitle=displayTitle;
        this.itemListLabelStyle=skin.get(styleName, ItemListLabelStyle.class);
        this.itemListTitleStyle=skin.get(styleName, ItemListTitleStyle.class);
        this.methodName=methodName;
        this.tableColumnIndex=tableColumnIndex;
        
        this.itemDataClass=itemDataClass;
        if(displayTitle==true) {
            itemListTitle = new ItemListTitle<>(this, titleName, itemListTitleStyle);
        }

        this.itemTable=itemTable;
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
        itemLabels.clear();
        clear();
        if(displayTitle==true) {
            add(itemListTitle).width(columnWidth);
        }

        row();
        for(int count=0; count<size; count++){
           ItemLabel<T> label= new ItemLabel<T>(this,  count,   itemListLabelStyle, items.get(count),editable, methodName, itemDataClass);
           label.setSize(getMaxWidth(), getMaxHeight());
            itemLabels.add(label);
            if(dragToChangeOrder==true && dragAndDrop!=null){
                if(label!=null) {
                    dragAndDrop.addTarget(new ItemListTarget<T>(this, label));
                    dragAndDrop.addSource(new ItemListSource<>(this, label, skin));
                }
            }
            add(label).width(columnWidth);
            row();
        }
        invalidateHierarchy();
        setBounds(getX(), getY(), columnWidth, getPrefHeight());

    }
    public Array<T> getItems() {
        return items;
    }
    public void setItems(Array<T> items, boolean trackItems) {
        if(trackItems==true) {
            this.items = items;
        }
        else{
            this.items= new Array<>(items.size);
            this.items.addAll(items);
            
        }
        remakeTable=true;
    }
    public   void setSelected(T item, boolean changeTable){
        this.selectedItem=item;
        for( int count=0; count<size; count++){
            ItemLabel label= itemLabels.get(count);
            if(label.getItem()==item){
                label.setSelected(true);
                selectedLabel=label;
                count=selectedIndex;
            }
            else{
                label.setSelected(false);
            }
        }

        if(itemTable!=null && changeTable==true){
            itemTable.setSelectedIndex(selectedIndex);
            itemTable.forceRemakeTable();

        }

        fire(new ChangeEvent());

    }
    public T getSelectedItem() {
        return selectedItem;
    }
    public ItemLabel<T> getSelectedLabel() {
        return selectedLabel;
    }
    public int getSelectedIndex() {
        return selectedIndex;
    }
    public void setSelected(int index, boolean changeTable) {
        this.selectedIndex=index;

        for( int count=0; count<size; count++){
           ItemLabel label= itemLabels.get(count);
            if(label.getIndex()==index){
                label.setSelected(true);
                selectedLabel=label;
                selectedItem= (T) label.getItem();
            }
            else{
                label.setSelected(false);
            }
        }

        if(itemTable!=null && changeTable==true){
            itemTable.setSelectedIndex(selectedIndex);
            itemTable.forceRemakeTable();
        }
        fire(new ChangeEvent());

    }
    public void setEditableLabel(ItemLabel setLabel) {
        for (int count = 0; count < size; count++) {
            ItemLabel label = itemLabels.get(count);
            if (setLabel == label) {
                label.displayTextField();
            } else {
                label.displayLabel();
            }
        }
    }
    public void setEditable(boolean editable) {
        this.editable = editable;
        remakeTable=true;
    }
    public boolean isEditable() {
        return editable;
    }
    public void forceRemakeTable() {
        this.remakeTable = true;

    }
   public void  enableDragOrderChanging(DragAndDrop dragAndDrop){
        this.dragToChangeOrder=true;
        this.dragAndDrop=dragAndDrop;
    }
    public void  enableDragOrderChanging(){
        this.dragToChangeOrder=true;
        this.dragAndDrop=new DragAndDrop();
    }
    public void disableDragOrderChanged(){
        dragAndDrop=null;
        this.dragToChangeOrder=false;
    }
    public void sort( boolean descending){
        if(descending==false) {
            items.sort(itemComparator);
        }
        else {
            items.sort(reverseItemComparator);
        }
        remakeTable=true;

        if(itemTable!=null){
            itemTable.setTableItems(items);
            itemTable.forceRemakeTable();
            itemTable.forceRemakeColumns();
        }
        fire(new ChangeEvent());
    }
    public boolean isSortable() {
        return sortable;
    }
    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }
    public ItemListTitle<T> getItemListTitle() {
        return itemListTitle;
    }
    public  String getSelectedData(){
        return String.valueOf(selectedLabel.getText());
    }
    public String  getItemValue(T item)  {
        String value="";
        if(item!=null){
            Class<T> cls= (Class<T>) item.getClass();
            Method method = null;
            try {
                method = cls.getDeclaredMethod("get"+methodName);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Method Not Found");
            }
            try {
                value= String.valueOf(method.invoke(item));
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Method Not Found");
            } catch (InvocationTargetException e) {
                throw new IllegalArgumentException("Method Not Found");
            }
        }
        return value;
    }
    public float getColumnWidth() {
        return columnWidth;
    }
    public void setColumnWidth(float columnWidth) {
        this.columnWidth = columnWidth;
        if(itemTable!=null){
            itemTable.forceRemakeColumns();
            itemTable.forceRemakeTable();
        }

    }


    public void setTableColumnIndex(int tableColumnIndex) {
        this.tableColumnIndex = tableColumnIndex;
    }

    public int getTableColumnIndex() {
        return tableColumnIndex;
    }


    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getTitleName() {
        return titleName;
    }

    public Class getItemDataClass() {
        return itemDataClass;
    }

    @Override
    public Skin getSkin() {
        return skin;
    }

    public Array<ItemLabel<T>> getItemLabels() {
        return itemLabels;
    }

    public boolean isDisplayTitle() {
        return displayTitle;
    }

    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }

    @Override
    public void setItem(T item) {
        setSelected(item, false);
    }
}

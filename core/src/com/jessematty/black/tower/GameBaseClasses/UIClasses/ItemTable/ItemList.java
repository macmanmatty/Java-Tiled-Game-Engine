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

/**
 *  a  UI class for a modifiable list of items used as columns in item tables  or as a single list of items
 * @param <T> the Item Object Class
 */

public class ItemList<T > extends Table implements  ItemListAdapter<T>, ItemSettable<T> {
    /**
     *  the size of the current number of items in the array
     */
    private int size;
    /**
     *  the libGDX Array of items in the array
     */
    private Array<T> items;
    /**
     *  the libGDX Array of  labels for  the items in the array
     */
    private Array<ItemLabel<T>> itemLabels= new  Array<>();
    /**
     *  libGDX ui skin
     */
    private Skin skin;
    /**
     *  the current selected item
     */
    private T selectedItem;
    /**
     *  the index in the array of  the current selected item
     */
    private int selectedIndex;
    /**
     *  are the names in the list editable
     */
    private boolean editable;
    /**
     *  can you drag to change list order
     */
    private boolean dragToChangeOrder;
    /**
     *  is the list sortable?
     */
    private boolean sortable;
    /**
     *  is the list track able should usually be true
     */
    private boolean trackItems=true;
    /**
     *  libGDX Drag and Drop object for dragging and dropping
     */
    private DragAndDrop dragAndDrop;
    /**
     *  the label od the currently selected item
     */
    private ItemLabel<T> selectedLabel;
    /**
     * remake the table on column change
     */
    private boolean remakeTable;
    /**
     *  the name of the getter and setter methods used to displayed name of the item for the label minus get and set
     *
     */
    private String  methodName="";
    /**
     *  the class for that data type to be displayed  IE Integer String Double etc.
     */
    private Class itemDataClass=String.class;
    /**
     * the title for the list of items
     */
    private ItemListTitle<T> itemListTitle;
    private float columnWidth=100f;
    /**
     * if being used as a column  in  Item Table the linked ItemTable
     */
    private ItemTable<T> itemTable;
    /**
     *  the position in the array  of  ItemLists in the linked ItemTable
     */
    private int tableColumnIndex;
    /**
     * the name of the title list
     */
    private String titleName;
    /**
     * custom libGDX UI styles for the title label and item labels
     */
    private ItemListLabelStyle itemListLabelStyle;
    private ItemListTitleStyle itemListTitleStyle;
    /**
     * whether fpr not to display  the title for the list of items
     */
    private boolean displayTitle;


    /**
     * the comparator  used for item sorting
     */
    private Comparator<T> itemComparator= new Comparator<T>() {
        boolean descend;
        @Override
        public int compare(T item1, T item2) {
                return getItemValue(item1).compareTo(getItemValue(item2));
        }
    };
    /**
     * the comparator  used for  reverse item sorting
     */
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

    /**
     * libGDX ui act method called by the stage object or parent UI object
     * used  to check for new items and  remake the list UI display them
     * @param delta delta time
     */
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

    /**
     * creates the UI list Of Items  fro the array of items
     */

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

    /**
     *  sets the list of items  if track items is false creates new array of items so the original array of items won't be modified
     * @param items
     * @param trackItems
     */
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

    /**
     *  sets the currently selected item to the passed in item
     * @param item
     * @param changeTable
     */
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
    /**
     *  sets the currently selected item to the passed in item index
     * @param index the item index in array
     * @param changeTable
     */
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

    /**
     * returns the value for the given item
     * @param item
     * @return
     */
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

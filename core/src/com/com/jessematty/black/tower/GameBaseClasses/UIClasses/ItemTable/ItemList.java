package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyAction;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.ItemSettable;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.DeleteOptionPane;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
/**
 *  a  UI class for a modifiable list of items
 *  is also used as columns in  the ItemTable Class
 * @linked ItemTable
 * @see ItemTable
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
    protected boolean editable;
    /**
     *  can you drag to change list order
     */
    protected boolean dragToChangeOrder;
    /**
     *  is the list sortable? if true clicking
     *  on the title will sort the contents of the list ascending  using a basic comparator or one you specify
     *  and clicking again will sort  the list descending using a basic comparator or one you specify
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
     * remake the table on column change flag
     */
    private boolean remakeList;
    /**
     *  the name of the getter and setter methods used to displayed name of the item for the label minus get and set
     *  if not set will use  the java object toString method
     *
     */
    private String  methodName="";
    /**
     *  the class for that data type to be displayed  IE Integer String Double etc.
     *  accepts
     *  String.class
     *  Long.class
     *  Integer.class
     *  Float.class
     *  Double.class
     *
     */
    private Class itemDataClass=String.class;
    /**
     * the title for the list of items
     */
    private ItemListTitle<T> itemListTitle;
    /**
     *  the width of the list or column width if used with an ItemTable
     */
    private float columnWidth=100f;
    /**
     * if being used as a column  in  Item Table the linked ItemTable
     * @see ItemTable
     */
    private ItemTable<T> itemTable;
    /**
     *  the position in the array  of  ItemLists in the linked ItemTable
     */
    private int tableColumnIndex;
    /**
     * the name of the title list if used with an ItemTable This will be the column header
     */
    private String titleName;
    /**
     * custom libGDX UI styles for the title label and item labels
     */
    protected ItemListLabelStyle itemListLabelStyle;
    private ItemListTitleStyle itemListTitleStyle;
    /**
     * whether for not to display  the title for the list of items
     */
    private boolean displayTitle;
    /**
     * if true the list will be non editable and the objects to string method result will be displayed for the list text
     */
    protected boolean useToString;
    /**
     * whether or not  an item can be deleted by pressing the delete key
     * this  is linked to the  table as well so if table is set to delete on delete
     * this cant be true
     */
    private boolean pressDeleteToDeleteItem;
    /**
     * whether or not  to display a dialog  box to confirm your delete
     * if true a DeleteOptionPane box will be displayed at the center of the screen
     * asking you to confirm your delete
     * @see com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.DeleteOptionPane
     *
     */
    private boolean confirmDelete;
    /**
     * functional interface method that called when an item is selected
     */
    private OnSelected<T>  onSelected;
    /**
     * the comparator  used for ascending  item sorting can be  replaced with a custom comparator
     * the default uses the items .compareTo() method
     *
     */
    /**
     *  they key input combo for deleting  a item from the list
     *  if it is enabled. this will use the delete key to remove
     *  the selected item from the list
     */
    private  final InputKeyCombo deleteCombo;
    /**
     * the comparator  used for ascending   item sorting can be  replaced with a custom comparator
     * the default uses the items .compareTo() method with items swapped for descending sorting
     *
     */
    private Comparator<T> itemComparator= new Comparator<T>() {
        boolean descend;
        @Override
        public int compare(T item1, T item2) {
                return getItemValue(item1).compareTo(getItemValue(item2));
        }
    };
    /**
     * the comparator  used for descending  item sorting can be  replaced with a custom comparator
     * the default uses the items .compareTo() method with items swapped in order  for descending sorting
     *
     */
    private Comparator<T> reverseItemComparator= new Comparator<T>() {
        boolean descend;
        @Override
        public int compare(T item1, T item2) {
            return getItemValue(item2).compareTo(getItemValue(item1));
        }
    };
    
    public ItemList(Skin skin, String titleName,  Class itemDataClass, boolean displayTitle) {
        this(skin,"default", titleName, "", itemDataClass, null, 0, displayTitle);
        useToString=true;
        editable=false;
    }
    public ItemList(Skin skin, String titleName, String methodName, Class itemDataClass, boolean displayTitle) {
       this(skin,"default", titleName, methodName, itemDataClass, null, 0, displayTitle);
    }
    /**
     * 
     * @param skin libGDX Skin object
     * @param styleName the style name for the libGDX Skin object
     * @param titleName the title for the list
     * @param methodName the method name used retrieve the displayed data
     * @param itemDataClass the item data class Boolean.class, Float.class, Long.class, Double.class, Integer.class or String.class
     * @param itemTable the linked item table
     * @param tableColumnIndex the index of the column in the table
     * @param displayTitle whether on not display the list title
     */
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
        // set up for delete key
        KeyAction keyAction= new KeyAction() {
            @Override
            public void act() {
                    if(selectedItem!=null && confirmDelete) {
                        DeleteOptionPane deleteOptionPane = new DeleteOptionPane(getSkin(), "",   items, selectedItem);
                        deleteOptionPane.setPosition(ScreenPosition.CENTER.getX(), ScreenPosition.CENTER.getY());
                        getStage().addActor(deleteOptionPane);
                        remakeList =true;
                    }
                    else if(selectedItem!=null){
                        items.removeValue(selectedItem, true);
                        remakeList =true;
                    }
                }
        };
        deleteCombo= new InputKeyCombo(keyAction,"Delete Item "+this, Keys.DEL );
        deleteCombo.setFocusActor(this);
        GameAssets.getGameInput().getKeyListener().addInputKeyCombo(deleteCombo);
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
        if((size!=items.size) || remakeList ==true) {
                size = items.size;
            remakeList =false;
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
    protected void makeItems(){
        int size=items.size;
        itemLabels.clear();
        clear();
        if(displayTitle==true) {
            add(itemListTitle).width(columnWidth);
        }
        row();
        for(int count=0; count<size; count++){
           ItemLabel<T> label= new ItemLabel<T>(this,  count,   itemListLabelStyle, items.get(count),editable, methodName, itemDataClass);
           label.setSize(getPrefWidth(), getPrefHeight());
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
        setBounds(getX(), getY(), columnWidth, getPrefHeight());
        invalidateHierarchy();
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
        remakeList =true;
    }
    /**
     *  sets the currently selected item to the passed in item
     * @param item the item to select
     * @param changeTable whether or not to set the selected index
     *   of the parent  ItemTable as well and then remake it.
     *
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
        if(onSelected!=null){
            onSelected.onSelected(item);
        }
        fire(new ChangeEvent());
    }
    /**
     *
     * @return the currently selected item
     */
    public T getSelectedItem() {
        return selectedItem;
    }
    /**
     *
     * @return the ItemLabel of the selected Item
     */
    public ItemLabel<T> getSelectedLabel() {
        return selectedLabel;
    }
    /**
     * returns
     * @return the index in the array of the selected item
     */
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
        if(onSelected!=null){
            onSelected.onSelected(selectedItem);
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
        if(useToString==true){
            return;
        }
        this.editable = editable;
        remakeList =true;
    }
    public boolean isEditable() {
        return editable;
    }
    /**
     * set the force remake flag to rue to remake the  the list / column
     * if there is linked table sets the parents ItemTable force remake flags too.
     * @linked ItemTable
     */
    public void forceRemakeList() {
        this.remakeList = true;
        if(itemTable!=null){
            itemTable.forceRemakeTable();
            itemTable.forceRemakeColumns();
        }
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
        remakeList =true;
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
     * returns the value for the given item using java reflection
     * based on the passed getter method name
     * if no method name specified will   use
     * the java objects.toString() method and editing will be disabled
     * @param item
     * @return
     */
    public String  getItemValue(T item)  {
        String value="";
        if(item!=null){
            Class<T> cls= (Class<T>) item.getClass();
            Method method = null;
            try {
                if(useToString || methodName==null || methodName.isEmpty()){
                    method = cls.getDeclaredMethod("toString");
                    editable=false;
                }
                else {
                    method = cls.getDeclaredMethod("get" + methodName);
                }
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
    /**
     * sets the width for the column  or if  a single list the width of the list
     * forces remaking of the ItemTable and  columns if there is table associated with it
     *
     * @param columnWidth
     */
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
        remakeList =true;
        if(itemTable!=null){
            itemTable.forceRemakeColumns();
            itemTable.forceRemakeTable();
        }
    }
    public boolean isUseToString() {
        return useToString;
    }
    /**
     * sets wether or not to use  the to string for the list data
     * if true sets editable to false as to string cannot be set
     * @param useToString
     */
    public void setUseToString(boolean useToString) {
        this.useToString = useToString;
        if(useToString){
            editable=false;
        }
        remakeList =true;
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
    public OnSelected<T> getOnSelected() {
        return onSelected;
    }
    public void setOnSelected(OnSelected<T> onSelected) {
        this.onSelected = onSelected;
    }
    public Comparator<T> getItemComparator() {
        return itemComparator;
    }
    public void setItemComparator(Comparator<T> itemComparator) {
        this.itemComparator = itemComparator;
    }
    public Comparator<T> getReverseItemComparator() {
        return reverseItemComparator;
    }
    public void setReverseItemComparator(Comparator<T> reverseItemComparator) {
        this.reverseItemComparator = reverseItemComparator;
    }
    public boolean isPressDeleteToDeleteItem() {
        return pressDeleteToDeleteItem;
    }
    public void setPressDeleteToDeleteItem(boolean pressDeleteToDeleteItem) {
        this.pressDeleteToDeleteItem = pressDeleteToDeleteItem;
        deleteCombo.setDisabled(!pressDeleteToDeleteItem);
    }
    public boolean isConfirmDelete() {
        return confirmDelete;
    }
    public void setConfirmDelete(boolean confirmDelete) {
        this.confirmDelete = confirmDelete;
    }
    public void setDisplayTitle(boolean displayTitle) {
        this.displayTitle = displayTitle;
    }
}

package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;

/**
 * class for UI table similar to a javafx table
 * @param <T>
 */
public class ItemTable<T> extends Table {
    /**
     * the array of items in the table
     */
    private  Array<T>  tableItems= new Array<>();
    /**
     * the array of items lists aka the table columns
     */
    private Array<ItemList<T>> tableColumns= new Array();

private Skin skin;
private boolean remakeTable;
private boolean remakeColumns;
private int numberOfColumns;
private int selectedIndex;
private boolean editable;
private boolean sortable;
    /**
     * the selected item in the table
     */
    private T selectedItem;

 /**
  * libGDX drag and drop for moving for columns
  */
private DragAndDrop dragAndDrop;
private boolean  dragToChangeOrder;
private String styleName;
    public ItemTable(Skin skin, String styleName,  Array<T> tableItems) {
        super(skin);
        this.tableItems = tableItems;
        this.skin=skin;
        this.styleName=styleName;
    }

    public ItemTable(Skin skin) {
        super(skin);
        this.skin=skin;
    }

    /**
     * makes the table using the array of items to get teh data from
     */
    private void makeTable(){
        int size=tableColumns.size;
        clear();
        for(int count=0; count<size; count++){
        ItemList itemList=tableColumns.get(count);
        if(remakeColumns ==true) {
            itemList.forceRemakeTable();
            remakeColumns=false;
        }
            if (dragToChangeOrder == true && dragAndDrop != null) {
                dragAndDrop.addTarget(new ItemTableTarget<T>(this, itemList));
                dragAndDrop.addSource(new ItemTableSource<T>( itemList));
            }

        add(itemList).width(itemList.getColumnWidth());
    }
        validate();
        invalidateHierarchy();

}
    /**
     * libGDX ui act method called by the stage object or parent UI object
     * used  to check for new items and  remake the table  UI display them
     * @param delta delta time
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        int itemsSize=tableItems.size;
        if(remakeTable==true || numberOfColumns !=itemsSize){
            this.numberOfColumns =itemsSize;
            remakeTable=false;
            makeTable();
        }


    }

    /**
     * sets the selected item to the passed in one
     * @param item
     */
    public void setSelected(T item){
        for(int count = 0; count< numberOfColumns; count++){
          if(item==  tableItems.get(count)){
              selectedIndex=count;
              setSelected(selectedIndex);
              
              return;
          }
        }
    }
    /**
     * sets the selected item index to the passed in one
     * @param index the items position in the array
     */
    public  void setSelected(int index){
        int size=tableColumns.size;
        for(int count=0; count<size; count++ ){
            tableColumns.get(count).setSelected(index, false);
        }
    }

    /**
     * add a new Item List aka column  to the table used java reflection to retrieve column data
     * @param title the title of the column
     * @param methodName the method name used to retrieve  the data for the column
     * @param dataClass the  class for the type of data String.class, Float.class, Double.class, Boolean.Class, Integer.class, Long.Class or Object.class
     */
    public void addColumn( String title, String methodName, Class dataClass){
        ItemList<T> column= new ItemList(skin, styleName, title, methodName, dataClass, this, tableColumns.size, true );
        column.setItems(tableItems, true);
        column.disableDragOrderChanged();
        this.tableColumns.add(column);
        remakeTable=true;
    }
    public void forceRemakeTable() {
        this.remakeTable = true;
    }
    public Array<T> getTableItems() {
        return tableItems;
    }
    public void setTableItems(Array<T> tableItems) {
        this.tableItems = tableItems;
        for(int count = 0; count< numberOfColumns; count++ ){
           ItemList column=tableColumns.get(count);
            column.setItems(tableItems, true);

        }
        remakeTable=true;
        remakeColumns=true;
    }


    public void forceRemakeColumns(){

        remakeColumns=true;
    }
    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        setSelected(selectedIndex);
    }

    public T getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(T selectedItem) {
        this.selectedItem = selectedItem;
        setSelected(selectedItem);
    }


    public void setEditable(boolean editable) {
        this.editable = editable;

        int size=tableColumns.size;
        for(int count=0; count<size; count++){
            tableColumns.get(count).setEditable(true);
        }

    }

    public boolean isEditable() {
        return editable;
    }

    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        int size=tableColumns.size;
        for(int count=0; count<size; count++){
            tableColumns.get(count).setSortable(true);
        }
    }

    public Array<ItemList<T>> getTableColumns() {
        return tableColumns;
    }

    public void  enableDragOrderChanging(DragAndDrop dragAndDrop){
        this.dragToChangeOrder=true;
        this.dragAndDrop=dragAndDrop;
        forceRemakeTable();
        forceRemakeColumns();
    }

    public void  enableColumnDragOrderChanging(DragAndDrop dragAndDrop){
        this.dragAndDrop=dragAndDrop;
        int size=tableColumns.size;
        for(int count=0; count<size; count++){
            tableColumns.get(count).enableDragOrderChanging(dragAndDrop);
        }

        forceRemakeTable();
        forceRemakeColumns();
    }
    public void  enableDragOrderChanging(){
        this.dragToChangeOrder=true;
        this.dragAndDrop=new DragAndDrop();
        forceRemakeTable();
        forceRemakeColumns();
    }
    public void disableDragOrderChanged(){
        dragAndDrop=null;
        this.dragToChangeOrder=false;

    }


}

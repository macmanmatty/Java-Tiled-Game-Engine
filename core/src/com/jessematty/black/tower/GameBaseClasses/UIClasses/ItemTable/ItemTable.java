package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;

public class ItemTable<T> extends Table {
private  Array<T>  tableItems= new Array<>();
private Array<ItemList<T>> tableColumns= new Array();
private Skin skin;
private boolean remakeTable;
private boolean remakeColumns;
private int size;
private int selectedIndex;
private boolean editable;
private boolean sortable;
private T selectedItem;
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
    @Override
    public void act(float delta) {
        super.act(delta);
        int itemsSize=tableItems.size;
        if(remakeTable==true || size!=itemsSize){
            this.size=itemsSize;
            remakeTable=false;
            makeTable();
        }


    }
    public void setSelected(T item){
        for(int count=0; count<size; count++){
          if(item==  tableItems.get(count)){
              selectedIndex=count;
              setSelected(selectedIndex);
              
              return;
          }
        }
    }
    public  void setSelected(int index){
        int size=tableColumns.size;
        for(int count=0; count<size; count++ ){
            tableColumns.get(count).setSelected(index, false);
        }
    }

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
        for(int count=0; count<size; count++ ){
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

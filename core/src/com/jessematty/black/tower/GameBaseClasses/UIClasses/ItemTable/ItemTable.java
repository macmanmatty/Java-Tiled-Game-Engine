package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyAction;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.DeleteOptionPane;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;

/**
 * class for UI table similar to a javafx table
 * @param <T>
 */
public class ItemTable<T> extends Table {
    /**
     * the array of items in the table
     */
    private Array<T> tableItems = new Array<>();
    /**
     * the array of items lists aka the table columns
     */
    private Array<ItemList<T>> tableColumns = new Array();

    private Skin skin;
    /**
     * flag to force remaking of the table
     */
    private boolean remakeTable;

    private boolean remakeColumns;
    private int numberOfRows;
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
    private boolean dragToChangeOrder;
    private String styleName;

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
     *
     * @see com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.DeleteOptionPane
     */
    private boolean confirmDelete;

    /**
     *  they key input combo for deleting  a item from the list
     *  if it is enabled. this will use the delete key to remove
     *  the selected item from the list
     */

    private final  InputKeyCombo deleteCombo;

    public ItemTable(Skin skin, String styleName, Array<T> tableItems) {
        this(skin);
        this.tableItems = tableItems;
        this.styleName = styleName;
    }

    public ItemTable(Skin skin) {
        super(skin);
        this.skin = skin;
        KeyAction keyAction = new KeyAction() {
            @Override
            public void act() {
                    if (selectedItem != null && confirmDelete) {
                        DeleteOptionPane deleteOptionPane = new DeleteOptionPane(getSkin(), tableItems, selectedItem);
                        deleteOptionPane.setPosition(ScreenPosition.CENTER.getX(), ScreenPosition.CENTER.getY());
                        getStage().addActor(deleteOptionPane);
                        remakeTable = true;
                    } else if (selectedItem != null) {
                        tableItems.removeValue(selectedItem, true);
                        remakeTable = true;
                    }

                }

        };
        deleteCombo = new InputKeyCombo(keyAction, "Delete Item " + this, Keys.DEL);
        deleteCombo.setFocusActor(this);
        GameAssets.getGameInput().getKeyListener().addInputKeyCombo(deleteCombo);
    }

    /**
     * makes the table using the array of items to get the data from
     */
    private void makeTable() {
        int size = tableColumns.size;
        clear();
        for (int count = 0; count < size; count++) {
            ItemList itemList = tableColumns.get(count);
            if (remakeColumns == true) {
                itemList.forceRemakeList();
                remakeColumns = false;
            }
            if (dragToChangeOrder == true && dragAndDrop != null) {
                dragAndDrop.addTarget(new ItemTableTarget<T>(this, itemList));
                dragAndDrop.addSource(new ItemTableSource<T>(itemList));
            }

            add(itemList).width(itemList.getColumnWidth());
        }
        validate();
        invalidateHierarchy();

    }

    /**
     * libGDX ui act method called by the stage object or parent UI object
     * used  to check for new items and  remake the table  UI display them
     *
     * @param delta delta time
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        int itemsSize = tableItems.size;
        if (remakeTable == true || numberOfRows != itemsSize) {
            this.numberOfRows = itemsSize;
            remakeTable = false;
            makeTable();
        }


    }

    /**
     * sets the selected item to the passed in one
     *
     * @param item
     */
    public void setSelected(T item) {
        for (int count = 0; count < numberOfRows; count++) {
            if (item == tableItems.get(count)) {
                selectedIndex = count;
                setSelected(selectedIndex);

                return;
            }
        }
    }

    /**
     * sets the selected item index to the passed in one
     *
     * @param index the items position in the array
     */
    public void setSelected(int index) {
        int size = tableColumns.size;
        for (int count = 0; count < size; count++) {
            tableColumns.get(count).setSelected(index, false);
        }
    }

    /**
     * add a new Item List aka column  to the table used java reflection to retrieve column data
     *
     * @param title      the title of the column
     * @param methodName the method name used to retrieve  the data for the column
     * @param dataClass  the  class for the type of data String.class, Float.class, Double.class, Boolean.Class, Integer.class, Long.Class or Object.class
     */
    public void addColumn(String title, String methodName, Class dataClass) {
        ItemList<T> column = new ItemList(skin, styleName, title, methodName, dataClass, this, tableColumns.size, true);
        column.setItems(tableItems, true);
        column.disableDragOrderChanged();
        this.tableColumns.add(column);
        remakeTable = true;
    }

    public void forceRemakeTable() {
        this.remakeTable = true;
    }

    public Array<T> getTableItems() {
        return tableItems;
    }

    public void setTableItems(Array<T> tableItems) {
        this.tableItems = tableItems;
        for (int count = 0; count < numberOfRows; count++) {
            ItemList column = tableColumns.get(count);
            column.setItems(tableItems, true);

        }
        remakeTable = true;
        remakeColumns = true;
    }


    public void forceRemakeColumns() {

        remakeColumns = true;
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

        int size = tableColumns.size;
        for (int count = 0; count < size; count++) {
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
        int size = tableColumns.size;
        for (int count = 0; count < size; count++) {
            tableColumns.get(count).setSortable(true);
        }
    }

    public Array<ItemList<T>> getTableColumns() {
        return tableColumns;
    }

    public void enableDragOrderChanging(DragAndDrop dragAndDrop) {
        this.dragToChangeOrder = true;
        this.dragAndDrop = dragAndDrop;
        forceRemakeTable();
        forceRemakeColumns();
    }

    public void enableColumnDragOrderChanging(DragAndDrop dragAndDrop) {
        this.dragAndDrop = dragAndDrop;
        int size = tableColumns.size;
        for (int count = 0; count < size; count++) {
            tableColumns.get(count).enableDragOrderChanging(dragAndDrop);
        }

        forceRemakeTable();
        forceRemakeColumns();
    }

    public void enableDragOrderChanging() {
        this.dragToChangeOrder = true;
        this.dragAndDrop = new DragAndDrop();
        forceRemakeTable();
        forceRemakeColumns();
    }

    public void disableDragOrderChanged() {
        dragAndDrop = null;
        this.dragToChangeOrder = false;

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

}




package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
/**
 *  target for dragging columns in the ItemTable  used with libGDX DragAndDrop
 * @see com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop
 * @see ItemTable
 * @param <T> The generic  class of the items in the column
 */
public class ItemTableTarget<T > extends Target {
    private  final ItemTable<T> itemTable;
    private ItemList<T> tableColumn;
    public ItemTableTarget(ItemTable<T> itemTable, ItemList<T> tableColumn ) {
        super(tableColumn);
        this.itemTable=itemTable;
        this.tableColumn=tableColumn;
    }
    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        return true;
    }
    /**
     * 
     * @param source The Column aka ItemList your inserting to the right of
     * @param payload The column aka ItemList your moving
     * @param x screen location x
     * @param y screen location x
     * @param pointer
     */
    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
            int index=tableColumn.getTableColumnIndex();
            if(x>tableColumn.getColumnWidth()){
                index++;
            }
       itemTable.getTableColumns().removeValue((ItemList<T>) payload.getObject(), true);
        itemTable.getTableColumns().insert(index, (ItemList<T>) payload.getObject());
            itemTable.forceRemakeTable();
            itemTable.forceRemakeColumns();
    }
}

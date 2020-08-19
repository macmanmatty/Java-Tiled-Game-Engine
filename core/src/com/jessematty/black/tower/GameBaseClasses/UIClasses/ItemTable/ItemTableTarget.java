package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

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

    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
            int index=tableColumn.getTableColumnIndex();
            if(x>tableColumn.getColumnWidth()){
                index++;
            }

            System.out.println(index);
       itemTable.getTableColumns().removeValue((ItemList<T>) payload.getObject(), true);

        itemTable.getTableColumns().insert(index, (ItemList<T>) payload.getObject());


            itemTable.forceRemakeTable();
            itemTable.forceRemakeColumns();

    }




}

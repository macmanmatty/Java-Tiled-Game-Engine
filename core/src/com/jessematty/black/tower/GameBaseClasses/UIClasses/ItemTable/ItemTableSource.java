package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class ItemTableSource<T > extends Source {


    final Payload payload= new Payload();
    private ItemList<T> tableColumn;
    public ItemTableSource( ItemList<T> tableColumn ) {
        super(tableColumn);
        this.tableColumn=tableColumn;

    }
    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {

        payload.setObject(tableColumn);
        ItemList<T> column=new ItemList<T>(tableColumn.getSkin(), tableColumn.getName(), tableColumn.getTitleName(),tableColumn.getItemDataClass(), true);
        payload.setDragActor(column);
        payload.setValidDragActor(column);
        payload.setInvalidDragActor(column);

        return payload;
    }
    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
        super.dragStop(event, x, y, pointer, payload, target);






    }
}

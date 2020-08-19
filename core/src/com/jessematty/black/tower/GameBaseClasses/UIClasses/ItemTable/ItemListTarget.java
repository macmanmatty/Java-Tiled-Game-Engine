package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class ItemListTarget<T > extends Target {
    private  final ItemListAdapter<T> items;
    private ItemLabel<T> itemLabel;
    public ItemListTarget(ItemListAdapter<T> items, ItemLabel<T> itemLabel ) {
        super(itemLabel);
        this.items=items;
        this.itemLabel=itemLabel;

    }

    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        return true;
    }

    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
            int index=itemLabel.getIndex();
            if(y>itemLabel.getHeight()){
                index++;
            }
        items.getItems().removeValue((T) payload.getObject(), true);

        items.getItems().insert(index, (T) payload.getObject());


            items.forceRemakeTable();


    }




}

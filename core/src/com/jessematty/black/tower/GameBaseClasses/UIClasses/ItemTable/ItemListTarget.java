package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
/**
 *  target for dragging columns in the ItemTable  used with libGDX DragAndDrop
 * @see com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop
 * @see ItemList
 * @param <T> The generic  class of the items in the Array
 * **/
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
    /**
     *
     * @param source The Item  aka ItemLabel your inserting to the top of
     * @param payload The Item aka ItemLabel your moving
     * @param x screen location x
     * @param y screen location x
     * @param pointer
     */
    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
            int index=itemLabel.getIndex();
            if(y>itemLabel.getHeight()){
                index++;
            }
        items.getItems().removeValue((T) payload.getObject(), true);
        items.getItems().insert(index, (T) payload.getObject());
            items.forceRemakeList();
    }
}

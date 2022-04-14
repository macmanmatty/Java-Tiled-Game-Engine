package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
/**
 *  target for dragging columns in the ItemTable  used with libGDX DragAndDrop
 * @see com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop
 * @see ItemList
 * @param <T> The generic  class of the items in the Array
 * **/
public class ItemListSource<T > extends Source {
    private final ItemListAdapter<T> items;
    private final ItemLabel itemLabel;
    private final Skin skin;
    private int selectedIndex;
    final Payload payload= new Payload();
    public ItemListSource(ItemListAdapter<T> items, ItemLabel<T> itemLabel, Skin skin) {
        super(itemLabel);
        this.items=items;
        this.skin=skin;
        this.itemLabel =itemLabel;
    }
    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        selectedIndex=items.getSelectedIndex();
        T item= (T) itemLabel.getItem();
        payload.setObject(item);
        payload.setDragActor(new Label(itemLabel.getText(), skin, "background"));
        Label invalidLabel= new Label(itemLabel.getText(), skin, "background");
        payload.setInvalidDragActor(invalidLabel);
        return payload;
    }
    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
        super.dragStop(event, x, y, pointer, payload, target);

    }
}

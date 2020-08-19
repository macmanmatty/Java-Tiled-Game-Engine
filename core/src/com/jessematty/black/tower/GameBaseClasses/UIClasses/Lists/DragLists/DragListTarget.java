package com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists.DragLists;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class DragListTarget<T> extends Target {
    private  final List<T> items;
    public DragListTarget( List<T> items ) {
        super(items);
        this.items=items;
    }


    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        return true;
    }

    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
            items.getItems().add((T)payload.getObject());
            items.invalidate();
            items.invalidateHierarchy();



             System.out.println("Item dropped!!");
    }


}

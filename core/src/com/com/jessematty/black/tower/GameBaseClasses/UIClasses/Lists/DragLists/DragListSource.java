package com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists.DragLists;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class DragListSource<T> extends Source {
    private final List<T> items;
    private final List<T> items2;

    private final Skin skin;
    private int selectedIndex;
    final Payload payload= new Payload();
    private  boolean removeOnDropInvalid;
    private boolean addToSecondListOnInvalid;
    public DragListSource(List<T> items, Skin skin) {
        this(  items, null, skin,  false, false);


    }
    public DragListSource( List<T> items, List<T> items2, Skin skin, boolean removeOnDropInvalid, boolean addToSecondListOnInvalid) {
        super(items);
        this.items=items;
        this.items2=items2;
        this.skin=skin;

        this.removeOnDropInvalid=removeOnDropInvalid;
        this.addToSecondListOnInvalid=addToSecondListOnInvalid;
    }
    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        selectedIndex=items.getSelectedIndex();

        T item=items.getSelected();
        payload.setObject(item);
        items.getItems().removeValue(item, false);
        payload.setDragActor(new Label(item.toString(), skin));
        Label invalidLabel= new Label(item.toString(), skin);
        payload.setInvalidDragActor(invalidLabel);
        Label validLabel= new Label(item.toString(), skin);
        payload.setInvalidDragActor(validLabel);
        System.out.println("Item dragged Started!!");

        return payload;
    }
    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
        super.dragStop(event, x, y, pointer, payload, target);

        if(!removeOnDropInvalid){
        if(target==null ){

            if(selectedIndex<items.getItems().size) {
                items.getItems().insert(selectedIndex, (T) payload.getObject());
            }
            else{

                items.getItems().add((T) payload.getObject());
            }
        }
        }


        if(addToSecondListOnInvalid){
            if(items2!=null && target==null) {
                items2.getItems().add((T) payload.getObject());
                items2.invalidateHierarchy();

            }

        }

    }
}

package com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
public class ObservableList<T> extends List<T> {
    private int size;
    private Array<T> items;
    private  float maxHeight;
    public ObservableList(Skin skin, Array<T> items) {
        super(skin);
        size=items.size;
        this.items=items;
    }
    public ObservableList(Skin skin, String styleName, Array<T> items) {
        super(skin, styleName);
        size=items.size;
        this.items=items;
    }
    public ObservableList(ListStyle style, Array<T> items) {
        super(style);
        size=items.size;
        this.items=items;
    }
    public ObservableList(Skin skin) {
        super(skin);
    }
    public ObservableList(Skin skin, String styleName) {
        super(skin, styleName);
    }
    public ObservableList(ListStyle style) {
        super(style);
    }
    @Override
    public void act(float delta) {
        if(items.size!=size){
            size=items.size;
            setItems(items);
        }
        super.act(delta);
    }
    @Override
    public float getMaxHeight() {
        return maxHeight;
    }
    public void setMaxHeight(float maxHeight) {
        this.maxHeight = maxHeight;
    }
    public void setItems(Array items) {
        if(items==null){
            return;
        }
        super.setItems(items);
        this.items = items;
    }
}

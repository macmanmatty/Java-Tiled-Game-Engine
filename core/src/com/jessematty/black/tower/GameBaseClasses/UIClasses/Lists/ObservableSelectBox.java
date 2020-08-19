package com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists;

import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class ObservableSelectBox<T> extends SelectBox<T> {

    private int size;
    private Array<T> items;
    private  float maxHeight;
    private boolean remakeList;
    public ObservableSelectBox(Skin skin, Array<T> items) {
        super(skin);
        size=items.size;
        this.items=items;
        remakeList=true;

    }

    public ObservableSelectBox(Skin skin, String styleName, Array<T> items) {
        super(skin, styleName);
        size=items.size;

        this.items=items;
    }

    public ObservableSelectBox(SelectBoxStyle style, Array<T> items) {
        super(style);
        size=items.size;

        this.items=items;
    }

    public ObservableSelectBox(Skin skin) {
        super(skin);
    }

    public ObservableSelectBox(Skin skin, String styleName) {
        super(skin, styleName);
    }

    public ObservableSelectBox(SelectBoxStyle style) {
        super(style);
    }

    @Override
    public void act(float delta) {


        if( items!=null && (items.size!=size || remakeList==true)){
            size=items.size;
            setItems(items);

            remakeList=false;

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
        super.setItems(items);
        this.items = items;

        remakeList=true;



    }


    public void forceRemakeList(){
        remakeList=true;
    }


}

package com.jessematty.black.tower.GameBaseClasses.UIClasses.Table;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;


public abstract class  UITable extends Table {

    private String backGroundName;
    public UITable(Skin skin, String backGroundName) {
        super(skin);
        this.backGroundName=backGroundName;
        NinePatchDrawable ninePatchDrawable= new NinePatchDrawable(skin.getPatch(backGroundName));
        setBackground(ninePatchDrawable);
    }


    public void update(){


    }



}

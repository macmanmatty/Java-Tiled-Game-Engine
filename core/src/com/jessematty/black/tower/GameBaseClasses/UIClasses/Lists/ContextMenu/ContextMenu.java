package com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists.ContextMenu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ContextMenu<E> {

    VerticalGroup verticalGroup;
    TextButton close;
Skin skin;
SelectBox<E> list;
E object;


    public ContextMenu(Skin skin, final SelectBox<E> list) {
        this.skin=skin;
        this.list=list;




       verticalGroup=new VerticalGroup();
       close= new TextButton("Select", skin);
       close.addListener(new ClickListener(){

           @Override
           public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {


        object=list.getSelected();

        return true;


           }
       });


       verticalGroup.addActor(list);
       verticalGroup.addActor(close);


    }


    public E getObject() {
        return object;
    }
}

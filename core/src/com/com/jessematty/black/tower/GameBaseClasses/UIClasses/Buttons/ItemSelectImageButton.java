package com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ItemSelectImageButton<T> extends Image {

   private  T  item;
   private  TextureRegion textureRegion;
   private ItemSettable<T> itemSettable;



    public ItemSelectImageButton(final T item, TextureRegion textureRegion, final ItemSettable<T> itemSettable) {
        super(textureRegion);
        this.item = item;
        this.textureRegion = textureRegion;
        this.itemSettable = itemSettable;

        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setDebug(true);
                if (button == 0) {

                    itemSettable.setItem(item);

                }

                return true;
            }


            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                setColor(1, 1, 1, .5f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                setColor(1, 1, 1, 1);
            }
        });
    }



}

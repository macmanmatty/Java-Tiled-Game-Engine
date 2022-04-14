package com.jessematty.black.tower.Editor.EditMode.Windows.EntitySelect;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.ItemSettable;


public class EntitySelectImageButton  extends ImageButton {


    private int clicks;
    int counter;
    public EntitySelectImageButton(final Entity item, TextureRegion textureRegion, final MapEditScreen mapEditScreen, final ItemSettable<Entity> itemSettable) {
        super(new TextureRegionDrawable(textureRegion));

        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setDebug(true);
                if (button == 0 && clicks==1) {
                    itemSettable.setItem(item);
                }

                if(clicks==2){
                    PositionComponent positionComponent= GameComponentMapper.getPositionComponentMapper().get(item);
                    if(positionComponent==null){
                        return  true;
                    }

                    float positionX=positionComponent.getLocationX();
                    float  positionY=positionComponent.getLocationY();


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


    @Override
    public void act(float delta){
        super.act(delta);
        // used for resetting button clicks
        if(counter==1000){
            clicks=0;
            counter=0;
        }
        counter++;

    }




}

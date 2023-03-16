package com.jessematty.black.tower.Editor.EditMode.UIElements.PopUpMenus.MenuItems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.Components.Other.EditorImageComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer;

public class CreateEntity extends MapEditScreenMenuItem {

    public CreateEntity(String text, final MapEditScreen mapEditScreen) {
        super(text, mapEditScreen);
        addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                BasicEntityContainer container= EntityUtilities.makeBasicEntity();
                Entity entity=container.getEntity();
                PositionComponent position= new PositionComponent();
                position.setLocationX(x);
                position.setLocationY(y);
                entity.add(position);
                EditorImageComponent editorImageComponent = new EditorImageComponent();
                entity.add(editorImageComponent);
                getMapEditScreen().getWorld().addEntityToWorld(entity);
                return  true;



            }
        });

    }



}

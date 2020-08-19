package com.jessematty.black.tower.Editor.EditMode.UIElements.PopUpMenus.MenuItems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.Components.EditorImageComponent;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Generators.Entity.EntityContainers.BasicEntityContainer;
import com.kotcrab.vis.ui.widget.MenuItem;

public class CreateEntity extends MapEditScreenMenuItem {

    public CreateEntity(String text, final MapEditScreen mapEditScreen) {
        super(text, mapEditScreen);
        addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                BasicEntityContainer container= EntityUtilities.makeBasicEntity();
                Entity entity=container.getEntity();
                Position position= new Position();
                position.setScreenLocationX(x);
                position.setScreenLocationY(y);
                entity.add(position);
                EditorImageComponent editorImageComponent = new EditorImageComponent();
                entity.add(editorImageComponent);
                getMapEditScreen().getWorld().addEntity(entity);
                return  true;



            }
        });

    }



}

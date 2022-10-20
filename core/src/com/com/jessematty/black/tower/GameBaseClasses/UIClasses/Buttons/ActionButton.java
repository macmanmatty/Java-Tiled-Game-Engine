package com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ActionComponent;

/**
 *  Action Button that adds an action to an entity
 */
public class ActionButton extends ImageButton {
   private ActionComponent action;
    private Entity entity;

    public ActionButton(Skin skin, ActionComponent action, Entity entity) {
        super(skin);
        this.action = action;
        this.entity = entity;

        addListener();
    }

    public ActionButton(Skin skin, String styleName, ActionComponent action, Entity entity) {
        super(skin, styleName);
        this.action = action;
        this.entity = entity;
        addListener();

    }

    public ActionButton(ImageButtonStyle style, ActionComponent action, Entity entity) {
        super(style);
        this.action = action;
        this.entity = entity;
        addListener();

    }
    public ActionButton( ActionComponent action, Entity entity) {
        this(action.getButtonUpImage().getDrawable(), action.getButtonDownImage().getDrawable(),action,  entity);

        this.action = action;
        this.entity = entity;
        addListener();

    }

    public ActionButton(Drawable imageUp, ActionComponent action, Entity entity) {
        super(imageUp);
        this.action = action;
        this.entity = entity;
        addListener();

    }

    public ActionButton(Drawable imageUp, Drawable imageDown, ActionComponent action, Entity entity) {
        super(imageUp, imageDown);
        this.action = action;
        this.entity = entity;
        addListener();

    }

    public ActionButton(Drawable imageUp, Drawable imageDown, Drawable imageChecked, ActionComponent action, Entity entity) {
        super(imageUp, imageDown, imageChecked);
        this.action = action;
        this.entity = entity;
        addListener();

    }

    public void  addListener(){
        addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               entity.add(action);
               return true;

            }
        });

   }
}

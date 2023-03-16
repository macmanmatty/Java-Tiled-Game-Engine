package com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ItemActionImageComponent;

/**
 *  Action Button that adds an action to an entity
 */
public class ActionButton extends ImageButton {
   private ItemActionImageComponent action;
    private Entity entity;

    public ActionButton(Skin skin, ItemActionImageComponent action, Entity entity) {
        super(skin);
        this.action = action;
        this.entity = entity;

        addListener();
    }

    public ActionButton(Skin skin, String styleName, ItemActionImageComponent action, Entity entity) {
        super(skin, styleName);
        this.action = action;
        this.entity = entity;
        addListener();

    }

    public ActionButton(ImageButtonStyle style, ItemActionImageComponent action, Entity entity) {
        super(style);
        this.action = action;
        this.entity = entity;
        addListener();

    }
    public ActionButton(ItemActionImageComponent action, Entity entity) {
        this(action.getButtonUpImage().getDrawable(), action.getButtonDownImage().getDrawable(),action,  entity);

        this.action = action;
        this.entity = entity;
        addListener();

    }

    public ActionButton(Drawable imageUp, ItemActionImageComponent action, Entity entity) {
        super(imageUp);
        this.action = action;
        this.entity = entity;
        addListener();

    }

    public ActionButton(Drawable imageUp, Drawable imageDown, ItemActionImageComponent action, Entity entity) {
        super(imageUp, imageDown);
        this.action = action;
        this.entity = entity;
        addListener();

    }

    public ActionButton(Drawable imageUp, Drawable imageDown, Drawable imageChecked, ItemActionImageComponent action, Entity entity) {
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

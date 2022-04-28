package com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


public class EntitySelectButton extends ImageButton {
   Entity entity;

    public EntitySelectButton(Skin skin, Entity entity) {
        super(skin);
        this.entity = entity;
    }

    public EntitySelectButton(Skin skin, String styleName, Entity entity) {
        super(skin, styleName);
        this.entity = entity;
    }

    public EntitySelectButton(ImageButtonStyle style, Entity entity) {
        super(style);
        this.entity = entity;
    }

    public EntitySelectButton(Drawable imageUp, Entity entity) {
        super(imageUp);
        this.entity = entity;
    }

    public EntitySelectButton(Drawable imageUp, Drawable imageDown, Entity entity) {
        super(imageUp, imageDown);
        this.entity = entity;
    }

    public EntitySelectButton(Drawable imageUp, Drawable imageDown, Drawable imageChecked, Entity entity) {
        super(imageUp, imageDown, imageChecked);
        this.entity = entity;
    }
}

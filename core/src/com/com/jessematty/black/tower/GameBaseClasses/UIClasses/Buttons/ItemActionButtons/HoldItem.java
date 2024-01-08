package com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.ItemActionButtons;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Character.PlayerItemFunctions;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class HoldItem extends ZRPGItemActionButton {
    public HoldItem(String text, Skin skin, String styleName, Entity item, ZRPGCharacter zrpgCharacter, MapDraw mapDraw, String handName) {
        super(text, skin, styleName, item, zrpgCharacter, mapDraw);
        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                PlayerItemFunctions.addItemToHandFromPack(zrpgCharacter, item, mapDraw.getWorld(),  handName);
                return false;

            }
        });
    }




}

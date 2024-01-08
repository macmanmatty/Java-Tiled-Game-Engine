package com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.ItemActionButtons;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.World;


public class ZRPGItemActionButton extends TextButton {
        private Entity item;
        private ZRPGCharacter zrpgCharacter;
        private MapDraw mapDraw;

    public ZRPGItemActionButton(String text, Skin skin, String styleName, Entity item, ZRPGCharacter zrpgCharacter, MapDraw mapDraw) {
        super(text, skin, styleName);
        this.item = item;
        this.zrpgCharacter = zrpgCharacter;
        this.mapDraw = mapDraw;
    }
}

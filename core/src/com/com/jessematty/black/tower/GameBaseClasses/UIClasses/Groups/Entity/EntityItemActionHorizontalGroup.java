package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups.Entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.ItemActionButtons.HoldItem;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.ItemActionButtons.ZRPGItemActionButton;
import com.jessematty.black.tower.GameBaseClasses.Utilities.ZRPGCharacterUtilities;

/**
 * ui class that extends the libGDX HorizontalGroup
 * that contains and entities name, image ( if it has one) and button to pick one
 */
public   class EntityItemActionHorizontalGroup extends HorizontalGroup {
    /**
     * libGDX skin
     */
    private Skin skin;
    /**
     * the ashely  Entity object
     */
    private Entity item;

    /**
     * game assets object used to retrieve an entities  image
     */
    private GameAssets gameAssets;
    /**
     * the ZRPGCharacter
     */

    private ZRPGCharacter zrpgCharacter;


    private Array<ZRPGItemActionButton> buttons= new Array<>();



    public EntityItemActionHorizontalGroup(ZRPGCharacter zrpgCharacter, GameAssets gameAssets, Skin skin, Entity item) {
        this.skin = skin;
        this.item = item;
        this.gameAssets=gameAssets;
        this.zrpgCharacter=zrpgCharacter;
        makeGroup();
    }

    /**
     *  makes the horizontal group
     */
    private void makeGroup(){
        getButtons();
        for(Button button: buttons) {
            addActor(button);
        }
        space(5);
    }

    private void getButtons() {
        Holder[] holders= ZRPGCharacterUtilities.getHandHolders(zrpgCharacter, gameAssets.getWorld());
        if(holders[0].getItemToHoldId()==null || holders[0].getItemToHoldId().isEmpty()){
            buttons.add(new HoldItem("Hold Left Hand" , skin, "default", item, zrpgCharacter, gameAssets.getMapDraw(), "leftHand"));

        }
        if(holders[1].getItemToHoldId()==null || holders[1].getItemToHoldId().isEmpty()){
            buttons.add(new HoldItem("Hold Right Hand", skin, "default", item, zrpgCharacter, gameAssets.getMapDraw(), "rightHand"));

        }



    }

    public Skin getSkin() {
        return skin;
    }
    public Entity getItem() {
        return item;
    }
}

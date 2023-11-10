package com.jessematty.black.tower.GameBaseClasses.UIClasses;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.EntitySelectButton;

import java.util.ArrayList;

public class ItemSlots {

    int slots;
ArrayList<EntitySelectButton> buttons= new ArrayList<EntitySelectButton>();
ArrayList<ImageButton> abilitiesButtons= new ArrayList<ImageButton>(10);

HorizontalGroup itemGroup=new HorizontalGroup();
    HorizontalGroup abilityGroup=new HorizontalGroup();
    ZRPGCharacter fighter;

GameAssets assetts;

    public ItemSlots(int slots, ZRPGCharacter fighter) { // creates  slots of empty items that you can addCopiedItem items too
       // this.assetts=fighter.getAssetts();
        this.slots = slots;




    }




/*
    public void setFighter(Player fighter) {
        this.fighter = fighter;
        for (int count=0; count<slots; count++){


            buttons.addEntity(new ItemButton(null, assetts.getAtlasRegionByName("empty", "assetts.atlas"), fighter));
            itemGroup.addActor(buttons.get(count));

        }
        int size=abilites.size();
        for (int count=0; count<size; count++){

            abilitiesButtons.addEntity(new AbilityButton(abilites.get(count),  fighter));
            abilityGroup.addActor(abilitiesButtons.get(count));

        }



    }
    public VerticalGroup getItemSlots(){
        VerticalGroup vGroup= new VerticalGroup();
        vGroup.addActor(itemGroup);
        vGroup.addActor(abilityGroup);
        return vGroup;


    }

*/

}

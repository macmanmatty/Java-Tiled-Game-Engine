package com.jessematty.black.tower.Components.Containers;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

/**
 * used to hold an array of packs
 * a player / character may have
 */
public class CharacterPacksComponent implements Component {
   private Array<String>  packEntityIds= new Array<>();

    public Array<String> getPackEntityIds() {
        return packEntityIds;
    }

}

package com.jessematty.black.tower.Generators.Components.StringStats;

import com.badlogic.gdx.utils.Array;

public class StringStatDTO {
    /**
     * the name of the stat
     */
   private  String name="stat";
    /**
     * whether or not the stat is displayable in UI windows
     */
    private boolean displayable;

    /**
      * whether or not the stat is removable from the entity
     */
    private boolean removable;

    /**
     * the groups the stat can be changed by
     */

    protected Array<String> changeGroups= new Array<String>();

    /**
     *  this locks changing of the state if true this stat cant be changed
     */
    protected boolean unchangeable;

}

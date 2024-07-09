package com.jessematty.black.tower.Generators.Components.BooleanStats;

import com.badlogic.gdx.utils.Array;

public class BooleanStatDTO {
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


    /**
     *  if true this stat will be given a random of either true or false
     */
    private  boolean randomValue=true;

    /**
     * the stats value
     */

    private boolean value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDisplayable() {
        return displayable;
    }

    public void setDisplayable(boolean displayable) {
        this.displayable = displayable;
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public Array<String> getChangeGroups() {
        return changeGroups;
    }

    public void setChangeGroups(Array<String> changeGroups) {
        this.changeGroups = changeGroups;
    }

    public boolean isUnchangeable() {
        return unchangeable;
    }

    public void setUnchangeable(boolean unchangeable) {
        this.unchangeable = unchangeable;
    }

    public boolean isRandomValue() {
        return randomValue;
    }

    public void setRandomValue(boolean randomValue) {
        this.randomValue = randomValue;
    }

    public Boolean getRandomValue() {
        return randomValue;
    }

    public void setRandomValue(Boolean randomValue) {
        this.randomValue = randomValue;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}

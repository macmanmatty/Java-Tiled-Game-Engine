package com.jessematty.black.tower.Components.Stats;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Interfaces.Copyable;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Labels.NameEditableLabel.Nameable;
import java.util.Objects;

/**
 *  libGDX Ashley Component class that all other game stats extend
 * is Copyable  so you can  deep copy  stats
 *
 */
public  abstract class  Stat   implements Component , Copyable<Stat>, Nameable{
    /**
     * whether or the stat is displayable in the UI
     */
    protected    boolean displayable=true;
    /**
     * // whether or not the stat is combined during crafting
     */
    protected  boolean combinable=true;
    /**
     * groups this stat can be changed by in change stat system
     * if the changing stat is not this list of groups
     * it won't be changed
     * if this list is empty any stat can change this  stat
     */
    protected Array<String> changeGroups= new Array<String>();
    /**
     * the name of the stat
     */
    protected  String name;
    
    /**
     * whether or not the stat can be removed from entity
     */
    protected boolean removable;
    /**
     * windows which should display this stat
     */
    protected Array<String> displayGroups= new Array();
    /**
     *  this locks changing of the state if true this stat cant be changed
     */
    protected boolean unchangeable;
    public Stat() {
    }
    public Stat(Stat other) {
        this.displayable = other.displayable;
        this.combinable = other.combinable;
        this.changeGroups = new Array<>(other.changeGroups);
        this.name = other.name;
        this.removable = other.removable;
        this.displayGroups= new Array<>(other.displayGroups);
        this.unchangeable=other.unchangeable;
    }



    public Stat(boolean displayable, String name) {
        this.displayable = displayable;
        this.name = name;
    }
    public abstract String getStatAsString();
    
    public Stat(String name) {
        this.name = name;
    }
    public boolean isDisplayable() {
        return displayable;
    }
    public void setDisplayable(boolean displayable) {
        this.displayable = displayable;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stat)) return false;
        Stat stat = (Stat) o;
        return Objects.equals(name, stat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Array<String> getChangeGroups() {
        return changeGroups;
    }
    public boolean isCombinable() {
        return combinable;
    }
    public void setCombinable(boolean combinable) {
        this.combinable = combinable;
    }
    
    public boolean isRemovable() {
        return removable;
    }
    public void setRemovable(boolean removable) {
        this.removable = removable;
    }
    public Array<String> getDisplayGroups() {
        return displayGroups;
    }
    public boolean isUnchangeable() {
        return unchangeable;
    }
    public void setUnchangeable(boolean unchangeable) {
        this.unchangeable = unchangeable;
    }
}

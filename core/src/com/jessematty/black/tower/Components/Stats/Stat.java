package com.jessematty.black.tower.Components.Stats;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Copyable;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Labels.NameEditableLabel.Nameable;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists.BasicLists.Listable;

import java.util.Objects;

public  abstract class  Stat   implements Component , Copyable<Stat>, Nameable{
    protected    boolean displayable=true;
    protected     boolean checkForEquality;
    protected  boolean combinable=true; // whether or not the stat is combined during crafting
    protected Array<String> changeGroups= new Array<String>();
    protected  String name;
    protected float  visibleDistance=1;
    protected  float smellDistance=1;
    protected boolean removable;
    protected Array<String> displayGroups= new Array();
    protected boolean changeable;
    public Stat() {
    }

    public Stat(Stat other) {
        this.displayable = other.displayable;
        this.checkForEquality = other.checkForEquality;
        this.combinable = other.combinable;
        this.changeGroups = new Array<>(other.changeGroups);
        this.name = other.name;
        this.visibleDistance = other.visibleDistance;
        this.smellDistance = other.smellDistance;
        this.removable = other.removable;
        this.displayGroups= new Array<>(other.displayGroups);

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

    public boolean isCheckForEquality() {
        return checkForEquality;
    }

    public void setCheckForEquality(boolean checkForEquality) {
        this.checkForEquality = checkForEquality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(this.getClass().equals(o.getClass())) ) {
            return false;
        }
        Stat stat = (Stat) o;
        return name.equals(stat.name);
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

    public float getVisibleDistance() {
        return visibleDistance;
    }

    public void setVisibleDistance(float visibleDistance) {
        this.visibleDistance = visibleDistance;
    }

    public float getSmellDistance() {
        return smellDistance;
    }

    public void setSmellDistance(float smellDistance) {
        this.smellDistance = smellDistance;
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

    public boolean isChangeable() {
        return changeable;
    }

    public void setChangeable(boolean changeable) {
        this.changeable = changeable;
    }


}

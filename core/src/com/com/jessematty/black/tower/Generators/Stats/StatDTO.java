package com.jessematty.black.tower.Generators.Stats;


import com.badlogic.gdx.utils.Array;

public class StatDTO {
    /**
     * the kind stat is is String Boolean or Numeric
     */
    String kind;
    /**
     * the name of the stat
     */
    String name;
    /**
     * if that stat is  a numeric stat  the value
     */
    double value;
    /**
     * if that stat is  a numeric stat  the max value
     */
    double maxValue;
    /**
     * if the stat is  a numeric stat  the min value;
     */
    double minValue;

    /**
     * if that stat is string stat  the text of stat
     */
    String stringStat;

    /**
     * if that stat is a boolean stat the boolean value
     */
    boolean booleanStat;

    /**
     * is the stat displayable in UI  game windows
     */

    boolean displayable;

    /**
     * can tha stat be removed from the  entity
     */

    boolean removable;

    /**
     * can the stat vale be changed once it is added to the entity
     */

    boolean unchangeable;

    /**
     * if the stat is numeric should be display a stat bar
     */

    boolean hasStatBar;

    /**
     * if the stat is numeric is the entity
     * destroyed when it reaches zero
     */

    boolean killWhenZero;

    /**
       if true a random value will be  created for the stat
     each time the stat is created
     */
    boolean randomValue;
    /**
     *  if the stat the  is numeric  a random min value will be given
     *  each time the stat is created
     */

    boolean randomMin;
    /**
     *  if the stat the  is numeric  a random man value will be given
     *  each time the stat is created
     */
    boolean randomMax;



    Array<String> groups= new Array<>();

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public String getStringStat() {
        return stringStat;
    }

    public void setStringStat(String stringStat) {
        this.stringStat = stringStat;
    }

    public boolean isBooleanStat() {
        return booleanStat;
    }

    public void setBooleanStat(boolean booleanStat) {
        this.booleanStat = booleanStat;
    }

    public Array<String> getGroups() {
        return groups;
    }

    public void setGroups(Array<String> groups) {
        this.groups = groups;
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

    public boolean isUnchangeable() {
        return unchangeable;
    }

    public void setUnchangeable(boolean unchangeable) {
        this.unchangeable = unchangeable;
    }

    public boolean isHasStatBar() {
        return hasStatBar;
    }

    public void setHasStatBar(boolean hasStatBar) {
        this.hasStatBar = hasStatBar;
    }

    public boolean isKillWhenZero() {
        return killWhenZero;
    }

    public void setKillWhenZero(boolean killWhenZero) {
        this.killWhenZero = killWhenZero;
    }
}

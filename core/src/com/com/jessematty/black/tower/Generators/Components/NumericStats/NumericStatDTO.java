package com.jessematty.black.tower.Generators.Components.NumericStats;

import com.badlogic.gdx.utils.Array;

/**
 * dto class for numeric stat @see NumericStat.class
 */
public class NumericStatDTO {
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

    private Array<String> changeGroups= new Array<String>();

    /**
     *  this locks changing of the state if true this stat cant be changed
     */
    private boolean unchangeable;

    /**
     *  if true this stat will be given a random value between
     *  the random min and random max
     */
    private  boolean randomValue=true;
    /**
     *  if true this stat will be given a random value between
     *  the stats mi and max values as opposed to the random min and max
     */
    private  boolean useStatMinAndMax;

    /**
     * the value of the stat
     */
    private double value=0;

    /**
     * the  minimum  value the stat can be set to
     */
    private double minValue;

    /**
     * the  maximum value the stat can be set to
     */
    private double maxValue=0;

    /**
     * the  minimum  value  that the random value  of te stat can be
     */
    private double randomMinValue;

    /**
     * the  maximum value the the random value  stat can be set to
     */
    private double randomMaxValue=0;

    /**  if this true this  stat is watched along with any other stats on the entity
     *  that has with this set to true
            *     and when they reach zero the entity  Die flag Component is added
     */
    private  boolean killWhenZero;

    /**
     * whether or not to display the min max values in the UI
     * for NumericStat based Horizontal Groups
     */
    private   boolean displayMinAndMax =true;

    /**
     * whether or not this stat will have  have a stat bar in ui windows
     */
    private  boolean hasStatBar=true;

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

    public boolean isUseStatMinAndMax() {
        return useStatMinAndMax;
    }

    public void setUseStatMinAndMax(boolean useStatMinAndMax) {
        this.useStatMinAndMax = useStatMinAndMax;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getRandomMinValue() {
        return randomMinValue;
    }

    public void setRandomMinValue(double randomMinValue) {
        this.randomMinValue = randomMinValue;
    }

    public double getRandomMaxValue() {
        return randomMaxValue;
    }

    public void setRandomMaxValue(double randomMaxValue) {
        this.randomMaxValue = randomMaxValue;
    }

    public boolean isKillWhenZero() {
        return killWhenZero;
    }

    public void setKillWhenZero(boolean killWhenZero) {
        this.killWhenZero = killWhenZero;
    }

    public boolean isDisplayMinAndMax() {
        return displayMinAndMax;
    }

    public void setDisplayMinAndMax(boolean displayMinAndMax) {
        this.displayMinAndMax = displayMinAndMax;
    }

    public boolean isHasStatBar() {
        return hasStatBar;
    }

    public void setHasStatBar(boolean hasStatBar) {
        this.hasStatBar = hasStatBar;
    }
}

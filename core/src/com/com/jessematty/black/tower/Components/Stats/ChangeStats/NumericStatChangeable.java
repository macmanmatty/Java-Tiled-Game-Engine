package com.jessematty.black.tower.Components.Stats.ChangeStats;

import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.NumericStat;
/**
 * class the represents a numeric stat that changes  a numeric stat with same name
 * it extends the numeric stat fo the same name and entity that has stats that change other stats
 * will have  a  NumericStatsChangeable Component which contains an array of these  changeable stats.
 * some event like collision
 *
 */
public   class NumericStatChangeable extends NumericStat {// class for changing a numeric stat with the same name
    /**
     *  how long the change stays for 0 or less means permanent change
     */
    protected   int amountOfTimeToChangeFor;
    /**
     * what actions this stat changes  the other stat on like collision , touch, inView, onHera etc.
     */
    protected Array<String> actionsToChangeOn = new Array<String>();
    /**
     * // if this flag is the change to the other state  will occur randomly between the min and min and max values for this stat
     */
    protected boolean randomChange;
    public NumericStatChangeable(boolean displayable, String name, double value, int amountOfTimeToChangeFor) {
        super(displayable, name, value);
        this.value = value;
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
    }
    public NumericStatChangeable(boolean displayable, String name, double value, double minValue, double maxValue, int amountOfTimeToChangeFor) {
        super(displayable, name, value, minValue, maxValue);
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
    }
    public NumericStatChangeable(String name) {
        super(name);
    }
    public NumericStatChangeable(boolean displayable, String name, double value, double minValue, double maxValue) {
        super(displayable, name, value, minValue, maxValue);
    }
    public NumericStatChangeable() {
    }
    public NumericStatChangeable(NumericStat other) {
        super(other);
        this.value = other.getDoubleValue();
        this.minValue = other.getMinValue();
        this.maxValue = other.getMaxValue();
    }
    /**
     * copy constructor
     * @param  other the stat to copy
     */
    public NumericStatChangeable(NumericStatChangeable other) {
        super(other);
        this.amountOfTimeToChangeFor = other.amountOfTimeToChangeFor;
        this.randomChange = other.randomChange;
        this.value = other.value;
        this.minValue = other.minValue;
        this.maxValue = other.maxValue;
        this.amountOfTimeToChangeFor = other.amountOfTimeToChangeFor;
        this.actionsToChangeOn=new Array<>(other.actionsToChangeOn);
    }
    public void setAmountOfTimeToChangeFor(int amountOfTimeToChangeFor) {
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
    }
    public int getAmountOfTimeToChangeFor() {
        return amountOfTimeToChangeFor;
    }
 
    public Array<String> getActionsToChangeOn() {
        return actionsToChangeOn;
    }
    public void addValues(NumericStatChangeable stat) { // adds another changeable numeric stat using the highest value of time to change for of either stat
        super.addValues(stat);
        this.amountOfTimeToChangeFor = Math.max(amountOfTimeToChangeFor ,stat.getAmountOfTimeToChangeFor());
        this.actionsToChangeOn.addAll(stat.getActionsToChangeOn());
        if(stat.isRandomChange()){
            randomChange=stat.randomChange;
        }
    }
    public void subtractValues(NumericStatChangeable stat) {
        super.subtractValues(stat);
        this.amountOfTimeToChangeFor = Math.min(amountOfTimeToChangeFor ,stat.getAmountOfTimeToChangeFor());
        this.actionsToChangeOn.removeAll(stat.getActionsToChangeOn(), false);
    }
    public boolean isRandomChange() {
        return randomChange;
    }
    public void setRandomChange(boolean randomChange) {
        this.randomChange = randomChange;
    }
  
}

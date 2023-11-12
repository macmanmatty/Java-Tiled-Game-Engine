package com.jessematty.black.tower.Components.Stats;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.ChangeStats.SelfChangableNumericStatChangeable;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.StatBar;
/**
 * class that represents a number stat in the game
 */
public   class NumericStat extends Stat {
    /**
     * the current value of the stat
     */
    protected   double value; // the current stat value
    /**
     * the minimum value for the stat
     */
    protected    double minValue;
    /**
     * the maximum value for the stat
     */
    protected   double maxValue;
    /**
     *  if this true this  stat is watched along with any other stats on the entity
     *  that has with this set to true
     *     and when they reach zero the entity  Die flag Component is added
     */
    protected boolean killWhenZero;
    /**
     * whether or not to display the min max values in the UI
     * for NumericStat based Horizontal Groups
     */
    protected    boolean displayMinAndMax =true;
    /**
     * linked stats to change
     * these will change when this stat has changed
     */
    protected Array<SelfChangableNumericStatChangeable> linkedStatsToChange = new Array<>();
    /**
     * GUI bar to show the stat a bar in the UI
     */
     transient protected  StatBar statBar ;

    /**
     * whether or not this stat will have  have a stat bar
     */
    public boolean hasStatBar=true;
    public NumericStat(boolean displayable, String name, double value) {
        super(displayable, name);
        this.value = value;
    }
    public NumericStat(boolean displayable, String name, double value, double minValue, double maxValue) {
        super(displayable, name);
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    public NumericStat(GameAssets assets, boolean displayable, String name, double value, double minValue, double maxValue) {
        super(displayable, name);
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
        if(hasStatBar) {
            createStatBar(assets);
        }
    }
    public NumericStat(String name) {
        super(name);
    }
    public NumericStat() {
    }
    @Override
    public String getStatAsString() {
        if(displayMinAndMax =true) {
            return name + ": " + value + " Min: " + minValue + "Max: " + maxValue;
        }
        else{
            return name + ": " + value;
        }
    }
    /**
     * copy constructor
     * @param other
     */
    public NumericStat(NumericStat other) {
        super(other);
        this.value = other.value;
        this.minValue = other.minValue;
        this.maxValue = other.maxValue;
        this.killWhenZero = other.killWhenZero;
        this.displayMinAndMax=other.displayMinAndMax;
        int size=linkedStatsToChange.size;
        for(int count=0; count<size; count++) {
            linkedStatsToChange.add(new SelfChangableNumericStatChangeable(other.linkedStatsToChange.get(count)));
        }
            this.statBar = new StatBar(other.statBar);
    }
    public double getDoubleValue() {
        return value;
    }
    /**
     * set the value fo the stat
     * if the value is greater than the max value for the stat
     * sets it to the max value
     * likewise for the min value
     * 
     * @param value the numeric  value to set the stat to
     */
    public void setValue(double value) {
        if(value>maxValue && maxValue>0){
            value=maxValue;
        }
        else if(value<minValue){
            value=minValue;
        }
        this.value = value;
    }
    public float getFloatValue() {
            return (float)value;
}
    public int getIntValue() {
        return (int)value;
    }
    public long getLongValue() {
        return (long)value;
    }
    public double getMinValue() {
        return minValue;
    }
    public int  getMinIntValue() {
        return (int)minValue;
    }
    /**
     * sets the min value for the stat
     * if it is greater than max value sets it to the max value
     * @param minValue
     */
    public void setMinValue(double minValue) {
        if(minValue>maxValue){
            minValue=maxValue;
        }
        this.minValue = minValue;
    }
    public double getMaxValue() {
        return maxValue;
    }
    public int  getMaxIntValue() {
        return (int)maxValue;
    }
    /**
     * sets the max value for the stat
     * if it is  less than min value sets it to the min value
     * @param maxValue
     */
    public void setMaxValue(double maxValue) {
        if(maxValue<minValue){
            maxValue=minValue;
        }
        this.maxValue = maxValue;
    }
    /**
     * adds the specified values to the stat min, max,, and current value
     * @param value 
     * @param min
     * @param max
     */
    public void addValues( double value, double min, double max){
        this.value=this.value+value;
        minValue=minValue+min;
        maxValue=maxValue+max;
        if(statBar!=null){
            statBar.update();
        }
    }
    public void addValue(double value){
        addValues(value, 0, 0);
    }
    public void subtractValue(double value){
        addValues(-value, 0, 0);
    }
    public void addValues( NumericStat stat){
       addValues(stat.getDoubleValue(), stat.getMinValue(), stat.getMaxValue());
    }
    // randomly adds
    public  void addValuesRandom(NumericStat numericStat, float multiplier){
        double value = RandomNumbers.getRandomNumber(getMinIntValue(),getMaxIntValue() )*multiplier;
        numericStat.addValues(value, 0, 0);
    }
    public  void subtractValuesRandom(NumericStat numericStat, float multiplier){
        double value =RandomNumbers.getRandomNumber(getMinIntValue(),getMaxIntValue() )*multiplier;
        numericStat.addValues(-value, 0, 0);
    }
    @Override
    public String toString() {
        if(displayMinAndMax =true) {
            return name + ": " + value + " Min: " + minValue + "Max: " + maxValue;
        }
        else{
            return name + ": " + value;
        }
    }
    @Override
    public Stat makeCopy() {
        return new NumericStat(this);
    }
    public StatBar getStatBar() {
        return statBar;
    }
    public StatBar createStatBar(GameAssets assets) {
     statBar =new StatBar(this, assets);
        return statBar;
    }
    public boolean isDisplayMinAndMax() {
        return displayMinAndMax;
    }
    public void setDisplayMinAndMax(boolean displayMinAndMax) {
        this.displayMinAndMax = displayMinAndMax;
    }
    public boolean isKillWhenZero() {
        return killWhenZero;
    }
    public void setKillWhenZero(boolean killWhenZero) {
        this.killWhenZero = killWhenZero;
    }
    public void subtractValues(NumericStat stat) {
        subtractValues(stat.getDoubleValue(), stat.getMinValue(), stat.getMaxValue());
    }
    public void subtractValues(double value, double min, double max) {
       addValues(-value, -min, -max);
    }
    public Array<SelfChangableNumericStatChangeable> getLinkedStatsToChange() {
        return linkedStatsToChange;
    }

    public boolean isHasStatBar() {
        return hasStatBar;
    }

    public void setHasStatBar(boolean hasStatBar) {
        this.hasStatBar = hasStatBar;
    }
}

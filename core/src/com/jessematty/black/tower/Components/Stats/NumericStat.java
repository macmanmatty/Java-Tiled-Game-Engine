package com.jessematty.black.tower.Components.Stats;

import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.ChangeStats.SelfChangableNumericStatChangeable;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.StatBar;

public   class NumericStat extends Stat {
    protected   double value; // the current stat value
    protected    double minValue;
    protected   double maxValue;
    protected boolean killWhenZero;// if this true this  stat is watch ed along with anyother stats the entity has with this checked
    // and when they reach zero the entity is killed
    protected    boolean displayMinAndMax =true;
    protected Array<SelfChangableNumericStatChangeable> linkedStatsToChange = new Array<>(); // stats that change when this stat chages
     transient protected  StatBar statBar ; // gui bar representation  of the stat

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
            createStatBar(assets);
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
    public void setMaxValue(double maxValue) {
        if(maxValue<minValue){
            maxValue=minValue;
        }
        this.maxValue = maxValue;
    }
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
    public void createStatBar(GameAssets assets) {
        statBar =new StatBar(this, assets);
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




}

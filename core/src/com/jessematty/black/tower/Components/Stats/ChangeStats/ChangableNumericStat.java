package com.jessematty.black.tower.Components.Stats.ChangeStats;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.Stat;

public   class ChangableNumericStat extends NumericStat {// class for changing a numeric stat with the same name
    protected   int amountOfTimeToChangeFor; // how long the change stays for 0 or less means perminante  change
    protected Array<String> actionsToChangeOn = new Array<String>(); // what actions will case this change to applied it touching, ingesting, penetrating
    protected boolean randomChange; // if this flag is the change will occur ramdomly between the min and min and max values for this stat
    public ChangableNumericStat(boolean displayable, String name, double value, int amountOfTimeToChangeFor) {
        super(displayable, name, value);
        this.value = value;
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
    }
    public ChangableNumericStat(boolean displayable, String name, double value, double minValue, double maxValue, int amountOfTimeToChangeFor) {
        super(displayable, name, value, minValue, maxValue);
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;

    }

    public ChangableNumericStat(String name) {
        super(name);
    }

    public ChangableNumericStat(boolean displayable, String name, double value, double minValue, double maxValue) {
        super(displayable, name, value, minValue, maxValue);
    }
    public ChangableNumericStat() {
    }
    public ChangableNumericStat(NumericStat other) {
        super(other);
        this.value = other.getDoubleValue();
        this.minValue = other.getMinValue();
        this.maxValue = other.getMaxValue();
        this.canBeNegative = other.isCanBeNegative();

    }


    public ChangableNumericStat(ChangableNumericStat other) {
        super(other);
        this.amountOfTimeToChangeFor = other.amountOfTimeToChangeFor;
        this.randomChange = other.randomChange;

        this.value = other.value;
        this.minValue = other.minValue;
        this.maxValue = other.maxValue;
        this.canBeNegative = other.canBeNegative;
        this.amountOfTimeToChangeFor = other.amountOfTimeToChangeFor;
        this.actionsToChangeOn=new Array<>(other.actionsToChangeOn);


    }

    public void setAmountOfTimeToChangeFor(int amountOfTimeToChangeFor) {
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
    }
    public int getAmountOfTimeToChangeFor() {
        return amountOfTimeToChangeFor;
    }
    @Override
    public Stat makeCopy() {
        return new ChangableNumericStat(this);
    }
    public Array<String> getActionsToChangeOn() {
        return actionsToChangeOn;
    }
    public void addValues(ChangableNumericStat stat) { // adds another changeable numeric stat using the highest value of time to change for of either stat
        super.addValues(stat);
        this.amountOfTimeToChangeFor = Math.max(amountOfTimeToChangeFor ,stat.getAmountOfTimeToChangeFor());
        this.actionsToChangeOn.addAll(stat.getActionsToChangeOn());
        if(stat.isRandomChange()){
            randomChange=stat.randomChange;
        }
    }
    public void subtractValues(ChangableNumericStat stat) {
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

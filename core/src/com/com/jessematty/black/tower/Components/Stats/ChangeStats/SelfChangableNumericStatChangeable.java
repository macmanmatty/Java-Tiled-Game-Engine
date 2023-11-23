package com.jessematty.black.tower.Components.Stats.ChangeStats;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Other.CompareMode;
import com.jessematty.black.tower.Components.Stats.FollowingNumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.Stat;
public   class SelfChangableNumericStatChangeable extends NumericStatChangeable {
    private double changePercent;
    protected  boolean changesWithTime; // flag for whether or not the  base numeric stat changes by itself with time.
    protected Array<com.jessematty.black.tower.Components.Stats.FollowingNumericStat> statsToFollow= new Array<>();
    protected int counter;
    public SelfChangableNumericStatChangeable(boolean displayable, String name, double value, int amountOfTimeToChangeFor) {
        super(displayable, name, value, amountOfTimeToChangeFor);
    }
    public SelfChangableNumericStatChangeable(boolean displayable, String name, double value, double minValue, double maxValue, int amountOfTimeToChangeFor) {
        super(displayable, name, value, minValue, maxValue, amountOfTimeToChangeFor);
    }
    public SelfChangableNumericStatChangeable(boolean displayable, String name, double value, double minValue, double maxValue) {
        super(displayable, name, value, minValue, maxValue);
    }
    public SelfChangableNumericStatChangeable() {
    }
    public SelfChangableNumericStatChangeable(String name) {
        super(name);
    }
    public SelfChangableNumericStatChangeable(SelfChangableNumericStatChangeable other) {
        super(other);
        this.value = other.value;
        this.minValue = other.minValue;
        this.maxValue = other.maxValue;
        this.amountOfTimeToChangeFor = other.amountOfTimeToChangeFor;
        this.changePercent =other.changePercent;
        int size=other.statsToFollow.size;
        for(int count=0; count<size; count++) {
            this.statsToFollow.add(new com.jessematty.black.tower.Components.Stats.FollowingNumericStat(other.statsToFollow.get(count)));
        }
    }
    public SelfChangableNumericStatChangeable(NumericStatChangeable other) {
        super(other);
    }
    public void setAmountOfTimeToChangeFor(int amountOfTimeToChangeFor) {
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
    }
    public int getAmountOfTimeToChangeFor() {
        return amountOfTimeToChangeFor;
    }
    public void addValues(SelfChangableNumericStatChangeable stat) {
        super.addValues(stat);
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor +stat.getAmountOfTimeToChangeFor();
    }
    public boolean isRandomChange() {
        return randomChange;
    }
    public void setRandomChange(boolean randomChange) {
        this.randomChange = randomChange;
    }
   
   
    public double getChangePercent() {
        return changePercent;
    }
    public void setChangePercent(double changePercent) {
        this.changePercent = changePercent;
    }
    public Array<FollowingNumericStat> getStatsToFollow() {
        return statsToFollow;
    }
    public void addStatToFollow(com.jessematty.black.tower.Components.Stats.NumericStat stat, CompareMode compareMode){
        statsToFollow.add(new com.jessematty.black.tower.Components.Stats.FollowingNumericStat(stat, compareMode));
    }
    public void removeStatToFollow(NumericStat stat){
        int size=statsToFollow.size;
        for(int count=0; count<size; count++){
            FollowingNumericStat statToFollow=statsToFollow.get(count);
            if(statToFollow.getName().equals(stat.getName())){
                statsToFollow.removeValue(statToFollow, true);
            }
        }
    }
    public boolean isChangesWithTime() {
        return changesWithTime;
    }
    public void setChangesWithTime(boolean changesWithTime) {
        this.changesWithTime = changesWithTime;
    }
    public void tick() {
        counter++;
    }
    public void resetCounter(){
        counter=0;
    }
    public int getCounter() {
        return counter;
    }
}

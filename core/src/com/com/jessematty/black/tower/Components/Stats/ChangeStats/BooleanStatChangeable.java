package com.jessematty.black.tower.Components.Stats.ChangeStats;

import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStatCombine;

import java.util.List;
public class BooleanStatChangeable extends com.jessematty.black.tower.Components.Stats.BooleanStat {
   private  int amountOfTimeToChangeFor;
   private com.jessematty.black.tower.Components.Stats.BooleanStatCombine combineMode = com.jessematty.black.tower.Components.Stats.BooleanStatCombine.AND;
   private Array<String> actionsToChangeOn = new Array<String>();
    public BooleanStatChangeable(boolean displayable, String name, boolean flag, int amountOfTimeToChangeFor) {
        super(displayable, name, flag);
        this.flag = flag;
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
    }
    public BooleanStatChangeable(boolean displayable, String name, boolean flag, com.jessematty.black.tower.Components.Stats.BooleanStatCombine booleanStatCombine, int amountOfTimeToChangeFor) {
        super(displayable, name, flag);
        this.flag = flag;
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
        this.combineMode=booleanStatCombine;
    }
    public BooleanStatChangeable(boolean displayable, String name, boolean flag, int amountOfTimeToChangeFor, com.jessematty.black.tower.Components.Stats.BooleanStatCombine combineMode, List<String> actionsToChangeOn) {
        super(displayable, name, flag);
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
        this.combineMode = combineMode;
    }
    public BooleanStatChangeable() {
    }
    public BooleanStatChangeable(BooleanStatChangeable other) {
        super(other);
        this.amountOfTimeToChangeFor = other.amountOfTimeToChangeFor;
        this.combineMode = other.combineMode;
    }
    public BooleanStatChangeable(BooleanStat other) {
        super(other);
    }
    @Override
    public String toString() {
        return name+": "+flag;
    }
    public int getAmountOfTimeToChangeFor() {
        return amountOfTimeToChangeFor;
    }
    public void setAmountOfTimeToChangeFor(int amountOfTimeToChangeFor) {
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
    }
    public com.jessematty.black.tower.Components.Stats.BooleanStatCombine getCombineMode() {
        return combineMode;
    }
    public void setCombineMode(BooleanStatCombine combineMode) {
        this.combineMode = combineMode;
    }
    public Array<String> getActionsToChangeOn() {
        return actionsToChangeOn;
    }
    public void setActionsToChangeOn(Array<String> actionsToChangeOn) {
        this.actionsToChangeOn = actionsToChangeOn;
    }
}

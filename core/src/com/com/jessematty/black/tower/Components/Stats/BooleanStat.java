package com.jessematty.black.tower.Components.Stats;

import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatChangeable;

import java.util.ArrayList;
import java.util.List;

/**
 * class that represents a true false  boolean stat
 */
public class BooleanStat extends Stat {
    /**
     * the stat aka boolean flag
     */
    protected   boolean flag;
    /**
     * list of linked  stats to change  when this stat changes
     */
    protected List<BooleanStatChangeable> linkedBooleanStats= new ArrayList<>();

    public BooleanStat(boolean displayable, String name, boolean flag) {
        super(displayable, name);
        this.flag = flag;
    }
    public BooleanStat() {
    }


    public BooleanStat(String name) {
        super(name);
    }

    public BooleanStat(BooleanStat other) {
        super(other);
        this.flag = other.flag;

    }
    public boolean getFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public void addFlag(boolean flag, BooleanStatCombine combineMode, boolean invert){ // combines aka adds two boolean flags
        if(invert==true){
            flag=!flag;

            return;


        }
        switch (combineMode){
            case AND: {
                this.flag = this.flag && flag;
                break;
            }
            case OR: {
                this.flag = this.flag || flag;
                break;
            }
            case XOR: {
                this.flag = this.flag ^ flag;
                break;
            }
            case SET: {
                this.flag = flag;
                break;
            }
        }
    }

    public void removeFlag(boolean flag) {
        flag = this.flag || flag;
    }
    @Override
    public String toString() {
        return name+": "+flag;
    }

    public List<BooleanStatChangeable> getLinkedStatsToChange() {
        return linkedBooleanStats;
    }
}

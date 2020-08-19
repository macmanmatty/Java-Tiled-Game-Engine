package com.jessematty.black.tower.Components.Stats.ChangeStats;

import com.jessematty.black.tower.Components.Stats.Stat;
import com.jessematty.black.tower.Components.Stats.StringStat;

import java.util.ArrayList;
import java.util.List;

public class ChangableStringStat extends StringStat {

   private int amountOfTimeToChangeFor;
    private List<String> actionsToChangeOn = new ArrayList<String>();
    private boolean addText;



    public ChangableStringStat(StringStat other, int amountOfTimeToChangeFor) {
        super(other);
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
    }

    public ChangableStringStat(boolean displayable, String name, String stat, int amountOfTimeToChangeFor) {
        super(displayable, name, stat);
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
    }

    public ChangableStringStat(int amountOfTimeToChangeFor) {
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
    }

    public ChangableStringStat() {
    }

       public ChangableStringStat(ChangableStringStat other) {
        super(other);
        this.amountOfTimeToChangeFor = other.amountOfTimeToChangeFor;
    }

    public ChangableStringStat(StringStat other) {
        super(other);
    }


    @Override
    public String toString() {
        return name+": "+getText();
    }


    @Override
    public Stat makeCopy() {
        return  new ChangableStringStat(this);
    }

    public List<String> getActionsToChangeOn() {
        return actionsToChangeOn;
    }

    public void setActionsToChangeOn(List<String> actionsToChangeOn) {
        this.actionsToChangeOn = actionsToChangeOn;
    }

    public void setAmountOfTimeToChangeFor(int amountOfTimeToChangeFor) {
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
    }

    public int getAmountOfTimeToChangeFor() {
        return amountOfTimeToChangeFor;
    }

    public boolean isAddText() {
        return addText;
    }

    public void setAddText(boolean addText) {
        this.addText = addText;
    }
}

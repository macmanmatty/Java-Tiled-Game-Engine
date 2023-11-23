package com.jessematty.black.tower.Components.Stats.ChangeStats;
import com.jessematty.black.tower.Components.Stats.Stat;
import com.jessematty.black.tower.Components.Stats.StringStat;
import java.util.ArrayList;
import java.util.List;
public class StringStatChangeable extends StringStat {
   private int amountOfTimeToChangeFor;
    private List<String> actionsToChangeOn = new ArrayList<String>();
    private boolean addText;
    public StringStatChangeable(StringStat other, int amountOfTimeToChangeFor) {
        super(other);
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
    }
    public StringStatChangeable(boolean displayable, String name, String stat, int amountOfTimeToChangeFor) {
        super(displayable, name, stat);
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
    }
    public StringStatChangeable(int amountOfTimeToChangeFor) {
        this.amountOfTimeToChangeFor = amountOfTimeToChangeFor;
    }
    public StringStatChangeable() {
    }
       public StringStatChangeable(StringStatChangeable other) {
        super(other);
        this.amountOfTimeToChangeFor = other.amountOfTimeToChangeFor;
    }
    public StringStatChangeable(StringStat other) {
        super(other);
    }
    @Override
    public String toString() {
        return name+": "+ getStat();
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

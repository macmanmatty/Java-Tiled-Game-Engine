package com.jessematty.black.tower.Components.Stats.ChangeStats;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.Stat;
public class TimeChangeStat {
    private  int timeToChange;
    private Stat oldStat;
    private int counter=0;
    public TimeChangeStat(Stat stat, int timeToChange) {
        this.timeToChange = timeToChange;
        this.oldStat =stat;
    }
    public void tick(){
        counter++;
    }
    public void reset(){
        counter=0;
    }
    public int getCounter() {
        return counter;
    }
    public int getTimeToChange() {
        return timeToChange;
    }
    public void setTimeToChange(int timeToChange) {
        this.timeToChange = timeToChange;
    }
    public Stat getOldStat() {
        return oldStat;
    }
}

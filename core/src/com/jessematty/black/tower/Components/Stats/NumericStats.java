package com.jessematty.black.tower.Components.Stats;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.badlogic.gdx.utils.OrderedMap;
import com.jessematty.black.tower.Components.Stats.ChangeStats.SelfChangableNumericStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangingStat;

public class NumericStats implements Component {
    protected OrderedMap<String, NumericStat> numericStats =  new OrderedMap<String, NumericStat>(); // list of number based stats
    protected Array<com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangingStat> colorChangingStats= new Array<com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangingStat>(); // stats that change the entities color
    protected Array<NumericStat> dieWhenZero= new Array<>(); // list of stats when  they reach zero destroty the entity
    protected Array<SelfChangableNumericStatChangeable> selfChangableNumericStats= new Array<>();
    private boolean statHasChanged; // flag for whether or not a stat was changed during the lst run of the loop
    public void addStat(NumericStat stat){ // adds a stat if doesn't  already exist
        if(numericStats.get(stat.getName())!=null){
            return;
        }
            numericStats.put(stat.getName(),  stat);
            if(stat instanceof ColorChangingStat){
                colorChangingStats.add((com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangingStat) stat);
            }
        if (stat instanceof SelfChangableNumericStatChangeable) {
            selfChangableNumericStats.add((SelfChangableNumericStatChangeable) stat);
        }
            if(stat.isKillWhenZero()){
                dieWhenZero.add(stat);
            }
    }
    public void addOrCombineStat(NumericStat stat){ // add a stat if  it doesn't exist  or  if does exist combines the values
        if(numericStats.get(stat.getName())!=null){
            numericStats.get(stat.getName()).addValues(stat);
        }
        else {
            numericStats.put(stat.getName(), stat);
            if (stat instanceof com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangingStat) {
                colorChangingStats.add((com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangingStat) stat);
            }
            if (stat.isKillWhenZero()) {
                dieWhenZero.add(stat);
            }
            if (stat instanceof SelfChangableNumericStatChangeable) {
                selfChangableNumericStats.add((SelfChangableNumericStatChangeable) stat);
            }
        }
    }






    public  void removeStat(  String name){
        NumericStat stat=numericStats.get(name);
            numericStats.remove(name);
        if(stat instanceof com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangingStat){
            colorChangingStats.removeValue((ColorChangingStat) stat, false);
        }
        dieWhenZero.removeValue( stat, false);
        if(stat instanceof SelfChangableNumericStatChangeable){
            selfChangableNumericStats.removeValue((SelfChangableNumericStatChangeable) stat, true);
        }
    }
    public Array<NumericStat> getStatsAsAnArray(){ // used for displaying of stats
       Array<NumericStat>  numericStatArray= new Array<NumericStat>();
       Values<NumericStat> values=numericStats.values();
       while(values.hasNext){
           numericStatArray.add(values.next());
       }
       return numericStatArray;
    }
    public NumericStat getNumericStat(String  statName){
        return numericStats.get(statName);
    }
    public OrderedMap<String, NumericStat> getNumericStats() {
        return numericStats;
    }
    public Array<ColorChangingStat> getColorChangingStats() {
        return colorChangingStats;
    }
    public Array<NumericStat> getDieWhenZero() {
        return dieWhenZero;
    }
    public boolean isStatHasChanged() {
        return statHasChanged;
    }
    public void setStatHasChanged(boolean statHasChanged) {
        this.statHasChanged = statHasChanged;
    }
    public Array<SelfChangableNumericStatChangeable> getSelfChangableNumericStats() {
        return selfChangableNumericStats;
    }
}

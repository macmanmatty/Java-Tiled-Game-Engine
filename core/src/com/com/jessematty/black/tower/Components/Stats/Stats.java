package com.jessematty.black.tower.Components.Stats;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangingStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.SelfChangableNumericStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatChangeable;
public class Stats {
    protected OrderedMap <String, BooleanStat>  booleanStats= new OrderedMap<String, com.jessematty.black.tower.Components.Stats.BooleanStat>();
    private  boolean statHasChanged;
    protected OrderedMap<String, NumericStat> numericStats =  new OrderedMap<String, NumericStat>(); // list of number based stats
    protected Array<ColorChangingStat> colorChangingStats= new Array<com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangingStat>(); // stats that change the entities color
    protected Array<NumericStat> dieWhenZero= new Array<>(); // list of stats when  they reach zero destroty the entity
    protected Array<SelfChangableNumericStatChangeable> selfChangableNumericStats= new Array<>();
    protected OrderedMap< String,  StringStat> stringStats= new OrderedMap<String, StringStat>();
    protected Array<NumericStatChangeable> numericStatChangeables = new Array<NumericStatChangeable>();
    private  Array<BooleanStatChangeable> booleanStatChangeables = new Array<BooleanStatChangeable>();
    private Array<StringStatChangeable> stringStatChangeables = new Array<StringStatChangeable>();
    public BooleanStatChangeable getBooleanStatChangeableStat(String name){
        int size= booleanStatChangeables.size;
        for(int count=0; count<size; count++){
            BooleanStatChangeable numericStatChangeable= booleanStatChangeables.get(count);
            if(numericStatChangeable.getName().equals(name)){
                return numericStatChangeable;
            }
        }
        return  null;
    }
    public NumericStatChangeable getNumericChangeableStat(String name){
        int size= numericStatChangeables.size;
        for(int count=0; count<size; count++){
            NumericStatChangeable numericStatChangeable= numericStatChangeables.get(count);
            if(numericStatChangeable.getName().equals(name)){
                return numericStatChangeable;
            }
        }
        return  null;
    }
    public Array<NumericStatChangeable> getNumericStatChangeables() {
        return numericStatChangeables;
    }
    public Array<BooleanStatChangeable> getBooleanStatChangeables() {
        return booleanStatChangeables;
    }
    public BooleanStat getBooleanStat(String statName){
        return booleanStats.get(statName);
    }
    public OrderedMap<String, BooleanStat> getBooleanStats() {
        return booleanStats;
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
    public Array<SelfChangableNumericStatChangeable> getSelfChangableNumericStats() {
        return selfChangableNumericStats;
    }
    public OrderedMap<String, StringStat> getStringStats() {
        return stringStats;
    }
    public boolean isStatHasChanged() {
        return statHasChanged;
    }
    public void setStatHasChanged(boolean statHasChanged) {
        this.statHasChanged = statHasChanged;
    }
    public void addOrCombineStat(BooleanStat booleanStat) {
        if(booleanStats.get(booleanStat.getName())!=null) {
            BooleanStat booleanStatInStats = booleanStats.get(booleanStat.getName());
            booleanStat.setFlag(booleanStat.getFlag() && booleanStat.getFlag());
        }
        else {
            booleanStats.put((booleanStat.getName()), (BooleanStat) booleanStat);
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
    public void addOrCombineStat(NumericStatChangeable changableNumericStat) {
        int size= numericStatChangeables.size;
        boolean addStat=true;
        for(int count=0; count<size; count++){
            if(numericStatChangeables.get(count).getName().equals(changableNumericStat.getName())){
                numericStatChangeables.add(changableNumericStat);
                addStat=false;
                break;
            }
        }
        if(addStat==true) {
            numericStatChangeables.add(changableNumericStat);
        }
    }
    public void addOrCombineStat(BooleanStatChangeable booleanStatChangeable) {
        int size= booleanStatChangeables.size;
        boolean addStat=true;
        for(int count=0; count<size; count++){
            if(booleanStatChangeables.get(count).getName().equals(booleanStatChangeable.getName())){
                booleanStatChangeables.add(booleanStatChangeable);
                addStat=false;
                break;
            }
        }
        if(addStat==true) {
            booleanStatChangeables.add(booleanStatChangeable);
        }
    }
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
    public void addStat(StringStat stat){
        if(stringStats.get(stat.getName())!=null){
            return;
        }
        stringStats.put(stat.getName(), stat);
    }
    public void addStat(BooleanStat stat){
        if(booleanStats.get(stat.getName())!=null){
            return;
        }
        booleanStats.put((stat.getName()), (BooleanStat)stat);
    }
    public void addStat(NumericStatChangeable changableNumericStat ){
        int size= numericStatChangeables.size;
        boolean addStat=true;
        for(int count=0; count<size; count++){
            if(numericStatChangeables.get(count).getName().equals(changableNumericStat.getName())){
                addStat=false;
                break;
            }
        }
        if(addStat==true) {
            numericStatChangeables.add(changableNumericStat);
        }
    }
    public void addStat(BooleanStatChangeable changableBooleanStat ){
        int size= booleanStatChangeables.size;
        boolean addStat=true;
        for(int count=0; count<size; count++){
            if(booleanStatChangeables.get(count).getName().equals(changableBooleanStat.getName())){
                addStat=false;
                break;
            }
        }
        if(addStat==true) {
            booleanStatChangeables.add(changableBooleanStat);
        }
    }
    public void removeStat(NumericStatChangeable stat ){
        int size= numericStatChangeables.size;
        for(int count=0; count<size; count++){
            if (numericStatChangeables.get(count).getName().equals(stat.getName())){
                numericStatChangeables.removeIndex(count);
            }
        }
    }
    public  void removeStat(  NumericStat numericStat){
        NumericStat stat=numericStats.get(numericStat.getName());
        numericStats.remove(numericStat.getName());
        if(stat instanceof com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangingStat){
            colorChangingStats.removeValue((ColorChangingStat) stat, false);
        }
        dieWhenZero.removeValue( stat, false);
        if(stat instanceof SelfChangableNumericStatChangeable){
            selfChangableNumericStats.removeValue((SelfChangableNumericStatChangeable) stat, true);
        }
    }
    public void removeStat(BooleanStatChangeable stat){
        int size= booleanStatChangeables.size;
        for(int count=0; count<size; count++){
            if (booleanStatChangeables.get(count).getName().equals(stat.getName())){
                booleanStatChangeables.removeIndex(count);
            }
        }
    }
    public Array<StringStatChangeable> getStringStatChangeables() {
        return stringStatChangeables;
    }
    public void addStatToChange(StringStatChangeable changableStringStat ){
        int size= stringStatChangeables.size;
        boolean addStat=true;
        for(int count=0; count<size; count++){
            if(stringStatChangeables.get(count).getName().equals(changableStringStat.getName())){
                addStat=false;
                break;
            }
        }
        if(addStat==true) {
            stringStatChangeables.add(changableStringStat);
        }
    }
    public void removeStatToChange(String name){
        int size= stringStatChangeables.size;
        for(int count=0; count<size; count++){
            if (stringStatChangeables.get(count).getName().equals(name)){
                stringStatChangeables.removeIndex(count);
            }
        }
    }
    public StringStatChangeable getStat(String name){
        int size= stringStatChangeables.size;
        for(int count=0; count<size; count++){
            StringStatChangeable numericStatChangeable= stringStatChangeables.get(count);
            if(numericStatChangeable.getName().equals(name)){
                return numericStatChangeable;
            }
        }
        return  null;
    }
    
    public  void removeStat(  BooleanStat stat){
        booleanStats.remove(stat.getName());
    }
    public void addOrCombineStat(StringStat stat) {
        if(stringStats.get(stat.getName())!=null){
            stat.addTextToStat(stat.getStat());
        }
        stringStats.put(stat.getName(), stat);
    }
    public  void removeStat(  String name){
        stringStats.remove(name);
    }
    public Array<Stat> getAllEntityStatsAsArray(){
        Array<Stat> stats= new Array<>();
        stats.addAll(numericStats.values().toArray());
        stats.addAll(booleanStats.values().toArray());
        stats.addAll(stringStats.values().toArray());
        return stats;
    }
    public Array<Stat> getAllStatsAsArray(){
        Array<Stat> stats= new Array<>();
        stats.addAll(numericStats.values().toArray());
        stats.addAll(booleanStats.values().toArray());
        stats.addAll(stringStats.values().toArray());
        stats.addAll(numericStatChangeables);
        stats.addAll(booleanStatChangeables);
        return stats;
    }
    public ObjectMap<String, Stat> getAllEntityStatsAsMap(){
        ObjectMap< String, Stat> stats= new OrderedMap<>();
        stats.putAll(numericStats);
        stats.putAll(booleanStats);
        stats.putAll(stringStats);
        return stats;
    }
}

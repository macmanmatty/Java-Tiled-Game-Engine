package com.jessematty.black.tower.Generators.Stats;

import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.Stat;
import com.jessematty.black.tower.Components.Stats.StringStat;

public class StatGenerator {

    public Stat generateStat(StatDTO statDTO){
        Stat stat=null;
        if(statDTO.kind.equals("numeric")){
            stat=new NumericStat();
            NumericStat numericStat= (NumericStat) stat;
            numericStat.setMinValue(statDTO.getMinValue());
            numericStat.setMaxValue(statDTO.getMaxValue());
            numericStat.setValue(statDTO.getValue());
            numericStat.setKillWhenZero(statDTO.hasStatBar);
        }
        else if(statDTO.kind.equals("boolean")){
            stat=new BooleanStat();
            BooleanStat numericStat= (BooleanStat) stat;
        }

        else{
            stat=new StringStat();
        }
        stat.setName(statDTO.getName());
        stat.setDisplayable(statDTO.displayable);
        stat.setChangeGroups(statDTO.getGroups());
        return stat;
    }
}

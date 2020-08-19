package com.jessematty.black.tower.GameBaseClasses.Entity;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;

import java.util.List;

public class CheckStats {


    public static boolean hasAllStats(List<? extends NumericStat> numericStatsList, NumericStats numericStats){

        int size=numericStatsList.size();
        for(int count=0; count<size; count++){

            NumericStat stat=numericStats.getNumericStat(numericStatsList.get(count).getName());
            if(stat==null){
                return false;

            }
            
        }

        return true;
    }

   public static  boolean hasAllStats(List<? extends BooleanStat> booleanStatsList, BooleanStats booleanStats){

        int size=booleanStatsList.size();
        for(int count=0; count<size; count++){

            BooleanStat stat=booleanStats.getBooleanStat(booleanStatsList.get(count).getName());
            if(stat==null){
                return false;

            }
        }

        return true;
    }

    public static  boolean hasAllStats(List<? extends StringStat> stringStatsList, StringStats stringStats){

        int size=stringStatsList.size();
        for(int count=0; count<size; count++){

            StringStat stat=stringStats.getStringStat(stringStatsList.get(count).getName());
            if(stat==null){
                return false;

            }
        }

        return true;
    }



    public static boolean valuesAreEqual(List<? extends NumericStat> numericStatsList, NumericStats numericStats){

        int size=numericStatsList.size();
        for(int count=0; count<size; count++){

            NumericStat stat=numericStatsList.get(count);
            NumericStat statToCheck=numericStats.getNumericStat(stat.getName());
            if(statToCheck==null){
                return false;

            }
            else{
                double statToCheckDoubleValue=statToCheck.getDoubleValue();
                double statValue=stat.getDoubleValue();
                if(statToCheckDoubleValue!=statValue){
                    return  false;
                    
                }
                
            }

        }

        return true;
    }


    public static boolean valuesAreEqualOrGreaterThan(List<? extends NumericStat> numericStatsList, NumericStats numericStats){

        int size=numericStatsList.size();
        for(int count=0; count<size; count++){

            NumericStat stat=numericStatsList.get(count);
            NumericStat statToCheck=numericStats.getNumericStat(stat.getName());
            if(statToCheck==null){
                return false;

            }
            else{
                double statToCheckDoubleValue=statToCheck.getDoubleValue();
                double statValue=stat.getDoubleValue();
                if(statToCheckDoubleValue<statValue){
                    return  false;

                }

            }

        }

        return true;
    }


    public static boolean valuesAreEqualOrLessThan(List<? extends NumericStat> numericStatsList, NumericStats numericStats){

        int size=numericStatsList.size();
        for(int count=0; count<size; count++){

            NumericStat stat=numericStatsList.get(count);
            NumericStat statToCheck=numericStats.getNumericStat(stat.getName());
            if(statToCheck==null){
                return false;

            }
            else{
                double statToCheckDoubleValue=statToCheck.getDoubleValue();
                double statValue=stat.getDoubleValue();
                if(statToCheckDoubleValue>statValue){
                    return  false;

                }

            }

        }

        return true;
    }

    public static boolean valuesAreEqual(List<? extends BooleanStat> booleanStatsList, BooleanStats booleanStats){

        int size=booleanStatsList.size();
        for(int count=0; count<size; count++){

            BooleanStat stat=booleanStatsList.get(count);
            BooleanStat statToCheck=booleanStats.getBooleanStat(stat.getName());
            if(statToCheck==null){
                return false;

            }
            else{
                boolean statToCheckValue=statToCheck.getFlag();
                boolean statValue=stat.getFlag();
                if(statToCheckValue!=statValue){
                    return  false;

                }

            }

        }

        return true;
    }



    public static boolean valuesAreEqual(List<? extends StringStat> stringStatsList, StringStats stringStats){

        int size=stringStatsList.size();
        for(int count=0; count<size; count++){

            StringStat stat=stringStatsList.get(count);
            StringStat statToCheck=stringStats.getStringStat(stat.getName());
            if(statToCheck==null){
                return false;

            }
            else{
                String statToCheckValue=statToCheck.getText();
                String statValue=stat.getText();
                if(!statToCheckValue.equals(statValue)){
                    return  false;

                }

            }

        }

        return true;
    }





}

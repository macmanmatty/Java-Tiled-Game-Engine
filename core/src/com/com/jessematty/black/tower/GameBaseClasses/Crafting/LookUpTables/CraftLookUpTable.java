package com.jessematty.black.tower.GameBaseClasses.Crafting.LookUpTables;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.Crafting.Craft;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;

public class CraftLookUpTable  extends LookUpTable{


    public CraftLookUpTable(GameComponentMapper gameComponentMapper) {
        super(gameComponentMapper);
    }

    private Array<NumericStats> numericStatsForEntitiesAddedToCreate = new Array<>();
    private Array<BooleanStats> booleanStatsForEntitiesAddedToCreate = new Array<>();
   private  Array<StringStats> stringStatsForEntitiesAddedToCreate = new Array<>();
    public Array<Entity>  lookUpCraft(Craft craft){
        float mass=0;
        float volume=0;
        Array<Entity> entities= craft.getEntities();
        int size=entities.size;
      
        for(int count=0; count<size;  count++){
            Entity entity=entities.get(count);
            PhysicalObjectComponent physicalObject=physicalObjectComponentMapper.get(entity);
            mass=mass+physicalObject.getMass();
            volume=volume+physicalObject.getVolume();
            numericStatsForEntitiesAddedToCreate.add(numericStatsComponentMapper.get(entity));
            booleanStatsForEntitiesAddedToCreate.add(booleanStatsComponentMapper.get(entity));
            stringStatsForEntitiesAddedToCreate.add(stringStatsComponentMapper.get(entity));
        }
        Array<Entity> entitiesCanMake= new Array<>();
       size= entitiesToCreate.size;
        for(int count=0; count<size; count++){
        EntityToCreate entityToCreate=entitiesToCreate.get(count);
            if(mass> entityToCreate.getMaxMassToCreate() || mass<entityToCreate.getMinMassToCreate() || volume>entityToCreate.getMaxVolumeToCreate() || volume<entityToCreate.getMinVolumeToCreate()){
                // not enough or volume to craft entity continue;
                continue;
            }
                Array<NumericStat> entityToCreatNumericStats=entityToCreate.getNumericStatsToCreate();// numeric stats required to create entity
        int numberOfNumericStatsRequiredToCreateEntity=entityToCreatNumericStats.size;
       boolean canMake=true;
        for(int count2=0; count2<numberOfNumericStatsRequiredToCreateEntity; count2++){
            NumericStat numericStat=entityToCreatNumericStats.get(count2);
            String statName=numericStat.getName();
            boolean numericStatInRange=numericStatInRange(numericStat);
            if(numericStatInRange==false){
                
                canMake=false;
                break;
            }
        }
        if(canMake==false){
            continue;
        }
            Array<BooleanStat> entityToCreateBooleanStats=entityToCreate.getBooleanStatsToCreate();
      
            int numberofBooleanStatsRequiredToCreateEntity=entityToCreateBooleanStats.size;
            for(int count2=0; count2<numberofBooleanStatsRequiredToCreateEntity; count2++) {
                BooleanStat booleanStat = entityToCreateBooleanStats.get(count2);
                boolean booleanStatInRange=booleanStatInRange(booleanStat);
                if(booleanStatInRange==false){
                    canMake=false;
                    break;
                    
                }
            }
            if(canMake==false){
                continue;
            }
            Array<StringStat> entityToCreateStringStats=entityToCreate.getStringStatsToCreate();
            int numberofStringStatsRequiredToCreateEntity=entityToCreateStringStats.size;
            for(int count2=0; count2<numberofStringStatsRequiredToCreateEntity; count2++) {
                StringStat stringStat = entityToCreateStringStats.get(count2);
                boolean statInRange=stringStatInRange(stringStat);
                if(statInRange==false){
                    canMake=false;
                    break;
                }
            }
            if(canMake==true){ // can create entity add it
               
                entitiesCanMake.add(entityToCreate.getEntity());
            }
        }
        return  entitiesCanMake;
    }

    private boolean numericStatInRange(NumericStat numericStat) {
        double statTotal = 0; // the total for the stat from the entites pass to create the entity
        String statName=numericStat.getName();
        int numberOfEntitiesCreatingNumericStats = numericStatsForEntitiesAddedToCreate.size;
        for (int count3 = 0; count3 < numberOfEntitiesCreatingNumericStats; count3++) {
            NumericStat numericStatFromEntities = numericStatsForEntitiesAddedToCreate.get(count3).getNumericStat(statName);
            // get the stat
            if (numericStat != null) {
                // add the total if stat exists
                statTotal = statTotal + numericStatFromEntities.getDoubleValue();
            }
        }
        if (statTotal < numericStat.getMinValue() || statTotal > numericStat.getMaxValue()) {
            // if stat is too high or too low to create break to outer loop
            return false;
        }
        
        return true;
        
    }
    private boolean booleanStatInRange(BooleanStat booleanStat) {
        String statName = booleanStat.getName();
        boolean flag = booleanStat.getFlag();
        int size3 = numericStatsForEntitiesAddedToCreate.size;
        for (int count3 = 0; count3 < size3; count3++) {
            BooleanStat entitiesBooleanStat = booleanStatsForEntitiesAddedToCreate.get(count3).getBooleanStat(statName);
            if (entitiesBooleanStat != null) {
                if (flag != entitiesBooleanStat.getFlag()) {
                    return false;
                    
                }
            }
        }
    return  true;
        
    
}
    private boolean stringStatInRange(StringStat stringStat) {
        String statName = stringStat.getName();
        String text=stringStat.getStat();
        int size3 = numericStatsForEntitiesAddedToCreate.size;
        for (int count3 = 0; count3 < size3; count3++) {
            StringStat entitiesStringStat = stringStatsForEntitiesAddedToCreate.get(count3).getStringStat(statName);
            if (entitiesStringStat != null) {
                if (!(text.equals(entitiesStringStat.getStat()))) {
                    return false;
                }
            }
        }
        return  true;
    }
    
}

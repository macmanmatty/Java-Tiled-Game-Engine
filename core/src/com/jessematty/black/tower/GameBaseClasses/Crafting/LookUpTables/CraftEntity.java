package com.jessematty.black.tower.GameBaseClasses.Crafting.LookUpTables;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableBooleanStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableStringStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangable;
import com.jessematty.black.tower.Components.PhysicalObject;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangable;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;

public class CraftEntity {/// class for combining multiple entities  into one
   protected ComponentMapper<PhysicalObject> physicalObjectComponentMapper;
   protected  ComponentMapper<NumericStats> numericStatsComponentMapper;
   protected ComponentMapper<BooleanStats> booleanStatsComponentMapper;
   protected  ComponentMapper<StringStats> stringStatsComponentMapper;
   protected  ComponentMapper<NumericStatsChangable> numericStatsChangableComponentMapper;
   protected ComponentMapper<BooleanStatsChangable> booleanStatsChangableComponentMapper;
   protected  ComponentMapper<StringStatsChangable> stringStatsChangableComponentMapper;
   protected GameComponentMapper gameComponentMapper;
   public CraftEntity(GameComponentMapper gameComponentMapper) {
      this.gameComponentMapper = gameComponentMapper;
      physicalObjectComponentMapper=gameComponentMapper.getPhysicalObjectComponentMapper();
      numericStatsComponentMapper=gameComponentMapper.getNumericStatsComponentMapper();
      booleanStatsComponentMapper=gameComponentMapper.getBooleanStatsComponentMapper();
      stringStatsComponentMapper=gameComponentMapper.getStringStatsComponentMapper();
      numericStatsChangableComponentMapper=gameComponentMapper.getNumericStatsChangableComponentMapper();
      booleanStatsChangableComponentMapper=gameComponentMapper.getBooleanStatsChangableComponentMapper();
      stringStatsChangableComponentMapper=gameComponentMapper.getStringStatsChangableComponentMapper();
   }
   public Entity craftEntities( Entity entityToMake, Entity... entitiesToCombine ){
      NumericStats combinedNumericStats= new NumericStats();  // the numeric stats to be created from the combined entities
      NumericStatsChangable combinedNumericStatsChangable=new NumericStatsChangable(); // the numeric stats changeable to be combined
      BooleanStats combinedBooleanStats= new BooleanStats(); // boolean stats to be combined
      //changeable stats to be combined
      BooleanStatsChangable combinedBooleanStatsChangable=new BooleanStatsChangable();
      StringStats combinedStringStats= new StringStats();
      StringStatsChangable combinedStringStatsChangable=new StringStatsChangable();

      // stats the entity to  create has
      NumericStats entityToMakeNumericStats=numericStatsComponentMapper.get(entityToMake);
     BooleanStats entityToMakeBooleanStats=booleanStatsComponentMapper.get(entityToMake);
      StringStats entityToMakeStringStats=stringStatsComponentMapper.get(entityToMake);
      NumericStatsChangable entityToMakeNumericStatsChangable=numericStatsChangableComponentMapper.get(entityToMake);
      BooleanStatsChangable entityToMakeBooleanStatsChangable=booleanStatsChangableComponentMapper.get(entityToMake);
      StringStatsChangable entityToMakeStringStatsChangable=stringStatsChangableComponentMapper.get(entityToMake);
      int size=entitiesToCombine.length;
      for(int count=0; count<size; count++){
         Entity entity=entitiesToCombine[count];
         Values<NumericStat> numericStats=numericStatsComponentMapper.get(entity).getNumericStats().values();
         while(numericStats.hasNext) {
            NumericStat numericStat=numericStats.next();
            String numericStatName=numericStat.getName();
            if(numericStat.isCombinable()) {// if stat can be combined
               combinedNumericStats.addOrCombineStat(numericStat);  // combine stat
            }
            else if(combinedNumericStats.getNumericStat(numericStatName)==null){
               NumericStat entityToMakeNumericStat=entityToMakeNumericStats.getNumericStat(numericStatName); // see if entity to create has stat
               if(entityToMakeNumericStat!=null){  // if so use that that stat
                  combinedNumericStats.addStat(entityToMakeNumericStat);
               }
               else {

                  // if entity to make doesn't have a given stat and the stat can't be combined  create a new empty one
                  NumericStat newStat = new NumericStat(numericStatName);
                  newStat.setDisplayable(numericStat.isDisplayable());
                  newStat.setKillWhenZero(numericStat.isKillWhenZero());
                  newStat.setCanBeNegative(numericStat.isCanBeNegative());
                  newStat.setCombinable(numericStat.isCombinable());
                  combinedNumericStats.addStat(newStat);
               }
               
            }
         }

         // repeat process for all other stats
         Values<StringStat> stringStats=stringStatsComponentMapper.get(entity).getStringStats().values();
         while(stringStats.hasNext) {
            StringStat stringStat=stringStats.next();
            String stringStatName=stringStat.getName();
            if(stringStat.isCombinable()) {
               combinedStringStats.addOrCombineStat(stringStat);
            }
            else if(combinedStringStats.getStringStat(stringStatName)==null){
               StringStat entityToMakeStringStat=entityToMakeStringStats.getStringStat(stringStatName);
               if(entityToMakeStringStat!=null){
                  combinedStringStats.addStat(entityToMakeStringStat);
               }
               else {
                  StringStat newStat = new StringStat(stringStatName);
                  newStat.setDisplayable(stringStat.isDisplayable());
                  newStat.setCombinable(stringStat.isCombinable());
                  combinedStringStats.addStat(newStat);
               }
            }
         }
         Values<BooleanStat> booleanStats=booleanStatsComponentMapper.get(entity).getBooleanStats().values();
         while(booleanStats.hasNext) {
            BooleanStat booleanStat=booleanStats.next();
            String booleanStatName=booleanStat.getName();
            if(booleanStat.isCombinable()) {
               combinedBooleanStats.addOrCombineStat(booleanStat);
            }
            else if(combinedBooleanStats.getBooleanStat(booleanStatName)==null){
               BooleanStat entityToMakeBooleanStat=entityToMakeBooleanStats.getBooleanStat(booleanStatName);
               if(entityToMakeBooleanStat!=null){
                  combinedBooleanStats.addStat(entityToMakeBooleanStat);
               }
               else {
                  BooleanStat newStat = new BooleanStat(booleanStatName);
                  newStat.setDisplayable(booleanStat.isDisplayable());
                  newStat.setCombinable(booleanStat.isCombinable());
                  combinedBooleanStats.addStat(newStat);
               }
            }
         }
         Array<ChangableNumericStat> numericStatsChangable=numericStatsChangableComponentMapper.get(entity).getStatsToChange();
         int numberOfChangeableNumericStats=numericStatsChangable.size;
         for(int count2=0; count2<numberOfChangeableNumericStats; count2++) {
            ChangableNumericStat changableNumericStat = numericStatsChangable.get(count2);
            String changableNumericStatName=changableNumericStat.getName();
            if (changableNumericStat.isCombinable()) {
               combinedNumericStatsChangable.addOrCombineStat(changableNumericStat);
            } else {
               ChangableNumericStat entityToMakeNumericStat=entityToMakeNumericStatsChangable.getStat(changableNumericStatName);
               if (entityToMakeNumericStat != null) {
                  combinedNumericStats.addStat(entityToMakeNumericStat);
               } else {
                  NumericStat newStat = new NumericStat(changableNumericStatName);
                  newStat.setDisplayable(changableNumericStat.isDisplayable());
                  newStat.setKillWhenZero(changableNumericStat.isKillWhenZero());
                  newStat.setCanBeNegative(changableNumericStat.isCanBeNegative());
                  newStat.setCombinable(changableNumericStat.isCombinable());
                  combinedNumericStats.addStat(newStat);
               }
            }
         
         
         }
         Array<ChangableStringStat> stringStatsChangable=stringStatsChangableComponentMapper.get(entity).getStatsToChange();
         int numberOfChangeableStringStats=stringStatsChangable.size;
         for(int count2=0; count2<numberOfChangeableStringStats; count2++) {
            ChangableStringStat changableStringStat=stringStatsChangable.get(count2);
            String changableStringStatName=changableStringStat.getName();
            if (changableStringStat.isCombinable()) {
               combinedStringStatsChangable.addOrCombineStat(changableStringStat);
            } else {
               ChangableStringStat entityToMakeStringStat=entityToMakeStringStatsChangable.getStat(changableStringStatName);
               if (entityToMakeStringStat != null) {
                  combinedStringStats.addStat(entityToMakeStringStat);
               } else {
                  StringStat newStat = new StringStat(changableStringStatName);
                  newStat.setDisplayable(changableStringStat.isDisplayable());
                  newStat.setCombinable(changableStringStat.isCombinable());
                  combinedStringStats.addStat(newStat);
               }
            }
         }
         Array<ChangableBooleanStat> booleanStatsChangable=booleanStatsChangableComponentMapper.get(entity).getStatsToChange();
         int numberOfChangeableBooleanStats=booleanStatsChangable.size;
         for(int count2=0; count2<numberOfChangeableBooleanStats; count2++) {
            ChangableBooleanStat changableBooleanStat=booleanStatsChangable.get(count2);
           String changableBooleanStatName=changableBooleanStat.getName();
            if (changableBooleanStat.isCombinable()) {
               combinedBooleanStatsChangable.addOrCombineStat(changableBooleanStat);
            } else {
               ChangableBooleanStat entityToMakeBooleanStat=entityToMakeBooleanStatsChangable.getStat(changableBooleanStatName);
               if (entityToMakeBooleanStat != null) {
                  combinedBooleanStats.addStat(entityToMakeBooleanStat);
               } else {
                  BooleanStat newStat = new BooleanStat(changableBooleanStatName);
                  newStat.setDisplayable(changableBooleanStat.isDisplayable());
                  newStat.setCombinable(changableBooleanStat.isCombinable());
                  combinedBooleanStats.addStat(newStat);
               }
            }
         }
      }
      
      return entityToMake;
   }
   }

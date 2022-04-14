package com.jessematty.black.tower.GameBaseClasses.Crafting.LookUpTables;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.StringStat;


public class EntityToCreate { // class for an entity that created through crafting
   private  Entity entity;
    private Array<NumericStat> minNumericStatsToCreate = new Array<>(); // stats requires for  crafting
   private Array< BooleanStat> booleanStatsToCreate = new Array<BooleanStat>();
    private  Array<StringStat> stringStatsToCreate = new Array<>();
    private float minMassToCreate; // min mass to create
    private float minVolumeToCreate; // min volume
    private float maxMassToCreate; // max volume to create
    private float maxVolumeToCreate;
    Array<String> creationModes= new Array<>();

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Array<NumericStat> getNumericStatsToCreate() {
        return minNumericStatsToCreate;
    }


    public Array<BooleanStat> getBooleanStatsToCreate() {
        return booleanStatsToCreate;
    }



    public Array<StringStat> getStringStatsToCreate() {
        return stringStatsToCreate;
    }


    public float getMinMassToCreate() {
        return minMassToCreate;
    }

    public void setMinMassToCreate(float minMassToCreate) {
        this.minMassToCreate = minMassToCreate;
    }

    public float getMinVolumeToCreate() {
        return minVolumeToCreate;
    }

    public void setMinVolumeToCreate(float minVolumeToCreate) {
        this.minVolumeToCreate = minVolumeToCreate;
    }

    public float getMaxMassToCreate() {
        return maxMassToCreate;
    }

    public void setMaxMassToCreate(float maxMassToCreate) {
        this.maxMassToCreate = maxMassToCreate;
    }

    public float getMaxVolumeToCreate() {
        return maxVolumeToCreate;
    }

    public void setMaxVolumeToCreate(float maxVolumeToCreate) {
        this.maxVolumeToCreate = maxVolumeToCreate;
    }
}

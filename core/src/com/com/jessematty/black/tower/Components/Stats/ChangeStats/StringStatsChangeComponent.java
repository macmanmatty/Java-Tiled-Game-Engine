package com.jessematty.black.tower.Components.Stats.ChangeStats;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class StringStatsChangeComponent implements Component  {

    private String changeAction;
    private boolean addStat;

    private  Entity changerEntity;

    public StringStatsChangeComponent(Entity changerEntity, String changeAction, boolean addStat) {
        this.changeAction = changeAction;
        this.addStat = addStat;
        this.changerEntity = changerEntity;
    }


    public String getChangeAction() {
        return changeAction;
    }

    public void setChangeAction(String changeAction) {
        this.changeAction = changeAction;
    }

    public boolean isAddStat() {
        return addStat;
    }

    public void setAddStat(boolean addStat) {
        this.addStat = addStat;
    }


    public Entity getChangerEntity() {
        return changerEntity;
    }

    public void setChangerEntity(Entity changerEntity) {
        this.changerEntity = changerEntity;
    }

}

package com.jessematty.black.tower.Components.ChangeImage;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangeImageOnStat;

import java.util.ArrayList;
import java.util.List;

public class ChangeImageOnStatsValueChanges implements Component {

    private boolean inChangeSteps;
    private  boolean removeOnceChanged;
   private  List<ChangeImageOnStat> changeImageOnNumericStats= new ArrayList<>();

    public boolean isInChangeSteps() {
        return inChangeSteps;
    }

    public boolean isRemoveOnceChanged() {
        return removeOnceChanged;
    }

    public List<ChangeImageOnStat> getChangeImageOnNumericStats() {
        return changeImageOnNumericStats;
    }

    public void setInChangeSteps(boolean inChangeSteps) {
        this.inChangeSteps = inChangeSteps;
    }

    public void setRemoveOnceChanged(boolean removeOnceChanged) {
        this.removeOnceChanged = removeOnceChanged;
    }
}

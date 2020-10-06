package com.jessematty.black.tower.Components;

import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat;
import com.jessematty.black.tower.Components.Stats.Stat;

public   class TileWeatherChangableNumericStat extends com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat {
    private float changePoint;
    public TileWeatherChangableNumericStat(boolean displayable, String name, double value, int amountOfTimeToChangeFor) {
        super(displayable, name, value, amountOfTimeToChangeFor);
    }

    public TileWeatherChangableNumericStat(boolean displayable, String name, double value, double minValue, double maxValue, int amountOfTimeToChangeFor) {
        super(displayable, name, value, minValue, maxValue, amountOfTimeToChangeFor);
    }

    public TileWeatherChangableNumericStat(boolean displayable, String name, double value, double minValue, double maxValue) {
        super(displayable, name, value, minValue, maxValue);
    }

    public TileWeatherChangableNumericStat(boolean displayable, String name, double value, double minValue, double maxValue, float changePoint) {
        super(displayable, name, value, minValue, maxValue);
        this.changePoint = changePoint;
    }

    public TileWeatherChangableNumericStat() {
    }

    public TileWeatherChangableNumericStat(ChangableNumericStat other) {
        super(other);
    }

    public TileWeatherChangableNumericStat(TileWeatherChangableNumericStat other) {
        super(other);
        this.value = other.value;
        this.minValue = other.minValue;
        this.maxValue = other.maxValue;
    }



    @Override
    public Stat makeCopy() {
        return new TileWeatherChangableNumericStat(this);
    }




    public void addValues(TileWeatherChangableNumericStat stat) {
        super.addValues(stat);
    }










    public float getChangePoint() {
        return changePoint;
    }

    public void setChangePoint(float changePoint) {
        this.changePoint = changePoint;
    }
}


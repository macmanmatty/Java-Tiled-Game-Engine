package com.jessematty.black.tower.Components.Tiles;

import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatChangeable;
import com.jessematty.black.tower.Components.Stats.Stat;

public   class TileWeatherChangableNumericStatChangeable extends NumericStatChangeable {
    private float changePoint;
    public TileWeatherChangableNumericStatChangeable(boolean displayable, String name, double value, int amountOfTimeToChangeFor) {
        super(displayable, name, value, amountOfTimeToChangeFor);
    }

    public TileWeatherChangableNumericStatChangeable(boolean displayable, String name, double value, double minValue, double maxValue, int amountOfTimeToChangeFor) {
        super(displayable, name, value, minValue, maxValue, amountOfTimeToChangeFor);
    }

    public TileWeatherChangableNumericStatChangeable(boolean displayable, String name, double value, double minValue, double maxValue) {
        super(displayable, name, value, minValue, maxValue);
    }

    public TileWeatherChangableNumericStatChangeable(boolean displayable, String name, double value, double minValue, double maxValue, float changePoint) {
        super(displayable, name, value, minValue, maxValue);
        this.changePoint = changePoint;
    }

    public TileWeatherChangableNumericStatChangeable() {
    }

    public TileWeatherChangableNumericStatChangeable(NumericStatChangeable other) {
        super(other);
    }

    public TileWeatherChangableNumericStatChangeable(TileWeatherChangableNumericStatChangeable other) {
        super(other);
        this.value = other.value;
        this.minValue = other.minValue;
        this.maxValue = other.maxValue;
    }



    @Override
    public Stat makeCopy() {
        return new TileWeatherChangableNumericStatChangeable(this);
    }




    public void addValues(TileWeatherChangableNumericStatChangeable stat) {
        super.addValues(stat);
    }










    public float getChangePoint() {
        return changePoint;
    }

    public void setChangePoint(float changePoint) {
        this.changePoint = changePoint;
    }
}


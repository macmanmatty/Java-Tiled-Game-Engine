package com.jessematty.black.tower.Components.Stats.ChangeStats;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class TimeChangingStats implements Component {

    private Array<TimeChangeStat> timeChangeNumericStats= new Array<>();



    public Array<TimeChangeStat> getTimeChangeNumericStats() {
        return timeChangeNumericStats;
    }


}

package com.jessematty.black.tower.Components;

import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ActionComponent;
import com.jessematty.black.tower.Components.Stats.StringStat;

public class InfoDisplayable extends com.jessematty.black.tower.Components.Stats.StringStat  {

    public InfoDisplayable(StringStat other) {
        super(other);
    }

    public InfoDisplayable(  String info) {
        super(true,  "info", info);
    }




}

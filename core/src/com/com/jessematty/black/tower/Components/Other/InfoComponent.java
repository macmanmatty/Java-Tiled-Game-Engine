package com.jessematty.black.tower.Components.Other;

import com.jessematty.black.tower.Components.Stats.StringStat;

public class InfoComponent extends com.jessematty.black.tower.Components.Stats.StringStat {

    public InfoComponent(StringStat other) {
        super(other);
    }

    public InfoComponent(boolean displayable, String name, String stat) {
        super(displayable, name, stat);
    }

    public InfoComponent() {
    }




}



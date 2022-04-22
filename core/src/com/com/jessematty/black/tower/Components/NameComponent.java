package com.jessematty.black.tower.Components;

import com.jessematty.black.tower.Components.Stats.StringStat;

public class NameComponent extends com.jessematty.black.tower.Components.Stats.StringStat {

    public NameComponent(StringStat other) {
        super(other);
    }

    public NameComponent() {
        name="Name";

    }




    public NameComponent(boolean displayable, String stat) {
        super(displayable, "name", stat);
    }

    public NameComponent(String stat) {
        super(true, "name", stat);
    }


}

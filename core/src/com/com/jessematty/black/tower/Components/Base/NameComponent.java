package com.jessematty.black.tower.Components.Base;

import com.jessematty.black.tower.Components.Stats.StringStat;

public class NameComponent extends com.jessematty.black.tower.Components.Stats.StringStat {

    public NameComponent(StringStat other) {
        super(other);
    }

    public NameComponent() {
        name="Name";

    }




    public NameComponent(boolean displayable, String stat) {
        super(displayable, "Name", stat);
    }

    public NameComponent(String stat) {
        super(true, "Name", stat);
    }

    @Override
    public void setName(String name) {
    }
}

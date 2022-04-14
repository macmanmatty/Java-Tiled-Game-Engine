package com.jessematty.black.tower.Components;

import com.jessematty.black.tower.Components.Stats.StringStat;

public class Name extends com.jessematty.black.tower.Components.Stats.StringStat {

    public Name(StringStat other) {
        super(other);
    }

    public Name() {
        name="Name";

    }




    public Name(boolean displayable,  String stat) {
        super(displayable, "name", stat);
    }

    public Name(String stat) {
        super(true, "name", stat);
    }


}

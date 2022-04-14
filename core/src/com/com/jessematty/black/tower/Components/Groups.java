package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class Groups implements Component {

    private Array<String> groups= new Array<String>();

    public Array<String> getGroups() {
        return groups;
    }

    public void setGroups(Array<String> groups) {
        this.groups = groups;
    }
}

package com.jessematty.black.tower.Components.Base;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

/**
 * component that contains a list of groups represented as strings that an entity belongs to.
 */
public class GroupsComponent implements Component {

    private Array<String> groups= new Array<String>();

    public Array<String> getGroups() {
        return groups;
    }

    public void setGroups(Array<String> groups) {
        this.groups = groups;
    }
}

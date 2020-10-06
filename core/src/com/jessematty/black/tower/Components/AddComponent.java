package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class AddComponent implements  Component {
  private   Component component;
  private Array<String> groupsToAddTo= new Array<>();

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Array<String> getGroupsToAddTo() {
        return groupsToAddTo;
    }

    public void setGroupsToAddTo(Array<String> groupsToAddTo) {
        this.groupsToAddTo = groupsToAddTo;
    }
}

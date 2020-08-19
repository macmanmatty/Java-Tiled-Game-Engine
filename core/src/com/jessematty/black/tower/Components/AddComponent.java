package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

public class AddComponent implements  Component {
  private   Component component;

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}

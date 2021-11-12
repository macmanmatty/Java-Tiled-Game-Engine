package com.jessematty.black.tower.Editor.EditMode.Windows.EntityEdit;

import com.badlogic.ashley.core.Component;

public class NamedComponent {
 private    String name;
   private  Component component;

    public NamedComponent(String name, Component component) {
        this.name = name;
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public Component getComponent() {
        return component;
    }

    public Class<? extends Component> getComponentClass() {
        return component.getClass();
    }
}

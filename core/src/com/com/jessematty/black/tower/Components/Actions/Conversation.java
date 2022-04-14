package com.jessematty.black.tower.Components.Actions;

import com.badlogic.ashley.core.Component;

public class Conversation implements Component {
  private   String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

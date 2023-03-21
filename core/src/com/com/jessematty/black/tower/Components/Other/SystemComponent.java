package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class SystemComponent  {

 private   Class<? extends GameEntitySystem> classOfSystemToAdd; // the entity  system to add to the engine
  private  boolean add; // whether to add or remove the system.   true=add to system,


    public Class<? extends GameEntitySystem> getClassOfSystemToAdd() {
        return classOfSystemToAdd;
    }

    public void setClassOfSystemToAdd(Class<? extends GameEntitySystem> classOfSystemToAdd) {
        this.classOfSystemToAdd = classOfSystemToAdd;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }
}

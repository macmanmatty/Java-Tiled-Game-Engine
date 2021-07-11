package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.SystemComponent;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class AddSystemsComponent implements Component {

 private Array<Class< ? extends GameEntitySystem>> systemComponentArray= new Array<>();

 public Array<Class<? extends GameEntitySystem>> getSystemComponentArray() {
  return systemComponentArray;
 }

 public void setSystemComponentArray(Array<Class<? extends GameEntitySystem>> systemComponentArray) {
  this.systemComponentArray = systemComponentArray;
 }
}

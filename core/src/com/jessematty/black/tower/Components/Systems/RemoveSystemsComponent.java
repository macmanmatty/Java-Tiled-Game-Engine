package com.jessematty.black.tower.Components.Systems;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class RemoveSystemsComponent  implements Component {

 private Array<Class< ? extends GameEntitySystem>> systemComponentArray= new Array<>();

 public Array<Class<? extends GameEntitySystem>> getSystemComponentArray() {
  return systemComponentArray;
 }
}

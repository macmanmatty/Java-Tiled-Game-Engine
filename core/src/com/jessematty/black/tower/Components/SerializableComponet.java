package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;

public  interface  SerializableComponet extends Component {


     public abstract  void  deSerialize(GameAssets assets);
    public abstract  void  serialize();




}

package com.jessematty.black.tower.Components.Actions;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public interface ItemAction {

    public  void  action(MapDraw mapDraw, Entity entity);

}

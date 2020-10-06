package com.jessematty.black.tower.Components.Tiles;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public interface OnExitTile {
   void onExitTile(Entity entity, Entity tile, MapDraw draw);

}

package com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable;

import com.jessematty.black.tower.Components.Interfaces.ColorSettable;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;

public interface ColoredTiledMapTile extends NamedTiledMapTile, ColorSettable {
    NamedColor getColor();
    void setColor(NamedColor color);


}

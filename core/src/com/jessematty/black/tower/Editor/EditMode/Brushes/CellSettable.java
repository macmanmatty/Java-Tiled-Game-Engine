package com.jessematty.black.tower.Editor.EditMode.Brushes;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public interface CellSettable {

    public void setCells(Cell [] [] [] cells );
    public void setCells(Cell [] [] cells );

    public Cell [] [] [] getCells();


}

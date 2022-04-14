package com.jessematty.black.tower.Components.Tiles;

import com.badlogic.ashley.core.Component;

public class OnExitTileComponent implements Component {
    private OnExitTile onExitTile;

    public OnExitTile getOnExitTile() {
        return onExitTile;
    }

    public void setOnExitTile(OnExitTile onExitTile) {
        this.onExitTile = onExitTile;
    }
}

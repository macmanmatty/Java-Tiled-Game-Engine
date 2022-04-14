package com.jessematty.black.tower.Components.Tiles;

import com.badlogic.ashley.core.Component;

public class OnEnterTileComponent  implements Component {
    private OnEnterTile onEnterTile;

    public OnEnterTile getOnEnterTile() {
        return onEnterTile;
    }

    public void setOnEnterTile(OnEnterTile onEnterTile) {
        this.onEnterTile = onEnterTile;
    }
}

package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

public class Human implements Component {

    private boolean player;

    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }
}


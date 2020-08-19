package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

public class Playable implements ActionableComponent  {
    private boolean currentPlayer;

    public boolean isCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}



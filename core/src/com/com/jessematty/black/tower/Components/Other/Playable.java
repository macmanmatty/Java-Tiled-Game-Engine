package com.jessematty.black.tower.Components.Other;

import com.jessematty.black.tower.Components.Actions.ActionableComponent;

public class Playable implements ActionableComponent {
    private boolean currentPlayer;

    public boolean isCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}



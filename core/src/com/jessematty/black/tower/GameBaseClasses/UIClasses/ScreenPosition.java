package com.jessematty.black.tower.GameBaseClasses.UIClasses;

import com.badlogic.gdx.Gdx;

public enum ScreenPosition {
    BOTTOM(0,  0),
    TOP(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()),
    CENTER(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2),
    LEFT(0, Gdx.graphics.getHeight()),
    RIGHT(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());







    private final  int x;
   private final  int y;

    ScreenPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

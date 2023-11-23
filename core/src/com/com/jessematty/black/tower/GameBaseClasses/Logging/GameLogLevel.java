package com.jessematty.black.tower.GameBaseClasses.Logging;


/**
 * enum for the  GameLogger Level @See GameLogger
 */

public enum GameLogLevel {

         OFF ("NONE", 0),
         ERROR ("WARNING", 1),
         INFO ("INFO", 2),
        DEBUG ("CONFIG", 3);
         String name;
         int value;
    private GameLogLevel(String name, int value) {
            this.name = name;
            this.value = value;

    }
    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}

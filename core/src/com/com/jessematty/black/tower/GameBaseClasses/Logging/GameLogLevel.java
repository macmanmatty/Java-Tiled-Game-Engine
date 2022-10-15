package com.jessematty.black.tower.GameBaseClasses.Logging;



public class GameLogLevel {
    private final String name;
    private final int value;
    public static final GameLogLevel OFF = new GameLogLevel("OFF", 2147483647);
    public static final GameLogLevel EXCEPTION = new GameLogLevel("EXCEPTION", 1200);
    public static final GameLogLevel SEVERE = new GameLogLevel("SEVERE", 1100);
    public static final GameLogLevel WARNING = new GameLogLevel("WARNING", 1000);
    public static final GameLogLevel INFO = new GameLogLevel("INFO", 800);
    public static final GameLogLevel FINE = new GameLogLevel("FINE", 700);
    public static final GameLogLevel FINER = new GameLogLevel("FINER", 600);
    public static final GameLogLevel FINEST = new GameLogLevel("FINEST", 500);
    public static final GameLogLevel DEBUG = new GameLogLevel("CONFIG", 400);
    public static final GameLogLevel ALL = new GameLogLevel("ALL", -2147483648);
    private GameLogLevel(String name, int value) {
        if (name == null) {
            throw new NullPointerException();
        } else {
            this.name = name;
            this.value = value;
        }
    }
    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}

package com.jessematty.black.tower.GameBaseClasses.Logging;

import com.badlogic.ashley.core.Component;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;

public class Log  implements  Component {
    /**
     * the log statement
     */
    private String text ="";
    /**
     *  the color of the log text on screen
     */
    private NamedColor textColor=NamedColor.BLACK;
    /**
     * how long to display the log on screen
     */
    private long timeToDisplayOnScreen =5000;
    /**
     *  the style of the log label
     */
    private String style="log";

    /**
     * the scale of the log label
     */
    private float scale;

    /**
     * the log level
     */

    private GameLogLevel gameLogLevel;
    /**
     * whether or not to display the on screen;
     */
    private boolean displayOnScreen;

    public Log(String text, GameLogLevel gameLogLevel, boolean displayOnScreen) {
        this.text = text;
        this.gameLogLevel = gameLogLevel;
        this.displayOnScreen = displayOnScreen;
    }

    public Log() {
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public NamedColor getTextColor() {
        return textColor;
    }

    public void setTextColor(NamedColor textColor) {
        this.textColor = textColor;
    }

    public long getTimeToDisplayOnScreen() {
        return timeToDisplayOnScreen;
    }

    public void setTimeToDisplayOnScreen(long timeToDisplayOnScreen) {
        this.timeToDisplayOnScreen = timeToDisplayOnScreen;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Log(boolean displayOnScreen) {
        this.displayOnScreen = displayOnScreen;
    }

    public GameLogLevel getGameLogLevel() {
        return gameLogLevel;
    }

    public void setGameLogLevel(GameLogLevel gameLogLevel) {
        this.gameLogLevel = gameLogLevel;
    }

    public boolean isDisplayOnScreen() {
        return displayOnScreen;
    }

    public void setDisplayOnScreen(boolean displayOnScreen) {
        this.displayOnScreen = displayOnScreen;
    }
}

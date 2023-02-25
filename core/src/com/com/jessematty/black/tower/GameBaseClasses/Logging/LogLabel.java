package com.jessematty.black.tower.GameBaseClasses.Logging;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * libGDX label with ime to live on the screen parameter for logging
 */
public class LogLabel  extends Label {

    /**
     * how long to display the text on the screen in milliseconds
     * -1 means stay until pushed off by other logs
     */
    float timeToLive=100;
    float timeLived=0;

    public LogLabel(CharSequence text, Skin skin) {
        super(text, skin);
    }

    public LogLabel(CharSequence text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }

    public LogLabel(CharSequence text, Skin skin, String fontName, Color color) {
        super(text, skin, fontName, color);
    }

    public LogLabel(CharSequence text, Skin skin, String fontName, String colorName) {
        super(text, skin, fontName, colorName);
    }

    public LogLabel(CharSequence text, LabelStyle style) {
        super(text, style);
    }

    public float getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(float timeToLive) {
        this.timeToLive = timeToLive;
    }

    public float getTimeLived() {
        return timeLived;
    }

    public void setTimeLived(float timeLived) {
        this.timeLived = timeLived;
    }

    public void addiTme(float time) {
        this.timeLived=this.timeLived+time;
    }
}

package com.jessematty.black.tower.GameBaseClasses.UIClasses.Labels;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
public class TextTypeWriterLabel extends Label { //a label that displays it's  text like a  typewriter one char at time 
    private CharSequence disdplayedText;
   private  int counter;
   private int numberOfChars;
   private boolean finished;
   private int waitTime=10;
   private int pauseTime;
    public TextTypeWriterLabel(CharSequence text, Skin skin) {
        this(text, skin, "default");
    }
    public TextTypeWriterLabel(CharSequence text, Skin skin, String styleName) {
        this(text, skin, styleName, new Color(1,1,1,1));

    }
    public TextTypeWriterLabel(CharSequence text, Skin skin, String fontName, Color color) {
        super(text, skin, fontName, color);
        this.disdplayedText=text.subSequence(0, 1);
        setText(disdplayedText);
        numberOfChars=text.length();
        pauseTime=(int)((waitTime*numberOfChars)/4);

    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(counter%waitTime==0) {
            setText(disdplayedText.subSequence(0, counter));
            if (counter < numberOfChars) {
                invalidateHierarchy();
            }
        }
        counter++;

        if(counter==waitTime*numberOfChars+pauseTime){

            finished=true;

        }

        super.draw(batch, parentAlpha);


    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getPauseTime() {
        return pauseTime;
    }

    public void setPauseTime(int pauseTime) {
        this.pauseTime = pauseTime;
    }
}

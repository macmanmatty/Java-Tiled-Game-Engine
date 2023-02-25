package com.jessematty.black.tower.GameBaseClasses.Logging;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Queue;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;

/**
 * class that writes  logs statements to the libGDX screen extends a libGDX table for displaying the logs
 * this table  is added to the game stage
 */
public class GameLogger  extends Table {
    /**
     * the level at which to write logs at
     * 0= no logging
     * 1= log info
     * 2= log error
     * 3= log debug
     */

    
    private int logLevel=3;
    /**
     * the Maximum number of logs to display on the screen
     */
    private int maxDisplayedLogs=4;
    /**
    the top padding in between each log statement
     */
    private int logPadding;
    /**
     *  the queue of log labels added to The table
     */
    private Queue<LogLabel> displayLogs= new Queue<>(10);

    /**
     * the position of the logger on the screen;
     */
    private ScreenPosition loggerScreenPosition=ScreenPosition.BOTTOM;
    
    public GameLogger(Skin skin) {
        super(skin);
    }
    public GameLogger() {

    }
    @Override
    public void act(float deltaTime){
        super.act(deltaTime);

        if(displayLogs.size>maxDisplayedLogs){
         removeLogs(displayLogs.size-maxDisplayedLogs);
        }
        checkTime(deltaTime);
    }

    /**
     *  Checks how long a log label has been on screen
     *  and if it has been on longer than Time To Live Of
     *  The label removes it with remove actor acton
     *
     * @param deltaTime libGDX delta time
     */
    private void checkTime(float deltaTime){
        for(int count=0; count<displayLogs.size; count++){
            LogLabel logLabel=displayLogs.get(count);
            System.out.println( "log :"+logLabel.getTimeLived() + " delta time: " +deltaTime);

            if(logLabel.getTimeToLive()>-1 && logLabel.getTimeLived()>=logLabel.getTimeToLive()) {
                removeLogLabel(logLabel);


            }
            else{
                logLabel.addiTme(deltaTime);
            }
        }

    }
    /**
     * removes all logs before  the end  position
     * by adding the remove actor actions to the logs label
     * @param end the number of logs to remove
     */
    private void removeLogs( int end){
        end=end-1;
        for(int count=0; count<end; count++){
           removeLogLabel(displayLogs.last());
        }
        validate();
        pack();
    
    }

    /**
     * removes a log label from  the table
     * @param logLabel  the log label to remove
     */
    private void removeLogLabel(LogLabel logLabel){
        logLabel.addAction(Actions.removeActor());
        displayLogs.removeValue(logLabel, true);
        validate();
        pack();
    }



    /**
     * adds a log label to the log table to be displayed on the  screen
     * @param logLabel the log label to add
     */
    private void  addLogLabel(LogLabel logLabel){
        add(logLabel).padTop(logPadding);
        displayLogs.addFirst(logLabel);
        row();
        pack();
        validate();
    }
    
    /**
     * logs an game log object message to the log table (this)
     * @param gameLog the gameLog to log
     */
    public void log(GameLog gameLog){
        int value=gameLog.getGameLogLevel().value;
        if(logLevel>value && value>0){
          LogLabel label= new LogLabel(gameLog.getText(), getSkin());
          label.setFontScale(gameLog.getScale());
          label.setColor(gameLog.getTextColor());
          addLogLabel(label);
        }
        
    }
    /**
     * logs an game log object message to the log table (this) with different  skin than the table
     * @param gameLog the gameLog to log
     * @param skin  the libGDX skin for the label
     *
     */
    public void log(GameLog gameLog, Skin skin){
        int value=gameLog.getGameLogLevel().value;
        if(logLevel>value && value>0){
            LogLabel label= new LogLabel(gameLog.getText(), skin);
            label.setFontScale(gameLog.getScale());
            label.setColor(gameLog.getTextColor());
            addLogLabel(label);
        }

    }
    /**
     * logs an error message to the log table (this)
     * @param text the text to log
     */
    public void logError(String text){
        if(logLevel>1){
            LogLabel label= new LogLabel(text, getSkin());
            addLogLabel(label);
        }
        
    }
    /**
     * logs an info message to the log table (this)
     * @param text the text to log
     */
    public void logInfo(String text){
        if(logLevel>0){
            LogLabel label= new LogLabel(text, getSkin());
            addLogLabel(label);
             
        }
    }
    /**
     * logs a debug message to the log table (this)
     * @param text the ext to log
     */
    public void logDebug(String text){
        if(logLevel>2){
            LogLabel label= new LogLabel(text, getSkin());
            addLogLabel(label);
        }
    }

    /**
     * removes all logs from the screen
     */
    public void clearLogs(){
        removeLogs(displayLogs.size);
    }
    /**
     * getter and setters for fields
     */
    public int getLogLevel() {
        return logLevel;
    }
    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }
    public void setLogLevel(GameLogLevel logLevel) {
        this.logLevel = logLevel.value;
    }
    public int getMaxDisplayedLogs() {
        return maxDisplayedLogs;
    }
    public void setMaxDisplayedLogs(int maxDisplayedLogs) {
        this.maxDisplayedLogs = maxDisplayedLogs;
    }
    public int getLogPadding() {
        return logPadding;
    }
    public void setLogPadding(int logPadding) {
        this.logPadding = logPadding;
    }
    public Queue<LogLabel> getDisplayLogs() {
        return displayLogs;
    }

    public ScreenPosition getLoggerScreenPosition() {
        return loggerScreenPosition;
    }

    public void setLoggerScreenPosition(ScreenPosition loggerScreenPosition) {
        this.loggerScreenPosition = loggerScreenPosition;
        setPosition(loggerScreenPosition.getX(), loggerScreenPosition.getY());
    }
}

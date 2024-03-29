package com.jessematty.black.tower.GameBaseClasses.UIClasses.Stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Logging.ScreenLogger;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
public class GameStage extends Stage implements Disposable {

    /**
     * the logger object for printing logs to the screen
     */
    private  final ScreenLogger screenLogger;

    public GameStage(float width, float height, ScreenLogger screenLogger) {
       Viewport stageViewPort=new FillViewport(width, height);
        setViewport(stageViewPort);
        this.screenLogger = screenLogger;
        addActor(screenLogger);
    }

    public GameStage(ScreenLogger screenLogger) {
        this.screenLogger = screenLogger;
        addActor(screenLogger);
        screenLogger.setZIndex(Integer.MAX_VALUE);
    }

    /**
     * update stage  method  calls act and draw on the on the stage
     */
    public void update(float deltaTime, GameTime gameTime){
       act(deltaTime);
        draw();
    }
    /**
     * resizes  the stages view port
     */
    public void resize(int width, int height){
        getViewport().setScreenSize(width, height);
    }
    /**
     *  adds  a libGDX scene 2d UI component to the  screen to be displayed
     * @param window
     * @param positionX
     * @param positionY
     */
    public void addWindow(Actor window, float positionX, float positionY) {
        window.setPosition(positionX, positionY);
       addActor(window);
    }
    /**
     *  adds  a libGDX scene 2d UI component  to the  screen to be displayed
     * @param window
     * @param screenPosition
     */
    public void addWindow(Actor window, ScreenPosition screenPosition) {
        window.setPosition(screenPosition.getX(), screenPosition.getY());
     addActor(window);
    }
    /**
     *  adds  a libGDX scene 2d UI component  to the  screen to be displayed
     * @param window
     * @param screenPosition
     */
    public void addWindow(Dialog window, ScreenPosition screenPosition) {
        window.setPosition(screenPosition.getX(), screenPosition.getY());
        window.show(this);
    }

    public ScreenLogger getScreenLogger() {
        return screenLogger;
    }


}

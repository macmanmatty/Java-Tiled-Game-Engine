package com.jessematty.black.tower.GameBaseClasses.UIClasses.Stages;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import com.sun.media.jfxmediaimpl.MediaDisposer.Disposable;
public class GameStage extends Stage implements Disposable {
    public GameStage(float width, float height) {
       Viewport stageViewPort=new FillViewport(width, height);
        setViewport(stageViewPort);
    }
    /**
     * update stage  method  calls act and draw on the on the stage
     */
    public void update(float deltaTime, GameTime gameTime){
       act();
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
    public void addSpeechBubble(Label label, float positionX, float positionY){
        label.setPosition(positionX, positionY);
        addActor(label);
    }

}

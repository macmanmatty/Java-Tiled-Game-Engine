package com.jessematty.black.tower.GameBaseClasses.UIClasses.Stages;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputMultiplexer;

public class LockingInputStage extends Stage {

   private  LockableInputMultiplexer lockableInputMultiplexer;

    public LockingInputStage(LockableInputMultiplexer lockableInputMultiplexer) {
        this.lockableInputMultiplexer = lockableInputMultiplexer;
    }

    public LockingInputStage(Viewport viewport, LockableInputMultiplexer lockableInputMultiplexer) {
        super(viewport);
        this.lockableInputMultiplexer = lockableInputMultiplexer;
    }

    public LockingInputStage(Viewport viewport, Batch batch, LockableInputMultiplexer lockableInputMultiplexer) {
        super(viewport, batch);
        this.lockableInputMultiplexer = lockableInputMultiplexer;
    }

    // adds an actor to the stage  and removes all other input processors
    public  void addLockingActor(Actor actor){
        super.addActor(actor);
        lockableInputMultiplexer.lockAllOtherProcessorKeyInput(this);
        lockableInputMultiplexer.lockAllOtherProcessorMouseInput(this);


    }


    public LockableInputMultiplexer getLockableInputMultiplexer() {
        return lockableInputMultiplexer;
    }

    public void setLockableInputMultiplexer(LockableInputMultiplexer lockableInputMultiplexer) {
        this.lockableInputMultiplexer = lockableInputMultiplexer;
    }
}

package com.jessematty.black.tower.GameBaseClasses.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
// class the holds all of the input classes  for the game
public class GameInput {

    private final  KeyListener keyListener= new KeyListener(); // the key listner
    private final LockableInputMultiplexer lockableInputMultiplexer= new LockableInputMultiplexer();

    public GameInput() {
        lockableInputMultiplexer.addProcessor(keyListener);
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

    public LockableInputMultiplexer getLockableInputMultiplexer() {
        return lockableInputMultiplexer;
    }
    public void addProcessor(InputProcessor inputProcessor){

        lockableInputMultiplexer.addProcessor(inputProcessor);
    }

    public void addProcessor(Stage stage){

        lockableInputMultiplexer.addProcessor(stage);
        keyListener.getStages().add(stage);
    }

    public void removeProcessor(InputProcessor inputProcessor) {

        this.lockableInputMultiplexer.removeProcessor(inputProcessor);
    }




}



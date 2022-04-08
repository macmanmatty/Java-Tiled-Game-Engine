package com.jessematty.black.tower.GameBaseClasses.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

/**
 * class the holds all of the input processors  for the game
 * **/
public class GameInput {

    private final  KeyListener keyListener= new KeyListener(); // the key listner
    private final LockableInputMultiplexer lockableInputMultiplexer= new LockableInputMultiplexer();

    public GameInput() {
        lockableInputMultiplexer.addProcessor(keyListener);
        Gdx.input.setInputProcessor(lockableInputMultiplexer);
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

    /**
     * adds an array of inout processors to the InputMultiplexer
     * @param inputProcessors
     */
    public void addProcessor(Array< ? extends InputProcessor> inputProcessors){
        for(InputProcessor inputProcessor : inputProcessors) {
            lockableInputMultiplexer.addProcessor(inputProcessor);
        }

    }
    /**
     * removes  an array of inout processors from the InputMultiplexer
     * @param inputProcessors
     */
    public void removeProcessor(Array< ? extends InputProcessor> inputProcessors){
        for(InputProcessor inputProcessor : inputProcessors) {
            lockableInputMultiplexer.removeProcessor(inputProcessor);
        }

    }

    public void addProcessor(Stage stage){

        lockableInputMultiplexer.addProcessor(stage);
        keyListener.getStages().add(stage);
    }

    public void removeProcessor(InputProcessor inputProcessor) {

        this.lockableInputMultiplexer.removeProcessor(inputProcessor);
    }




}



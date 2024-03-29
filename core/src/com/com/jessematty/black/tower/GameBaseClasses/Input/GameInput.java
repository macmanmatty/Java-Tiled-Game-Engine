package com.jessematty.black.tower.GameBaseClasses.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;

/**
 * class the holds all of the input processors  for the game
 * additionally holds the global KeyListener To Check For Key input
 * **/
public class GameInput {

    /**
     * the key listener  object that listens for keys pressed
     */
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

    /**
     * add a libGDX InputProcessor to the InputMultiplexer provided
     * it hasn't already been added
     * @param inputProcessor the processor to add
     */
    public void addProcessor(InputProcessor inputProcessor){
        if(!InList.isInList(inputProcessor, lockableInputMultiplexer.getProcessors())) {
            lockableInputMultiplexer.addProcessor(inputProcessor);
        }

    }

    /**
     * adds an array of inout processors to the InputMultiplexer checking that
     * each InputProcessor is not already added to the InputMultiplexer
     * @param inputProcessors the processors to add
     */
    public void addProcessor(Array< ? extends InputProcessor> inputProcessors){
        for(InputProcessor inputProcessor : inputProcessors) {
            if(!InList.isInList(inputProcessors, lockableInputMultiplexer.getProcessors())) {
                lockableInputMultiplexer.addProcessor(inputProcessor);
            }

        }

    }
    /**
     * removes  an array of inout processors from the InputMultiplexer
     * @param inputProcessors the processors to remove
     */
    public void removeProcessor(Array< ? extends InputProcessor> inputProcessors){
        for(InputProcessor inputProcessor : inputProcessors) {
            lockableInputMultiplexer.removeProcessor(inputProcessor);
        }

    }

    public void addProcessor(Stage stage){
        if(!InList.isInList(stage, lockableInputMultiplexer.getProcessors())) {
            lockableInputMultiplexer.addProcessor(stage);
            keyListener.getStages().add(stage);
        }
    }
    /**
     * removes  an  inout processor from the InputMultiplexer
        @param inputProcessor  the processor to remove
     */
    public void removeProcessor(InputProcessor inputProcessor) {
        this.lockableInputMultiplexer.removeProcessor(inputProcessor);
    }


}



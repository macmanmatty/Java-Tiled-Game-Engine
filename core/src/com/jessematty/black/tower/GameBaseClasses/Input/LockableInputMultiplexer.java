package com.jessematty.black.tower.GameBaseClasses.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

/** An {@link InputProcessor} that delegates to an ordered list of other InputProcessors. Delegation for an event stops if a
 * processor returns true, which indicates that the event was handled. Addded additional functionally for lock input processors from being used
 * allowing one processor to be used at time if desired.
 * @author Nathan Sweet*/
public class LockableInputMultiplexer implements  InputProcessor {

    private SnapshotArray<InputProcessor> processors = new SnapshotArray(4);
    private InputProcessor currentUnlockedKeyProcessor; // the current keyprocessor that the game  is using
    private InputProcessor getCurrentUnlockedMouseProcessor; // the current mouse processor that the game is using
    public LockableInputMultiplexer() {
    }

    public LockableInputMultiplexer(InputProcessor... processors) {
        this.processors.addAll(processors);
    }

    public void addProcessor(int index, InputProcessor processor) {
        if (processor == null) throw new NullPointerException("processor cannot be null");
        processors.insert(index, processor);
    }

    public void removeProcessor(int index) {
        processors.removeIndex(index);
    }

    public void addProcessor(InputProcessor processor) {
        if (processor == null) throw new NullPointerException("processor cannot be null");
        processors.add(processor);
    }

    public void removeProcessor(InputProcessor processor) {
        processors.removeValue(processor, true);
    }

    /**
     * @return the number of processors in this multiplexer
     */
    public int size() {
        return processors.size;
    }

    public void clear() {
        processors.clear();
    }

    public void setProcessors(InputProcessor... processors) {
        this.processors.clear();
        this.processors.addAll(processors);
    }

    public void setProcessors(Array<InputProcessor> processors) {
        this.processors.clear();
        this.processors.addAll(processors);
    }

    public SnapshotArray<InputProcessor> getProcessors() {
        return processors;
    }

    public boolean keyDown(int keycode) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
                InputProcessor inputProcessor = (InputProcessor) items[i];
                if (inputProcessor instanceof LockableInputProcessor && ((LockableInputProcessor) inputProcessor).isKeyInputLocked()) {
                    continue;
                }
                if (inputProcessor.keyDown(keycode)) {
                    return true;
                }
            }

        } finally {
            processors.end();
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
               InputProcessor inputProcessor = (InputProcessor) items[i];
                if (inputProcessor instanceof LockableInputProcessor && ((LockableInputProcessor) inputProcessor).isKeyInputLocked()) {
                    continue;
                }
                if (inputProcessor.keyUp(keycode)) {
                    return true;
                }
            }

        } finally {
            processors.end();
        }
        return false;
    }

    public boolean keyTyped(char character) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
                InputProcessor inputProcessor = (InputProcessor) items[i];
                if (inputProcessor instanceof LockableInputProcessor && ((LockableInputProcessor) inputProcessor).isKeyInputLocked()) {
                    continue;
                }
                if (inputProcessor.keyTyped(character)) {
                    return true;


                }
            }

        } finally {
            processors.end();
        }
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
                InputProcessor inputProcessor = (InputProcessor) items[i];
                if (inputProcessor instanceof LockableInputProcessor && ((LockableInputProcessor) inputProcessor).isKeyInputLocked()) {
                    continue;
                }
                if (inputProcessor.touchDown(screenX, screenY, pointer, button)) {
                    return true;
                }

            }
        } finally {
            processors.end();
        }
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
                InputProcessor inputProcessor = (InputProcessor) items[i];
                if (inputProcessor instanceof LockableInputProcessor && ((LockableInputProcessor) inputProcessor).isMouseInputLocked()) {
                    continue;
                }
                if (inputProcessor.touchUp(screenX, screenY, pointer, button)) {
                    return true;

                }
            }

        } finally {
            processors.end();
        }
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
                InputProcessor inputProcessor = (InputProcessor) items[i];
                if (inputProcessor instanceof LockableInputProcessor && ((LockableInputProcessor) inputProcessor).isMouseInputLocked()) {
                    continue;
                }
                if (inputProcessor.touchDragged(screenX, screenY, pointer)) {

                    return true;
                }
            }


        } finally {
            processors.end();
        }
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
                InputProcessor inputProcessor = (InputProcessor) items[i];
                if (inputProcessor instanceof LockableInputProcessor && ((LockableInputProcessor) inputProcessor).isMouseInputLocked()) {
                    continue;
                }
                if (inputProcessor.mouseMoved(screenX, screenY)) {
                    return true;
                }

            }

        } finally {
            processors.end();
        }
        return false;
    }

    public boolean scrolled(int amount) {

        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
                InputProcessor inputProcessor = (InputProcessor) items[i];
                if (inputProcessor instanceof LockableInputProcessor && ((LockableInputProcessor) inputProcessor).isMouseInputLocked()) {
                    continue;
                }

                if (inputProcessor.scrolled(amount)) {
                    return true;
                }
            }

        } finally {
            processors.end();
        }
        return false;
    }

    // locks all other processors key based inputs ie prevents them from being used  other than  the passed input processor

    public void lockAllOtherProcessorKeyInput(InputProcessor inputProcessor) {
                if(currentUnlockedKeyProcessor==inputProcessor){
                    return;
                }

        currentUnlockedKeyProcessor=inputProcessor;
        int size = processors.size;
        for (int count = 0; count < size; count++) {
            InputProcessor currentInputProcessor = processors.get(count);
            if (currentInputProcessor == inputProcessor) {
                continue;
            }
           if (currentInputProcessor instanceof LockableInputProcessor) {
                ((LockableInputProcessor) currentInputProcessor).setKeyInputLocked(true);


            }


        }

    }

    // locks all other processors key based inputs ie prevents them from being used  other than  the passed input processor

    public void lockAllOtherProcessorMouseInput(InputProcessor inputProcessor) {
        if(getCurrentUnlockedMouseProcessor==inputProcessor){
            return;
        }
            getCurrentUnlockedMouseProcessor=inputProcessor;
        int size = processors.size;
        for (int count = 0; count < size; count++) {
            InputProcessor currentInputProcessor = processors.get(count);
            if (currentInputProcessor == inputProcessor) {
                continue;
            }
            if (currentInputProcessor instanceof LockableInputProcessor) {
                ((LockableInputProcessor) currentInputProcessor).setMouseInputLocked(true);


            }


        }

    }
    public void unlockAllMouseInputProcessors() {

        int size = processors.size;
        for (int count = 0; count < size; count++) {
            InputProcessor inputProcessor = processors.get(count);

            if (inputProcessor instanceof LockableInputProcessor) {
                ((LockableInputProcessor) inputProcessor).setMouseInputLocked(false);


            }


        }


    }

    public void unlockAllKeyInputProcessors() {

        int size = processors.size;
        for (int count = 0; count < size; count++) {
            InputProcessor inputProcessor = processors.get(count);

            if (inputProcessor instanceof LockableInputProcessor) {
                ((LockableInputProcessor) inputProcessor).setKeyInputLocked(false);


            }


        }


    }


    public void unlockAllProcessors(){
        unlockAllKeyInputProcessors();
        unlockAllMouseInputProcessors();
    }

}



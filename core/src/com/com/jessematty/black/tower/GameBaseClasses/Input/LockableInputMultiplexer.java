package com.jessematty.black.tower.GameBaseClasses.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
/** An {@link InputProcessor} that delegates to an ordered list of other InputProcessors. Delegation for an event stops if a
 * processor returns true, which indicates that the event was handled. Added additional functionally for locking input processors from being used
 * allowing one processor to be used at time if desired.
 * @author Nathan Sweet*/
public class LockableInputMultiplexer implements  InputProcessor {
    private SnapshotArray<InputProcessor> processors = new SnapshotArray(4);

    /**
     *  if this  InputProcessor  is not null this will be  the only
     * mouse  processor being polled for input on the
     * touchDown(int screenX, int screenY, int pointer, int button) InputProcessor method call
     *
     */
    private InputProcessor currentUnlockedTouchDownMouseProcessor;

    /**
     *  if this  InputProcessor  is not null this will be  the  only
     * mouse  processor being polled for input on the
     * touchUp(int screenX, int screenY, int pointer, int button) InputProcessor method call
     *
     */
    private InputProcessor currentUnlockedTouchUpMouseProcessor;


    /**
     *  if this  InputProcessor  is not null this will be  the only
     * mouse  processor being polled for input on the
     * touchDragged(int screenX, int screenY, int pointer) InputProcessor method call
     *
     */
    private InputProcessor currentUnlockedTouchDraggedMouseProcessor;


    /**
     *  if this  InputProcessor  is not null this will be   the  only
     * mouse  processor being polled for input on the
     * touchDragged(int screenX, int screenY) InputProcessor method call
     *
     */
    private InputProcessor currentUnlockedMouseMovedMouseProcessor;

    /**
     *  if this  InputProcessor  is not null this will be  the  only
     * mouse  processor being polled for input on the
     * scrolled(int amount)  InputProcessor method call
     *
     */
    private InputProcessor currentUnlockedScrolledMouseProcessor;

    /**
     *  if this  InputProcessor  is not null this will be   the  only
     * key InputProcessor being polled for input on the
     * scrolled(char key)  InputProcessor method call
     *
     */
    private InputProcessor currentUnlockedKeyTypedProcessor;


    /**
     *  if this  InputProcessor  is not null this will be   the only
     * key  InputProcessor being polled for input on the
     * keyUp(int keycode)  InputProcessor method call
     *
     */
    private InputProcessor currentUnlockedKeyUpProcessor;
    /**
     *  if this  InputProcessor  is not null this will be  the  only
     * key InputProcessor processor being polled for input on the
     * keyDown(int keycode)  InputProcessor method call
     *
     */
    private InputProcessor currentUnlockedKeyDownProcessor;

    public LockableInputMultiplexer() {
    }
    public LockableInputMultiplexer(InputProcessor... processors) {
        this.processors.addAll(processors);
    }

    /**
     * adds an input processor to the array at the specified location
     *
     * @param index the location in the Array of Processor to add the processor to.
     @param processor the InputProcessor To Add
     */
    public void addProcessor(int index, InputProcessor processor) {
        if (processor == null) throw new NullPointerException("processor cannot be null");
        processors.insert(index, processor);
    }
    public void removeProcessor(int index) {
        processors.removeIndex(index);
    }
    /**
     * adds an input processor to the array at the last index
     *
     * @param processor the InputProcessor To Add
     */
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
    /**
     *  calls the key up method on all input processors in the array that aren't locked
     *  unless a the currentUnlockedKeyUpProcessor is set
     *  then acts on only on the currentUnlockedKeyUpProcessor
     * @param keycode the current character typed
     * @return
     */
    public boolean keyDown(int keycode) {
        if(currentUnlockedKeyDownProcessor!=null){
           return  currentUnlockedKeyDownProcessor.keyDown(keycode);
        }
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
                InputProcessor inputProcessor = (InputProcessor) items[i];
                if (inputProcessor instanceof LockableInputProcessor && (((LockableInputProcessor) inputProcessor).isKeyInputLocked()|| ((LockableInputProcessor) inputProcessor).isKeyDownKeyInputLocked())) {
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
    /**
     *  calls the key up method on all input processors in the array that aren't locked
     *  unless a the currentUnlockedKeyUpProcessor is set
     *  then acts on only on the currentUnlockedKeyUpProcessor
     * @param keycode the current character typed
     * @return
     */
    public boolean keyUp(int keycode) {
        if(currentUnlockedKeyUpProcessor!=null){
            return  currentUnlockedKeyUpProcessor.keyUp(keycode);
        }
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
               InputProcessor inputProcessor = (InputProcessor) items[i];
                if (inputProcessor instanceof LockableInputProcessor && (((LockableInputProcessor) inputProcessor).isKeyInputLocked() || ((LockableInputProcessor) inputProcessor).isKeyUpKeyInputLocked())) {
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
    /**
     *  calls the key typed  method on all input processors in the array that aren't locked
     *  unless a the currentUnlockedKeyTypedProcessor is set
     *  then acts on only on the currentUnlockedKeyTypedProcessor
     * @param character the current character typed
     * @return
     */
    public boolean keyTyped(char character) {
        if(currentUnlockedKeyTypedProcessor!=null){
            return  currentUnlockedKeyTypedProcessor.keyTyped(character);
        }
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
                InputProcessor inputProcessor = (InputProcessor) items[i];

                if (inputProcessor instanceof LockableInputProcessor && (((LockableInputProcessor) inputProcessor).isKeyInputLocked() || ((LockableInputProcessor) inputProcessor).isKeyTypedKeyInputLocked())) {
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
    /**
     *  calls the touch down method on all input processors in the array that aren't locked
     *  unless a the currentUnlockedTouchDownMouseProcessor is set
     *  then acts on only on the currentTouchDownScrolledMouseProcessor
     * @param screenX  the current screen location x of the  mouse
     * @param screenY the current screen location y of the  mouse
     * @param pointer the current pointer
     * @param button the buttons used to touch down
     * @return
     */
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(currentUnlockedTouchDownMouseProcessor!=null){
            return currentUnlockedTouchDownMouseProcessor.touchDown(screenX, screenY,  pointer, button);
        }
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
                InputProcessor inputProcessor = (InputProcessor) items[i];

                if (inputProcessor instanceof LockableInputProcessor && (((LockableInputProcessor) inputProcessor).isMouseInputLocked() || ((LockableInputProcessor) inputProcessor).isTouchDownMouseInputLocked())) {
                    continue;
                }
                if (  inputProcessor.touchDown(screenX, screenY, pointer, button)) {
                    return true;
                }
            }
        } finally {
            processors.end();
        }
        return false;
    }


    /**
     *  calls the touch up method on all input processors in the array that aren't locked
     *  unless a the currentUnlockedTouchUpMouseProcessor is set
     *  then acts on only on the currentTouchUpScrolledMouseProcessor
     * @param screenX  the current screen location x of the  mouse
     * @param screenY the current screen location y of the  mouse
     * @param pointer the current pointer
     * @param button the buttons used to tough up
     * @return
     */
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(currentUnlockedTouchUpMouseProcessor!=null){
            return currentUnlockedTouchUpMouseProcessor.touchUp(screenX, screenY,  pointer, button);
        }
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
                InputProcessor inputProcessor = (InputProcessor) items[i];
                if (inputProcessor instanceof LockableInputProcessor && (((LockableInputProcessor) inputProcessor).isMouseInputLocked() || ((LockableInputProcessor) inputProcessor).isTouchUpMouseInputLocked())) {
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
    /**
     * calls the touch dragged method on all input processors in the array that aren't locked
     * unless a the currentUnlockedTouchDraggedMouseProcessor is set
     * then acts on only on the currentTouchDraggedScrolledMouseProcessor
     * @param screenX  the current screen location x of the  mouse
     * @param screenY the current screen location y of the  mouse
     * @return
     */
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(currentUnlockedTouchDraggedMouseProcessor!=null){
            return currentUnlockedTouchDraggedMouseProcessor.touchDragged(screenX, screenY,  pointer);
        }
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
                InputProcessor inputProcessor = (InputProcessor) items[i];
                if (inputProcessor instanceof LockableInputProcessor && (((LockableInputProcessor) inputProcessor).isMouseInputLocked() || ((LockableInputProcessor) inputProcessor).isTouchDraggedMouseInputLocked())) {
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
    /**
     * calls the mouse moved  method on all input processors in the array that aren't locked
     * unless a the currentUnlockedMouseMovedMouseProcessor is set
     * then acts on only on the currentMouseMovedScrolledMouseProcessor
     * @param screenX  the current screen location x of the  mouse
     * @param screenY the current screen location y of the  mouse
     * @return
     */
    public boolean mouseMoved(int screenX, int screenY) {
        if(currentUnlockedMouseMovedMouseProcessor!=null){
            return currentUnlockedMouseMovedMouseProcessor.mouseMoved(screenX, screenY);
        }
        Object[] items = processors.begin();
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
                InputProcessor inputProcessor = (InputProcessor) items[i];
                if (inputProcessor instanceof LockableInputProcessor && (((LockableInputProcessor) inputProcessor).isMouseInputLocked() || ((LockableInputProcessor) inputProcessor).isMouseMovedMouseInputLocked())) {
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


    /**
     * calls the scrolled method on all input processors in the array that aren't locked
     * unless a the currentUnlockedScrolledMouseProcessor is set
     * then acts on only on the currentUnlockedScrolledMouseProcessor
     * @param amountX the amount scrolled in the x direction
     * @param amountY the amount scrolled in the y direction
     * @return
     */
    @Override
    public boolean scrolled(float amountX, float amountY) {
        Object[] items = processors.begin();
        if(currentUnlockedScrolledMouseProcessor!=null){
           return currentUnlockedScrolledMouseProcessor.scrolled(amountX,amountY);
        }
        try {
            for (int i = 0, n = processors.size; i < n; i++) {
                InputProcessor inputProcessor = (InputProcessor) items[i];
                if (inputProcessor instanceof LockableInputProcessor && (((LockableInputProcessor) inputProcessor).isMouseInputLocked() || ((LockableInputProcessor) inputProcessor).isScrolledMouseInputLocked())) {
                    continue;
                }
                if (inputProcessor.scrolled(amountX, amountY)) {
                    return true;
                }
            }
        } finally {
            processors.end();
        }
        return false;
    }
    /**
     *    // locks all other lockable input  processors key  based inputs ie prevents them from being used  other than  the passed input processor
     * @param inputProcessor the input processor to not lock
     */
    public void lockAllOtherProcessorKeyInput(InputProcessor inputProcessor) {
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

    // TO DO: add multiple processors to be unlocked
    /**
     *     // locks all other lockable input  processors mouse based inputs ie prevents them from being used  other than  the passed input processor
     * @param inputProcessor the input processor to not lock
     */
    public void lockAllOtherProcessorMouseInput(InputProcessor inputProcessor) {
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

    /**
     * unlocks all Mouse Input Processors
     */
    public void unlockAllMouseInputProcessors() {
        int size = processors.size;
        for (int count = 0; count < size; count++) {
            InputProcessor inputProcessor = processors.get(count);
            if (inputProcessor instanceof LockableInputProcessor) {
                ((LockableInputProcessor) inputProcessor).setMouseInputLocked(false);
            }
        }
    }

    /**
     * unlocks all Key  Input Processors
     */
    public void unlockAllKeyInputProcessors() {
        int size = processors.size;
        for (int count = 0; count < size; count++) {
            InputProcessor inputProcessor = processors.get(count);
            if (inputProcessor instanceof LockableInputProcessor) {
                ((LockableInputProcessor) inputProcessor).setKeyInputLocked(false);
            }
        }
    }
    /**
     * unlocks all  Input Processors Both Mouse and Key
     */
    public void unlockAllProcessors(){
        unlockAllKeyInputProcessors();
        unlockAllMouseInputProcessors();
    }

    /**
     * sets all of the current mouse  processors to the passed in input processor
     * allowing it to only be used for mouse input
     * @param inputProcessor
     */
    public  void setCurrentMouseProcessor(InputProcessor inputProcessor){
        currentUnlockedTouchUpMouseProcessor=inputProcessor;
        currentUnlockedTouchDownMouseProcessor=inputProcessor;
        currentUnlockedTouchDraggedMouseProcessor=inputProcessor;
        currentUnlockedMouseMovedMouseProcessor=inputProcessor;
        currentUnlockedScrolledMouseProcessor=inputProcessor;
    }
    /**
     * sets all of the current key  processors to the passed in input processor
     * allowing it to only be used for key input
     * @param inputProcessor
     */
    public void setCurrentKeyInputProcessor(InputProcessor inputProcessor){
        currentUnlockedKeyDownProcessor=inputProcessor;
        currentUnlockedKeyUpProcessor=inputProcessor;
        currentUnlockedKeyTypedProcessor=inputProcessor;
    }

    public InputProcessor getCurrentUnlockedTouchDownMouseProcessor() {
        return currentUnlockedTouchDownMouseProcessor;
    }

    public void setCurrentUnlockedTouchDownMouseProcessor(InputProcessor currentUnlockedTouchDownMouseProcessor) {
        this.currentUnlockedTouchDownMouseProcessor = currentUnlockedTouchDownMouseProcessor;
    }

    public InputProcessor getCurrentUnlockedTouchUpMouseProcessor() {
        return currentUnlockedTouchUpMouseProcessor;
    }

    public void setCurrentUnlockedTouchUpMouseProcessor(InputProcessor currentUnlockedTouchUpMouseProcessor) {
        this.currentUnlockedTouchUpMouseProcessor = currentUnlockedTouchUpMouseProcessor;
    }

    public InputProcessor getCurrentUnlockedTouchDraggedMouseProcessor() {
        return currentUnlockedTouchDraggedMouseProcessor;
    }

    public void setCurrentUnlockedTouchDraggedMouseProcessor(InputProcessor currentUnlockedTouchDraggedMouseProcessor) {
        this.currentUnlockedTouchDraggedMouseProcessor = currentUnlockedTouchDraggedMouseProcessor;
    }

    public InputProcessor getCurrentUnlockedMouseMovedMouseProcessor() {
        return currentUnlockedMouseMovedMouseProcessor;
    }

    public void setCurrentUnlockedMouseMovedMouseProcessor(InputProcessor currentUnlockedMouseMovedMouseProcessor) {
        this.currentUnlockedMouseMovedMouseProcessor = currentUnlockedMouseMovedMouseProcessor;
    }

    public InputProcessor getCurrentUnlockedScrolledMouseProcessor() {
        return currentUnlockedScrolledMouseProcessor;
    }

    public void setCurrentUnlockedScrolledMouseProcessor(InputProcessor currentUnlockedScrolledMouseProcessor) {
        this.currentUnlockedScrolledMouseProcessor = currentUnlockedScrolledMouseProcessor;
    }

    public InputProcessor getCurrentUnlockedKeyTypedProcessor() {
        return currentUnlockedKeyTypedProcessor;
    }

    public void setCurrentUnlockedKeyTypedProcessor(InputProcessor currentUnlockedKeyTypedProcessor) {
        this.currentUnlockedKeyTypedProcessor = currentUnlockedKeyTypedProcessor;
    }

    public InputProcessor getCurrentUnlockedKeyUpProcessor() {
        return currentUnlockedKeyUpProcessor;
    }

    public void setCurrentUnlockedKeyUpProcessor(InputProcessor currentUnlockedKeyUpProcessor) {
        this.currentUnlockedKeyUpProcessor = currentUnlockedKeyUpProcessor;
    }

    public InputProcessor getCurrentUnlockedKeyDownProcessor() {
        return currentUnlockedKeyDownProcessor;
    }

    public void setCurrentUnlockedKeyDownProcessor(InputProcessor currentUnlockedKeyDownProcessor) {
        this.currentUnlockedKeyDownProcessor = currentUnlockedKeyDownProcessor;
    }
    /**
     * clears all of the current input processors
     */
    public void clearAllCurrentInputProcessors() {
        currentUnlockedKeyTypedProcessor=null;
        currentUnlockedKeyUpProcessor=null;
        currentUnlockedKeyDownProcessor=null;
        currentUnlockedScrolledMouseProcessor=null;
        currentUnlockedMouseMovedMouseProcessor=null;
        currentUnlockedTouchDraggedMouseProcessor=null;
        currentUnlockedTouchDownMouseProcessor=null;
        currentUnlockedTouchUpMouseProcessor=null;

    }
}

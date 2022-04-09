package com.jessematty.black.tower.GameBaseClasses.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Labels.NameEditableLabel.Nameable;
import java.util.Objects;
/** class  that holds a combination of  input keys that when pressed will
call the key action function they key listener class
 @see KeyListener
 **/
public class InputKeyCombo implements Nameable {
    /** 
     * the integers  keyboard key code from libGDX  input keys (libGDX.Input.Keys);
     * **/
   private Array<Integer> keysPressed= new Array<>();
    /**
     * // the press mode for they key that correlates
     *     // to the libGDX key input method.
     *     Key.KEY_UP
     *     Key.KEY_DOWN
     *     Key.KEY_PRESSED
     */
    private KeyPressMode keyPressMode=KeyPressMode.KEY_DOWN;
    /**
     * // the action to be preformed when the keys are pressed
     */
    private KeyAction keyAction;
    /**
     * // the key action name
     */
    private String name="";
    /**
     *  if this is true the key action will never be executed in the key listener
     */
    private boolean disabled;
    /**
     * the actor a stage must be focused on for the key  action to fire
     * may be null if no actor is required
     * if you set this  the action will only fire if  the given actor
     * is keyboard focused on any of the  stage(s) in the KeyInput class
     *
     */
    private Actor focusActor;
    /**
     *
     * @param keysPressed the keys that need to pressed to preform the action
     * @param keyPressMode  the mode of when to action the key
     * @param keyAction the action to be preformed when the keys are pressed
     */
    public InputKeyCombo(Array<Integer> keysPressed, KeyPressMode keyPressMode, KeyAction keyAction) {
        this(keysPressed, keyAction, "combo");
        this.keyPressMode = keyPressMode;
    }
    public InputKeyCombo(Array<Integer> keysPressed, KeyPressMode keyPressMode, KeyAction keyAction, String name) {
        this.keysPressed = keysPressed;
        this.keyPressMode = keyPressMode;
        this.keyAction = keyAction;
        this.name = name;
    }
    public InputKeyCombo(Array<Integer> keysPressed, KeyAction keyAction, String name) {
        this.keysPressed = keysPressed;
        this.keyAction = keyAction;
        this.name = name;
    }
    public InputKeyCombo( KeyAction action, String name,  Integer... keys) {
        this.keysPressed.addAll(keys);
        this.keyAction=action;
        this.name = name;
    }
    public InputKeyCombo(  Integer... keys) {
       this(null,"combo",  keys);
    }
    public InputKeyCombo() {
    }
    // sets the  key  value;
    public KeyAction getKeyAction() {
        return keyAction;
    }
    public void setKeyAction(KeyAction keyAction) {
        this.keyAction = keyAction;
    }

    /**
     * keys are equal if keys  and keymode are equal  and the stage focused  actor is equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InputKeyCombo)) return false;
        InputKeyCombo that = (InputKeyCombo) o;
        return keysPressed.equals(that.keysPressed) &&
                keyPressMode == that.keyPressMode &&
                keyAction.equals(that.keyAction) &&
                Objects.equals(focusActor, that.focusActor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keysPressed, keyPressMode, keyAction, focusActor);
    }



    public KeyPressMode getKeyPressMode() {
        return keyPressMode;
    }
    public void setKeyPressMode(KeyPressMode keyPressMode) {
        this.keyPressMode = keyPressMode;
    }
    public Array<Integer> getKeysPressed() {
        return keysPressed;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isDisabled() {
        return disabled;
    }
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Actor getFocusActor() {
        return focusActor;
    }

    public void setFocusActor(Actor focusActor) {
        this.focusActor = focusActor;
    }
}

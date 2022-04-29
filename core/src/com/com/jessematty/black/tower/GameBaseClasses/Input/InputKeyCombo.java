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
   private int [] keysPressed;
    /**
     * // the press mode for they key that correlates
     *     // to the libGDX key input method.
     *     Key.KEY_UP  will fire the KeyAction if any of the keys in the key pressed array are pressed up 
     *     Key.KEY_DOWN will fire  the  KeyAction if all of the keys in the array are pressed down
     *     Key.KEY_PRESSED will fire the KeyAction at the set rate  for the duration  the keys  are held down
     *     Key.Key_Typed will fire when  a key is typed
     */
    private KeyPressMode [] keyPressModes= new KeyPressMode[]{KeyPressMode.KEY_DOWN};
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
     * @param keys the keys that need to pressed to preform the action
     * @param keyPressMode  the mode(s) of when to action the key
     * @param keyAction the action to be preformed when the keys are pressed
     */

    /**
     * the rate in Milliseconds at which the  key input is called when they key is held down;
     */
    private float fireRate=1;

    /**
     * counter for the time passed when counter >=fireRate counter is reset;
     *
     */
    private float counter;

    public InputKeyCombo(KeyAction keyAction, KeyPressMode keyPressMode,  String name , int... keys) {
        this(keyAction,new KeyPressMode[] {keyPressMode}, name, keys);

    }
    public InputKeyCombo(KeyAction keyAction, KeyPressMode  [] keyPressMode,  String name , int... keys) {
        this.keysPressed=keys;
        this.keyPressModes =keyPressMode;
        this.keyAction = keyAction;
        this.name = name;
    }
    public InputKeyCombo(KeyAction keyAction,  float fireRate,  String name , int... keys) {
       this(keyAction,new KeyPressMode[]{KeyPressMode.KEY_DOWN, KeyPressMode.KEY_PRESSED} , name, keys );
        this.fireRate=fireRate;
    }

    public InputKeyCombo( KeyAction keyAction, String name,  int... keys) {
        this(keyAction,new KeyPressMode[]{KeyPressMode.KEY_DOWN} , name, keys );

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
                keyPressModes == that.keyPressModes &&
                keyAction.equals(that.keyAction) &&
                Objects.equals(focusActor, that.focusActor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keysPressed, keyPressModes, keyAction, focusActor);
    }



    public KeyPressMode [] getKeyPressModes() {
        return keyPressModes;
    }
    public void setKeyPressModes(KeyPressMode [] keyPressModes) {
        this.keyPressModes = keyPressModes;
    }

    public int [] getKeysPressed() {
        return keysPressed;
    }

    public void setKeysPressed(int [] keysPressed) {
        this.keysPressed = keysPressed;
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

    public float getFireRate() {
        return fireRate;
    }

    public void setFireRate(float fireRate) {
        this.fireRate = fireRate;
    }

    public float getCounter() {
        return counter;
    }

    public void increaseCounter(float increase) {
        this.counter =this.counter+ increase;
    }

    public void resetCounter() {
        counter=0;
    }
}

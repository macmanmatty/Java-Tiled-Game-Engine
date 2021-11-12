package com.jessematty.black.tower.GameBaseClasses.Input;


import com.badlogic.gdx.utils.Array;

import java.util.Objects;

// class  that holds a combination of  input keys that when pressed will
// call the key action function they key listener class
public class InputKeyCombo  {
   private Array<Integer> keysPressed= new Array<>();// the integers  keyboard key code from libGDX  input keys (libgdx.Input.Keys);
    // -1 == no key
    private KeyPressMode keyPressMode=KeyPressMode.KEY_DOWN; // the press mode for they key that corrospodes
    // to the libGDX key input method.

    private KeyAction keyAction; // the action to be preformed when the keys are pressed
    private String name=""; // the key action name
    private boolean disabled; // if this is true the key action will never be executed in the key listener

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


    // if keys are equal and keymode are equal object is equal


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InputKeyCombo)) return false;
        InputKeyCombo that = (InputKeyCombo) o;
        return keysPressed.equals(that.keysPressed) &&
                keyAction.equals(that.keyAction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keysPressed, keyAction);
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
}

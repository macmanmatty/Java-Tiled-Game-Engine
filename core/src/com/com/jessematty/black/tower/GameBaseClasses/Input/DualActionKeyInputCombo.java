package com.jessematty.black.tower.GameBaseClasses.Input;
/**
 * class for  a key combo that holds to input key combos linked to the same key(s)
 * one  key combo has key up action  and the other a key down action
 * 
 */
public class DualActionKeyInputCombo {
    /**
     * the input key combos
     * 1= key up function
     * 2=key down function
     */
  private  InputKeyCombo [] inputKeyCombos = new InputKeyCombo[2];

    /**
     * constructor for a single function to be called on both key pressed
     * @param action the action to take when a key is pressed up or down
     * @param name the key combo / functions name to be used with UI classes
     * @param keys the keys that must be pressed
     */
    public DualActionKeyInputCombo(KeyAction action, String name, int... keys) {
        inputKeyCombos[0]= new InputKeyCombo(action, KeyPressMode.KEY_UP, name+" Key Up", keys);
        inputKeyCombos[1]= new InputKeyCombo(action, KeyPressMode.KEY_DOWN,  name+" Key Up", keys);
    }
    /**
     * constructor for a single function to be called on both key pressed
     * @param keyUpAction the action to take when a key is pressed up 
     * @param  keyDownAction the action to take when a key is pressed down
     * @param name the key combo / functions name to be used with UI classes
     * @param keys the keys that must be pressed
     */
    public DualActionKeyInputCombo(KeyAction keyUpAction, KeyAction keyDownAction, String name, int... keys) {
        inputKeyCombos[0]= new InputKeyCombo(keyUpAction,  KeyPressMode.KEY_UP, name+" Key Up", keys);
        inputKeyCombos[1]= new InputKeyCombo(keyDownAction, KeyPressMode.KEY_DOWN,  name+" Key Up", keys);
    }

    public InputKeyCombo[] getInputKeyCombos() {
        return inputKeyCombos;
    }
}

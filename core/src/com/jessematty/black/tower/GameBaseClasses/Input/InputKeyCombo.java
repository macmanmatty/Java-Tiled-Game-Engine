package com.jessematty.black.tower.GameBaseClasses.Input;




// class  that holds a combination of two  input keys
public class InputKeyCombo {
   private int key1; // the integer  keyboard key code from libGDX  input keys (libgdx.Input.Keys);
    // -1 == no key

    private int key2;

    public InputKeyCombo(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public InputKeyCombo() {
    }

    public int getKey1() {
        return key1;
    }


    // sets the  key  value;
    public void setKey1(int key1) {
        // makes sure the 2 keys aren't the same key.
        // if they are removes key1 's value
        if(key2==key1){
            this.key1=-1;
        }
        this.key1 = key1;
    }

    public int getKey2() {
        return key2;
    }

    public void setKey2(int key2) {
        if(key2==key1){
            key2=-1;
            return;
        }
        this.key2 = key2;

    }
}

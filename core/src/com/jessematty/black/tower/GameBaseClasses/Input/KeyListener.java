package com.jessematty.black.tower.GameBaseClasses.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.ObjectMap;
// class that detects  input from multiple keys  at the same time
public class KeyListener implements InputProcessor {
    
   private final ObjectMap<Integer, Boolean> keysPressed= new ObjectMap<Integer, Boolean>(); // map of libGDX keycodes  to  boolean values for whether or not they are  pressed.
    public KeyListener() {
        for(int count=0; count<255; count++){
            keysPressed.put(count, false);
            
        }
    }
    @Override
    public boolean keyDown(int keycode) {
        // set key boolean to pressed to true
        keysPressed.put(keycode, true);
        return false;
        
    }
    @Override
    public boolean keyUp(int keycode) {
        // set key pressed to false
        keysPressed.put(keycode, false);
        return false;
        
    }

    // required unused input listener  methods return true  because they not used and allow input listeners to use those methods
    @Override
    public boolean keyTyped(char character) {
        return true;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }
    @Override
    public boolean scrolled(int amount) {
        return true;
    }

    // checks if all of the keys are pressed in the array of int that correspond to a given libGDX keycode.
    // returns true  only if ALL keys in the array are pressed.

    public boolean allKeysPressed(int... keys){
        int size=keys.length;
        for(int count=0; count<size; count++){
          boolean pressed=  keysPressed.get(keys[count]);
          if(pressed==false){
              return  false;
              
          }
            
        }
        
        return true;
        
    }
    // checks if any of the keys are pressed in the array of int that correspond to a given libGDX keycode returns true if any of the
    // keys are pressed

    public boolean anyKeysPressed(int... keys){
        int size=keys.length;
        for(int count=0; count<size; count++){
            boolean pressed=  keysPressed.get(keys[count]);
            if(pressed==true){
                return  true;

            }

        }
        return false;

    }



    
}

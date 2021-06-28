package com.jessematty.black.tower.GameBaseClasses.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Values;

// class that detects  input from multiple keys  at the same time
public class KeyListener implements LockableInputProcessor {

   private final ObjectMap<Integer, Boolean> keysPressed= new ObjectMap<Integer, Boolean>(); // map of libGDX keycodes  to  boolean values for whether or not they are  pressed.
    private boolean useKeyCombos=true;
    private Array< InputKeyCombo> inputKeyCombos = new Array<>();
    private Array<Stage> stages= new Array<>(); // the game stages
    private boolean keyInputLocked;


    public KeyListener(boolean useKeyCombos) {
        this();
        this.useKeyCombos=useKeyCombos;
    }
    public KeyListener() {
        // add all the keys to the map
        for(int count=0; count<255; count++){
            keysPressed.put(count, false);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        // set key boolean to pressed to true
        keysPressed.put(keycode, true);
       boolean focusText= isKeyboardFocusOnTextField();
      // if keyboard is focused  on  a text field  don't try call key functions
       useKeyCombos=!focusText;

        if(useKeyCombos==true){
            checkForKeyAction(KeyPressMode.KEY_DOWN);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // set key pressed to false
        boolean focusText= isKeyboardFocusOnTextField();
        // if keyboard is focused  on  a text field  don't try call key functions
        useKeyCombos=!focusText;

        keysPressed.put(keycode, false);
        if(useKeyCombos==true){
            checkForKeyAction(KeyPressMode.KEY_UP);
        }

        return false;
        
    }

    // required unused input listener  methods return false  because they not used and allow input listeners to use those methods
    @Override
    public boolean keyTyped(char character) {
        if(useKeyCombos==true){
            checkForKeyAction(KeyPressMode.KEY_TYPED);
        }
        return false;
    }

    // unused methods required by  the input processor interface.
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private boolean isKeyboardFocusOnTextField(){
        int size=stages.size;
        for(int count=0;  count<size; count++){
            Stage stage=stages.get(count);
            Actor actor=stage.getKeyboardFocus();
            if(actor instanceof TextField){
                return true;

            }


        }
        return  false;

    }

    // check to see if the current pressed keys match  any of  key actions  in  input key combo object

    // if they do it calls  the  key actions act method.

    // returns true if key combo  was  acted on  false if no key was acted on

    public  boolean  checkForKeyAction(KeyPressMode keyPressMode){
        int size=inputKeyCombos.size;
        for(int count=0; count<size; count++) {

          InputKeyCombo inputKeyCombo= inputKeyCombos.get(count);
          if(inputKeyCombo.isDisabled()){
              continue;
          }
            Array<Integer> keysToBePressed=inputKeyCombo.getKeysPressed();
            if(keyPressMode!=inputKeyCombo.getKeyPressMode()){
                continue;
            }

            int numberOfKeysToPress=keysToBePressed.size;
            int totalKeysPressed=0;
            for(int count2=0; count2<numberOfKeysToPress; count2++){

                // numberOfKeyboardKeys=255;
                int keyPressed=keysToBePressed.get(count2);


                    boolean keyIsPressed=keysPressed.get(keyPressed);
                    if(keyIsPressed){
                        totalKeysPressed++;
                    }


            }
            if(totalKeysPressed==numberOfKeysToPress){

                inputKeyCombo.getKeyAction().act();
                return  true;

            }

        }

        return false;

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

    public boolean isUseKeyCombos() {
        return useKeyCombos;
    }

    public void setUseKeyCombos(boolean useKeyCombos) {
        this.useKeyCombos = useKeyCombos;
    }

    public Array< InputKeyCombo> getInputKeyCombos() {
        return inputKeyCombos;
    }

    public Array<Stage> getStages() {
        return stages;
    }

    public void addInputKeys(Array<InputKeyCombo> inputKeyComboList) {

        int size=inputKeyComboList.size;
        for(int count=0; count<size; count++){
            InputKeyCombo inputKeyCombo=inputKeyComboList.get(count);
            inputKeyCombos.add(inputKeyCombo);

        }


    }

    public void removeInputKeys(Array<InputKeyCombo> inputKeyComboList) {

        int size=inputKeyComboList.size;
        for(int count=0; count<size; count++){
            InputKeyCombo inputKeyCombo=inputKeyComboList.get(count);
            inputKeyCombos.removeValue(inputKeyCombo, true);

        }

    }

    // return false  no mouse input in this class.
    @Override
    public boolean isMouseInputLocked() {
        return false;
    }

    @Override
    public void setMouseInputLocked(boolean locked) {

        // this a key listener no mouse input needed
    }

    @Override
    public boolean isKeyInputLocked() {
        return keyInputLocked;
    }

    @Override
    public void setKeyInputLocked(boolean locked) {


        this.keyInputLocked=keyInputLocked;
    }
}

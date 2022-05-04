package com.jessematty.black.tower.GameBaseClasses.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;

/**
 * class that detects  input from multiple keys  at the same time
 * holds map of key codes to keys pressed
 * holds a array of InputKeyCombos and acts on them when the correct
 * conditions are met key input mode combination  of keys pressed etc.
 * also holds an array  the games stages to check what the stage(s)
 * keyboard focused actor is and locks all other key typed functions from being called
 * if the any of the stages keyboard focused actors are TextFields
 *
 */
public class KeyListener implements LockableInputProcessor {
    /**
     *  The current key that is pressed up;
     *  Only one key can be pressed up at a time
     */
    private int currentKeyUpCode;
    /**
     *  The current char that is typed
     *  Only one key can be pressed up at a time
     */
    private char currentTypedChar;
    /**
     * the map containing which keys are pressed down
     * @see com.badlogic.gdx.utils.ObjectMap.Keys
     */
    private final ObjectMap<Integer, Boolean> keysPressedDown = new ObjectMap<Integer, Boolean>();
    /**
     * whether or not to use key input combos and functions or just check for key presses
     */
    private boolean useKeyCombosForKeyTyped =true;
    /**
     * map containing the array of input actions mapped to keys
     * @see InputKeyCombo
     */
    private Array< InputKeyCombo> inputKeyCombos = new Array<>();
    /**
     * the games stages used to check for keyboard actor focus
     */
    private Array<Stage> stages= new Array<>(); // the game stages
    /**
     * is the key input locked?
     * this mainly set for text boxes when they focused
     * to avoid other key actions from being called
     */
    private boolean keyInputLocked=false;
    private boolean keyTypedKeyInputLocked=false;
    private boolean keyUpKeyInputLocked=false;
    private boolean keyDownKeyInputLocked=false;


    /**
     * if true only one  action be used per key combo
     */
    private  boolean onlyOneKeyComboPerAction;
    public KeyListener(boolean useKeyCombosForKeyTyped, boolean onlyOneKeyComboPerAction) {
        this(useKeyCombosForKeyTyped);
        this.onlyOneKeyComboPerAction = onlyOneKeyComboPerAction;
    }
    public KeyListener(boolean useKeyCombosForKeyTyped) {
        this();
        this.useKeyCombosForKeyTyped = useKeyCombosForKeyTyped;
    }
    public KeyListener() {
        // add all the keys to the map
        for(int count=0; count<255; count++){
            keysPressedDown.put(count, false);
        }
    }
    /**
     * sets the key pressed to true in the boolean array
     * then checks for key actions  and acts on them if a  TextField  or other typing input Actor  is not the main focus of the stage
     * @param keycode the code for the key that was pressed
     * @return
     */
    @Override
    public boolean keyDown(int keycode) {
        // set key boolean to pressed to true
        keysPressedDown.put(keycode, true);
        checkForKeyAction(0, KeyPressMode.KEY_DOWN);
        return false;
    }
    /**
     * sets the key pressed to false in the boolean array
     * then checks for key actions  and acts on them if a  TextField  or other typing input Actor  is not the main focus of the stage
     * @param keycode the code for the key that was pressed
     * @return
     */
    @Override
    public boolean keyUp(int keycode) {
        // set key pressed to false
        keysPressedDown.put(keycode, false);
        currentKeyUpCode =keycode;
        checkForKeyAction(0, KeyPressMode.KEY_UP);
        return false;
    }
    /**
     *
     * sets the current char typed field
     * if use keyCombosForKeyTyped is true
     * will check for key actions on key typed.
     * @return
     */
    @Override
    public boolean keyTyped(char character) {
        currentTypedChar=character;
        if(useKeyCombosForKeyTyped){
            checkForKeyAction(0, KeyPressMode.KEY_TYPED);
        }
        return false;
    }
    /**
     * unused methods required by  the  libGDX input processor interface.
     * @see  com.badlogic.gdx.InputProcessor
     *
     */
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
    /**
     * checks to see if  a TextField is the primary  focus of the keyboard
     * @return true of the actor is  a  TextField false of not
     */
    public boolean isKeyboardFocusOnTextField(){
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
    /**
     * checks to see if  a a passed actor  is the primary  focus of the keyboard
     * for any of the games stages
     * @param actor the actor to compare to.
     * @return
     */
    private boolean isKeyboardFocusOnActor(Actor actor){
        int size=stages.size;
        for(int count=0;  count<size; count++){
            Stage stage=stages.get(count);
            Actor actor2=stage.getKeyboardFocus();
            if(actor ==actor2){
                return true;
            }
        }
        return  false;
    }
    /** checks to see if the current pressed keys match  any of  key actions  in  the  input key combo array
     if they do it calls  the  key actions act method provided  if the action is not currently locked
     and the KeyPressMode  and if applicable  the stage focused actor  match
     returns true if any  key combo  was  acted on  false if no key was acted on
     @param  keyPressMode the current mode of keys being acted on
     @param delta libGDX delta time only used  with update(float delta) Method  call to this method.
     **/
    public  boolean  checkForKeyAction(float delta , KeyPressMode keyPressMode){
        int size=inputKeyCombos.size;
        boolean pressed=false;
        for(int count=0; count<size; count++) {
          InputKeyCombo inputKeyCombo= inputKeyCombos.get(count);
          KeyPressMode [] keyComboPressMode=inputKeyCombo.getKeyPressModes();
          if(!InList.isInList(keyPressMode, keyComboPressMode)){
              continue;
          }
          if(inputKeyCombo.isDisabled()){
              continue;
          }
          // check for stage focused actor
          Actor actor=inputKeyCombo.getFocusActor();
          if(actor!=null){
              if(!isKeyboardFocusOnActor(actor)){
                  continue;
              }
          }
            pressed=checkForKeyPress(delta, keyPressMode, inputKeyCombo);
        }
        return pressed;
    }
    /**
     * checks to see if any of the keys are pressed for a given keyInputCombo
     * based on the key pressed mode
     * @param keyPressMode current mode  of keys being operated on
     * @param inputKeyCombo the keyInput combo to check for matching key presses
     * @param  delta libGDX delta time
     * @return true if the actions key(s) are pressed false if not
     */
    public boolean  checkForKeyPress( float delta , KeyPressMode keyPressMode, InputKeyCombo inputKeyCombo){
        int [] keysToBePressed=inputKeyCombo.getKeysPressed();
        boolean pressed=false;
        if(keyPressMode==KeyPressMode.KEY_DOWN ){
            pressed=keysPressedDown(keysToBePressed);
        }
        else if(keyPressMode==KeyPressMode.KEY_PRESSED){
            inputKeyCombo.increaseCounter(delta);

            if(inputKeyCombo.getCounter()>=inputKeyCombo.getFireRate()){
                pressed=keysPressedDown(keysToBePressed);
                inputKeyCombo.resetCounter();
            }
        }
        else if(keyPressMode==KeyPressMode.KEY_UP){
            pressed= keysPressedUp(keysToBePressed);
        }
        if(pressed){
            inputKeyCombo.getKeyAction().act();
        }
        return  pressed;
    }
    /**
     * called to check if keys are pressed this should be called from a libGDX Screen render method
     * if you do not call this method  any InputKeyCombos with the Key_Pressed mode will not be called
     * more than once
     */
    public void update(float delta){
        checkForKeyAction(delta, KeyPressMode.KEY_PRESSED);
    }
    /**
     * checks if all of the key codes  in the array of key codes aka Integers
     * match  are currently pressed down
     * returns true if all of they keys are pressed down
     * and false if not
     * @param keysToBePressed the Array of keys to match
     * @return
     */
    private boolean keysPressedDown(int [] keysToBePressed){
        int numberOfKeysToPress=keysToBePressed.length;
        for(int count2=0; count2<numberOfKeysToPress; count2++){
            // numberOfKeyboardKeys=255;
            int keyPressed=keysToBePressed[count2];
            boolean keyIsPressed= keysPressedDown.get(keyPressed);
            if(!keyIsPressed){
                return false;
            }
        }
        return  true;
    }
    /**
     * checks if any of the key codes  in the array of key codes aka Integers
     * match the current key that is pressed up
     * returns true if any one of they keys matches
     * and false if none of the keys match
     * @param keysToBePressed the Array of keys to match
     * @return
     */
    private boolean keysPressedUp(int [] keysToBePressed){
        int numberOfKeysToPress=keysToBePressed.length;
        for(int count2=0; count2<numberOfKeysToPress; count2++){
            // numberOfKeyboardKeys=255;
           if( currentKeyUpCode ==keysToBePressed[count2]){
               return true;
           }
        }
        return  false;
    }

    
    /** checks if all of the keys are pressed in the array of int that correspond to a given libGDX keycode.
     returns true  only if ALL keys in the array are pressed.
     @param keys  the array of keys that need to be pressed
     **/
    public boolean allKeysPressed(int... keys){
        int size=keys.length;
        for(int count=0; count<size; count++){
          boolean pressed=  keysPressedDown.get(keys[count]);
          if(pressed==false){
              return  false;
              
          }
            
        }
        
        return true;
        
    }
    /** checks if any of the keys are pressed in the array of int that correspond to a given libGDX keycode returns true if any of the
    // keys are pressed
     @param keys  the array of keys that need to be pressed
     **/
    public boolean anyKeysPressed(int... keys){
        int size=keys.length;
        for(int count=0; count<size; count++){
            boolean pressed=  keysPressedDown.get(keys[count]);
            if(pressed==true){
                return  true;
            }
        }
        return false;
    }
    public boolean isUseKeyCombosForKeyTyped() {
        return useKeyCombosForKeyTyped;
    }
    public void setUseKeyCombosForKeyTyped(boolean useKeyCombosForKeyTyped) {
        this.useKeyCombosForKeyTyped = useKeyCombosForKeyTyped;
    }
    public Array< InputKeyCombo> getInputKeyCombos() {
        return inputKeyCombos;
    }
    public Array<Stage> getStages() {
        return stages;
    }
    public void addInputKeyCombo(
            InputKeyCombo inputKeyCombo){
        inputKeyCombos.add(inputKeyCombo);
    }
    public void addInputKeyCombo(
            DualActionKeyInputCombo inputKeyCombo){
        inputKeyCombos.addAll(inputKeyCombo.getInputKeyCombos());
    }
    public void removeInputKeyCombo(
            DualActionKeyInputCombo inputKeyCombo){
        inputKeyCombos.removeValue(inputKeyCombo.getInputKeyCombos()[0], true);
        inputKeyCombos.removeValue(inputKeyCombo.getInputKeyCombos()[1], true);
    }
    public void removeInputKeyCombo(InputKeyCombo inputKeyCombo){
        inputKeyCombos.removeValue(inputKeyCombo, true);
    }
    public void addInputKeyCombos(Array<InputKeyCombo> inputKeyComboList) {
        this.inputKeyCombos.addAll(inputKeyComboList);
    }
    public void removeInputKeyCombos(Array<InputKeyCombo> inputKeyComboList) {
      this.inputKeyCombos.removeAll(inputKeyComboList, true);
    }
    /**
     sets all keys down to false
     */
    public void clearKeysDown(){
        for(int count=0; count<255; count++){
            keysPressedDown.put(count, false);
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

    /**
     * all return true for locking all  mouse input  because is not used here
     * and locking mouse input  is not allowed for the  key listener class
     * @return
     */
    @Override
    public boolean isTouchUpMouseInputLocked() {
        return true;
    }

    @Override
    public void setTouchDownMouseInputLocked(boolean locked) {

    }

    @Override
    public boolean isTouchDownMouseInputLocked() {
        return true;
    }

    @Override
    public void setTouchUpMouseInputLocked(boolean locked) {

    }

    @Override
    public boolean isMouseMovedMouseInputLocked() {
        return true;
    }

    @Override
    public void setMouseMovedMouseInputLocked(boolean locked) {

    }

    @Override
    public boolean isScrolledMouseInputLocked() {
        return true;
    }

    @Override
    public void setScrolledMouseInputLocked(boolean locked) {

    }

    @Override
    public boolean isTouchDraggedMouseInputLocked() {
        return true;
    }

    @Override
    public void setTouchDraggedMouseInputLocked(boolean locked) {

    }

    @Override
    public boolean isKeyInputLocked() {
        return keyInputLocked;
    }
    @Override
    public void setKeyInputLocked(boolean locked) {
        this.keyInputLocked=keyInputLocked;
    }
    public boolean isOnlyOneKeyComboPerAction() {
        return onlyOneKeyComboPerAction;
    }
    public void setOnlyOneKeyComboPerAction(boolean onlyOneKeyComboPerAction) {
        this.onlyOneKeyComboPerAction = onlyOneKeyComboPerAction;
    }

    @Override
    public boolean isKeyTypedKeyInputLocked() {
        return keyTypedKeyInputLocked;
    }
    @Override

    public void setKeyTypedKeyInputLocked(boolean keyTypedKeyInputLocked) {
        this.keyTypedKeyInputLocked = keyTypedKeyInputLocked;
    }
    @Override

    public boolean isKeyUpKeyInputLocked() {
        return keyUpKeyInputLocked;
    }
    @Override

    public void setKeyUpKeyInputLocked(boolean keyUpKeyInputLocked) {
        this.keyUpKeyInputLocked = keyUpKeyInputLocked;
    }
    @Override

    public boolean isKeyDownKeyInputLocked() {
        return keyDownKeyInputLocked;
    }
    @Override
    public void setKeyDownKeyInputLocked(boolean keyDownKeyInputLocked) {
        this.keyDownKeyInputLocked = keyDownKeyInputLocked;
    }

    public char getCurrentTypedChar() {
        return currentTypedChar;
    }

    public void setCurrentTypedChar(char currentTypedChar) {
        this.currentTypedChar = currentTypedChar;
    }
}

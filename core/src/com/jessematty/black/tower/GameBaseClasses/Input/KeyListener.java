package com.jessematty.black.tower.GameBaseClasses.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

/**class that detects  input from multiple keys  at the same time
 *
 */
public class KeyListener implements LockableInputProcessor {
    /**
     * the map containing which keys are pressed
     * @see com.badlogic.gdx.utils.ObjectMap.Keys
     */
   private final ObjectMap<Integer, Boolean> keysPressed= new ObjectMap<Integer, Boolean>();
    /**
     * whether or not to use key input combos and functions or just check for key presses
     */
    private boolean useKeyCombos=true;
    /**
     * map containing the array of input actions mapped to keys
     * @see InputKeyCombo
     */
    private Array< InputKeyCombo> inputKeyCombos = new Array<>();
    /**
     * the games stages
     */
    private Array<Stage> stages= new Array<>(); // the game stages
    /**
     * is the key input locked?
     * this mainly set for text boxes when they focused
     * to avoid other key actions from being called
     */
    private boolean keyInputLocked=false;

    /**
     * if true only key combo may be used per action
     */
    private  boolean onlyOneKeyComboPerAction;

    public KeyListener(boolean useKeyCombos, boolean onlyOneKeyComboPerAction) {
        this(useKeyCombos);
        this.onlyOneKeyComboPerAction = onlyOneKeyComboPerAction;
    }

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
    /**
     * sets the key pressed to true in the boolean array
     * then checks for key actions  and acts on them if a  TextField  or other typing input Actor  is not the main focus of the stage
     * @param keycode the code for the key that was pressed
     * @return
     */
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

    /**
     * sets the key pressed to false in the boolean array
     * then checks for key actions  and acts on them if a  TextField  or other typing input Actor  is not the main focus of the stage
     * @param keycode the code for the key that was pressed
     * @return
     */
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
    /**
     * sets the key pressed to false in the boolean array
     * then checks for key actions  and acts on them
     *  unlike other listeners ignoring if
     * TextField  or other typing input Actor
     * is not the main focus of the stage
     * @param character the character that was typed
     * @return
     */
    @Override
    public boolean keyTyped(char character) {
        if(useKeyCombos==true){
            checkForKeyAction(KeyPressMode.KEY_TYPED);
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
     * checks to see if TextField is the primary  focus of the keyboard
     * @return
     */
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
    /** checks to see if the current pressed keys match  any of  key actions  in  input key combo object
     if they do it calls  the  key actions act method.
     returns true if key combo  was  acted on  false if no key was acted on
     **/
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
    
    /** checks if all of the keys are pressed in the array of int that correspond to a given libGDX keycode.
     returns true  only if ALL keys in the array are pressed.
     @param keys  the array of keys that need to be pressed
     **/
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
    /** checks if any of the keys are pressed in the array of int that correspond to a given libGDX keycode returns true if any of the
    // keys are pressed
     @param keys  the array of keys that need to be pressed
     **/

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
    public void addInputKeyCombo(InputKeyCombo inputKeyCombo){
        inputKeyCombos.add(inputKeyCombo);
    }
    public void removeInputKeyCombo(InputKeyCombo inputKeyCombo){
        inputKeyCombos.removeValue(inputKeyCombo, true);
    }
    public void addInputKeys(Array<InputKeyCombo> inputKeyComboList) {
        inputKeyComboList.addAll(inputKeyComboList);

    }
    public void removeInputKeys(Array<InputKeyCombo> inputKeyComboList) {
      inputKeyComboList.removeAll(inputKeyComboList, true);

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

    public boolean isOnlyOneKeyComboPerAction() {
        return onlyOneKeyComboPerAction;
    }

    public void setOnlyOneKeyComboPerAction(boolean onlyOneKeyComboPerAction) {
        this.onlyOneKeyComboPerAction = onlyOneKeyComboPerAction;
    }
}

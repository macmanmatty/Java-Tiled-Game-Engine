package com.jessematty.black.tower.GameBaseClasses.Settings.GameSettings;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeys;

public class GameInputKeys implements InputKeys {
    int moveUpKey= Keys.UP;
    int moveDownKey= Keys.DOWN;
    int moveLeftKey= Keys.LEFT;
    int moveRightKey= Keys.RIGHT;
    int speedUpKey= Keys.CONTROL_RIGHT;
    int moveLeftDownKey= Keys.J;
    int moveLeftUpKey= Keys.N;
    int moveRightUpKey = Keys.K;
    int moveRightDownKey= Keys.M;
    int throwKey = Keys.T;
    int pickUpItemKey= Keys.P;
    int changeAttackModeKey= Keys.SYM;
    int changeHoldPositionKey= Keys.H;
    int eatKey= Keys.E;
    int drinkKey= Keys.W;
    int dropItemKey= Keys.D;
    int cameraUpKey= Keys.UP;
    int cameraDownKey= Keys.DOWN;
    int cameraLeftKey= Keys.LEFT;
    int cameraRightKey= Keys.RIGHT;
    int cameraZoomInKey= Keys.EQUALS;
    int cameraZoomOutKey= Keys.MINUS;
    int changeHandNumberKey= Keys.BACKSLASH;
    int increaseSpeedKey = Keys.CONTROL_RIGHT;
    int decreaseSpeedKey = Keys.ALT_RIGHT;
    int slashKey=Keys.SPACE;
    int thrustKey=Keys.T;
    int shootKey=Keys.ALT_LEFT;

   private  Array<InputKeyCombo> keys= new Array<>();

    public boolean isAMoveKey(int keycode){


        if( keycode == moveDownKey ||  keycode==moveUpKey||  keycode== moveLeftDownKey ||  keycode==moveLeftUpKey|| keycode== moveLeftKey || keycode==moveRightKey || keycode==moveRightUpKey || keycode==moveRightDownKey){
            return true;

        }

        return  false;

    }

    public int getMoveUpKey() {
        return moveUpKey;
    }

    public void setMoveUpKey(int moveUpKey) {
        this.moveUpKey = moveUpKey;
    }

    public int getMoveDownKey() {
        return moveDownKey;
    }

    public void setMoveDownKey(int moveDownKey) {
        this.moveDownKey = moveDownKey;
    }

    public int getMoveLeftKey() {
        return moveLeftKey;
    }

    public void setMoveLeftKey(int moveLeftKey) {
        this.moveLeftKey = moveLeftKey;
    }

    public int getMoveRightKey() {
        return moveRightKey;
    }

    public void setMoveRightKey(int moveRightKey) {
        this.moveRightKey = moveRightKey;
    }

    public int getSpeedUpKey() {
        return speedUpKey;
    }

    public void setSpeedUpKey(int speedUpKey) {
        this.speedUpKey = speedUpKey;
    }

    public int getThrowKey() {
        return throwKey;
    }

    public void setThrowKey(int throwKey) {
        this.throwKey = throwKey;
    }

    public int getPickUpItemKey() {
        return pickUpItemKey;
    }

    public void setPickUpItemKey(int pickUpItemKey) {
        this.pickUpItemKey = pickUpItemKey;
    }


    public int getChangeAttackModeKey() {
        return changeAttackModeKey;
    }

    public void setChangeAttackModeKey(int changeAttackModeKey) {
        this.changeAttackModeKey = changeAttackModeKey;
    }

    public int getChangeHoldPositionKey() {
        return changeHoldPositionKey;
    }

    public void setChangeHoldPositionKey(int changeHoldPositionKey) {
        this.changeHoldPositionKey = changeHoldPositionKey;
    }

    public int getEatKey() {
        return eatKey;
    }

    public void setEatKey(int eatKey) {
        this.eatKey = eatKey;
    }

    public int getDrinkKey() {
        return drinkKey;
    }

    public void setDrinkKey(int drinkKey) {
        this.drinkKey = drinkKey;
    }

    public int getDropItemKey() {
        return dropItemKey;
    }

    public void setDropItemKey(int dropItemKey) {
        this.dropItemKey = dropItemKey;
    }

    public int getCameraUpKey() {
        return cameraUpKey;
    }

    public void setCameraUpKey(int cameraUpKey) {
        this.cameraUpKey = cameraUpKey;
    }

    public int getCameraDownKey() {
        return cameraDownKey;
    }

    public void setCameraDownKey(int cameraDownKey) {
        this.cameraDownKey = cameraDownKey;
    }

    public int getCameraLeftKey() {
        return cameraLeftKey;
    }

    public void setCameraLeftKey(int cameraLeftKey) {
        this.cameraLeftKey = cameraLeftKey;
    }

    public int getCameraRightKey() {
        return cameraRightKey;
    }

    public void setCameraRightKey(int cameraRightKey) {
        this.cameraRightKey = cameraRightKey;
    }

    public int getCameraZoomInKey() {
        return cameraZoomInKey;
    }

    public void setCameraZoomInKey(int cameraZoomInKey) {
        this.cameraZoomInKey = cameraZoomInKey;
    }

    public int getCameraZoomOutKey() {
        return cameraZoomOutKey;
    }

    public void setCameraZoomOutKey(int cameraZoomOutKey) {
        this.cameraZoomOutKey = cameraZoomOutKey;
    }

    public int getMoveLeftDownKey() {
        return moveLeftDownKey;
    }

    public void setMoveLeftDownKey(int moveLeftDownKey) {
        this.moveLeftDownKey = moveLeftDownKey;
    }

    public int getMoveLeftUpKey() {
        return moveLeftUpKey;
    }

    public void setMoveLeftUpKey(int moveLeftUpKey) {
        this.moveLeftUpKey = moveLeftUpKey;
    }

    public int getMoveRightUpKey() {
        return moveRightUpKey;
    }

    public void setMoveRightUpKey(int moveRightUpKey) {
        this.moveRightUpKey = moveRightUpKey;
    }

    public int getMoveRightDownKey() {
        return moveRightDownKey;
    }

    public void setMoveRightDownKey(int moveRightDownKey) {
        this.moveRightDownKey = moveRightDownKey;
    }

    public int getChangeHandNumberKey() {
        return changeHandNumberKey;
    }

    public void setChangeHandNumberKey(int changeHandNumberKey) {
        this.changeHandNumberKey = changeHandNumberKey;
    }

    public int getIncreaseSpeedKey() {
        return increaseSpeedKey;
    }

    public void setIncreaseSpeedKey(int increaseSpeedKey) {
        this.increaseSpeedKey = increaseSpeedKey;
    }

    public int getDecreaseSpeedKey() {
        return decreaseSpeedKey;
    }

    public void setDecreaseSpeedKey(int decreaseSpeedKey) {
        this.decreaseSpeedKey = decreaseSpeedKey;
    }

    public int getSlashKey() {
        return slashKey;
    }


    public int getThrustKey() {
        return thrustKey;
    }



    public int getShootKey() {
        return shootKey;
    }

    public void setSlashKey(int slashKey) {
        this.slashKey = slashKey;
    }

    public void setThrustKey(int thrustKey) {
        this.thrustKey = thrustKey;
    }

    public void setShootKey(int shootKey) {
        this.shootKey = shootKey;
    }


    public void addKey( InputKeyCombo inputKeyCombo) {
        this.keys.add(inputKeyCombo);


    }



    @Override
    public Array<InputKeyCombo> getKeys() {
        return keys;

    }
}

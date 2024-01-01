package com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer;

import com.badlogic.gdx.Input.Keys;

public class PlayerKeys {
    int [] moveLeft= {Keys.LEFT};
    int []  moveRight= {Keys.RIGHT};
    int []  moveDown= {Keys.DOWN};
    int [] moveUp= {Keys.UP};
    int [] moveUpLeft= {Keys.LEFT, Keys.UP};
    int []  moveUpRight= {Keys.RIGHT, Keys.UP};
    int []  moveDownLeft= {Keys.DOWN, Keys.LEFT};
    int []  moveDownRight= {Keys.DOWN, Keys.RIGHT};

    int [] increaseSpeed= {Keys.CONTROL_RIGHT};
    int [] decreaseSpeed= {Keys.ALT_LEFT};
    int [] pickupItemLeft= {Keys.L};
    int [] pickupItemRight= {Keys.T};
    int [] slash= {Keys.S};
    int [] thrust= {Keys.T};
    int [] attack= {Keys.SPACE};
    int [] spellCast= {Keys.C};
    int [] ingestItem= {Keys.I};
    int [] addItemToPackRightHand= {Keys.P, Keys.R};
    int [] addItemToPackLeftHand= {Keys.P, Keys.L};
    int [] pause={Keys.ESCAPE};
    int [] displayPack= {Keys.ALT_RIGHT, Keys.P};

    public int[] getMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(int[] moveLeft) {
        this.moveLeft = moveLeft;
    }

    public int[] getMoveRight() {
        return moveRight;
    }

    public void setMoveRight(int[] moveRight) {
        this.moveRight = moveRight;
    }

    public int[] getMoveDown() {
        return moveDown;
    }

    public void setMoveDown(int[] moveDown) {
        this.moveDown = moveDown;
    }

    public int[] getMoveUp() {
        return moveUp;
    }

    public void setMoveUp(int[] moveUp) {
        this.moveUp = moveUp;
    }

    public int[] getMoveUpLeft() {
        return moveUpLeft;
    }

    public void setMoveUpLeft(int[] moveUpLeft) {
        this.moveUpLeft = moveUpLeft;
    }

    public int[] getMoveUpRight() {
        return moveUpRight;
    }

    public void setMoveUpRight(int[] moveUpRight) {
        this.moveUpRight = moveUpRight;
    }

    public int[] getMoveDownLeft() {
        return moveDownLeft;
    }

    public void setMoveDownLeft(int[] moveDownLeft) {
        this.moveDownLeft = moveDownLeft;
    }

    public int[] getIncreaseSpeed() {
        return increaseSpeed;
    }

    public void setIncreaseSpeed(int[] increaseSpeed) {
        this.increaseSpeed = increaseSpeed;
    }

    public int[] getDecreaseSpeed() {
        return decreaseSpeed;
    }

    public void setDecreaseSpeed(int[] decreaseSpeed) {
        this.decreaseSpeed = decreaseSpeed;
    }

    public int[] getPickupItemLeft() {
        return pickupItemLeft;
    }

    public void setPickupItemLeft(int[] pickupItemLeft) {
        this.pickupItemLeft = pickupItemLeft;
    }

    public int[] getPickupItemRight() {
        return pickupItemRight;
    }

    public void setPickupItemRight(int[] pickupItemRight) {
        this.pickupItemRight = pickupItemRight;
    }

    public int[] getSlash() {
        return slash;
    }

    public void setSlash(int[] slash) {
        this.slash = slash;
    }

    public int[] getThrust() {
        return thrust;
    }

    public void setThrust(int[] thrust) {
        this.thrust = thrust;
    }

    public int[] getAttack() {
        return attack;
    }

    public void setAttack(int[] attack) {
        this.attack = attack;
    }

    public int[] getSpellCast() {
        return spellCast;
    }

    public void setSpellCast(int[] spellCast) {
        this.spellCast = spellCast;
    }

    public int[] getIngestItem() {
        return ingestItem;
    }

    public void setIngestItem(int[] ingestItem) {
        this.ingestItem = ingestItem;
    }

    public int[] getAddItemToPackRightHand() {
        return addItemToPackRightHand;
    }

    public void setAddItemToPackRightHand(int[] addItemToPackRightHand) {
        this.addItemToPackRightHand = addItemToPackRightHand;
    }

    public int[] getAddItemToPackLeftHand() {
        return addItemToPackLeftHand;
    }

    public void setAddItemToPackLeftHand(int[] addItemToPackLeftHand) {
        this.addItemToPackLeftHand = addItemToPackLeftHand;
    }

    public int[] getPause() {
        return pause;
    }

    public void setPause(int[] pause) {
        this.pause = pause;
    }

    public int[] getDisplayPack() {
        return displayPack;
    }

    public void setDisplayPack(int[] displayPack) {
        this.displayPack = displayPack;
    }

    public int[] getMoveDownRight() {
        return moveDownRight;
    }

    public void setMoveDownRight(int[] moveDownRight) {
        this.moveDownRight = moveDownRight;
    }
}

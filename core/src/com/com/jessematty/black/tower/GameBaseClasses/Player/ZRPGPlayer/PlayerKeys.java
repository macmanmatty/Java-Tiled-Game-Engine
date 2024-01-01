package com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer;
import com.badlogic.gdx.Input.Keys;
/**
 *
 * DTO class for the key functions that control the player actions
 */
public class PlayerKeys {
    private int [] moveLeft= {Keys.LEFT};
    private int []  moveRight= {Keys.RIGHT};
    private int []  moveDown= {Keys.DOWN};
    private int [] moveUp= {Keys.UP};
    private int [] moveUpLeft= {Keys.LEFT, Keys.UP};
    private int []  moveUpRight= {Keys.RIGHT, Keys.UP};
    private int []  moveDownLeft= {Keys.DOWN, Keys.LEFT};
    private int []  moveDownRight= {Keys.DOWN, Keys.RIGHT};
    private int [] increaseSpeed= {Keys.CONTROL_RIGHT};
    private int [] decreaseSpeed= {Keys.ALT_LEFT};
    private int [] pickupItem = {Keys.SHIFT_RIGHT};
    private int [] slash= {Keys.S};
    private int [] thrust= {Keys.T};
    private int [] attack= {Keys.SPACE};
    private int [] spellCast= {Keys.C};
    private int [] ingestItem= {Keys.I};
    private int [] addItemToPack = {Keys.P};
    private int [] pause={Keys.ESCAPE};
    private int [] displayPack= {Keys.ALT_RIGHT, Keys.P};
    private int [] dropItem= {Keys.ALT_RIGHT, Keys.D};
    private int [] switchHand= {Keys.ENTER};

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
    public int[] getPickupItem() {
        return pickupItem;
    }
    public void setPickupItem(int[] pickupItem) {
        this.pickupItem = pickupItem;
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
    public int[] getAddItemToPack() {
        return addItemToPack;
    }
    public void setAddItemToPack(int[] addItemToPack) {
        this.addItemToPack = addItemToPack;
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

    public int[] getDropItem() {
        return dropItem;
    }

    public void setDropItem(int[] dropItem) {
        this.dropItem = dropItem;
    }

    public int[] getSwitchHand() {
        return switchHand;
    }

    public void setSwitchHand(int[] switchHand) {
        this.switchHand = switchHand;
    }
}

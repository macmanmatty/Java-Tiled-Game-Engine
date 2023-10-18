package com.jessematty.black.tower.Components.AttachEntity;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Other.HoldPosition;


/**
 * component for components  that hold other components IE hands
 *
 */
public class Holder   implements Component {
    /**
     * the  id of the current entity being held
     */
    private String itemToHoldId=null;
    /**
     * the x and y draw offsets of the item for displaying it
     */
    private float drawOffSetX;
    private float drawOffSetY;
    /**
     * what groups can the item hold
     */
    private Array<String> holdableItemGroups= new Array<>();
    /**
     * the current  holding position of the item
     */
    private HoldPosition holdPosition=HoldPosition.OUT;
    /**
     * whether or not the current holding position of the item has changed
     */
    private  boolean holdPositionChanged=true;
    /**
     * the current height the item is held at
     */
    private float holdHeight=4f;

    /**
     * whether or not render the held on the screen
     */

    private boolean drawHeldItem;


    public Holder() {
    }

    public Holder(String itemToHoldId) {
        this.itemToHoldId = itemToHoldId;
    }

    public Holder(String itemToHoldID, float drawOffSetX, float drawOffSetY) {
       this.itemToHoldId=itemToHoldID;
        this.drawOffSetX = drawOffSetX;
        this.drawOffSetY = drawOffSetY;
        drawHeldItem=true;
    }
    public String getItemToHoldId() {
        return itemToHoldId;
    }
    public void setItemToHoldId(String itemToHoldId) {
        this.itemToHoldId = itemToHoldId;
    }
    public  void removeItem(){
        itemToHoldId=null;
    }
    public float getDrawOffSetX() {
        return drawOffSetX;
    }
    public void setDrawOffSetX(float drawOffSetX) {
        this.drawOffSetX = drawOffSetX;
    }
    public float getDrawOffSetY() {
        return drawOffSetY;
    }
    public void setDrawOffSetY(float drawOffSetY) {
        this.drawOffSetY = drawOffSetY;
    }
    public Array<String> getHoldableItemGroups() {
        return holdableItemGroups;
    }

    public HoldPosition getHoldPosition() {
        return holdPosition;
    }

    public void setHoldPosition(HoldPosition holdPosition) {
        this.holdPosition = holdPosition;
        holdPositionChanged=true;
    }


    public boolean isHoldPositionChanged() {
        return holdPositionChanged;
    }


    public void setHoldPositionChanged(boolean holdPositionChanged) {
        this.holdPositionChanged = holdPositionChanged;
    }

    public float getHoldHeight() {
        return holdHeight;
    }

    public void setHoldHeight(float holdHeight) {
        this.holdHeight = holdHeight;
    }

    public boolean isDrawHeldItem() {
        return drawHeldItem;
    }

    public void setDrawHeldItem(boolean drawHeldItem) {
        this.drawHeldItem = drawHeldItem;
    }
}

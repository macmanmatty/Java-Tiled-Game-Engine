package com.jessematty.black.tower.Components.AttachEntity;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.HoldPosition;

public class Holder   implements Component {
    private String itemToHoldId=null;
    private float drawOffSetX;
    private float drawOffSetY;
    private Array<String> holdableItemGroups= new Array<>();
    private HoldPosition holdPosition=HoldPosition.OUT;
    private  boolean holdPositionChanged=true;
    private float holdHeight=4f;


    public Holder() {
    }
    public Holder(String itemToHoldID, float drawOffSetX, float drawOffSetY) {
       this.itemToHoldId=itemToHoldID;
        this.drawOffSetX = drawOffSetX;
        this.drawOffSetY = drawOffSetY;
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
}

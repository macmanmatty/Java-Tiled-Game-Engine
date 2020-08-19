package com.jessematty.black.tower.Components;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class Holder   implements Component {
    private String itemToHoldId;
    private float drawOffSetX;
    private float drawOffSetY;
    private Array<String> holdableItemGroups= new Array<>();
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
}

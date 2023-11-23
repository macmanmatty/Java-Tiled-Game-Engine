package com.jessematty.black.tower.Components.AttachEntity;
import com.badlogic.ashley.core.Component;
public class EquipItem  implements Component {
   private  String equiperID;
   private  boolean holdItem;
    public String getEquiperID() {
        return equiperID;
    }
    public void setEquiperID(String equiperID) {
        this.equiperID = equiperID;
    }
    public boolean isHoldItem() {
        return holdItem;
    }
    public void setHoldItem(boolean holdItem) {
        this.holdItem = holdItem;
    }
}

package com.jessematty.black.tower.Components.AttachEntity;
import com.badlogic.ashley.core.Component;
public class EquipItem  implements Component {
   private  String itemId;
   private String bodyPartId;
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getBodyPartId() {
        return bodyPartId;
    }

    public void setBodyPartId(String bodyPartId) {
        this.bodyPartId = bodyPartId;
    }
}

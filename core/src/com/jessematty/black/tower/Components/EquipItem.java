package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

public class EquipItem  implements Component {

   private  String equiperID;

    public String getEquiperID() {
        return equiperID;
    }

    public void setEquiperID(String equiperID) {
        this.equiperID = equiperID;
    }
}

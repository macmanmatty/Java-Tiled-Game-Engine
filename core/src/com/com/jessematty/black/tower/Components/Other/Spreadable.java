package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;

public class Spreadable implements Component {
  private   boolean [][] spreadMap= new boolean[3][3];

    public boolean[][] getSpreadMap() {
        return spreadMap;
    }

    public void setSpreadMap(boolean[][] spreadMap) {
        this.spreadMap = spreadMap;
    }
}

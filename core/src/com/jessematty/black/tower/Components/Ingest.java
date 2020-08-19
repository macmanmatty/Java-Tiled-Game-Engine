package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

public class Ingest implements Component {


   private  String ingestorid;


   public String getIngestorid() {
      return ingestorid;
   }

   public void setIngestorid(String ingestorid) {
      this.ingestorid = ingestorid;
   }
}


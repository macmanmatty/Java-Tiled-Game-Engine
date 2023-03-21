package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;

public class Ingest implements Component {


   private  String ingestorID;


   public String getIngestorID() {
      return ingestorID;
   }

   public void setIngestorID(String ingestorID) {
      this.ingestorID = ingestorID;
   }
}


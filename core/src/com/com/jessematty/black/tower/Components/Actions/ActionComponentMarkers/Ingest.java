package com.jessematty.black.tower.Components.Actions.ActionComponentMarkers;

import com.badlogic.ashley.core.Component;

public class Ingest implements Component {

    String ingestorID;

    public String getIngestorID() {
        return ingestorID;
    }

    public void setIngestorID(String ingestorID) {
        this.ingestorID = ingestorID;
    }
}



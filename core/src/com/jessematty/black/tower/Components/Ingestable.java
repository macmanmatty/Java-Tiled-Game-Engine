package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ActionComponent;

public class Ingestable implements ActionableComponent {

    private Entity ingestor;

    public Entity getIngestor() {
        return ingestor;
    }

    public void setIngestor(Entity ingestor) {
        this.ingestor = ingestor;
    }
}

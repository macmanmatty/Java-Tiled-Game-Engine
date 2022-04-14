package com.jessematty.black.tower.Components.Actions.ActionComponentMarkers;

import com.badlogic.ashley.core.Component;

public interface ActionMarkerComponent extends Component {

    boolean isFinished();
    void setFinished(boolean finished);

}

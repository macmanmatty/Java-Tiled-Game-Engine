package com.jessematty.black.tower.Components.Item;

import com.badlogic.ashley.core.Component;

public interface ActionMarkerComponent extends Component {

    boolean isFinished();
    void setFinished(boolean finished);

}

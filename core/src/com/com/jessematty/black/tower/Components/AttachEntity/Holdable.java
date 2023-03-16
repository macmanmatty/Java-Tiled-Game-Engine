package com.jessematty.black.tower.Components.AttachEntity;

import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionableComponent;

public class Holdable implements ActionableComponent {

  private Array<String> holdGroups= new Array<String>();


    private String holdErrorMessage="Cannot Hold Item!";
    private String holdErrorMessageTitle="Cannot Hold Item!";

    public String getHoldErrorMessage() {
        return holdErrorMessage;
    }

    public void setHoldErrorMessage(String holdErrorMessage) {
        this.holdErrorMessage = holdErrorMessage;
    }

    public String getHoldErrorMessageTitle() {
        return holdErrorMessageTitle;
    }

    public void setHoldErrorMessageTitle(String holdErrorMessageTitle) {
        this.holdErrorMessageTitle = holdErrorMessageTitle;
    }

    public Array<String> getHoldGroups() {
        return holdGroups;
    }


}


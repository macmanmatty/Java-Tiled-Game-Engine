package com.jessematty.black.tower.Components.AttachEntity;

import com.badlogic.ashley.core.Component;

public class UnHoldItem implements Component {

   private  Component detachItemAction;

    public Component getDetachItemAction() {
        return detachItemAction;
    }

    public void setDetachItemAction(Component detachItemAction) {
        this.detachItemAction = detachItemAction;
    }
}

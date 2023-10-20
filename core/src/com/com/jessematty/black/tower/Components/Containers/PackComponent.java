package com.jessematty.black.tower.Components.Containers;


import com.badlogic.gdx.utils.Array;

public class PackComponent extends ContainerComponent{

    private String fullMessage=" Pack is Full!!!";
    private String fullTitle="Pack is Full";
    private boolean fixedNumberOfSlots;
    private Array<PackSlot> slots =new Array();

    public String getFullMessage() {
        return fullMessage;
    }

    public void setFullMessage(String fullMessage) {
        this.fullMessage = fullMessage;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public boolean isFixedNumberOfSlots() {
        return fixedNumberOfSlots;
    }

    public void setFixedNumberOfSlots(boolean fixedNumberOfSlots) {
        this.fixedNumberOfSlots = fixedNumberOfSlots;
    }

    public Array<PackSlot> getSlots() {
        return slots;
    }
}

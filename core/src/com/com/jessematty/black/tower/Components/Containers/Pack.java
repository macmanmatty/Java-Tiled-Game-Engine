package com.jessematty.black.tower.Components.Containers;

import com.jessematty.black.tower.Components.Container;

public class Pack extends Container {

    private String fullMessage=" Pack is Full!!!";
    private String fullTitle="Pack is Full";
    private boolean fixedNumberOfSlots;
    private int numberOfSlots=20;

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

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    public void setNumberOfSlots(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }
}

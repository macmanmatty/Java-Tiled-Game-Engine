package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.ListWindow;

public interface ListWindowActions {
    /**
     *  action to be performed every turn
     */
    public void act();
    /**
     *  action to be preformed when an object is selected
     */
    public void setSelectedAction();
    /**
     *  action to be preformed when an object is removed
     */
    public void removeAction();
    /**
     *  action to be preformed when an object is added
     */
    public void addAction();

}

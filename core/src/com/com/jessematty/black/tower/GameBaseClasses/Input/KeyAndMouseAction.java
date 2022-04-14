package com.jessematty.black.tower.GameBaseClasses.Input;


// interface for action to be performed when keys are pressed and mouse is clicked
public interface KeyAndMouseAction {

    public  void action( int mouseButton,  int screenX, int ScreenY, int... keys);

}

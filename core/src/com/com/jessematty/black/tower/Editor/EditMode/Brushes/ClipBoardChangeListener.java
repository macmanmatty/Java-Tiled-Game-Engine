package com.jessematty.black.tower.Editor.EditMode.Brushes;

public interface ClipBoardChangeListener {

    /** listener for when the object on the  editor clipboard NOT the system clipboard has changed;**/
    public void objectChanged(Object object);

}

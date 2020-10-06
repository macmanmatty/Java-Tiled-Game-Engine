package com.jessematty.black.tower.Editor.EditMode.Windows;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Editor.EditMode.Screens.Interfaces.EditScreen;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.ClosableWindow;

public  abstract class EditWindow extends ClosableWindow {

    protected final Skin skin;
    protected final EditScreen editScreen;
    private float fontScale=.8f;



    public EditWindow(EditScreen editScreen, String title , Skin skin, String style) {
        super(title, skin, style);


        this.skin=skin;

        this.editScreen=editScreen;





    }

    public   void makeWindow(){}








    public EditScreen getEditScreen() {
        return editScreen;
    }

    public void setWindowSize(float width, float height){

        setHeight(height);
        setWidth(width);

    }


    public float getFontScale() {
        return fontScale;
    }


}

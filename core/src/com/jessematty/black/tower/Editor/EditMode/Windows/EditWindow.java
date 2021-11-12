package com.jessematty.black.tower.Editor.EditMode.Windows;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Editor.EditMode.Screens.Interfaces.EditScreen;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.ClosableWindow;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindow;

public   class EditWindow extends GameWindow {

    protected final Skin skin;
    protected final EditScreen editScreen;
    private float fontScale=.8f;



    public EditWindow(EditScreen editScreen, String title , Skin skin, String style) {
        super(title, skin, style,editScreen.getGameAssets());


        this.skin=skin;

        this.editScreen=editScreen;





    }

    public   void makeWindow(){}

    public void clearWindow(){
        clear();
    }








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

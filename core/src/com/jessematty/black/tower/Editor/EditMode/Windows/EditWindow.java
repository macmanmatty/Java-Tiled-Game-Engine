package com.jessematty.black.tower.Editor.EditMode.Windows;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Editor.EditMode.Screens.Interfaces.EditScreen;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindow;
public   class EditWindow extends GameWindow {
    protected final Skin skin;
    private float fontScale=.8f;
    public EditWindow(GameAssets gameAssets, String title , Skin skin, String style) {
        super(title, skin, style,gameAssets);
        this.skin=skin;
    }
    /**
     *  abstract method used to create the window
     */
    public   void makeWindow(){}
    public void clearWindow(){
        clear();
    }
    public void setWindowSize(float width, float height){
        setHeight(height);
        setWidth(width);
    }
    public float getFontScale() {
        return fontScale;
    }
}

package com.jessematty.black.tower.Editor.EditMode.Windows;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Editor.EditMode.EditorSettable;
import com.jessematty.black.tower.Editor.EditMode.Screens.Interfaces.EditScreen;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindow;
public   class EditWindow extends GameWindow  implements EditorSettable {
    private float fontScale=.8f;
    public MapEditScreen mapEditScreen;
    public EditWindow(GameAssets gameAssets, String title , Skin skin, String style) {
        super(title, skin, style,gameAssets);
    }

    public void clearWindow(){
        getChildren().clear();
    }
    public void setWindowSize(float width, float height){
        setHeight(height);
        setWidth(width);
    }
    public float getFontScale() {
        return fontScale;
    }

    public MapEditScreen getEditor() {
        return mapEditScreen;
    }

    public void setEditor(MapEditScreen mapEditScreen) {
        this.mapEditScreen = mapEditScreen;
    }
}

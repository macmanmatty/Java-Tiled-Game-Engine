package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
public class GameWindow extends ClosableWindow {
    boolean created;
     private final  GameAssets gameAssets;
     private Array<InputKeyCombo> inputKeyComboList= new Array<InputKeyCombo>();
    public GameWindow(String title, Skin skin, GameAssets gameAssets) {
        super(title, skin);
        this.gameAssets = gameAssets;
    }
    public GameWindow(String title, Skin skin, String styleName, GameAssets gameAssets) {
        super(title, skin, styleName);
        this.gameAssets=gameAssets;
    }
    public void makeWindow(){
        created=true;
    }
    public  void act(float delta) {
        super.act(delta);
        if (inputKeyComboList.size > 0) {
            if (getStage().getKeyboardFocus().equals(this)) {
                gameAssets.getGameInput().getKeyListener().addInputKeys(inputKeyComboList);
            } else {
                gameAssets.getGameInput().getKeyListener().removeInputKeys(inputKeyComboList);
            }
        }
    }

    public GameAssets getGameAssets() {
        return gameAssets;
    }
}

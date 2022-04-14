package com.jessematty.black.tower.GameBaseClasses.UIClasses.DropDownMenus;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.ChangableLabel;

public class  DropDownMenu  <T>   {
    private Image imageUp;
    private Image imageDown;
    private ChangableLabel titleText;
    private List<T> items;
    private HorizontalGroup title= new HorizontalGroup();
    private Skin skin;
    private int clicks;
    public DropDownMenu(Image imageUp, Image imageDown, Skin skin, String titleText) {
        this.imageUp = imageUp;
        this.imageDown = imageDown;
        this.titleText =  new ChangableLabel( titleText, skin);
        this.skin=skin;
        items= new List<T>(skin);
        items.setVisible(false);
        makeMenu();
        
    }
    private void makeMenu() {
        if(imageUp!=null) {
            title.addActor(imageUp);
        }
        title.addActor(titleText);
        title.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (button == Buttons.LEFT) {
                    if (items.isVisible() == false) {
                        items.setVisible(true);
                        if (imageDown != null) {
                            title.clear();
                            title.addActor(imageDown);
                            title.addActor(titleText);
                        }
                    } else {
                        items.setVisible(false);
                        if (imageUp != null) {
                            title.clear();
                            title.addActor(imageUp);
                            title.addActor(titleText);
                        }
                    }
                }
                if (button == Buttons.RIGHT) {
                    titleText.changeToTextField();
                    
                }
            }
        
        
            
            
        });
        
    }
}

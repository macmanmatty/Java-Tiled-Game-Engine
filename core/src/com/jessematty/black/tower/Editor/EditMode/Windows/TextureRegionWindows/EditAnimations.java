package com.jessematty.black.tower.Editor.EditMode.Windows.TextureRegionWindows;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;

public class EditAnimations extends MapEditWindow {

    private AnimatableComponent animatable;
    private boolean eightDirections;

    public EditAnimations(MapEditScreen mapEditScreen, String title, Skin skin, String style) {
        super(mapEditScreen, title, skin, style);
    }





    public AnimatableComponent getAnimatable() {
        return animatable;
    }

    public void setAnimatable(AnimatableComponent animatable) {
        this.animatable = animatable;
    }

    public boolean isEightDirections() {
        return eightDirections;
    }

    public void setEightDirections(boolean eightDirections) {
        this.eightDirections = eightDirections;
    }

    @Override
    public void makeWindow() {

    }
}


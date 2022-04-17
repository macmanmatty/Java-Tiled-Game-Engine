package com.jessematty.black.tower.Components.Actions;

import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.ColorSettable;
import com.jessematty.black.tower.Components.DirectionalAnimation;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;

public class ActionAnimation  implements ColorSettable {

    private ObjectMap< String, DirectionalAnimation> directionalAnimations= new ObjectMap<>(8);
    private NamedColor color;
    private ActionComponent actionComponent;

    public void addAnimation(DirectionalAnimation directionalAnimation){
        this.directionalAnimations.put(directionalAnimation.getDirection().toString(), directionalAnimation);
    }

    public ObjectMap<String, DirectionalAnimation> getDirectionalAnimations() {
        return directionalAnimations;
    }

    public void setDirectionalAnimations(ObjectMap<String, DirectionalAnimation> directionalAnimations) {
        this.directionalAnimations = directionalAnimations;
    }

    public NamedColor  getColor() {
        return color;
    }

    public void setColor(NamedColor color) {
        this.color = color;
    }

    public ActionComponent getActionComponent() {
        return actionComponent;
    }

    public void setActionComponent(ActionComponent actionComponent) {
        this.actionComponent = actionComponent;
    }
}

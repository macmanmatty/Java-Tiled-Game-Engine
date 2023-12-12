package com.jessematty.black.tower.Generators.Components.Animation;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Generators.Components.ExternalComponentDTO;

/**
 * DTO class for an animatable component;
 */
public class AnimatableComponentDTO implements ExternalComponentDTO {
    /**
     * the array of animation DTOs
     */
   private  Array<AnimationDTO> animationDTOArray= new Array<>();
   private Class<? extends Component> componentClass;
   private String id;

    public Array<AnimationDTO> getAnimationDTOArray() {
        return animationDTOArray;
    }

    public void setAnimationDTOArray(Array<AnimationDTO> animationDTOArray) {
        this.animationDTOArray = animationDTOArray;
    }

    public Class<? extends Component> getComponentClass() {
        return componentClass;
    }

    public void setComponentClass(Class<? extends Component> componentClass) {
        this.componentClass = componentClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

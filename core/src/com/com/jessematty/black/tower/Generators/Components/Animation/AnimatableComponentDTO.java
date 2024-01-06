package com.jessematty.black.tower.Generators.Components.Animation;

import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Generators.Components.ExternalComponentDTO;

/**
 * DTO class for an animatable component;
 */
public class AnimatableComponentDTO implements ExternalComponentDTO {
    /**
     * the array of animation DTOs
     */
    private Array<AnimationDTO> animationDTOArray = new Array<>();
    public Array<AnimationDTO> getAnimationDTOArray() {
        return animationDTOArray;
    }

    public void setAnimationDTOArray(Array<AnimationDTO> animationDTOArray) {
        this.animationDTOArray = animationDTOArray;
    }
}

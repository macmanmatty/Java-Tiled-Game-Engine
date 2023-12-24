package com.jessematty.black.tower.ZRPGTest;

import com.jessematty.black.tower.Generators.Components.Animation.AnimatableComponentDTO;
import com.jessematty.black.tower.Generators.Components.Animation.AnimationDTO;
import com.jessematty.black.tower.Generators.Components.Animation.AnimationDirections;

public class TestAnimations {
    static AnimationDTO potionAnimation= new AnimationDTO();

    static AnimationDTO swordOnGround= new AnimationDTO();
    
    static AnimatableComponentDTO sword= new AnimatableComponentDTO();


    static AnimatableComponentDTO potion= new AnimatableComponentDTO();

    static {
        swordOnGround.setAction("restOnGround");
        swordOnGround.setAnimationDirections(AnimationDirections.ONE);
        swordOnGround.setAtlasName("textureAtlases/testAssets/testAssets.atlas");
        swordOnGround.setFrames(1);
        swordOnGround.setBodyName("sword1");
        swordOnGround.setFrameRate(1);
        swordOnGround.setLayerNumbers(new int [] {Integer.MIN_VALUE});

        potionAnimation.setAction("restOnGround");
        potionAnimation.setAnimationDirections(AnimationDirections.ONE);
        potionAnimation.setAtlasName("textureAtlases/testAssets/testAssets.atlas");
        potionAnimation.setCalculateFrames(true);
        potionAnimation.setBodyName("potionOragneABubble");
        potionAnimation.setFrameRate(1);
        potionAnimation.setLayerNumbers(new int [] {-1});
        sword.getAnimationDTOArray().add(swordOnGround);
        potion.getAnimationDTOArray().add(potionAnimation);
    }
}

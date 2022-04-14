package com.jessematty.black.tower.Generators.Animations;

import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

public  abstract class AnimationGenerator   {


  private  AnimatableComponent animatableComponent;
  private GameAssets gameAssets;

    public AnimationGenerator(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
    }


    public    abstract AnimatableComponent generateAnimatableComponent();




    public AnimatableComponent getAnimatableComponent() {
        return animatableComponent;
    }
}

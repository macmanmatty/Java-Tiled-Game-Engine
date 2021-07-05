package com.jessematty.black.tower.Components.Animation;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;

public class AnimationUtilities {

   public static Array<AtlasNamedAtlasRegion> getAnimationRegions(AnimatableComponent animatableComponent){

       Array<AtlasNamedAtlasRegion> atlasNamedAtlasRegions= new Array<>();
       ObjectMap<String, ObjectMap<String, Animation>> animations= animatableComponent.getAnimations();
       Keys<String> keys=animations.keys();
       while( keys.hasNext==true ) {
           String key=keys.next();
           ObjectMap<String,  Animation> names=animations.get(key);
           Keys<String> keys2=names.keys();
           while(keys2.hasNext==true ) {
               String key2=keys2.next();
               Animation animation=names.get(key2);
               AtlasNamedAtlasRegion [] regions=animation.getFrames();
               atlasNamedAtlasRegions.addAll(regions);

           }
       }


       return  atlasNamedAtlasRegions;
    }
}

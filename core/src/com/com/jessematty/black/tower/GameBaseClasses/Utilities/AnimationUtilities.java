package com.jessematty.black.tower.GameBaseClasses.Utilities;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.Animation;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
public class AnimationUtilities {
    private  AnimationUtilities() {
    }
    /**
     * creates a new animation with a white color ie no color
     * @param frames the AtlasNamedAtlasRegions that make up frames of the animation
     * @param direction the direction the animation is displayed in 
     * @param action the action linked to the animation IE walk slash die etc. 
     * @param frameRate the frame rate of the animation
     * @param offsets the  draw offsets of the animation from the position ofg the entity
     * @param sublayerNumber the layer number of the animation  ie the drawable sublayer number @See Drawable.class
     * @return a new Animation Object
     */
    public static  Animation createAnimation(AtlasNamedAtlasRegion [] frames, Direction direction, String action, int frameRate, Vector2 offsets, int sublayerNumber, int layerNumber) {
        return createAnimation(frames , direction, action, frameRate, offsets, sublayerNumber, layerNumber,  NamedColor.WHITE);
    }
    /**
     * creates a new animation object to be used with an AnimatableComponent @See AnimataleComponent.class
     * @param frames the AtlasNamedAtlasRegions that make up frames of the animation
     * @param direction the direction the animation is displayed in 
     * @param action the action linked to the animation IE walk slash die etc. 
     * @param frameRate the frame rate of the animation
     * @param offsets the  draw offsets of the animation from the position ofg the entity
     * @param sublayerNumber the layer number of the animation  ie the drawable sublayer number @See Drawable.class
     * @param color the color of the animation
     * @param layerNumber the layer number of the animation
     * @return a new Animation Object
     */
        public static  Animation createAnimation(AtlasNamedAtlasRegion [] frames, Direction direction, String action, int frameRate, Vector2 offsets, int sublayerNumber, int layerNumber,  NamedColor color){
            Animation animation = new Animation();
            if(frames.length==0 ){
                throw new IllegalArgumentException("frames length must be >0");
            }
            if(frameRate<=0){
                throw new IllegalArgumentException("animation frame rate  must be >0");
            }
            for(AtlasNamedAtlasRegion atlasNamedAtlasRegion: frames ){
                if(atlasNamedAtlasRegion==null) {
                    throw new IllegalArgumentException("frame is missing");
                }
            }
            if( action==null|| action.isEmpty() ) {
                throw new IllegalArgumentException("action is null or blank");
            }
            animation.setFrames(frames);
            animation.setDirection(direction);
            animation.setAction(action);
            animation.setFrameRate(frameRate);
            animation.setOffsets(offsets);
            animation.setSubLayerNumber(sublayerNumber);
            animation.setLayerNumber(layerNumber);
            animation.setColor(color);
            return animation;
        }
    public static Array<AtlasNamedAtlasRegion> getAnimationRegions(AnimatableComponent animatableComponent){
        Array<AtlasNamedAtlasRegion> atlasNamedAtlasRegions= new Array<>();
        ObjectMap<String, ObjectMap<String, Animation>> animations= animatableComponent.getAnimations();
        ObjectMap.Keys<String> keys=animations.keys();
        while( keys.hasNext==true ) {
            String key=keys.next();
            ObjectMap<String,  Animation> names=animations.get(key);
            ObjectMap.Keys<String> keys2=names.keys();
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

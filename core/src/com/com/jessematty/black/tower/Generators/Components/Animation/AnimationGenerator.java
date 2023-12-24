package com.jessematty.black.tower.Generators.Components.Animation;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.Animation;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.JsonLoader;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.Generators.Components.ComponentGenerator;

import org.apache.commons.lang3.StringUtils;
/**
 * class for generating a new AnimatableComponent object @See Animation.class
 * @See AnimatableComponent.class
 */
public   class AnimationGenerator implements ComponentGenerator<AnimatableComponent> {
  private GameAssets gameAssets;
  private JsonLoader jsonLoader;
    public AnimationGenerator(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
        jsonLoader=gameAssets.getJsonLoader();
    }
    @Override
    public AnimatableComponent generateComponent(String filePath, FileUtilities.FileHandleType type) {
        AnimatableComponentDTO animationDTOS=jsonLoader.loadObject(AnimatableComponentDTO.class, filePath, type);
        return generateAnimatableComponent(animationDTOS);
    }
    /**
     * generates a new Animatable Component for an Entity
     * from an array of animation DTOs
     * @param animatableComponentDTO the Animation Component DTO Object
     * @return AnimatableComponent
     */
    public   AnimatableComponent generateAnimatableComponent ( AnimatableComponentDTO animatableComponentDTO){
        AnimatableComponent animatableComponent= new AnimatableComponent();
        Array<AnimationDTO> animationDTOS=animatableComponentDTO.getAnimationDTOArray();
        for(AnimationDTO animationDTO: animationDTOS){
            Array<Animation> animations=generateAnimation(animationDTO);
            for(Animation animation: animations){
                animatableComponent.addAnimation(animation);
            }
        }
        return animatableComponent;
    }
    /**
     * Generates a new Animation Object from an AnimationDTO Object
     * @param animationDTO the AnimationDTO Object
     * @return
     */
    private Array<Animation> generateAnimation(AnimationDTO animationDTO) {
        Array<Animation> animations=new Array<Animation>();
        int numberOfAnimations=animationDTO.getAnimationDirections();
        for(int count=0; count<numberOfAnimations; count++){
          Animation animation=  generateAnimation(animationDTO, count);
          animations.add(animation);
        }
        return animations;
    }
    /**
     * Generates a new Animation Object from an AnimationDTo Object
     * and the current  number .
     * @param animationDTO the AnimationDTO Object
     * @return
     */
    private Animation generateAnimation(AnimationDTO animationDTO, int count) {
        Direction direction=getDirection(count);
        String action=animationDTO.getAction();
        String animationName = animationDTO.getBodyName() + action;
        // only one direction don't require direction asd part of the name
        if(animationDTO.getAnimationDirections()>1) {
            String directionString= StringUtils.capitalize(direction.toString().toLowerCase());
            animationName=animationName+directionString;
        }
        int numberOfFrames=animationDTO.getFrames();
        int frameRate=animationDTO.getFrameRate();
        boolean calculateFrames=animationDTO.isCalculateFrames();
        if(numberOfFrames<=0 && !calculateFrames){
            throw new IllegalArgumentException("number of animation frames must be >0");
        }
        if(frameRate<=0){
            throw new IllegalArgumentException("animation frame rate  must be >0");
        }
        AtlasNamedAtlasRegion [] frames=null;
        if(calculateFrames){
         frames=   generateFrames(animationName, animationDTO.getAtlasName());
        }else {
            frames = generateFrames(animationName, animationDTO.getAtlasName(), numberOfFrames);
        }
        Vector2 offsets=new Vector2(animationDTO.getxOffset(), animationDTO.getyOffset());
        int layerNumber=animationDTO.getLayerNumbers()[count];
        int sublayerNumber=animationDTO.getSublayerNumbers()[count];
        Animation animation= new Animation();
        animation.setFrames(frames);
        animation.setDirection(direction);
        animation.setAction(action);
        animation.setFrameRate(frameRate);
        animation.setOffsets(offsets);
        animation.setSubLayerNumber(sublayerNumber);
        animation.setLayerNumber(layerNumber);
        animation.setColor(animationDTO.getNamedColor());
        return  animation;
    }
    /**
     * generates  the frames for a given animation
     * from a given animation name  and given TextureAtlas name and given number of frames
     * starting at number zero attempts to find all of the the
     * animation frames in the sequence until the number of frames are reached
     * @param animationName
     * @param atlasName
     * @return
     */
    private AtlasNamedAtlasRegion[] generateFrames(String animationName, String atlasName,  int numberOfFrames) {
        AtlasNamedAtlasRegion [] frames= new AtlasNamedAtlasRegion[numberOfFrames];
        for(int count=0; count<numberOfFrames; count++){
            animationName=animationName+count;
          AtlasNamedAtlasRegion atlasNamedAtlasRegion=  gameAssets.getAtlasRegionByName(animationName ,atlasName);
          if(atlasNamedAtlasRegion==null){
              throw new NullPointerException("Animation Frame: "+animationName +" is missing");
              
          }
          frames[count]=atlasNamedAtlasRegion;
            
        }
        return  frames;
    }
    /**
     * generates  the frames for a given animation
     * from a given animation name  and given TextureAtlas name
     * starting at number zero attempts to find all of the the
     * animation frames in the sequence stops when a given texture for
     * a frame is null
     * @param animationName
     * @param atlasName
     * @return
     */
    private AtlasNamedAtlasRegion[] generateFrames(String animationName, String atlasName) {
        Array<AtlasNamedAtlasRegion> regions= new Array<>();
        int counter=0;
            while(true){
            animationName=animationName+counter;
         AtlasNamedAtlasRegion  atlasNamedAtlasRegion=  gameAssets.getAtlasRegionByName(animationName ,atlasName);
            if(atlasNamedAtlasRegion==null){
                if(counter==0){
                    throw new IllegalArgumentException("Animation Frame: "+animationName +" is missing");
                }
                break;
            }
            counter++;
            regions.add(atlasNamedAtlasRegion);
        }
        return  regions.toArray(AtlasNamedAtlasRegion.class);
    }
    /**
     * translates a number to a direction
     * @param count the number to translate
     * @return a Direction  Object
     */
    private Direction getDirection(int count) {
        switch (count){
            case 1: {
                return Direction.UP;
            }
            case 2: {
                return Direction.LEFT;
            }
            case 3: {
                return Direction.RIGHT;
            }
            case 4: {
                return Direction.LEFTDOWN;
            }
            case 5: {
                return Direction.RIGHTDOWN;
            }
            case 6: {
                return Direction.RIGHTUP;
            }
            case 7: {
                return Direction.LEFTUP;
            }
            case 0:
            default:{
                return Direction.DOWN;
            }
        }
    }
}

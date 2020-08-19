package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Markers.AnimationFinished;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Entity.Transient;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangeMode;
import com.jessematty.black.tower.Components.Animation.Drawable;
import com.jessematty.black.tower.Components.Glow;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.Animation.AnimationState;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;

@Transient
public  class AnimationSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<Drawable> drawableComponentMapper;
    private ComponentMapper<AnimatableComponent> animatableComponentMapper;
    private ComponentMapper<Action> actionComponentMapper;
    private ComponentMapper<Position> positionComponentMapper;
    private  ComponentMapper<Glow> glowComponentMapper;
    private RenderSystem renderSystem;
    public AnimationSystem(MapDraw draw, RenderSystem system, int priority) {
        super(priority, draw );
        this.renderSystem=system;
    }
    @Override
    public void addedToEngine(Engine engine) {
        drawableComponentMapper =getGameComponentMapper().getDrawableComponentMapper();
        animatableComponentMapper =getGameComponentMapper().getAnimatableComponentMapper();
        actionComponentMapper =getGameComponentMapper().getActionComponentMapper();
        positionComponentMapper =getGameComponentMapper().getPositionComponentMapper();
        glowComponentMapper=getGameComponentMapper().getGlowComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all(  AnimatableComponent.class, Drawable.class, Position.class, Action.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            Position position= positionComponentMapper.get(entity);
            Drawable drawable = drawableComponentMapper.get(entity);
            if(getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY())!=getDraw().getCurrentMap()) {
                // not on current map  don't draw or calculate anything realated to drawing
                drawable.setDraw(false);
                continue;
            }
            drawable.setDraw(true);
            AnimatableComponent animatable= animatableComponentMapper.get(entity);

            if(animatable!=null) {

                // set animation variables and link them to the drawable if drawable has animatable component
                // set directions
                Direction direction=position.getDirection();
                if (animatable.isEightDirections()) {
                    animatable.setCurrentDirection(position.getDirection());
                } else {
                    animatable.setCurrentDirection(Direction.getBaseDirection(direction));
                }

                Action action = actionComponentMapper.get(entity);
                String currentAction=action.getText();
                action.setAnimationFrames(animatable.getFrames(currentAction, direction));
                action.setCurrentFrame(animatable.getCurrentFrameNumber());
                action.setFrameRate(animatable.getFrameRate(direction,currentAction ));
                //set actions
                if ((!animatable.getCurrentAction().equals(currentAction))) {
                    animatable.setCurrentAction(currentAction);
                    animatable.setAnimationState(AnimationState.ACTING);
                }
                drawable.setSubLayerNumber(animatable.getCurrentLayerNumber());
                drawable.setCurrentRegion(animatable.getCurrentTexture());
                drawable.setDrawOffsets(animatable.getCurrentDrawOffsets());
                if (drawable.getColorChangeMode() == ColorChangeMode.ACTION) {
                    NamedColor color = animatable.getCurrentColor();
                    Float brightness = animatable.getCurrentBrightness();
                    if (color != null) {
                        drawable.setColor(color);
                    }
                    if (brightness != null) {
                        drawable.setBrightness(brightness);
                    }
                }
                animatable.nextFrame();
                if (animatable.getFinishedAnimating()) {
                    entity.add(new AnimationFinished());
                }


            }


            Glow glow= glowComponentMapper.get(entity);
            if(glow!=null){
                drawable.setDraw(true);
                drawable.setCalculateColor(true);
            float brightness=drawable.getBrightness();
            if(!glow.isDecreaseBrightness()) {
                brightness = brightness + glow.getIncrease();
                double  maxValue=glow.getMaxValue();
                if (brightness > maxValue) {
                    glow.setDecreaseBrightness(true);
                    brightness= (float) maxValue;
                }
                drawable.setBrightness(brightness);
                glow.setValue(brightness);
            }

            else{
                brightness = brightness - glow.getIncrease();
                double  minValue=glow.getMinValue();
                if (brightness < minValue) {
                    glow.setDecreaseBrightness(true);
                    brightness= (float) minValue;
                }
                drawable.setBrightness(brightness);
                glow.setValue(brightness);

            }


        }
            drawable.setLayerNumber(position.getScreenLocationY()); //set layer number equal to y position
            if(drawable.isLayerChanged() || drawable.isSubLayerChanged()){ // layer changed sort entities before drawing them
                renderSystem.forceSort();
            }



        }
    }
}
package com.jessematty.black.tower.Systems.Drawing;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.FlagComponents.AnimationFinished;
import com.jessematty.black.tower.Components.FlagComponents.OnCurrentMap;
import com.jessematty.black.tower.Components.Interfaces.Transient;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;
@Transient
public  class AnimationSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<DrawableComponent> drawableComponentMapper;
    private ComponentMapper<AnimatableComponent> animatableComponentMapper;
    private ComponentMapper<ActionComponent> actionComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private RenderSystem renderSystem;
    public AnimationSystem(MapDraw draw, RenderSystem system, int priority) {
        super(priority, draw );
        this.renderSystem=system;
    }
    @Override
    public void addedToEngine(Engine engine) {
        drawableComponentMapper = GameComponentMapper.getDrawableComponentMapper();
        animatableComponentMapper =GameComponentMapper.getAnimatableComponentMapper();
        actionComponentMapper =GameComponentMapper.getActionComponentMapper();
        positionComponentMapper =GameComponentMapper.getPositionComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        entities = getEngine().getEntitiesFor(Family.all(OnCurrentMap.class, AnimatableComponent.class, DrawableComponent.class, PositionComponent.class, ActionComponent.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            String name=GameComponentMapper.getNameComponentMapper().get(entity).getStat();
            PositionComponent position = positionComponentMapper.get(entity);
            DrawableComponent drawableComponent = drawableComponentMapper.get(entity);
            drawableComponent.setDraw(true);
            AnimatableComponent animatable = animatableComponentMapper.get(entity);
                // set animation variables and link them to the drawable if drawable has animatable component
                // set directions
                Direction direction = position.getDirection();
                    animatable.setCurrentDirection(position.getDirection());
                ActionComponent actionComponent = actionComponentMapper.get(entity);
                actionComponent.addTurn();
                String currentAction = actionComponent.getAction();
                actionComponent.setAnimationFrames(animatable.getFrames(currentAction, direction));
                actionComponent.setCurrentFrame(animatable.getCurrentFrameNumber());
                actionComponent.setFrameRate(animatable.getFrameRate(direction, currentAction));
                //set actions
                if ((!animatable.getCurrentAction().equals(currentAction))) {
                    animatable.changeAction(currentAction);
                }
                drawableComponent.setSubLayerNumber(animatable.getCurrentSublayerNumber());
                drawableComponent.setLayerNumber(animatable.getCurrentLayerNumber());
                drawableComponent.setCurrentRegion(animatable.getCurrentTexture());
                drawableComponent.setDrawOffsets(animatable.getCurrentDrawOffsets());
                animatable.nextFrame();
                if (animatable.isFinishedAnimating()) {
                    entity.add(new AnimationFinished());
                    actionComponent.stopAction();
                    if(position.isOnGround()){
                        actionComponent.setAction("restOnGround");
                    }
                }
                if(drawableComponent.isSetLayerToYPosition()) {
                    drawableComponent.setLayerNumber(-position.getLocationY() - drawableComponent.getTextureRegion().getRegionHeight()); //set layer number equal to y position
                }
                if (drawableComponent.isLayerChanged() || drawableComponent.isSubLayerChanged()) { // layer changed sort entities before drawing them
                    renderSystem.forceSort();
                }

        }
    }
}
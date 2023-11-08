package com.jessematty.black.tower.Systems.Drawing;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.FlagComponents.AnimationFinished;
import com.jessematty.black.tower.Components.FlagComponents.OnCurrentMap;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Interfaces.Transient;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangeMode;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.Animation.AnimationState;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
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
        entities=getEngine().getEntitiesFor(Family.all(OnCurrentMap.class,  AnimatableComponent.class, DrawableComponent.class, PositionComponent.class, ActionComponent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            PositionComponent position= positionComponentMapper.get(entity);
            DrawableComponent drawableComponent = drawableComponentMapper.get(entity);
            drawableComponent.setDraw(true);
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
                ActionComponent actionComponent = actionComponentMapper.get(entity);
                int turnsToComplete=actionComponent.getTurnsToCompletion();
                actionComponent.addTurn();
                String currentAction= actionComponent.getStat();
                actionComponent.setAnimationFrames(animatable.getFrames(currentAction, direction));
                actionComponent.setCurrentFrame(animatable.getCurrentFrameNumber());
                actionComponent.setFrameRate(animatable.getFrameRate(direction,currentAction ));
                //set actions
                if ((!animatable.getCurrentAction().equals(currentAction))) {
                    animatable.setCurrentAction(currentAction);
                    animatable.setAnimationState(AnimationState.ACTING);
                }
                drawableComponent.setSubLayerNumber(animatable.getCurrentLayerNumber());
                drawableComponent.setCurrentRegion(animatable.getCurrentTexture());
                drawableComponent.setDrawOffsets(animatable.getCurrentDrawOffsets());
                if (drawableComponent.getColorChangeMode() == ColorChangeMode.ACTION) {
                    NamedColor color = animatable.getCurrentColor();
                    Float brightness = animatable.getCurrentBrightness();
                    if (color != null) {
                        drawableComponent.setColor(color);
                    }
                    if (brightness != null) {
                        drawableComponent.setBrightness(brightness);
                    }
                }
                animatable.nextFrame();
                if (animatable.getFinishedAnimating()) {
                    entity.add(new AnimationFinished());
                    actionComponent.stopAction();
                }
            }

            drawableComponent.setLayerNumber(-position.getLocationY()- drawableComponent.getTextureRegion().getRegionHeight()); //set layer number equal to y position
            if(drawableComponent.isLayerChanged() || drawableComponent.isSubLayerChanged()){ // layer changed sort entities before drawing them
                renderSystem.forceSort();
            }
        }
    }
}
package com.jessematty.black.tower.Systems.Entity;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Interfaces.Transient;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Position.BoundsChangeableComponent;
import com.jessematty.black.tower.Components.Position.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;
@Transient
/**
 * system that changes an entities  bounds based  direction and action
 */
public class ChangeBoundsSystem extends GameEntitySystem {
    private ComponentMapper<PositionComponent> positions;
    private ComponentMapper<ActionComponent> actionComponentMapper;
    private ComponentMapper<BoundsChangeableComponent> boundsChangeableComponentMapper;
    ImmutableArray<Entity> entities;
    MovableComponent movableComponent;
    PhysicalObjectComponent object;
    public ChangeBoundsSystem(MapDraw draw, int priorty) {
        super(priorty, draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        positions = GameComponentMapper.getPositionComponentMapper();
         actionComponentMapper = GameComponentMapper.getActionComponentMapper();
         boundsChangeableComponentMapper=GameComponentMapper.getBoundsChangeableComponentMapper();
    }
    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }
    @Override
    public void update(float deltaTime) {
        entities = getEngine().getEntitiesFor(Family.all(BoundsChangeableComponent.class, PositionComponent.class, ActionComponent.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            BoundsChangeableComponent boundsChangeableComponent =boundsChangeableComponentMapper.get(entity);
            PositionComponent position = positions.get(entity);
            ActionComponent actionComponent =actionComponentMapper.get(entity);
            Direction direction=position.getDirection();
            String actionValue= actionComponent.getAction();
            Polygon bounds = boundsChangeableComponent.getBounds(direction, actionValue);
            if(bounds!=null){
                position.setBounds(bounds);
            }
            Vector2 boundsOffset= boundsChangeableComponent.getBoundsOffset(direction, actionValue);
            if(boundsOffset!=null){
                position.setBoundsXOffset(boundsOffset.x);
                position.setBoundsYOffset(boundsOffset.y);
            }
        }
    }
}

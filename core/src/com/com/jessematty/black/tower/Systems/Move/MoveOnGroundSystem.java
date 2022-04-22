package com.jessematty.black.tower.Systems.Move;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Moved;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Systems.GameEntitySystem;
/**
 * System that moves  Entities on the Ground to be moved an Entity  must have
 * a PositionComponent a ActionComponent and MovableComponent and MovingOnGroundComponent
 */
public class MoveOnGroundSystem extends GameEntitySystem {
    /**
    the  entities  with a PositionComponent a ActionComponent and MovableComponent
     */
    private ImmutableArray<Entity> entities;
    /**
     the  MovableComponents  mapper
     */
    private ComponentMapper<MovableComponent> movables;
    /**
     the   PositionComponents Mapper
     */
    private ComponentMapper<PositionComponent> positions;
    /**
     the  ActionComponents mapper
     */
    private ComponentMapper<ActionComponent> actions;
    /**
     the Numeric Stats Component  mapper
     used to get speed
     */
    private ComponentMapper<NumericStats> numericStatsComponentMapper;
    protected  boolean eightDirections=true;
    public MoveOnGroundSystem(MapDraw draw) {
        super(draw);
    }
    /**
     * 
     * @param engine
     */
    @Override
    public void addedToEngine(Engine engine) {
        movables =GameComponentMapper.getMovableComponentMapper();
        positions= GameComponentMapper.getPositionComponentMapper();
        actions=GameComponentMapper.getActionComponentMapper();
        numericStatsComponentMapper=GameComponentMapper.getNumericStatsComponentMapper();

    }
    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all(MovableComponent.class, PositionComponent.class, ActionComponent.class, MovingOnGroundComponent.class, NumericStats.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
           Entity entity=entities.get(count);
           NumericStats numericStats=numericStatsComponentMapper.get(entity);
            NumericStat speed=numericStats.getNumericStat("speed");
            if(speed==null){
                continue;
            }
            float speedValue=speed.getFloatValue();
            ActionComponent actionComponent = actions.get(entity);
            MovableComponent movableComponent = movables.get(entity);
            movableComponent.setCurrentSpeed(speedValue);
            actionComponent.setStat("move");
                PositionComponent position = positions.get(entity);
                GameMap  map=getDraw().getWorld().getMap(position.getMapId());
                if (movableComponent.getCurrentSpeed() > 0) {
                    MoveOnGround.move(map, movableComponent, entity, position, deltaTime);
                    entity.add(new Moved());
                }
                else{
                    entity.remove(MovingOnGroundComponent.class);
                    entity.remove(Moved.class);
                }
        }
    }

}
package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Polygon;
import com.jessematty.black.tower.Components.Actions.Action;

import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Launched;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGround;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Launchable;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Target;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MathUtilities;
import com.jessematty.black.tower.Maps.GameMap;

public class LaunchEntity extends GameEntitySystem {
  private ComponentMapper<PositionComponent> positionComponentMapper;
   private ComponentMapper<Launchable> throwComponentMapper;
   private ComponentMapper<Action> actionComponentMapper;
   private ComponentMapper<Target> targetComponentMapper;
    public LaunchEntity(MapDraw draw) {
        super(draw);
       
    }
    @Override
    public void addedToEngine(Engine engine) {
        positionComponentMapper=getGameComponentMapper().getPositionComponentMapper();
        throwComponentMapper=getGameComponentMapper().getThrowComponentMapper();
        actionComponentMapper=getGameComponentMapper().getActionComponentMapper();
        targetComponentMapper=getGameComponentMapper().getTargetComponentMapper();
        
    }
    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
      ImmutableArray<Entity> entities= getEngine().getEntitiesFor(Family.all(  Target.class, PositionComponent.class, Launchable.class, Launched.class  ).get());
        int size=entities.size();
        for(int count=0; count<size; count++) {
            Entity entity=entities.get(count);
            
            Launchable launchableComponent =throwComponentMapper.get(entity);
            Action action=actionComponentMapper.get(entity);
            Target target=targetComponentMapper.get(entity);

            PositionComponent entityPosition=positionComponentMapper.get(entity);
                 float targetX=target.getScreenLocationX();
                 float targetY=target.getScreenLocationY();
                 float entityX=entityPosition.getLocationX();
                 float entityY=entityPosition.getLocationY();

                float speed = launchableComponent.getLaunchSpeed();
            GameMap map=getDraw().getWorld().getMap(target.getMapTargetX(), target.getMapTargetY());
                double gravity = map.getGravity();
                float launchAngle = launchableComponent.getLaunchAngle();
                float maxDistance = MathUtilities.findDistance((float) speed, (float) gravity, launchAngle);
                launchableComponent.setMaxHeight(maxDistance/2);
                float run = targetX - entityX;
                float rise = targetY - entityY;
                float angle = (float) Math.atan2(rise, run);
                Direction direction = launchableComponent.getLaunchDirection();
                Polygon bounds = entityPosition.getBounds();
                bounds.setOrigin(bounds.getX() / 2, bounds.getY() / 2);
                bounds.setRotation(Direction.getAngel(direction));
                Movable movable = new Movable();
                movable.setCurrentSpeed(speed);
                movable.setMoveAngle(angle);
                entity.add(movable);
                entity.add(new MovingOnGround());
                action.setStat("move");
                entity.remove(Target.class);

        }
            return;
        }
/*
    private void setItemScreenLocation(Direction direction, Projectile projectile) {
        float itemXOffset= itemToThrow.getXOffset();
        float itemYOffset=itemToThrow.getYOffset();
        switch (direction){
            case UP:
                projectile.setScreenLoaction(thrower.getScreenLocationX()+(thrower.getTextureRegion().getRegionWidth()/2)+itemXOffset,thrower.getScreenLocationY()+itemYOffset+thrower.getTextureRegion().getRegionHeight());
                break;
            case DOWN:
                projectile.setScreenLoaction(thrower.getScreenLocationX()+(thrower.getTextureRegion().getRegionWidth()/2)+itemXOffset,thrower.getScreenLocationY()+itemYOffset+itemYOffset);
                break;
            case LEFT:
                projectile.setScreenLoaction(thrower.getScreenLocationX()+itemXOffset,thrower.getScreenLocationY()+(thrower.getTextureRegion().getRegionHeight()/2)+itemYOffset );
                break;
            case RIGHT:
                projectile.setScreenLoaction(thrower.getScreenLocationX()+itemXOffset+thrower.getTextureRegion().getRegionWidth(),thrower.getScreenLocationY()+(thrower.getTextureRegion().getRegionHeight()/2)+itemYOffset );
                break;
            case LEFTUP:
                projectile.setScreenLoaction(thrower.getScreenLocationX()+itemXOffset,thrower.getScreenLocationY()+(thrower.getTextureRegion().getRegionHeight())+itemYOffset );
                break;
            case LEFTDOWN:
                projectile.setScreenLoaction(thrower.getScreenLocationX()+itemXOffset,thrower.getScreenLocationY()+itemYOffset );
                break;
            case RIGHTUP:
                projectile.setScreenLoaction(thrower.getScreenLocationX()+itemXOffset+(thrower.getTextureRegion().getRegionWidth())-itemXOffset,thrower.getScreenLocationY()+(thrower.getTextureRegion().getRegionHeight())+itemYOffset );
                break;
            case RIGHTDOWN:
                projectile.setScreenLoaction(thrower.getScreenLocationX()-itemXOffset+(thrower.getTextureRegion().getRegionWidth())+itemXOffset,thrower.getScreenLocationY()+itemYOffset );
                break;
            case SAME:
                projectile.setScreenLoaction(thrower.getScreenLocationX()-itemXOffset,thrower.getScreenLocationY()-itemYOffset );
                break;
        }
    }
*/
}

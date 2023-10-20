package Systems.Move;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Systems.Move.MoveOnGroundSystem;

import org.junit.Before;
import org.junit.Test;

import Maps.TestMap;
import TestEntities.TestEntity;

import static org.junit.Assert.assertEquals;

public final class MoveSystemTest {

  private   Engine engine;
  private Entity movable1;
  private MapDraw mapDraw;
  private World world;
  private TestMap testMap= new TestMap();

    @Before
    public void setup() {
      mapDraw =  testMap.mapDraw;
      mapDraw.setWorld(testMap.testWorld);
      movable1=  new TestEntity().movable;
      world= testMap.testWorld;
      PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
      positionComponent.setMapID(mapDraw.getCurrentMap().getId());
      positionComponent.setLocationX(128);
      positionComponent.setLocationY(128);
      movable1.add(new MovingOnGroundComponent());
      movable1.getComponent(NumericStats.class).addStat(new NumericStat(true, "speed", 32));
      engine=world.getEngine();
      engine.addSystem(new MoveOnGroundSystem(mapDraw));
      world.addEntityToWorld(movable1);
      positionComponent.setBounds(32, 32);

    }


    @Test
    public void moveDown(){
    MovableComponent movableComponent = movable1.getComponent(MovableComponent.class);
    PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
    movableComponent.moveDown();
    movableComponent.setCurrentSpeed(32);
      engine.update(1);
      assertEquals( 96f, positionComponent.getLocationY(),1);
      assertEquals( 128f, positionComponent.getLocationX(),1);

      assertEquals(positionComponent.getDirection(), Direction.DOWN);
      Rectangle rectangle=positionComponent.getBoundsBoundingRectangle();
      assertEquals(rectangle.x, 128f, 1);
      assertEquals(rectangle.y, 96f, 1);
      ActionComponent actionComponent=movable1.getComponent(ActionComponent.class);
      assertEquals(actionComponent.getStat(), "move");

    }


  @Test
  public void moveUp(){
    MovableComponent movableComponent = movable1.getComponent(MovableComponent.class);
    PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
    movableComponent.moveUp();
    movableComponent.setCurrentSpeed(32);
    engine.update(1);
    assertEquals( 160f, positionComponent.getLocationY(),1);
    assertEquals( 128f, positionComponent.getLocationX(),1);
    assertEquals(positionComponent.getDirection(), Direction.UP);
    Rectangle rectangle=positionComponent.getBoundsBoundingRectangle();
    assertEquals(rectangle.y, 160f, 1);
    assertEquals(rectangle.x, 128f, 1);
    ActionComponent actionComponent=movable1.getComponent(ActionComponent.class);
    assertEquals(actionComponent.getStat(), "move");

  }


  @Test
  public void moveLeft(){
    MovableComponent movableComponent = movable1.getComponent(MovableComponent.class);
    PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
    movableComponent.moveLeft();
    movableComponent.setCurrentSpeed(32);
    engine.update(1);
    assertEquals( 96f, positionComponent.getLocationX(),1);
    assertEquals( 128f, positionComponent.getLocationY(),1);
    assertEquals(positionComponent.getDirection(), Direction.LEFT);
    Rectangle rectangle=positionComponent.getBoundsBoundingRectangle();
    assertEquals(rectangle.x, 96f, 1);
    assertEquals(rectangle.y, 128f, 1);
    ActionComponent actionComponent=movable1.getComponent(ActionComponent.class);
    assertEquals(actionComponent.getStat(), "move");
  }

  @Test
  public void moveRight(){
    MovableComponent movableComponent = movable1.getComponent(MovableComponent.class);
    PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
    movableComponent.moveRight();
    movableComponent.setCurrentSpeed(32);
    engine.update(1);
    assertEquals( 160f, positionComponent.getLocationX(),1);
    assertEquals( 128f, positionComponent.getLocationY(),1);
    assertEquals(positionComponent.getDirection(), Direction.RIGHT);
    Rectangle rectangle=positionComponent.getBoundsBoundingRectangle();
    assertEquals(rectangle.x, 160f, 1);
    assertEquals(rectangle.y, 128f, 1);
    ActionComponent actionComponent=movable1.getComponent(ActionComponent.class);
    assertEquals(actionComponent.getStat(), "move");
  }

  @Test
  public void moveLeftUp(){
    MovableComponent movableComponent = movable1.getComponent(MovableComponent.class);
    PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
    movableComponent.moveLeftUp();
    movableComponent.setCurrentSpeed(32);
    engine.update(1);
    float speedX = (float) Math.sin(movableComponent.getMoveAngle() * 32);
    float speedY = (float) Math.cos(movableComponent.getMoveAngle() * 32);
    float y= (float) (positionComponent.getLocationY()+ speedY);
    float x= (float) (positionComponent.getLocationX()+speedX);
    assertEquals(positionComponent.getDirection(), Direction.LEFTUP);
    assertEquals( x, positionComponent.getLocationX(),1);
    assertEquals( y, positionComponent.getLocationY(),1);
    Rectangle rectangle=positionComponent.getBoundsBoundingRectangle();
    assertEquals(rectangle.y, y, 1);
    assertEquals(rectangle.x, x, 1);
    ActionComponent actionComponent=movable1.getComponent(ActionComponent.class);
    assertEquals(actionComponent.getStat(), "move");
  }

  @Test
  public void moveRightUp(){
    MovableComponent movableComponent = movable1.getComponent(MovableComponent.class);
    PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
    movableComponent.moveRightUp();
    movableComponent.setCurrentSpeed(32);
    engine.update(1);
    engine.update(1);
    float speedX = (float) Math.sin(movableComponent.getMoveAngle() * 32);
    float speedY = (float) Math.cos(movableComponent.getMoveAngle() * 32);
    float y= (float) (positionComponent.getLocationY()+ speedY);
    float x= (float) (positionComponent.getLocationX()+speedX);
    assertEquals( y, positionComponent.getLocationY(),1);
    assertEquals( x, positionComponent.getLocationX(),1);
    assertEquals(positionComponent.getDirection(), Direction.RIGHTUP);
    Rectangle rectangle=positionComponent.getBoundsBoundingRectangle();
    assertEquals(rectangle.y, y, 1);
    assertEquals(rectangle.x, x, 1);
    ActionComponent actionComponent=movable1.getComponent(ActionComponent.class);
    assertEquals(actionComponent.getStat(), "move");


  }
  @Test
  public void moveLeftDown(){
    MovableComponent movableComponent = movable1.getComponent(MovableComponent.class);
    PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
    movableComponent.moveLeftDown();
    movableComponent.setCurrentSpeed(32);
    engine.update(1);
    float speedX = (float) Math.sin(movableComponent.getMoveAngle() * 32);
    float speedY = (float) Math.cos(movableComponent.getMoveAngle() * 32);
    float y= (float) (positionComponent.getLocationY()+ speedY);
    float x= (float) (positionComponent.getLocationX()+speedX);
    assertEquals( x, positionComponent.getLocationX(),1);
    assertEquals( y, positionComponent.getLocationY(),1);
    assertEquals(Direction.LEFTDOWN, positionComponent.getDirection());
    Rectangle rectangle=positionComponent.getBoundsBoundingRectangle();
    assertEquals(rectangle.y, y, 1);
    assertEquals(rectangle.x, x, 1);
    ActionComponent actionComponent=movable1.getComponent(ActionComponent.class);
    assertEquals(actionComponent.getStat(), "move");

  }
  @Test
  public void moveRightDown(){
    MovableComponent movableComponent = movable1.getComponent(MovableComponent.class);
    PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
    movableComponent.moveRightDown();
    movableComponent.setCurrentSpeed(32);
    engine.update(1);
    float speedX = (float) Math.sin(movableComponent.getMoveAngle() * 32);
    float speedY = (float) Math.cos(movableComponent.getMoveAngle() * 32);
    float y= (float) (positionComponent.getLocationY()+ speedY);
    float x= (float) (positionComponent.getLocationX()+speedX);
    assertEquals( x, positionComponent.getLocationX(),1);
    assertEquals( y, positionComponent.getLocationY(),1);
    assertEquals( Direction.RIGHTDOWN, positionComponent.getDirection());
    Rectangle rectangle=positionComponent.getBoundsBoundingRectangle();
    assertEquals(rectangle.y, y, 1);
    assertEquals(rectangle.x, x, 1);
    ActionComponent actionComponent=movable1.getComponent(ActionComponent.class);
    assertEquals(actionComponent.getStat(), "move");

  }

}

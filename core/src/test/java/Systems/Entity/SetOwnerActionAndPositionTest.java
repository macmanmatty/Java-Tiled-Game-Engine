package Systems.Entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Systems.Owner.SetEntityPositionAndActionToOwnerSystem;
import com.jessematty.black.tower.Systems.Move.MoveOnGroundSystem;

import org.junit.Before;
import org.junit.Test;

import Maps.TestMap;
import TestEntities.TestEntity;

import static org.junit.Assert.assertEquals;

public final class SetOwnerActionAndPositionTest {

  private   Engine engine;
  private Entity movable1;
  private MapDraw mapDraw;
  private World world;
  private TestMap testMap= new TestMap();
  private Entity owned;

    @Before
    public void setup() {
      mapDraw =  testMap.mapDraw;
      mapDraw.setWorld(testMap.testWorld);
      movable1=  new TestEntity().movable;
      owned= new Entity();
      PositionComponent positionComponentOwned= new PositionComponent();
      positionComponentOwned.setMapID(mapDraw.getCurrentMap().getId());
      positionComponentOwned.setLocationX(32);
      positionComponentOwned.setLocationY(32);
      owned.add(positionComponentOwned);
      owned.add(new ActionComponent());
      world= testMap.testWorld;
      PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
      positionComponent.setMapID(mapDraw.getCurrentMap().getId());
      positionComponent.setLocationX(128);
      positionComponent.setLocationY(128);
      movable1.add(new MovingOnGroundComponent());
      movable1.getComponent(NumericStats.class).addStat(new NumericStat(true, "speed", 32));
      engine=world.getEngine();
      engine.addSystem(new MoveOnGroundSystem( mapDraw));
      engine.addSystem(new SetEntityPositionAndActionToOwnerSystem(0, mapDraw));
      world.addEntityToWorld(movable1);
      world.addEntityToWorld(owned);
      positionComponent.setBounds(32, 32);
     Boolean attach= EntityUtilities.attachEntity(movable1, owned);


    }


    @Test
    public void moveOwnerDownWihOwned(){
    MovableComponent movableComponent = movable1.getComponent(MovableComponent.class);
    PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
      PositionComponent positionComponentOwned=  owned.getComponent(PositionComponent.class);
      OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
      ownedComponent.setSetEntityPositionToOwner(true);
      movableComponent.moveDown();
    movableComponent.setCurrentSpeed(32);
      engine.update(1);
      assertEquals( 96f, positionComponent.getLocationY(),1);
      assertEquals( 128f, positionComponent.getLocationX(),1);
      assertEquals( 96f, positionComponentOwned.getLocationY(),1);
      assertEquals( 128f, positionComponentOwned.getLocationX(),1);
      assertEquals(positionComponent.getDirection(), Direction.DOWN);
      Rectangle rectangle=positionComponent.getBoundsBoundingRectangle();
      assertEquals(rectangle.x, 128f, 1);
      assertEquals(rectangle.y, 96f, 1);
      ActionComponent actionComponent=movable1.getComponent(ActionComponent.class);
      assertEquals(actionComponent.getStat(), "move");

    }


  @Test
  public void moveOwnerUpButNotOwned(){
    MovableComponent movableComponent = movable1.getComponent(MovableComponent.class);
    PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
    PositionComponent positionComponentOwned=  owned.getComponent(PositionComponent.class);
    OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
    ownedComponent.setSetEntityPositionToOwner(false);
    positionComponentOwned.setPosition(11, 11, 1);
    movableComponent.moveUp();
    movableComponent.setCurrentSpeed(32);
    engine.update(1);
    assertEquals( 160f, positionComponent.getLocationY(),1);
    assertEquals( 128f, positionComponent.getLocationX(),1);
    assertEquals( 11f, positionComponentOwned.getLocationY(),1);
    assertEquals( 11f, positionComponentOwned.getLocationX(),1);
    assertEquals(positionComponent.getDirection(), Direction.UP);
    Rectangle rectangle=positionComponent.getBoundsBoundingRectangle();
    assertEquals(rectangle.y, 160f, 1);
    assertEquals(rectangle.x, 128f, 1);
    ActionComponent actionComponent=movable1.getComponent(ActionComponent.class);
    assertEquals(actionComponent.getStat(), "move");

  }


  @Test
  public void setActionToOwner(){
    MovableComponent movableComponent = movable1.getComponent(MovableComponent.class);
    PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
    ActionComponent actionComponent=owned.getComponent(ActionComponent.class);
    actionComponent.setStat("roll");
    OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
    ownedComponent.setSetEntityActionToOwner(false);
    movableComponent.moveLeft();
    movableComponent.setCurrentSpeed(32);
    engine.update(1);
    assertEquals( 96f, positionComponent.getLocationX(),1);
    assertEquals( 128f, positionComponent.getLocationY(),1);
    assertEquals(positionComponent.getDirection(), Direction.LEFT);
    Rectangle rectangle=positionComponent.getBoundsBoundingRectangle();
    assertEquals(rectangle.x, 96f, 1);
    assertEquals(rectangle.y, 128f, 1);
    assertEquals( "roll", actionComponent.getStat());
  }

  @Test
  public void setOwnerToAction(){
    MovableComponent movableComponent = movable1.getComponent(MovableComponent.class);
    PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
    ActionComponent actionComponent=owned.getComponent(ActionComponent.class);
    actionComponent.setStat("roll");
    OwnedComponent ownedComponent=owned.getComponent(OwnedComponent.class);
    ownedComponent.setSetEntityActionToOwner(true);
    movableComponent.moveRight();
    movableComponent.setCurrentSpeed(32);
    engine.update(1);
    assertEquals( 160f, positionComponent.getLocationX(),1);
    assertEquals( 128f, positionComponent.getLocationY(),1);
    assertEquals(positionComponent.getDirection(), Direction.RIGHT);
    Rectangle rectangle=positionComponent.getBoundsBoundingRectangle();
    assertEquals(rectangle.x, 160f, 1);
    assertEquals(rectangle.y, 128f, 1);
    assertEquals(actionComponent.getStat(), "move");
  }


}

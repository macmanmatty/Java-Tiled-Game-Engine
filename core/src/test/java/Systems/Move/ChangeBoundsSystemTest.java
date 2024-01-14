package Systems.Move;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Position.BoundsChangeableComponent;
import com.jessematty.black.tower.Components.Position.EntityBounds;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Systems.Entity.ChangeBoundsSystem;
import com.jessematty.black.tower.Systems.Move.MoveOnGroundSystem;

import org.junit.Before;
import org.junit.Test;

import Maps.TestMap;
import TestEntities.TestEntity;

import static org.junit.Assert.assertEquals;

public class ChangeBoundsSystemTest {

    private Engine engine;
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
        BoundsChangeableComponent boundsChangeableComponent= new BoundsChangeableComponent();
        EntityBounds entityBoundsDown= new EntityBounds();
        entityBoundsDown.setBounds(100, 100);
        EntityBounds entityBoundsUp= new EntityBounds();
        entityBoundsUp.setBounds(99, 99);
        entityBoundsUp.setBoundsOffset(10, -10);
        EntityBounds entityBoundsEat= new EntityBounds();
        entityBoundsEat.setBounds(11, 11);
        boundsChangeableComponent.addBounds(Direction.DOWN,"move",  entityBoundsDown);
        boundsChangeableComponent.addBounds(Direction.UP,"move",  entityBoundsUp);
        boundsChangeableComponent.addBounds(Direction.LEFT,"eat",  entityBoundsEat);
        movable1.add(boundsChangeableComponent);
        engine=world.getEngine();
        engine.addSystem(new MoveOnGroundSystem(mapDraw));
        engine.addSystem(new ChangeBoundsSystem(mapDraw, 1));
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
        Rectangle rectangle=positionComponent.getBoundsBoundingRectangle();
        assertEquals(100f, rectangle.width, 0);
        assertEquals(100f, rectangle.height, 0);
        assertEquals( 96f, positionComponent.getLocationY(),1);
        assertEquals( 128f, positionComponent.getLocationX(),1);
        assertEquals(positionComponent.getDirection(), Direction.DOWN);
        assertEquals(rectangle.x, 128f, 1);
        assertEquals(rectangle.y, 96f, 1);
        ActionComponent actionComponent=movable1.getComponent(ActionComponent.class);
        assertEquals(actionComponent.getAction(), "move");

    }


    @Test
    public void moveUp(){
        MovableComponent movableComponent = movable1.getComponent(MovableComponent.class);
        PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
        positionComponent.setDirection(Direction.UP);
        movableComponent.moveUp();
        movableComponent.setCurrentSpeed(32);
        engine.update(1);
        Rectangle rectangle=positionComponent.getBoundsBoundingRectangle();
        assertEquals(99f, rectangle.width, 0);
        assertEquals(99f, rectangle.height, 0);
        assertEquals( 160f, positionComponent.getLocationY(),1);
        assertEquals( 128f, positionComponent.getLocationX(),1);
        assertEquals(positionComponent.getDirection(), Direction.UP);
        assertEquals(rectangle.y+10, 160f, 1);
        assertEquals(rectangle.x-10, 128f, 1);
        ActionComponent actionComponent=movable1.getComponent(ActionComponent.class);
        assertEquals(actionComponent.getAction(), "move");

    }


    @Test
    public void eat(){
        movable1.remove(MovableComponent.class);
        PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
        positionComponent.setDirection(Direction.LEFT);
        ActionComponent actionComponent=movable1.getComponent(ActionComponent.class);
        actionComponent.setAction("eat");
        engine.update(1);
        Rectangle rectangle=positionComponent.getBoundsBoundingRectangle();
        assertEquals(11f, rectangle.width, 0);
        assertEquals(11f, rectangle.height, 0);
    }



}

package Utilities;

import static org.junit.Assert.assertEquals;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MapUtilities;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Systems.Move.MoveOnGroundSystem;

import org.junit.Before;
import org.junit.Test;

import Maps.TestMap;
import TestEntities.TestEntity;

public class MapUtilitiesTest {


    private Engine engine;
    private Entity movable1;
    private Entity moveable2;
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
        positionComponent.setLocationX(222);
        positionComponent.setLocationY(222);
        positionComponent.setBounds(32,32);
        movable1.add(new MovingOnGroundComponent());
        movable1.getComponent(NumericStats.class).addStat(new NumericStat(true, "speed", 32));
        engine=world.getEngine();
        engine.addSystem(new MoveOnGroundSystem(mapDraw));
        world.addEntityToWorld(movable1);
        positionComponent.setBounds(32, 32);

    }
    @Test
    public void testGetAllEntitiesAndTiles(){
     Array<Entity> entities = MapUtilities.getAllEntitiesAndTiles(testMap.testMap1, 161, 160,  2, 2);
     assertEquals(true, entities.contains(movable1, true));
     assertEquals(testMap.testMap1.getTileFromWorldUnitCoordinates(222,222).getEntities().size, 1);
     assertEquals(testMap.testMap1.getEntities().size, 1);

    }

    @Test
    public void testGetCenterTile() {
        Rectangle rectangle = new Rectangle();
        rectangle.x = 0;
        rectangle.y = 0;
        rectangle.height = 32;
        rectangle.width = 32;
        LandSquareTile landSquareTile = MapUtilities.getCenterTile(testMap.testMap1, rectangle);
        assertEquals(0, landSquareTile.getLocationX());
        assertEquals(49, landSquareTile.getLocationY());
    }
    @Test
       public void  testGetCenterTile2(){
        Rectangle rectangle2= new Rectangle();
        rectangle2.x=10;
        rectangle2.y=10;
        rectangle2.height=99;
        rectangle2.width=99;
        LandSquareTile landSquareTile2 = MapUtilities.getCenterTile(testMap.testMap1,rectangle2);
        assertEquals(1, landSquareTile2.getLocationX());
        assertEquals(48, landSquareTile2.getLocationY());

    }

    @Test
    public void  testGetCenterTile3(){
        Rectangle rectangle2= new Rectangle();
        rectangle2.x=0;
        rectangle2.y=0;
        rectangle2.height=0;
        rectangle2.width=0;
        LandSquareTile landSquareTile2 = MapUtilities.getCenterTile(testMap.testMap1,rectangle2);
        assertEquals(0, landSquareTile2.getLocationX());
        assertEquals(49, landSquareTile2.getLocationY());

    }


}

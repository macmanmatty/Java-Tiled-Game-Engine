package GameBaseClasses.Utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MapUtilities;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import org.junit.Before;
import org.junit.Test;

import Maps.TestMap;
import TestEntities.TestEntity;

public class MapUtilitiesTest {


    private Engine engine;
    private Entity movable1;
    private Entity item;
    private MapDraw mapDraw;
    private World world;
    private TestMap testMap= new TestMap();

    @Before
    public void setup() {
        mapDraw =  testMap.mapDraw;
        mapDraw.setWorld(testMap.testWorld);
        TestEntity testEntity= new TestEntity();
        movable1=  testEntity.movable;
        item =testEntity.item;

        world= testMap.testWorld;
        PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
        positionComponent.setMapID(mapDraw.getCurrentMap().getId());
        positionComponent.setLocationX(222);
        positionComponent.setLocationY(222);
        positionComponent.setBounds(32,32);
        item.add(positionComponent);
        item.add(new ItemComponent());
        movable1.add(new MovingOnGroundComponent());
        movable1.getComponent(NumericStats.class).addStat(new NumericStat(true, "speed", 32));
        world.addEntityToWorld(movable1);
        world.addEntityToWorld(item);
        positionComponent.setBounds(32, 32);

    }
    @Test
    public void testGetAllEntitiesAndTiles(){
     Array<Entity> entities = MapUtilities.getAllEntitiesAndTiles(testMap.testMap1, 161, 160,  2, 2);
        assertTrue(entities.contains(movable1, true));
     assertEquals(testMap.testMap1.getTileFromWorldUnitCoordinates(222,222).getEntities().size, 2);
     assertEquals(testMap.testMap1.getEntities().size, 2);

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

    @Test
    public void  getAllEntities(){
        Rectangle rectangle2= new Rectangle();
        rectangle2.x=200;
        rectangle2.y=200;
        rectangle2.height=100;
        rectangle2.width=100;
        Array<Entity> entities = MapUtilities.getAllEntities(testMap.testMap1,  rectangle2);
        assertEquals(2, entities.size);

    }
    @Test
    public void  getAllEntitiesExcluding(){
        Rectangle rectangle2= new Rectangle();
        rectangle2.x=200;
        rectangle2.y=200;
        rectangle2.height=100;
        rectangle2.width=100;
        Array<Entity> entitiesExclude= new Array<>();
        entitiesExclude.add(movable1);
        Array<Entity> entities = MapUtilities.getAllEntitiesExcluding(entitiesExclude, testMap.testMap1,  rectangle2, null, null, null);
        assertEquals(1, entities.size);

    }
    @Test
    public void  getAllEntitiesWithComponents(){
        Rectangle rectangle2= new Rectangle();
        rectangle2.x=200;
        rectangle2.y=200;
        rectangle2.height=100;
        rectangle2.width=100;
        Array<Entity> entities = MapUtilities.getAllEntities( testMap.testMap1,  rectangle2, ItemComponent.class);
        assertEquals(1, entities.size);

    }

}

package Utilities;

import static org.junit.Assert.assertEquals;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
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
        LandSquareTile tile2=testMap.testMap1.getTileFromWorldUnitCoordinates(222,222);
     Array<Entity> entities = MapUtilities.getAllEntitiesAndTiles(testMap.testMap1, 161, 160,  2, 2);
     assertEquals(true, entities.contains(movable1, true));
     assertEquals(testMap.testMap1.getTileFromWorldUnitCoordinates(222,222).getEntities().size, 1);
     assertEquals(testMap.testMap1.getEntities().size, 1);

    }


}

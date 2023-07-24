package Utilities;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Systems.Move.MoveOnGroundSystem;

import org.junit.Before;

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
        positionComponent.setLocationX(0);
        positionComponent.setLocationY(0);
        movable1.add(new MovingOnGroundComponent());
        movable1.getComponent(NumericStats.class).addStat(new NumericStat(true, "speed", 32));
        engine=world.getEngine();
        engine.addSystem(new MoveOnGroundSystem(mapDraw));
        world.addEntityToWorld(movable1);
        positionComponent.setBounds(32, 32);

    }


}

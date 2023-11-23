package Tiles;

import static org.junit.Assert.assertEquals;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Position.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.World;

import org.junit.Test;

import Maps.TestMap;

public class LandSquareTileTest {

    private Engine engine;
    private Entity movable2;
    private Entity nonMovable;
    private Entity item= new Entity();
    private MapDraw mapDraw;
    private World world;
    private TestMap testMap= new TestMap();
    public  Entity movable= new Entity();
    public Entity item2= new Entity();
    PositionComponent positionComponent;
    public    Entity staticWall= new Entity();


    {



            movable.add(new PhysicalObjectComponent());
            movable.add(new PositionComponent());
            movable.add(new NameComponent());
            NumericStats numericStats= new NumericStats();
            movable.add(numericStats );
            movable.add(new ActionComponent());
            movable.add(new MovableComponent());
            item.add(new PhysicalObjectComponent());
            item.add(new PositionComponent());
            item.add(new NameComponent());
            NumericStats numericStats2= new NumericStats();
        numericStats2.addStat(new NumericStat(true, "hate", 1));

        item.add(numericStats2 );
            item.add(new ActionComponent());
            item.add(new ItemComponent());
            item2.add(new PhysicalObjectComponent());
            item2.add(new PositionComponent());
            item2.add(new NameComponent());
        BooleanStats booleanStats= new BooleanStats();
        booleanStats.addStat(new BooleanStat(true, "foo", true));
        StringStats srtingStats= new StringStats();
        srtingStats.addStat(new StringStat(true, "string", "uhg"));
        item2.add(srtingStats);
        item2.add(booleanStats);
            NumericStats numericStats3= new NumericStats();
            numericStats3.addStat(new NumericStat(true, "love", 1));
            item2.add(numericStats3 );
            item2.add(new ActionComponent());
            item2.add(new ItemComponent());
        world= testMap.testWorld;
        mapDraw=testMap.mapDraw;
       positionComponent=  movable.getComponent(PositionComponent.class);
        positionComponent.setMapID(mapDraw.getCurrentMap().getId());
        positionComponent.setLocationX(0);
        positionComponent.setLocationY(0);
        PositionComponent positionComponent2=  item.getComponent(PositionComponent.class);
        positionComponent2.setMapID(mapDraw.getCurrentMap().getId());
        positionComponent2.setLocationX(0);
        positionComponent2.setLocationY(0);

        PositionComponent positionComponent3=  item2.getComponent(PositionComponent.class);
        positionComponent3.setMapID(mapDraw.getCurrentMap().getId());
        positionComponent3.setLocationX(0);
        positionComponent3.setLocationY(0);
    }

    @Test
    public void testEntitiesOnTile(){
        world.addEntityToWorld(movable);
        world.addEntityToWorld(item);
        world.addEntityToWorld(item2);
       assertEquals(3, mapDraw.getCurrentMap().getTileFromWorldUnitCoordinates(0, 0).getEntities().size);
        assertEquals(0, mapDraw.getCurrentMap().getTileFromWorldUnitCoordinates(33, 33).getEntities().size);
        assertEquals(0, mapDraw.getCurrentMap().getTileFromWorldUnitCoordinates(33, 0).getEntities().size);
        assertEquals(0, mapDraw.getCurrentMap().getTileFromWorldUnitCoordinates(0, 33).getEntities().size);

        assertEquals(3, mapDraw.getCurrentMap().getEntities().size);
    }
    @Test
    public void testEntitiesWithItemComponent(){
        world.addEntityToWorld(movable);
        world.addEntityToWorld(item);
        world.addEntityToWorld(item2);
        assertEquals(2, mapDraw.getCurrentMap().getTileFromWorldUnitCoordinates(0, 0).getEntities(ItemComponent.class).size);
        assertEquals(2, mapDraw.getCurrentMap().getTileFromWorldUnitCoordinates(0, 0).getEntities(new Array<>(), null, null, ItemComponent.class).size);

    }
    @Test
    public void testEntitiesWithNumericStat(){
        world.addEntityToWorld(movable);
        world.addEntityToWorld(item);
        world.addEntityToWorld(item2);
        Array<String> numericStats=new Array<>();
        numericStats.add("love");
        assertEquals(1, mapDraw.getCurrentMap().getTileFromWorldUnitCoordinates(0, 0).getEntities(numericStats, null, null).size);
        assertEquals(1, mapDraw.getCurrentMap().getTileFromWorldUnitCoordinates(0, 0).getEntities(numericStats, null, null, ItemComponent.class).size);

    }
    @Test
    public void testEntitiesWithNumericStats(){
        world.addEntityToWorld(movable);
        world.addEntityToWorld(item);
        world.addEntityToWorld(item2);
        Array<String> numericStats=new Array<>();
        numericStats.add("love");
        numericStats.add("hate");
        assertEquals(0, mapDraw.getCurrentMap().getTileFromWorldUnitCoordinates(0, 0).getEntities(numericStats, null, null).size);
        assertEquals(0, mapDraw.getCurrentMap().getTileFromWorldUnitCoordinates(0, 0).getEntities(numericStats, null, null, ItemComponent.class).size);

    }
    @Test
    public void testEntitiesWithBooleanStat(){
        world.addEntityToWorld(movable);
        world.addEntityToWorld(item);
        world.addEntityToWorld(item2);
        Array<String> booleanStats=new Array<>();
        booleanStats.add("foo");
        assertEquals(1, mapDraw.getCurrentMap().getTileFromWorldUnitCoordinates(0, 0).getEntities(null, booleanStats, null).size);


    }
    @Test
    public void testEntitiesWithStringStat(){
        world.addEntityToWorld(movable);
        world.addEntityToWorld(item);
        world.addEntityToWorld(item2);
        Array<String> stats=new Array<>();
        stats.add("string");
        assertEquals(1, mapDraw.getCurrentMap().getTileFromWorldUnitCoordinates(0, 0).getEntities(null, null, stats).size);

    }
}

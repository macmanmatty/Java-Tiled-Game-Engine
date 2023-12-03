package Maps;

import static junit.framework.Assert.assertEquals;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Editor.Tools.MapTools.MapTools;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import org.junit.Test;
import org.mockito.Mockito;

import TestEntities.TestEntity;

public final  class TestMap {
   public   final  LandMap testMap1 = MapTools.newLandMap(9.8, "map", 50, 50, 32, 32);
   public final World testWorld=MapTools.newWorld("world");
   public   final LandMap testMap2= MapTools.newLandMap(-9.8, "map2", 30, 40, 32, 32);
   public    MapDraw mapDraw;
   {
       testWorld.addMap(testMap1);
       testWorld.addMap(testMap2);
       testWorld.setCurrentMap(testMap1.getId());
       mapDraw = new MapDraw(Mockito.any());
       mapDraw.setWorld(testWorld);
       mapDraw.setMap(testMap1, false);
   }

    public TestMap() {

    }

    @Test
   public void  basicMapTests(){
        assertEquals(testWorld.getWorldMaps().size, 2);
        assertEquals(testMap1.getTileHeight(), 32);
        assertEquals(testMap1.getTileWidth(), 32);
        assertEquals(testMap2.getXTiles(), 30);
        assertEquals(testMap2.getYTiles(), 40);
        assertEquals(testMap2.getMapName(), "map2");
        assertEquals(mapDraw.getWorld(), testWorld);
        assertEquals(mapDraw.getCurrentMap().getId(), testMap1.getId());
        Engine engine=testWorld.getEngine();
        int entities=50*50;
        assertEquals(engine.getEntities().size(), entities);
    }


    @Test
    public void getAllTilesAndAddEntity(){
        Entity entity= new TestEntity().movable;
        PositionComponent positionComponent=entity.getComponent(PositionComponent.class);
        positionComponent.setBounds(64, 64);
        positionComponent.setPosition(640 , 640);
       Array<LandSquareTile> tiles= testMap1.getAllTilesAndAddEntity(positionComponent.getBoundsBoundingRectangle(), entity);
        assertEquals(4, tiles.size);
        int size=tiles.size;
        for(int count=0; count<size; count++) {
            assertEquals(1,tiles.get(count).getEntities().size);
        }

    }
    @Test
    public void getAllTilesAndAddEntityNoBounds(){
        Entity entity= new TestEntity().movable;
        PositionComponent positionComponent=entity.getComponent(PositionComponent.class);
        positionComponent.setBounds(64, 64);
        positionComponent.setPosition(640 , 640);
        positionComponent.removeBounds();
        Array<LandSquareTile> tiles= testMap1.getAllTilesAndAddEntity(positionComponent.getBoundsBoundingRectangle(), entity);
        assertEquals(1, tiles.size);
        int size=tiles.size;
        for(int count=0; count<size; count++) {
            assertEquals(1,tiles.get(count).getEntities().size);
        }

    }
    @Test
    public void getAllTilesAndAddEntity2(){
        Entity entity= new TestEntity().movable;
        PositionComponent positionComponent=entity.getComponent(PositionComponent.class);
        positionComponent.setBounds(65, 65);
        positionComponent.setPosition(74 , 64);
        Array<LandSquareTile> tiles= testMap1.getAllTilesAndAddEntity(positionComponent.getBoundsBoundingRectangle(), entity);
        assertEquals(9, tiles.size);
        int size=tiles.size;
        for(int count=0; count<size; count++) {
            assertEquals(1,tiles.get(count).getEntities().size);
        }
    }
    @Test
    public void getAllTiles(){
        Entity entity= new TestEntity().movable;
        PositionComponent positionComponent=entity.getComponent(PositionComponent.class);
        positionComponent.setBounds(65, 65);
        positionComponent.setPosition(74 , 64);
        Array<LandSquareTile> tiles=testMap1.getAllTiles(positionComponent.getBoundsBoundingRectangle());
        assertEquals(9, tiles.size);

    }
    @Test
    public void getAllTilesNoBounds(){
        Entity entity= new TestEntity().movable;
        PositionComponent positionComponent=entity.getComponent(PositionComponent.class);
        positionComponent.setBounds(65, 65);
        positionComponent.setPosition(74 , 64);
        positionComponent.removeBounds();
        Array<LandSquareTile> tiles=testMap1.getAllTiles(positionComponent.getBoundsBoundingRectangle());
        assertEquals(1, tiles.size);

    }


    @Test
    public void getAllTiles2(){
        Entity entity= new TestEntity().movable;
        PositionComponent positionComponent=entity.getComponent(PositionComponent.class);
        positionComponent.setBounds(65, 33);
        positionComponent.setPosition(65 , 33);
        Array<LandSquareTile> tiles=testMap1.getAllTiles(positionComponent.getBoundsBoundingRectangle());
        assertEquals(6, tiles.size);

    }

    @Test
    public void removeEntity(){
        Entity entity= new TestEntity().movable;
        PositionComponent positionComponent=entity.getComponent(PositionComponent.class);
        positionComponent.setBounds(68, 68);
        positionComponent.setPosition(64 , 64);
        testMap1.addEntity(entity);
         assertEquals(1, testMap1.getEntities().size);
         testMap1.removeEntity(entity);
        assertEquals(0, testMap1.getEntities().size);

    }
    @Test
    public void getAllEntitiesById(){
        Entity entity= new Entity();
        EntityId entityId= new EntityId("1");
        entity.add(entityId);
        Entity entity1= new Entity();
        EntityId entityId1= new EntityId("2");
        entity1.add(entityId1);
        Entity entity2= new Entity();
        EntityId entityId2= new EntityId("3");
        entity2.add(entityId2);
        testWorld.addEntityToWorld(entity2);
        testWorld.addEntityToWorld(entity);
        testWorld.addEntityToWorld(entity1);
        Array<String> entityIds=new Array<>();
        entityIds.add("1");
        entityIds.add("3");
     Array<Entity>  entities=  testWorld.getEntitiesFromEntityIdsArray(entityIds);
     assertEquals(2, entities.size);

    }
}

package TestEntities;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.World;
import org.junit.Before;
import org.junit.Test;
import Maps.TestMap;
import static org.junit.Assert.assertEquals;
public class BaseEntityTests {
  private   Engine engine;
  private Entity movable1;
  private Entity movable2;
  private Entity nonMovable;
  private MapDraw mapDraw;
  private World world;
    @Before
    public void setup() {
      movable1= TestEntity.movable;
      world= TestMap.testWorld;
      mapDraw=TestMap.mapDraw;
    PositionComponent positionComponent=  movable1.getComponent(PositionComponent.class);
    positionComponent.setMapID(mapDraw.getCurrentMap().getId());
    positionComponent.setLocationX(128);
    positionComponent.setLocationY(128);
    }
    @Test
    public void basicFunctions(){
    world.addEntityToWorld(movable1);
    Engine engine=world.getEngine();
    int size=50*50+1;
    assertEquals(engine.getEntities().size(), size);
    world.removeEntityFromWorld(movable1);
    assertEquals(engine.getEntities().size(), 50*50);
    }
}
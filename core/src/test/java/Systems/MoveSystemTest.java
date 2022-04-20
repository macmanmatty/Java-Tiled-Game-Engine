package Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.World;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Maps.TestMap;
import TestEntities.TestEntity;

public final class MoveSystemTest {

  private   Engine engine;
  private Entity movable1;
  private Entity movable2;
  private Entity nonMovable;
  private MapDraw mapDraw;
  private World world;

    @Before
    public void setup() {
      mapDraw = new MapDraw(Mockito.any());
      mapDraw.setWorld(TestMap.testWorld);
      movable1= TestEntity.movable;
      world= TestMap.testWorld;
    }


    @Test
    public void move(){


    }



}

package Maps;

import com.badlogic.ashley.core.Engine;
import com.jessematty.black.tower.Editor.Tools.MapTools.MapTools;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;

public final  class TestMap {
   public  static final  LandMap testMap1 = MapTools.newLandMap(9.8, "map", 50, 50, 32, 32);
   public static final World testWorld=MapTools.newWorld("world");
   public  static final LandMap testMap2= MapTools.newLandMap(-9.8, "map2", 30, 40, 32, 32);
   public   static MapDraw mapDraw;
   static{
       testWorld.addMap(testMap1);
       testWorld.addMap(testMap2);
       testWorld.setCurrentMap(testMap1.getId());
       mapDraw = new MapDraw(Mockito.any());
       mapDraw.setWorld(TestMap.testWorld);
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
}

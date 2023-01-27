package Systems;

import com.badlogic.ashley.core.Engine;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Systems.UI.LoggerSystem;

import org.junit.Before;

import Maps.TestMap;

public class LogSystemTest {


    private Engine engine;
    private MapDraw mapDraw;
    private World world;
    private TestMap testMap= new TestMap();

    @Before
    public void setup() {
        mapDraw =  testMap.mapDraw;
        mapDraw.setWorld(testMap.testWorld);
        world= testMap.testWorld;
        engine=world.getEngine();
        engine.addSystem(new LoggerSystem(mapDraw));

    }
}

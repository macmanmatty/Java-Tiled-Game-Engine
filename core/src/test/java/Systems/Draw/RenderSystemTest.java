package Systems.Draw;

import com.badlogic.ashley.core.Engine;

import org.junit.Before;

import Maps.TestMap;
import libGDX.HeadlesslibGDX;

public class RenderSystemTest {
    TestMap testMap;
    @Before
    public void testLogger(){
        new HeadlesslibGDX();
        this.testMap= new TestMap();
      Engine engine= testMap.testWorld.getEngine();

    }
}

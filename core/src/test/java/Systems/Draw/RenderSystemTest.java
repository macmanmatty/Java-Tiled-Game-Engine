package Systems.Draw;

import static org.junit.Assert.assertEquals;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.Logging.GameLog;
import com.jessematty.black.tower.GameBaseClasses.Logging.GameLogLevel;
import com.jessematty.black.tower.GameBaseClasses.Logging.GameLogger;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Systems.Drawing.AnimationSystem;
import com.jessematty.black.tower.Systems.Drawing.RenderSystem;

import org.junit.Before;
import org.junit.Test;

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

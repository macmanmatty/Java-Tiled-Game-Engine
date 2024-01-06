package GameBaseClasses.Utilities;

import static org.junit.Assert.assertEquals;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.Logging.GameLog;
import com.jessematty.black.tower.GameBaseClasses.Logging.GameLogLevel;
import com.jessematty.black.tower.GameBaseClasses.Logging.ScreenLogger;

import org.junit.Test;

import libGDX.HeadlesslibGDX;

public class LoggerTest {

    @Test
    public void testLogger(){
        new HeadlesslibGDX();
        ScreenLogger screenLogger = new ScreenLogger();
        Skin skin= new Skin();
        screenLogger.setLogLevel(GameLogLevel.DEBUG);
        screenLogger.log(new GameLog("log3", GameLogLevel.OFF, false), skin);
        assertEquals(screenLogger.getDisplayLogs().size, 0);

    }

    @Test
    public void testLogger2(){
        new HeadlesslibGDX();
        ScreenLogger screenLogger = new ScreenLogger();
        Skin skin= new Skin();
        screenLogger.setLogLevel(GameLogLevel.OFF);
        screenLogger.log(new GameLog("log3", GameLogLevel.OFF, false), skin);
        screenLogger.log(new GameLog("log3", GameLogLevel.DEBUG, false), skin);
        screenLogger.log(new GameLog("log3", GameLogLevel.INFO, false), skin);
        screenLogger.log(new GameLog("log3", GameLogLevel.ERROR, false), skin);
        assertEquals(screenLogger.getDisplayLogs().size, 0);

    }

}

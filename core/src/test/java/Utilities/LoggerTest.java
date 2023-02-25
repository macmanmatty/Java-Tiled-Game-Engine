package Utilities;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.Logging.GameLog;
import com.jessematty.black.tower.GameBaseClasses.Logging.GameLogLevel;
import com.jessematty.black.tower.GameBaseClasses.Logging.GameLogger;

import org.junit.Test;

import libGDX.HeadlesslibGDX;

import static org.junit.Assert.assertEquals;

public class LoggerTest {

    @Test
    public void testLogger(){
        new HeadlesslibGDX();
        GameLogger gameLogger= new GameLogger();
        Skin skin= new Skin();
        gameLogger.setLogLevel(GameLogLevel.DEBUG);
        gameLogger.log(new GameLog("log3", GameLogLevel.OFF, false), skin);
        assertEquals(gameLogger.getDisplayLogs().size, 0);

    }

    @Test
    public void testLogger2(){
        new HeadlesslibGDX();
        GameLogger gameLogger= new GameLogger();
        Skin skin= new Skin();
        gameLogger.setLogLevel(GameLogLevel.OFF);
        gameLogger.log(new GameLog("log3", GameLogLevel.OFF, false), skin);
        gameLogger.log(new GameLog("log3", GameLogLevel.DEBUG, false), skin);
        gameLogger.log(new GameLog("log3", GameLogLevel.INFO, false), skin);
        gameLogger.log(new GameLog("log3", GameLogLevel.ERROR, false), skin);
        assertEquals(gameLogger.getDisplayLogs().size, 0);

    }

}

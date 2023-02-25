package Utilities;

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
        gameLogger.setLogLevel(1);
        gameLogger.log(new GameLog("log", GameLogLevel.ERROR, false));
        gameLogger.log(new GameLog("log1", GameLogLevel.INFO, false));
        gameLogger.log(new GameLog("log2", GameLogLevel.DEBUG, false));
        gameLogger.log(new GameLog("log3", GameLogLevel.OFF, false));
        assertEquals(gameLogger.getDisplayLogs().size, 3);

    }

}

package com.jessematty.black.tower.Systems.Unused;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Logger;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Logging.GameLog;
import com.jessematty.black.tower.GameBaseClasses.Logging.GameLogLevel;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class LoggerSystem extends GameEntitySystem {
    private ComponentMapper<GameLog> logComponentMapper;
    private ImmutableArray<Entity> entities;
    private Logger logger= new Logger("gameLogs");


    public LoggerSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
       logComponentMapper= GameComponentMapper.getLogComponentMapper();

    }

    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all( GameLog.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++) {
            Entity entity=entities.get(count);
            GameLog gameLog =logComponentMapper.get(entity);
            if(gameLog.isDisplayOnScreen()) {
            }
            systemLog(gameLog);
            // after logging remove log from engine
            getEngine().removeEntity(entity);

        }




    }

    /**
     * write logs to  system log file  based on  log level
     * OFF level doesn't write logs to the system log  file.
     * @param gameLog the log component
     */
    public void systemLog(GameLog gameLog){
        GameLogLevel logLevel= gameLog.getGameLogLevel();
        switch(logLevel){
            case ERROR:
                logger.error(gameLog.getText());
                break;
            case INFO:
                logger.info(gameLog.getText());
                break;
            case DEBUG:
                logger.debug(gameLog.getText());
                break;
            default:
                return;
        }


    }
}

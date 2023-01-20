package com.jessematty.black.tower.Systems.UI;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Logger;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Logging.GameLogLevel;
import com.jessematty.black.tower.GameBaseClasses.Logging.Log;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class LoggerSystem extends GameEntitySystem {
    private ComponentMapper<Log> logComponentMapper;
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
        entities=getEngine().getEntitiesFor(Family.all( Log.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++) {
            Entity entity=entities.get(count);
            Log log=logComponentMapper.get(entity);
            if(log.isDisplayOnScreen()) {
                getDraw().getUiStage().addLogLabel(new Label(log.getText(), getDraw().getCurrentMap().getSkin(), log.getStyle()));
            }
            systemLog(log);
            // after logging remove log from engine
            getEngine().removeEntity(entity);

        }




    }

    /**
     * write logs to  system log file  based on  log level
     * OFF level doesn't write logs to the system log  file.
     * @param log the log component
     */
    public void systemLog(Log log){
        GameLogLevel logLevel=log.getGameLogLevel();
        switch(logLevel){
            case ERROR:
                logger.error(log.getText());
                break;
            case INFO:
                logger.info(log.getText());
                break;
            case DEBUG:
                logger.debug(log.getText());
                break;
            default:
                return;
        }


    }
}

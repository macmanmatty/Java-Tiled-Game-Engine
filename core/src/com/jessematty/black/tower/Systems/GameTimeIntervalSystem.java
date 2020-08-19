package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.systems.IntervalSystem;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public abstract  class GameTimeIntervalSystem extends IntervalSystem  {


   private   final  MapDraw draw;
    private   final GameComponentMapper gameComponentMapper;
    private final GameTime gameTime;





    public GameTimeIntervalSystem(float interval, MapDraw draw) {
        super(interval);
        this.draw = draw;
        gameComponentMapper=draw.getGameComponentMapper();
        this.gameTime=draw.getGameTime();
    }

    public GameTimeIntervalSystem(float interval, int priority, MapDraw draw) {
        super(interval, priority);
        this.draw = draw;
        gameComponentMapper=draw.getGameComponentMapper();
        this.gameTime=draw.getGameTime();
    }

    public MapDraw getDraw() {
        return draw;
    }

    public GameComponentMapper getGameComponentMapper() {
        return gameComponentMapper;
    }

    public GameTime getGameTime() {
        return gameTime;
    }
}

package com.jessematty.black.tower.Systems.Engine;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

/**
 * system for a single event once the event has  acted it get removed from the libGDX Ashlely engine;
 *
 */
public abstract  class EventSystem  extends GameEntitySystem {


    public EventSystem(MapDraw draw) {
        super(draw);
    }

    public EventSystem(int priority, MapDraw draw) {
        super(priority, draw);
    }

    /**
     * the act method gets  called and once it has finished
     * the system is removed from the engine
     * @param deltaTime
     */
    @Override
   final  public void update(float deltaTime) {
        act(deltaTime);
        getEngine().removeSystem(this);
    }

    public abstract void act(float deltaTime);

}

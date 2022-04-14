package com.jessematty.black.tower.Systems;

        import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public abstract  class EventSystem  extends GameEntitySystem{
    // system for single event that is removed from the engine after the event is preformed


    public EventSystem(MapDraw draw) {
        super(draw);
    }

    public EventSystem(int priority, MapDraw draw) {
        super(priority, draw);
    }

    @Override
   final  public void update(float deltaTime) {
        act(deltaTime);
        getEngine().removeSystem(this);
    }

    public abstract void act(float deltaTime);

}

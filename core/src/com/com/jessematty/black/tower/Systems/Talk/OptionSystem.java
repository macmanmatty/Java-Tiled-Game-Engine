package com.jessematty.black.tower.Systems.Talk;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

import java.util.List;

public class OptionSystem extends EntitySystem {

    List<String> options;
    List<EntitySystem> actions;
    MapDraw draw;

    public OptionSystem(List<String> options, List<EntitySystem> actions, MapDraw draw) {
        this.options = options;
        this.actions = actions;
        this.draw = draw;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}

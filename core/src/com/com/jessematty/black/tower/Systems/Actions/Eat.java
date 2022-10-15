package com.jessematty.black.tower.Systems.Actions;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.jessematty.black.tower.Components.Stats.NumericStats;

public class Eat extends EntitySystem {

    Entity item;
    NumericStats stats;


    public Eat( Entity item, NumericStats stats) {
        this.item = item;
        this.stats = stats;
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

    }

}

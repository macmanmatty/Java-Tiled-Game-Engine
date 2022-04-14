package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.Engine;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class TileHumiditySystem extends GameTimeIntervalSystem {



    public TileHumiditySystem(float interval, MapDraw draw) {
        super(interval, draw);
    }

    @Override
    public void addedToEngine(Engine engine) {


    }

    @Override
    protected void updateInterval() {

    }



}




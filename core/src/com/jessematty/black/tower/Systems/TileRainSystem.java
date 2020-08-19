package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Tile;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class TileRainSystem extends GameTimeIntervalSystem {

    private ComponentMapper<Tile> tiles= ComponentMapper.getFor(Tile.class);

    private ImmutableArray<Entity> entities;
    private GameTime gameTime;

    @Override
    public void addedToEngine(Engine engine) {



    }

    public TileRainSystem(float interval, MapDraw draw) {
        super(interval, draw);
    }

    @Override
    protected void updateInterval() {









        }




    }






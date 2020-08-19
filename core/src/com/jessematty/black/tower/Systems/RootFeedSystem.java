package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.OwnedComponent;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.Root;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Tile;
import com.jessematty.black.tower.Components.TileWeatherNumericStatsChangable;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class RootFeedSystem extends GameTimeIntervalSystem {
    private ComponentMapper<Tile> tileComponentMapper;
    private ComponentMapper<NumericStats> numericStatsComponentMapper;
    private ComponentMapper<Position> positionComponentMapper;
    private ComponentMapper<Root> rootComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    private ImmutableArray<Entity> entities;
    private GameTime gameTime;
    private float interval;
    @Override
    public void addedToEngine(Engine engine) {
        numericStatsComponentMapper=getGameComponentMapper().getNumericStatsComponentMapper();
            tileComponentMapper=getGameComponentMapper().getTileComponentMapper();
            positionComponentMapper=getGameComponentMapper().getPositionComponentMapper();
            rootComponentMapper=getGameComponentMapper().getRootComponentMapper();
            ownedComponentComponentMapper=getGameComponentMapper().getOwnedComponentComponentMapper();
            gameTime=getGameTime();
    }
    public RootFeedSystem(float interval, MapDraw draw) {
        super(interval, draw);
        this.interval=interval;
    }
    @Override
    protected void updateInterval() {
        entities= getEngine().getEntitiesFor(Family.all( Root.class, NumericStats.class, TileWeatherNumericStatsChangable.class ).get());
        int size=entities.size();
        for(int count=0; count<size; count++) {
            Entity entity = entities.get(count);
            Root  root= rootComponentMapper.get(entity);
            Position position=positionComponentMapper.get(entity);
            LandSquareTile tile=position.getTiles().get(0);
            ChangeStats.changeStats(entity, tile,"capillary" , true,  true, true );

        }
    }
}

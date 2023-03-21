package com.jessematty.black.tower.Systems.Tiles;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Tiles.Tile;
import com.jessematty.black.tower.Components.Tiles.TileWeatherChangableNumericStatChangeable;
import com.jessematty.black.tower.Components.Tiles.TileWeatherNumericStatsChangable;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameTimeIntervalSystem;

public class TileWeatherSeasonSystem extends GameTimeIntervalSystem {
    private ComponentMapper<Tile> tileComponentMapper;
    private ComponentMapper<NumericStats> numericStatsComponentMapper;
    private ComponentMapper<TileWeatherNumericStatsChangable> numericStatsChangableComponentMapper;
    private ImmutableArray<Entity> entities;
    private GameTime gameTime;
    private float interval;
    @Override
    public void addedToEngine(Engine engine) {
        numericStatsComponentMapper= GameComponentMapper.getNumericStatsComponentMapper();
            numericStatsChangableComponentMapper =GameComponentMapper.getTileWeatherNumericStatsChangableComponentMapper();
            tileComponentMapper=GameComponentMapper.getTileComponentMapper();
            gameTime=getGameTime();
    }
    public TileWeatherSeasonSystem(float interval, MapDraw draw) {
        super(interval, draw);
        this.interval=interval;
    }
    @Override
    protected void updateInterval() {
        entities= getEngine().getEntitiesFor(Family.all( Tile.class, NumericStats.class, TileWeatherNumericStatsChangable.class ).get());
        int size=entities.size();
        for(int count=0; count<size; count++) {
            Entity entity = entities.get(count);
            NumericStats tileStats = numericStatsComponentMapper.get(entity);
            TileWeatherNumericStatsChangable tileWeatherNumericStatsChangable = numericStatsChangableComponentMapper.get(entity);
           Array<TileWeatherChangableNumericStatChangeable> changableNumericStats= tileWeatherNumericStatsChangable.getStatsToChange();
            int size2=changableNumericStats.size;
            for(int count2=0; count2<size2; count2++ ){
                TileWeatherChangableNumericStatChangeable changableNumericStat=changableNumericStats.get(count2);
                NumericStat stat=tileStats.getNumericStat(changableNumericStat.getName());
                if(stat!=null){
                    if(gameTime.getCurrentNumberOfDaysLapsedInCurrentYear()>=changableNumericStat.getChangePoint()){
                            if (changableNumericStat.isRandomChange()) {
                                stat.addValuesRandom(changableNumericStat, .1f*interval);
                            }
                        }
                        else if (gameTime.getCurrentNumberOfDaysLapsedInCurrentYear()<changableNumericStat.getChangePoint()){
                            if (changableNumericStat.isRandomChange()) {
                                stat.subtractValuesRandom(changableNumericStat, .1f*interval);
                            }
                        }
                        else{
                            
                            stat.addValues(changableNumericStat.getDoubleValue(), 0, 0);
                            
                    }
                }
            }
        }
    }
}

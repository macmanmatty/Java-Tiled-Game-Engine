package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.Transient;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangable;
import com.jessematty.black.tower.Components.Tiles.Tile;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.Season;

@Transient

public class ChangeTileNumericStatSystem extends GameTimeIntervalSystem {


    private ComponentMapper<Tile> tileComponentMapper;
    private ComponentMapper<NumericStatsChangable> numericStatChangeableComponentMapper;
    private ComponentMapper<NumericStats> numericStatsComponentMapper;
    ImmutableArray<Entity> entities;
    GameTime gameTime;
    float interval;




    public ChangeTileNumericStatSystem(float interval, MapDraw draw) {
        super(interval, draw);
        gameTime=draw.getGameTime();
        this.interval=interval;


    }

    @Override
    public void addedToEngine(Engine engine) {
        tileComponentMapper =getGameComponentMapper().getTileComponentMapper();
        numericStatChangeableComponentMapper=getGameComponentMapper().getNumericStatsChangableComponentMapper();
        numericStatsComponentMapper=getGameComponentMapper().getNumericStatsComponentMapper();


    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    protected void updateInterval() {
        entities= getEngine().getEntitiesFor(Family.all(NumericStats.class, Tile.class, NumericStatsChangable.class).get());

            int size=entities.size();
            for(int count=0; count<size; count++){
                Entity entity=entities.get(count);
                NumericStatsChangable numericStatsChangable = numericStatChangeableComponentMapper.get(entity);
                Array<ChangableNumericStat> changableNumericStats = numericStatsChangable.getStatsToChange();
                NumericStats stats= numericStatsComponentMapper.get(entity);
                int size2= changableNumericStats.size;

                for(int count2=0; count2<size2; count2++){

                    ChangableNumericStat changableNumericStat = changableNumericStats.get(count2);
                    NumericStat stat=stats.getNumericStat(changableNumericStat.getName());
                    if(stat==null){
                        continue;
                    }
                    int random= RandomNumbers.getRandomNumber(1, 100);
                    if(random%2==0) {
                        stat.subtractValuesRandom(changableNumericStat, .1f * interval);
                    }
                    else{

                        stat.addValuesRandom(changableNumericStat, .1f *interval);

                    }

                }
                Season season =gameTime.getSeason();
                if(season==Season.FALL || season==Season.SPRING){




                }














            }




        }



}




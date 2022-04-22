package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.FlagComponents.BooleanStatChanged;
import com.jessematty.black.tower.Components.FlagComponents.NumericStatChanged;
import com.jessematty.black.tower.Components.FlagComponents.StatChanged;
import com.jessematty.black.tower.Components.FlagComponents.StringStatChanged;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.TimeChangeStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.TimeChangingStats;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.Stat;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Transient;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import java.util.Iterator;
@Transient
public class TimeChangingStatSystem extends GameEntitySystem {
    private ComponentMapper<TimeChangingStats> timeChangingNumericStatsComponentMapper;
    private ComponentMapper<NumericStats> numericStatsComponentMapper;
    private ComponentMapper<BooleanStats> booleanStatsComponentMapper;
    private ComponentMapper<StringStats> stringStatsComponentMapper;
    public TimeChangingStatSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        timeChangingNumericStatsComponentMapper= GameComponentMapper.getTimeChangingNumericStatsComponentMapper();
        numericStatsComponentMapper=GameComponentMapper.getNumericStatsComponentMapper();
        booleanStatsComponentMapper=GameComponentMapper.getBooleanStatsComponentMapper();
        stringStatsComponentMapper=GameComponentMapper.getStringStatsComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities=getEngine().getEntitiesFor(Family.all(TimeChangingStats.class, NumericStats.class).get());
        int size=entities.size();
        for(int count=0;  count<size; count++ ) {
            Entity entity=entities.get(count);
            TimeChangingStats timeChangingStats =timeChangingNumericStatsComponentMapper.get(entity);
            changeStats(timeChangingStats, entity);
        }
    }
    private void changeStats(TimeChangingStats timeChangingStats, Entity  entity) {
        NumericStats numericStats=numericStatsComponentMapper.get(entity);
        BooleanStats booleanStats=booleanStatsComponentMapper.get(entity);
        StringStats stringStats=stringStatsComponentMapper.get(entity);
        Array<TimeChangeStat> timeChangeNumericStats= timeChangingStats.getTimeChangeNumericStats();
        Iterator<TimeChangeStat> timeChangeNumericStatArrayIterator=timeChangeNumericStats.iterator();
        int size=timeChangeNumericStats.size;
        // loop over time changing stats
        while(timeChangeNumericStatArrayIterator.hasNext()){
            TimeChangeStat timeChangeStat =timeChangeNumericStatArrayIterator.next();
            timeChangeStat.tick();// tick stat counter
            if(timeChangeStat.getCounter()== timeChangeStat.getTimeToChange()){
                // if time up change stat back and remove the time changing stat
                Stat statToChangeBackTo= timeChangeStat.getOldStat();
                if(statToChangeBackTo instanceof  NumericStat) {
                    numericStats.getNumericStats().put(statToChangeBackTo.getName(), (NumericStat) statToChangeBackTo);
                    entity.add(new NumericStatChanged());
                    entity.add(new StatChanged());
                }
               else  if(statToChangeBackTo instanceof BooleanStat) {
                    booleanStats.getBooleanStats().put(statToChangeBackTo.getName(), (BooleanStat) statToChangeBackTo);
                    entity.add(new BooleanStatChanged());
                    entity.add(new StatChanged());
                }
                else  if(statToChangeBackTo instanceof StringStat) {
                    stringStats.getStringStats().put(statToChangeBackTo.getName(), (StringStat) statToChangeBackTo);
                    entity.add(new StringStatChanged());
                    entity.add(new StatChanged());
                }
                timeChangeNumericStatArrayIterator.remove();
            }
        }
    }
}

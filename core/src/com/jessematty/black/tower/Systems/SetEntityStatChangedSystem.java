package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Markers.BooleanStatChanged;
import com.jessematty.black.tower.Components.Markers.NumericStatChanged;
import com.jessematty.black.tower.Components.Markers.StringStatChanged;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
public class SetEntityStatChangedSystem extends GameEntitySystem {
    private ComponentMapper<NumericStats> numericStatsComponentMapper;
    private ComponentMapper<BooleanStats> booleanStatsComponentMapper;
    private ComponentMapper<StringStats> stringStatsComponentMapper;
    private ImmutableArray<Entity> entities;
    public SetEntityStatChangedSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        numericStatsComponentMapper=getGameComponentMapper().getNumericStatsComponentMapper();
        stringStatsComponentMapper=getGameComponentMapper().getStringStatsComponentMapper();
        booleanStatsComponentMapper=getGameComponentMapper().getBooleanStatsComponentMapper();
        
        
    }
    @Override
    public void update(float deltaTime) {
        entities= getEngine().getEntitiesFor(Family.all(StringStats.class, NumericStats.class, BooleanStats.class ).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            StringStats stringStats=stringStatsComponentMapper.get(entity);
            if(stringStats.isStatHasChanged()){
                
                entity.add(new StringStatChanged());
            }
            else{
                
                entity.remove(StringStatChanged.class);
            }
            NumericStats numericStats=numericStatsComponentMapper.get(entity);
            if(numericStats.isStatHasChanged()){
                entity.add(new NumericStatChanged());
            }
            else{
                entity.remove(NumericStatChanged.class);
            }
            BooleanStats booleanStats=booleanStatsComponentMapper.get(entity);
            if(booleanStats.isStatHasChanged()){
                entity.add(new BooleanStatChanged());
            }
            else{
                entity.remove(BooleanStatChanged.class);
            }
        }
        super.update(deltaTime);
    }
}

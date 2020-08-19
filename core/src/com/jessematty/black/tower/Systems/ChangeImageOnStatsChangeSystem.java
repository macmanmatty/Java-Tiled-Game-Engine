package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangeImageOnStat;
import com.jessematty.black.tower.Components.ChangeImage.ChangeImageOnStatsValueChanges;
import com.jessematty.black.tower.Components.Animation.Drawable;
import com.jessematty.black.tower.Components.Markers.StatChanged;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.Entity.CheckStats;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import java.util.List;
public class ChangeImageOnStatsChangeSystem extends GameEntitySystem {
    private ComponentMapper<NumericStats>  numericStatsComponentMapper;
    private ComponentMapper<BooleanStats> booleanStatsComponentMapper;
    private ComponentMapper<StringStats> stringStatsComponentMapper;
    private ComponentMapper<ChangeImageOnStatsValueChanges> changeImageOnStatsValueChangesComponentMapper;
    private ComponentMapper<Drawable> drawableComponentMapper;
    private ComponentMapper<AnimatableComponent> animatableComponentMapper;
    private ImmutableArray<Entity> entities;
    public ChangeImageOnStatsChangeSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        numericStatsComponentMapper=getGameComponentMapper().getNumericStatsComponentMapper();
        booleanStatsComponentMapper=getGameComponentMapper().getBooleanStatsComponentMapper();
        stringStatsComponentMapper=getGameComponentMapper().getStringStatsComponentMapper();
        changeImageOnStatsValueChangesComponentMapper=getGameComponentMapper().getChangeImageOnStatsValueChangesComponentMapper();
        drawableComponentMapper=getGameComponentMapper().getDrawableComponentMapper();
        animatableComponentMapper=getGameComponentMapper().getAnimatableComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        entities= getEngine().getEntitiesFor(Family.all( Drawable.class, StatChanged.class, NumericStats.class, BooleanStats.class, StringStats.class, ChangeImageOnStatsValueChanges.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
           Entity entity=entities.get(count);
            NumericStats numericStats=numericStatsComponentMapper.get(entity);
            BooleanStats booleanStats=booleanStatsComponentMapper.get(entity);
            StringStats stringStats=stringStatsComponentMapper.get(entity);
            ChangeImageOnStatsValueChanges changeImageOnStatsValueChanges=changeImageOnStatsValueChangesComponentMapper.get(entity);
            List<ChangeImageOnStat> changeImageOnStats= changeImageOnStatsValueChanges.getChangeImageOnNumericStats();
            Drawable drawable=drawableComponentMapper.get(entity);
            AnimatableComponent animatable=animatableComponentMapper.get(entity);
            boolean incremental=changeImageOnStatsValueChanges.isInChangeSteps();
            int size2=changeImageOnStats.size();
            for(int count2=0; count2<size2; count2++){
                ChangeImageOnStat changeImageOnStat= changeImageOnStats.get(count2);
                boolean remove=changeImageOnStat.isPermanent();
                List<NumericStat> numericStatsToChangeOn=changeImageOnStat.getNumericStats();
                List<BooleanStat> booleanStatsToChangeOn=changeImageOnStat.getBooleanStats();
                List<StringStat> stringStatsToChageOn= changeImageOnStat.getStringStats();
                boolean hasNumericStats= CheckStats.valuesAreEqualOrGreaterThan(numericStatsToChangeOn, numericStats);
                boolean hasBooleanStats=CheckStats.valuesAreEqual(booleanStatsToChangeOn, booleanStats);
                boolean hasStringStats=CheckStats.valuesAreEqual(stringStatsToChageOn, stringStats);
                boolean changed=false;
                if(hasBooleanStats==true&& hasNumericStats==true && hasStringStats==true){
                        drawable.setCurrentRegion(getAsssets().getAtlasRegionByName(changeImageOnStat.getImageName(), changeImageOnStat.getAtlasName()));
                        changed=true;
                }
                if(changed==false && incremental==true){
                    break;
                }
                else if (changed==true){
                    if(remove==true){
                        changeImageOnStats.remove(changeImageOnStat);
                        
                    }
                    break;
                }
            }
        }
        super.update(deltaTime);
    }
}

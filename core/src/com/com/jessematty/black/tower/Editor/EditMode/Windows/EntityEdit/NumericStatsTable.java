package com.jessematty.black.tower.Editor.EditMode.Windows.EntityEdit;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeable;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeable;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.FloatField;
public class NumericStatsTable {
    private  NumericStats numericStats;
    private StringStats stringStats;
    private BooleanStats booleanStats;
    private NumericStatsChangeable numericStatsChangeable;
    private BooleanStatsChangeable booleanStatsChangeable;
    private StringStatsChangeable stringStatsChangeable;
    private Entity entity;
    private Table table = new Table();
    private VerticalGroup verticalGroup= new VerticalGroup();
    private Skin skin;
    public NumericStatsTable(Entity entity, Skin skin) {
        this.numericStats = entity.getComponent(NumericStats.class);
        this.booleanStats=entity.getComponent(BooleanStats.class);
        this.stringStats=entity.getComponent(StringStats.class);
        this.numericStatsChangeable =entity.getComponent(NumericStatsChangeable.class);
        this.booleanStatsChangeable =entity.getComponent(BooleanStatsChangeable.class);
        this.stringStatsChangeable =entity.getComponent(StringStatsChangeable.class);
        this.entity=entity;
        this.skin=skin;
        table.setDebug(true);
    }
    public void makeNumericStatsTable(){
        final Label remove= new Label("Remove", skin);
        Label name= new Label("Stat Name", skin);
        Label value= new Label("Value", skin);
        Label min= new Label(" Min Value", skin);
        Label max= new Label("Max Value", skin);
        Label displayable= new Label("Is Displayed In Windows", skin);
        final Label dieWhenZero= new Label("Die When Zero", skin);
        table.add(remove, name, value, min, max, displayable, dieWhenZero);
        table.row();
        Keys<String> keys= numericStats.getNumericStats().keys();
        while(keys.hasNext){
            final String key=keys.next();
            final NumericStat stat=numericStats.getNumericStat(key);
            CheckBox removeBox= new CheckBox("", skin);
            final Label statName= new Label(stat.getName(), skin);
            final FloatField valueField= new FloatField(""+stat.getDoubleValue(), skin);
            final FloatField minField= new FloatField(""+stat.getMinValue(), skin);
            final FloatField maxField= new FloatField(""+stat.getMaxValue(), skin);
            final CheckBox displayableBox= new CheckBox("", skin);
            displayableBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    stat.setDisplayable(displayableBox.isChecked());
                }
            });
            final CheckBox dieWhenZeroBox= new CheckBox("", skin);
            displayableBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                stat.setKillWhenZero(dieWhenZeroBox.isChecked());
                if(dieWhenZeroBox.isChecked()){
                    numericStats.getDieWhenZero().add(stat);
                }
                else{
                    numericStats.getDieWhenZero().removeValue(stat, false);
                }
                }
            });

            table.add(remove, name, valueField, minField, maxField, displayableBox, dieWhenZero);
            table.row();
            removeBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    numericStats.removeStat(key);
                    table.removeActor(remove);
                    table.removeActor(statName);
                    table.removeActor(valueField);
                    table.removeActor(maxField);
                    table.removeActor(minField);
                    table.removeActor(displayableBox);
                    table.removeActor(dieWhenZero);
                    
                }
            });
            
        }



    }
    public void makeNumericStatsChangeableTable(){
        final Label remove= new Label("Remove", skin);
        Label name= new Label("Stat Name", skin);
        Label value= new Label("Value", skin);
        Label min= new Label(" Min Value", skin);
        Label max= new Label("Max Value", skin);
        Label timeToChange= new Label("Change Length", skin);
        Label random =new Label("Random Change", skin);
        Label  actsOnEntity= new Label("Acts On Entity ", skin);
        Label  intervalToActOn= new Label("Interval To Self Act on", skin);
        Label displayable= new Label("Is Displayed In Windows", skin);
        table.add(remove, name, value, min, max, displayable);
        table.row();
        Keys<String> keys= numericStats.getNumericStats().keys();
        while(keys.hasNext){
            final String key=keys.next();
            final NumericStat stat=numericStats.getNumericStat(key);
            CheckBox removeBox= new CheckBox("", skin);
            final Label statName= new Label(stat.getName(), skin);
            final FloatField valueField= new FloatField(""+stat.getDoubleValue(),  skin );
            final FloatField minField= new FloatField(""+stat.getMinValue(), skin);
            final FloatField maxField= new FloatField(""+stat.getMaxValue(), skin);
            final CheckBox displayableBox= new CheckBox("", skin);
            displayableBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    stat.setDisplayable(displayableBox.isChecked());
                }
            });
            table.add(remove, name, valueField, minField, maxField, displayableBox);
            table.row();
            removeBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    numericStats.removeStat(key);
                    table.removeActor(remove);
                    table.removeActor(statName);
                    table.removeActor(valueField);
                    table.removeActor(maxField);
                    table.removeActor(minField);
                    table.removeActor(displayableBox);
                }
            });
        }
    }
    public NumericStats getNumericStats() {
        return numericStats;
    }
}

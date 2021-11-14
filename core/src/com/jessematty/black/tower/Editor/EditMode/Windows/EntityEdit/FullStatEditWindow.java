package com.jessematty.black.tower.Editor.EditMode.Windows.EntityEdit;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStatCombine;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangingStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.SelfChangableNumericStatChangeable;
import com.jessematty.black.tower.Components.Stats.Stat;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.TileWeatherChangableNumericStatChangeable;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists.DragLists.OneWayDragList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.FloatField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveIntegerField;

public   class FullStatEditWindow extends MapEditWindow {
    private Stat stat;
    private NumericStats numericStats;
    private Entity entity;
    public FullStatEditWindow(MapEditScreen mapEditScreen, String title, Skin skin, String style) {
        super(mapEditScreen, title, skin, style);
    }
    public void makeWindow(Entity entity, final Stat stat1) {
        this.stat = stat1;
        add(new Label(stat.getName(), getSkin()));
        row();
        Array<String> gameGroups = getMapEditScreen().getWorldObjects().getGroups();
        Array<String> itemChangeGroups = stat.getChangeGroups();
        final CheckBox displayable = new CheckBox("", getSkin());
        displayable.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stat.setDisplayable(displayable.isChecked());
            }
        });
        final CheckBox combinable = new CheckBox("", getSkin());
        combinable.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stat.setDisplayable(combinable.isChecked());
            }
        });         
        OneWayDragList<String> changeGroup = new OneWayDragList<>(getMapEditScreen().getDragAndDrop(), getSkin(), gameGroups, itemChangeGroups, "Groups In Game", "Groups That Change Stat");
        add(displayable);
        row();
        add(combinable);
        add(changeGroup);
        row();
        if (stat instanceof  NumericStat){
            makeNumericStatWindow();
        }
        if(stat instanceof  ColorChangingStat){
            makeColorChangingNumericStatWindow();
        }
        if(stat instanceof NumericStatChangeable){
            makeChangeableNumericStat();
        }
        if(stat instanceof SelfChangableNumericStatChangeable){
            makeSelfChangeableNumericStat();
        }

        if(stat instanceof TileWeatherChangableNumericStatChangeable){

            makeTileWeatherChangeableNumericStat();
        }

        if(stat instanceof BooleanStat){
            makeBooleanStatWindow();
        }

        if(stat instanceof BooleanStatChangeable){

            makeChangableBooleanStat();
        }
        if(stat instanceof StringStat){
            makeStringStat();
        }
        if(stat instanceof StringStatChangeable){
            makeChangableStringStat();
        }
    }
    private void makeChangableStringStat() {
    }
    private void makeStringStat() {
    }
    private void makeTileWeatherChangeableNumericStat() {
    }
    private void makeSelfChangeableNumericStat() {
    }
    public  void makeBooleanStatWindow(){
        final BooleanStat booleanStat= (BooleanStat) stat;
        final CheckBox flagBox= new CheckBox("Stat Flag Checked=True Unchecked=False", skin);
        flagBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                booleanStat.setFlag(flagBox.isChecked());
            }
        });
        add(flagBox);
        row();
    }
    public void makeChangableBooleanStat(){
        final BooleanStatChangeable booleanStat= (BooleanStatChangeable) stat;

        Array<String> gameActions = getMapEditScreen().getWorldObjects().getChangeActions();
        Array<String> itemChangeActions = booleanStat.getActionsToChangeOn();
        OneWayDragList<String> actionsGroup = new OneWayDragList<>(getMapEditScreen().getDragAndDrop(), getSkin(), gameActions, itemChangeActions, "Groups In Game", "Groups That Change Stat");
        final SelectBox<BooleanStatCombine> booleanStatCombineSelectBox= new SelectBox<BooleanStatCombine>(skin);
        booleanStatCombineSelectBox.setItems(BooleanStatCombine.values());
        booleanStatCombineSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                booleanStat.setCombinable(true);
                booleanStat.setCombineMode(booleanStatCombineSelectBox.getSelected());
            }
        });

        final PositiveIntegerField timeField = new PositiveIntegerField(""+booleanStat.getAmountOfTimeToChangeFor(), skin);
        timeField.addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Keys.ENTER) {
                    booleanStat.setAmountOfTimeToChangeFor(timeField.getInteger());
                }
                return true;
            }
        });

        NamedField namedField= new NamedField("Duration Of Stat Change",skin, timeField);

        add(namedField);
        row();
        add(actionsGroup);


    }
    public void makeChangeableNumericStat(){
        final NumericStatChangeable changableNumericStat= (NumericStatChangeable) stat;
        Array<String> gameActions = getMapEditScreen().getWorldObjects().getChangeActions();
        Array<String> itemChangeActions = changableNumericStat.getActionsToChangeOn();
         OneWayDragList<String> actionsGroup = new OneWayDragList<>(getMapEditScreen().getDragAndDrop(), getSkin(), gameActions, itemChangeActions, "Groups In Game", "Groups That Change Stat");
        final CheckBox selfChangeable = new CheckBox("Stat Changes Entity's Color", skin);
        selfChangeable.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selfChangeable.isChecked()) {
                    if (stat instanceof NumericStat && !(stat instanceof SelfChangableNumericStatChangeable)) {
                        stat = new SelfChangableNumericStatChangeable((NumericStatChangeable) stat);
                        numericStats.getSelfChangableNumericStats().add((SelfChangableNumericStatChangeable) stat);
                        makeWindow(entity, stat);
                    }
                } else {
                    if (stat instanceof NumericStat) {
                        stat = new NumericStat((NumericStat) stat);
                        numericStats.getSelfChangableNumericStats().removeValue((SelfChangableNumericStatChangeable) stat, true);
                        makeWindow(entity, stat);
                    }
                }
            }
        });
        final PositiveIntegerField timeField = new PositiveIntegerField(""+changableNumericStat.getAmountOfTimeToChangeFor(), skin);
        timeField.addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Keys.ENTER) {
                    changableNumericStat.setAmountOfTimeToChangeFor(timeField.getInteger());
                }
                return true;
            }
        });

        NamedField namedField= new NamedField("Duration Of Stat Change",skin, timeField);

        add(namedField);
        row();
        final CheckBox randomBox= new CheckBox("Stat Flag Checked=True Unchecked=False", skin);
        randomBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changableNumericStat.setRandomChange(randomBox.isChecked());
            }
        });

        add(randomBox);
        row();


        add(actionsGroup);
        row();

    }
    public void makeNumericStatWindow() {
        Skin skin = getSkin();
        final NumericStat numericStat= (NumericStat) stat;
        final FloatField valueField = new FloatField(""+numericStat.getDoubleValue(), skin);
        valueField.addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Keys.ENTER) {
                    numericStat.setValue(valueField.getFloat());
                }
                return true;
            }
        });
        final FloatField minField = new FloatField(""+numericStat.getMinValue(), skin);
        minField.addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Keys.ENTER) {
                    numericStat.setMinValue(minField.getFloat());
                }
                return true;
            }
        });
        final FloatField maxField = new FloatField(""+numericStat.getMaxValue(), skin);
        maxField.addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Keys.ENTER) {
                    numericStat.setMaxValue(maxField.getFloat());
                }
                return true;
            }
        });
        final CheckBox dieWhenZero= new CheckBox("Stats Kills Entity When Zero", skin);
        dieWhenZero.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                numericStat.setKillWhenZero(dieWhenZero.isChecked());
            }
        });
        final CheckBox changesColor = new CheckBox("Stat Changes Entity's Color", skin);
        changesColor.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (changesColor.isChecked()) {
                    if (stat instanceof NumericStat && !(stat instanceof ColorChangingStat)) {
                        stat = new ColorChangingStat((NumericStat) stat);
                        numericStats.getColorChangingStats().add((ColorChangingStat) stat);
                        makeWindow(entity, stat);
                    }
                } else {
                    if (stat instanceof NumericStat) {
                        stat = new NumericStat((NumericStat) stat);
                        numericStats.getColorChangingStats().removeValue((ColorChangingStat) stat, false);
                        makeWindow(entity, stat);
                    }
                }
            }
        });
        add(new NamedField("Enter Stat Value", skin, valueField));
        row();
        add(new NamedField("Enter Min Stat Value", skin, minField));
        row();
        add(new NamedField("Enter Max Stat Value", skin, maxField));
        row();
        add(dieWhenZero);
        row();
         NumericStats stats= entity.getComponent(NumericStats.class);
        OneWayDragList<SelfChangableNumericStatChangeable> oneWayDragList= new OneWayDragList<>(getMapEditScreen().getDragAndDrop(), skin, stats.getSelfChangableNumericStats(), numericStat.getLinkedStatsToChange(), "Stats Attached To Entity", "Stats Linked To "+stat.getName());
        add(oneWayDragList);
        row();
    }
    public void makeColorChangingNumericStatWindow(){
        Skin skin=getSkin();
        final ColorChangingStat numericStat= (ColorChangingStat) stat;
        final FloatField colorField= new FloatField(""+numericStat.getColorMultiplier(), skin);
        colorField.addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Keys.ENTER) {
                    numericStat.setColorMultiplier(colorField.getFloat());
                }
                return true;

            }
        });
        final CheckBox affectsRed= new CheckBox("Affects Red Color", skin);
        affectsRed.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                numericStat.setAffectsRed(affectsRed.isChecked());
            }
        });
        final CheckBox affectsGreen= new CheckBox("Affects Green Color", skin);
        affectsGreen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                numericStat.setAffectsGreen(affectsGreen.isChecked());
            }
        });
        final CheckBox affectsBlue= new CheckBox("Affects Blue Color", skin);
        affectsBlue.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                numericStat.setAffectsBlue(affectsBlue.isChecked());
            }
        });
        final CheckBox affectsAlpha= new CheckBox("Affects Alpha Color", skin);
        affectsAlpha.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                numericStat.setAffectsAlpha(affectsAlpha.isChecked());
            }
        });

        NamedField namedColorField= new NamedField( "Enter Color Multiplier", skin, colorField );
        add(affectsRed, affectsGreen, affectsBlue, affectsAlpha);
        row();
        add(namedColorField);
        
       
    }

    @Override
    public void makeWindow() {

    }
}

package com.jessematty.black.tower.Editor.EditMode.Windows.OptionPaneWindows;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.FloatField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;


public class CreateStatOptionPane extends MapEditWindow {



        private SelectBox<com.jessematty.black.tower.Editor.EditMode.Windows.OptionPaneWindows.StatKinds> statKindsList;
        private FloatField globalMaxValue;
        private FloatField globalMinValue;
        private TextField statName;
        private Button addStat;



    public CreateStatOptionPane(MapEditScreen mapEditScreen, Skin skin) {
        super(mapEditScreen, "Create Stat", skin, "default");








    }

    @Override
    public void makeWindow() {
        statKindsList= new SelectBox<com.jessematty.black.tower.Editor.EditMode.Windows.OptionPaneWindows.StatKinds>(skin);
        statKindsList.setItems(com.jessematty.black.tower.Editor.EditMode.Windows.OptionPaneWindows.StatKinds.values());
        globalMaxValue= new FloatField(String.valueOf(Double.MAX_VALUE), skin);
        globalMinValue= new FloatField(String.valueOf(Double.MIN_VALUE), skin);
        statName=new TextField("", skin);
        addStat= new TextButton("Add Stat", skin);
        addStat.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                String name=statName.getText();
               StatKinds statKind=statKindsList.getSelected();
                switch(statKind){

                    case NUMERIC_STAT:
                        NumericStat stat= new NumericStat(true, name, 0 , globalMinValue.getDouble(), globalMaxValue.getDouble());
                        getMapEditScreen().getWorldObjects().addStat(stat);

                        break;
                    case STRING_STAT:
                        StringStat stringStat= new StringStat(true, name, "Stat");
                        getMapEditScreen().getWorldObjects().addStat(stringStat);


                        break;
                    case BOOLEAN_STAT:
                        BooleanStat booleanStat= new BooleanStat(true, name, true);
                        getMapEditScreen().getWorldObjects().addStat(booleanStat);

                        break;

                }


                return true;

            }
        });


        add(new Label("Select Stat Kind", skin));

        add(statKindsList);
        row();
        add( new NamedField("Enter Stat Name", getSkin(), statName));
        row();
        add( new NamedField("Min Global Stat Value", getSkin(), globalMinValue));
        add( new NamedField("Max Global Stat Value", getSkin(), globalMaxValue));



    }
}

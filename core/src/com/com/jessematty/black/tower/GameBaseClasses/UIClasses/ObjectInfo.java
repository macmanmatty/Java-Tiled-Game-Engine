package com.jessematty.black.tower.GameBaseClasses.UIClasses;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Other.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Other.ZRPGCharacter;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.util.Iterator;

public class ObjectInfo {

    Window window;
    GameAssets assetts;
    ComponentMapper<NameComponent> nameComponentMapper;
    ComponentMapper<NumericStats> numericStatsComponentMapper;
    ComponentMapper<BooleanStats> booleanStatsComponentMapper;
    ComponentMapper<StringStats> stringStatsComponentMapper;

    ComponentMapper<PhysicalObjectComponent> objectComponentMapper;
    ComponentMapper<ImageComponent> itemImageComponentMapper;

    public ObjectInfo(GameAssets assetts) {
        this.assetts = assetts;
       
         nameComponentMapper= GameComponentMapper.getNameComponentMapper();
        numericStatsComponentMapper =GameComponentMapper.getNumericStatsComponentMapper();
        booleanStatsComponentMapper=GameComponentMapper.getBooleanStatsComponentMapper();
        stringStatsComponentMapper =GameComponentMapper.getStringStatsComponentMapper();
        objectComponentMapper=GameComponentMapper.getPhysicalObjectComponentMapper();
        itemImageComponentMapper=GameComponentMapper.getImageComponentMapper();



    }

    public void listObjects(final LandSquareTile location, final ZRPGCharacter fighter, final  Skin skin, final  String style){
        window= new Window(location.toString(), skin);
        Array<Entity> entities=location.getEntities();

        int size=entities.size;
        for(int count=0; count<size; count++){
            final Entity entity=entities.get(count);
            Label label = new Label(nameComponentMapper.get(entity).getStat(), skin, style);
            Image image= itemImageComponentMapper.get(entity).getImage();
            Button button= new TextButton("Info", skin, style);
            button.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                   assetts.getMapDraw().getUiStage().addWindow(window, location.getScreenLocationX(), location.getScreenLocationY());
                }
            });
        }

    }
    public Window displayInfo (ZRPGCharacter player, Entity entity, Skin skin, String style){
        if(style==null || style.isEmpty()){
            style="default";
        }

        nameComponentMapper = GameComponentMapper.getNameComponentMapper();
        window = new Window("window", skin);
        String name = player.getNameComponent().getStat();
        Label label = new Label(name, skin, style);
        window.add(label);

        Image image = player.getPlayerEntity().getComponent(ImageComponent.class).getImage();
        window.add(image);
        StringStats stringStats = player.getStringStats();

        Keys<String> stringStatNames = stringStats.getStringStats().keys();

        for (Iterator<String> stringIterator = stringStatNames.iterator(); stringIterator.hasNext();) {
            StringStat stat = stringStats.getStringStat(stringIterator.next());
            if (stat.isDisplayable()) {
                String statString = stat.getName() + ": " + stat.getStat();
                Label numericStatLabel = new Label(statString, skin);
                window.add(numericStatLabel);
            }


        }

        NumericStats stats=player.getNumericStats();

       Keys<String> statNames= stats.getNumericStats().keys();
        for(Iterator<String> stringIterator=statNames.iterator(); stringIterator.hasNext();){
            NumericStat stat=stats.getNumericStat(stringIterator.next());
            if(stat.isDisplayable()) {
                String statString = stat.getName() + ": " + stat.getIntValue();
                if(stat.isDisplayMinAndMax()){
                    statString=statString+" "+stat.getMinIntValue() +" / "+ stat.getMaxIntValue();

                }
                Label numericStatLabel=new Label(statString,skin );
                window.add(numericStatLabel);
            }
        }


        BooleanStats booleanStats=player.getBooleanStats();
        Keys<String> booleanStatNames= booleanStats.getBooleanStats().keys();
        for(Iterator<String> stringIterator=booleanStatNames.iterator(); stringIterator.hasNext();){
            BooleanStat stat=booleanStats.getBooleanStat(stringIterator.next());
            if(stat.isDisplayable()) {
                String statString = stat.getName() + ": " + stat.getFlag();
                Label numericStatLabel=new Label(statString,skin );
                window.add(numericStatLabel);
            }

        }
        window.validate();
        window.pack();
        return window;
    }



    
}

package com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.BottomBars;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Other.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups.BooleanStatGroup;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups.NumericStatGroup;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups.StringStatGroup;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
public class StatsGroup extends VerticalGroup {
    private Skin skin;
    private MapDraw draw;
    private final String groupName;
    public StatsGroup(MapDraw draw, String groupName) {
        this.draw=draw;
        this.skin=draw.getCurrentMap().getSkin();
        this.groupName=groupName;
        
    }
    public   void update(Skin skin, ZRPGCharacter zrpgCharacter){
      clear();
       this.skin = skin;
       NumericStats numericStats= zrpgCharacter.getNumericStats();
       BooleanStats booleanStats= zrpgCharacter.getBooleanStats();
       StringStats stringStats= zrpgCharacter.getStringStats();
       String name= zrpgCharacter.getNameComponent().getStat();
       Image image=null;
       ImageComponent imageComponent= zrpgCharacter.getImageComponent();
       if(imageComponent!=null) {
       image=imageComponent.getImage();
       }
        HorizontalGroup nameAndImage=new HorizontalGroup();
        if(name!=null) {
            Label nameLabel = new Label(name, skin);
            nameAndImage.addActor(nameLabel);
        }
        if(image!=null) {
            nameAndImage.addActor(image);
        }
        nameAndImage.space(5);
        addActor(nameAndImage);
        if(stringStats!=null) {
            ObjectMap<String, StringStat> statsStringStats= stringStats.getStringStats();
            Values<StringStat> stringStatValues=statsStringStats.values();
            while (stringStatValues.hasNext) {
                StringStat stringStat = stringStatValues.next();
                if (stringStat.isDisplayable() && InList.isInList(stringStat.getDisplayGroups(), groupName)) {
                StringStatGroup stringStatGroup=new StringStatGroup(skin, stringStat);
                addActor(stringStatGroup);
                }
            }
        }
        if(numericStats!=null) {
            ObjectMap<String, NumericStat> numericStatsMap = numericStats.getNumericStats();
            Values<NumericStat> numericStatValues = numericStatsMap.values();
            while (numericStatValues.hasNext()) {
                NumericStat numericStat = numericStatValues.next();
                if (numericStat.isDisplayable() && InList.isInList(numericStat.getDisplayGroups(), groupName)) {
                    NumericStatGroup numericStatGroup= new NumericStatGroup(skin, numericStat, false);
                    addActor(numericStatGroup);
                }
            }
        }
        if(booleanStats!=null) {
            ObjectMap<String, BooleanStat> booleanStatsMap = booleanStats.getBooleanStats();
            Values<BooleanStat> booleanStatValues = booleanStatsMap.values();
            while (booleanStatValues.hasNext()) {
                BooleanStat booleanStat = booleanStatValues.next();
                if (booleanStat.isDisplayable() && InList.isInList(booleanStat.getDisplayGroups(), groupName)) {
                    BooleanStatGroup booleanStatGroup= new BooleanStatGroup(skin, booleanStat);
                    addActor(booleanStatGroup);
                }
            }
        }
    }
    @Override
    public void act(float delta) {
        super.act(delta);

        
    }
}

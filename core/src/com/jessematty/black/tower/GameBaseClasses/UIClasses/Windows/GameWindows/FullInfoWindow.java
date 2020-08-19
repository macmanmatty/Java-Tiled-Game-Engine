package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.jessematty.black.tower.Components.ImageComponent;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.OwnerComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.StatBar;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindow;
import com.jessematty.black.tower.Maps.World;

public class FullInfoWindow extends GameWindow {
   private MapDraw mapDraw;
   private GameComponentMapper gameComponentMapper;
   private Entity entity;
    private ComponentMapper<Name> nameComponentMapper;
   private  ComponentMapper<ImageComponent> imageComponentMapper;
   private  ComponentMapper<OwnerComponent>  ownerComponentComponentMapper;
   private ComponentMapper<StringStats> stringStatsComponentMapper;
   private ComponentMapper<NumericStats> numericStatsComponentMapper;
   private ComponentMapper<BooleanStats>booleanStatsComponentMapper;
   

    public FullInfoWindow( MapDraw mapDraw, Entity entity) {
        this("Info", mapDraw.getCurrentMap().getSkin(), "default", mapDraw, entity);


    }

    public FullInfoWindow(String title, Skin skin, String styleName, MapDraw mapDraw, Entity entity) {
        super(title, skin, styleName);
        this.mapDraw = mapDraw;
        this.entity = entity;
        this.gameComponentMapper=mapDraw.getGameComponentMapper();
        nameComponentMapper=gameComponentMapper.getNameComponentMapper();
        imageComponentMapper=gameComponentMapper.getImageComponentMapper();
        ownerComponentComponentMapper=gameComponentMapper.getOwnerComponentComponentMapper();
        booleanStatsComponentMapper=gameComponentMapper.getBooleanStatsComponentMapper();
        numericStatsComponentMapper=gameComponentMapper.getNumericStatsComponentMapper();
        stringStatsComponentMapper=gameComponentMapper.getStringStatsComponentMapper();

        setRemoveOnClose(true);
    }

    private  void makeUI(){
        String name=nameComponentMapper.get(entity).getText();



        Skin skin=getSkin();



        Image image=imageComponentMapper.get(entity).getImage();
        HorizontalGroup nameAndImage=new HorizontalGroup();
        Label nameLabel= new Label(name, skin);
        nameAndImage.addActor(nameLabel);
        nameAndImage.addActor(image);
        nameAndImage.space(5);
      add(nameAndImage);
        World world=mapDraw.getWorld();

        double [] weightMassVolume= EntityUtilities.getMassVolumeWeight(world, entity);


        Label mass= new Label("Mass: "+weightMassVolume[0], skin);
        Label weight=new Label("Weight: "+ weightMassVolume[1], skin);
        Label volume=new Label("Volume: "+ weightMassVolume[2], skin);
        
        HorizontalGroup weightMassVolumeGroup=new HorizontalGroup();
        weightMassVolumeGroup.addActor(weight);
        weightMassVolumeGroup.addActor(mass);
        weightMassVolumeGroup.addActor(volume);
        


        double [] totalWeightMassVolume= EntityUtilities.getTotalMassWeightVolume(world, entity);

        VerticalGroup verticalGroup= new VerticalGroup();
        HorizontalGroup totalWeightMassVolumeGroup=new HorizontalGroup();

        Label total= new Label("Total Weight Including All Attached Items", skin);
        Label totalMass= new Label("  Mass: "+totalWeightMassVolume[0], skin);
        Label totalWeight=new Label(" Weight: "+ totalWeightMassVolume[1], skin);
        Label totalVolume=new Label(" volume: "+ totalWeightMassVolume[2], skin);
        totalWeightMassVolumeGroup.addActor(total);
        totalWeightMassVolumeGroup.addActor(totalWeight);
        totalWeightMassVolumeGroup.addActor(totalMass);
        totalWeightMassVolumeGroup.addActor(totalVolume);

        add(weightMassVolumeGroup);
        add(totalWeightMassVolumeGroup);
        StringStats stringStats=stringStatsComponentMapper.get(entity);
        if(stringStats!=null) {
            ObjectMap<String, StringStat> stringStatsMap = stringStats.getStringStats();
            Keys<String> stringKeys = stringStatsMap.keys();

            while (stringKeys.hasNext()) {
                String key = stringKeys.next();
                StringStat stringStat = stringStatsMap.get(key);
                if (stringStat.isDisplayable()) {
                    Label statLabel = new Label(stringStat.getName() + ": " + stringStat.getText(), skin);
                   add(statLabel);
                }

            }
        }
        
        NumericStats numericStats=numericStatsComponentMapper.get(entity);
        if(numericStats!=null) {
            ObjectMap<String, NumericStat> numericStatsMap = numericStats.getNumericStats();
            Keys<String> numericKeys = numericStatsMap.keys();

            while (numericKeys.hasNext()) {
                String key = numericKeys.next();
                NumericStat numericStat = numericStatsMap.get(key);
                HorizontalGroup numericStatGroup = new HorizontalGroup();
                if (numericStat.isDisplayable()) {
                    Label statLabel = new Label(numericStat.getName() + ": " + numericStat.getDoubleValue() + "/" + numericStat.getMaxValue(), skin);
                    numericStatGroup.addActor(statLabel);
                    StatBar statBar = numericStat.getStatBar();
                    if (statBar != null) {
                        numericStatGroup.addActor(statBar);

                    }
                    add(numericStatGroup);
                }

            }

        }

        BooleanStats booleanStats=booleanStatsComponentMapper.get(entity);
        if(booleanStats!=null) {
            ObjectMap<String, BooleanStat> booleanStatsMap = booleanStats.getBooleanStats();
            Keys<String> booleanKeys = booleanStatsMap.keys();

            while (booleanKeys.hasNext()) {
                String key = booleanKeys.next();
                BooleanStat booleanStat = booleanStatsMap.get(key);
                HorizontalGroup booleanStatGroup = new HorizontalGroup();
                if (booleanStat.isDisplayable()) {
                    Label statLabel = new Label(booleanStat.getName() + ": " +  String.valueOf(booleanStat.getFlag()) , skin);
                    booleanStatGroup.addActor(statLabel);

                  add(booleanStatGroup);
                }

            }

        }




        displayAttachedItems(skin,  verticalGroup, entity);



    }


    public void displayAttachedItems(  Skin skin, VerticalGroup parentAttachGroup ,  Entity entity) {



        ownerComponentComponentMapper = gameComponentMapper.getOwnerComponentComponentMapper();
        String name=nameComponentMapper.get(entity).getText();


        OwnerComponent ownerComponent = ownerComponentComponentMapper.get(entity);
        World world = mapDraw.getWorld();

        if (ownerComponent != null) {
            Label attached = new Label("Items Attached To: "+name , skin);
            parentAttachGroup.addActor(attached);

            Array<String> ownedEntityIds = ownerComponent.getOwnedEntityIDs();
            int size = ownedEntityIds.size;
            for (int count = 0; count < size; count++) {

                Entity ownedEntity = world.getEntity(ownedEntityIds.get(count));
                OwnerComponent ownedEntityOwnerComponent=ownerComponentComponentMapper.get(ownedEntity);
                HorizontalGroup nameAndImage=makeImageAndName( skin, entity);
                parentAttachGroup.addActor(nameAndImage);
                if(ownedEntityOwnerComponent!=null){

                    final VerticalGroup childAttachGroup= new VerticalGroup();

                    childAttachGroup.setVisible(false);
                    ImageButton visibleButton= new ImageButton(skin);
                    visibleButton.addListener(new ClickListener(){

                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                            childAttachGroup.setVisible(!childAttachGroup.isVisible());
                            return  true;
                        }
                    });
                    parentAttachGroup.addActor(childAttachGroup);
                    nameAndImage.addActor(visibleButton);

                    displayAttachedItems( skin, childAttachGroup, ownedEntity);
                }




            }

        }



    }


    public HorizontalGroup makeImageAndName( Skin skin, Entity entity){

        String name=nameComponentMapper.get(entity).getText();

        Image image = imageComponentMapper.get(entity).getImage();
        HorizontalGroup nameAndImage = new HorizontalGroup();
        Label nameLabel = new Label(name, skin);
        nameAndImage.addActor(nameLabel);
        nameAndImage.addActor(image);
        nameAndImage.space(5);

        return nameAndImage;
    }


}

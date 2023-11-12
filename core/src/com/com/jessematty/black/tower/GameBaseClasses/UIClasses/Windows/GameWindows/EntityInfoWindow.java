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
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups.BooleanStatGroup;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups.NumericStatGroup;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups.StringStatGroup;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.ClosableWindow;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.StatBar;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindow;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.Maps.World;
public class EntityInfoWindow extends ClosableWindow {
    private final GameAssets gameAssets;
    private Entity entity;
    private ComponentMapper<NameComponent> nameComponentMapper;
   private  ComponentMapper<ImageComponent> imageComponentMapper;
   private  ComponentMapper<OwnerComponent>  ownerComponentComponentMapper;
   private ComponentMapper<StringStats> stringStatsComponentMapper;
   private ComponentMapper<NumericStats> numericStatsComponentMapper;
   private ComponentMapper<BooleanStats>booleanStatsComponentMapper;
   private final String displayName="FullInfoWindow";
   

    public EntityInfoWindow(Skin skin, String styleName, Entity entity, GameAssets gameAssets) {
        super("Info", skin, styleName);
        this.entity = entity;
        this.gameAssets=gameAssets;
        nameComponentMapper=GameComponentMapper.getNameComponentMapper();
        imageComponentMapper=GameComponentMapper.getImageComponentMapper();
        ownerComponentComponentMapper=GameComponentMapper.getOwnerComponentComponentMapper();
        booleanStatsComponentMapper=GameComponentMapper.getBooleanStatsComponentMapper();
        numericStatsComponentMapper=GameComponentMapper.getNumericStatsComponentMapper();
        stringStatsComponentMapper=GameComponentMapper.getStringStatsComponentMapper();
        setRemoveOnClose(true);
        makeUI();

    }
    private  void makeUI(){
        String name=nameComponentMapper.get(entity).getStat();
        getTitleLabel().setText(name);
        Skin skin=getSkin();
        ImageComponent imageComponent=imageComponentMapper.get(entity);
        HorizontalGroup nameAndImage = new HorizontalGroup();
        Label nameLabel = new Label(name, skin);
        nameAndImage.addActor(nameLabel);
        if(imageComponent!=null ) {
            Image image=imageComponent.getImage();
            nameAndImage.addActor(image);
            nameAndImage.space(5);
        }
        add(nameAndImage);
        row();
        World world=gameAssets.getWorld();
        double [] weightMassVolume= EntityUtilities.getMassVolumeWeight(world, entity);
        Label mass= new Label("Mass: "+weightMassVolume[0], skin);
        Label weight=new Label("Weight: "+ weightMassVolume[1], skin);
        Label volume=new Label("Volume: "+ weightMassVolume[2], skin);
        HorizontalGroup weightMassVolumeGroup=new HorizontalGroup();
        weightMassVolumeGroup.addActor(weight);
        weightMassVolumeGroup.addActor(mass);
        weightMassVolumeGroup.addActor(volume);
        double [] totalWeightMassVolume= EntityUtilities.getEntityMassAndVolume(world, entity);
        HorizontalGroup totalWeightMassVolumeGroup=new HorizontalGroup();
        Label total= new Label("Total Weight Including All Attached Items", skin);
        Label totalMass= new Label("  Mass: "+totalWeightMassVolume[0], skin);
        Label totalVolume=new Label(" volume: "+ totalWeightMassVolume[1], skin);
        totalWeightMassVolumeGroup.addActor(total);
        totalWeightMassVolumeGroup.addActor(totalMass);
        totalWeightMassVolumeGroup.addActor(totalVolume);
        totalWeightMassVolumeGroup.space(10);
        weightMassVolumeGroup.space(10);
        add(weightMassVolumeGroup);
        row();
        add(totalWeightMassVolumeGroup);
        row();
        StringStats stringStats=stringStatsComponentMapper.get(entity);
        if(stringStats!=null) {
            ObjectMap<String, StringStat> stringStatsMap = stringStats.getStringStats();
            Keys<String> stringKeys = stringStatsMap.keys();
            while (stringKeys.hasNext()) {
                String key = stringKeys.next();
                StringStat stringStat = stringStatsMap.get(key);
                if (stringStat.isDisplayable()) {
                    StringStatGroup stringStatGroup= new StringStatGroup(stringStat, skin);
                   add(stringStatGroup);
                   row();
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
                if (numericStat.isDisplayable()) {
                    NumericStatGroup numericStatGroup= new NumericStatGroup(gameAssets, numericStat, skin);
                    add(numericStatGroup);
                    row();
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
                if (booleanStat.isDisplayable()) {
                    BooleanStatGroup booleanStatGroup= new BooleanStatGroup(booleanStat, skin);
                  add(booleanStatGroup);
                  row();
                }
            }
        }
        pack();

    }
    public void displayAttachedItems(  Skin skin, VerticalGroup parentAttachGroup ,  Entity entity) {
        ownerComponentComponentMapper = GameComponentMapper.getOwnerComponentComponentMapper();
        String name=nameComponentMapper.get(entity).getStat();
        OwnerComponent ownerComponent = ownerComponentComponentMapper.get(entity);
        World world = gameAssets.getWorld();
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
        String name=nameComponentMapper.get(entity).getStat();
        Image image = imageComponentMapper.get(entity).getImage();
        HorizontalGroup nameAndImage = new HorizontalGroup();
        Label nameLabel = new Label(name, skin);
        nameAndImage.addActor(nameLabel);
        nameAndImage.addActor(image);
        nameAndImage.space(5);
        return nameAndImage;
    }
}

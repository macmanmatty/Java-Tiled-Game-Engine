package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.FloatField;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.IntegerField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveFloatField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveIntegerField;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.TextureAtlasRegionNames;

public class ThingEdit  extends ObjectEdit {
  protected   TextField damage;
   protected TextField maxDamage;
    protected TextField  name;
    protected TextField  displayName;

    protected AtlasRegion region;
    protected TextButton setAtlasRegion;
    protected TextButton setFrozenAtlasRegion;

    protected AtlasRegion [] animationRegions;
    protected TextField  animationFrames;
   protected TextField  xOffset; // the x offset for placing the object on the tile
    protected TextField  yOffset; // the x offset for placing the object on the tile




    protected TextField  infoUnidentified;
    protected TextField  infoIndentified;
    protected TextField  height;
    protected SelectBox<TextureAtlasRegionNames> atlasNames; // select box for texture atlases used

    
    protected SelectBox<String> groups;
    protected SelectBox<String> thingsGroups;
    VerticalGroup groupsGroup;
    VerticalGroup itemActionsGroup;
    protected  TextButton addDistanceAction;
    protected TextButton removeDistanceAction;


    protected CheckBox  invisible;       // is the thing invisble

    protected  Label glowLabel;

    protected PositiveFloatField immunityStrength;


    protected TextField  speed; // things  velocity




    protected TextField  health;

    protected TextField  maxHealth;


    protected TextField  contactOptionsMethod;
    protected CheckBox  glows;
    protected CheckBox drawThing;
    protected SelectBox<Color> glowColor;
    protected TextField boundsOffsetX; // the offset for the bounds allowing the fighterGroup to floatThing closer  or father away from the object as bounds are based texture weaponWidth and height;
    protected TextField boundsOffsetY;





    protected int locationx; // location x of thing on land square tile
    protected int locationy; // location y of thing land square tile
    protected double denisty;
    protected double weightNumber;
    protected  double volumeNumber;
    protected double massNumber;
    protected double burnRateNumber;

    
    protected CheckBox  indentified; // is the thing identified
    protected CheckBox  animated;
    protected CheckBox  item;
    protected CheckBox  fighter;
    protected TextButton addProblem;
    protected TextButton addNegatedProblem;
    protected TextButton addImmunity;
    protected TextButton removeProblem;
    protected TextButton removeNegatedProblem;
    protected TextButton addGroup;
    protected TextButton removeGroup;
    protected FloatField xBounds;
    protected FloatField yBounds;


    protected TextButton removeImmunity;
    protected TextButton removeMaterial;
    protected TextButton addMaterial;



   protected SelectBox<Color> textureColor;
    protected TextField  zIndex;
    protected TextField  heightFromGround;


    protected VerticalGroup thingGroup;
    protected VerticalGroup problemsGroup;
    protected VerticalGroup materialsGroup;
    protected VerticalGroup thingsStats;
    protected Label densityLabel;
    protected Label weightLabel;
    protected Label volumeLabel;
    protected Label massLabel;
    protected Label burnTempLabel;
    protected Label burnRateLabel;
    protected Label freezeTempLabel;
    protected float burnTempNumber;
    protected float freezeTempNumber;
    protected float earthMagicNumber;
    protected float fireMagicNumber;
    protected float waterMagicNumber;
    protected float windMagicNumber;
    protected float darkMagicNumber;
    protected float lightMagicNumber;
    protected float phNumber;
    protected float conductivityNumber;
    protected float toxicityNumber;
    
            
    protected Label earthMagicLabel;
    protected Label windMagicLabel;
    protected Label waterMagicLabel;
    protected Label fireMagicLabel;
    protected Label darkMagicLabel;
    protected Label lightMagicLabel;
    protected Label toxicityLabel;
    protected Label phLabel;
    protected Label conductivityLabel;
    











    protected  TextButton makeThing;


    protected CheckBox showThingGroup;
    protected CheckBox showProblemsGroup;
    protected CheckBox showMaterialsGroup;
    protected CheckBox showGroupsGroup;
    protected CheckBox showThingsStats;
    protected CheckBox showItemActionsGRoup;


    public ThingEdit() {
    }

    public ThingEdit(GameAssets asetts, final Skin  skin) {
        super(skin, asetts);


        this.assetts = asetts;
        this.skin= skin;


        immunityStrength= new PositiveFloatField(" ", skin);
        setAtlasRegion= new TextButton("Set Texture Region", skin);
        setFrozenAtlasRegion= new TextButton("Set Frozen Texture", skin);
        setFrozenAtlasRegion.addListener( new ClickListener(){


            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

            }
        });

        setAtlasRegion.addListener( new ClickListener(){


            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });

        showItemActionsGRoup=new CheckBox("Show Actions", skin);

        showItemActionsGRoup.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    itemActionsGroup.setVisible(true);
                }

                else {
                    itemActionsGroup.setVisible(false);

                }

            }
        });
        showThingGroup=new CheckBox("Show Thing Fields", skin);

        showThingGroup.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    thingGroup.setVisible(true);
                }

                else {
                    thingGroup.setVisible(false);

                }

            }
        });

        showThingsStats=new CheckBox("Show Stats", skin);

        showThingsStats.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    thingsStats.setVisible(true);
                }

                else {
                   thingsStats.setVisible(false);

                }

            }
        });
        showGroupsGroup=new CheckBox("Groups", skin);

        showGroupsGroup.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    groupsGroup.setVisible(true);
                }

                else {
                    groupsGroup.setVisible(false);

                }

            }
        });


        showMaterialsGroup=new CheckBox("Show  Materials", skin);

        showMaterialsGroup.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    materialsGroup.setVisible(true);
                }

                else {
                    materialsGroup.setVisible(false);

                }

            }
        });
        showProblemsGroup=new CheckBox("Show Problems", skin);

        showProblemsGroup.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    problemsGroup.setVisible(true);
                }

                else {
                    problemsGroup.setVisible(false);

                }

            }
        });

     



        Label materialAddlabel= new Label("Materials To Add", skin);
        Label materialRemovelabel= new Label("Materials To Remove", skin);
        materialsGroup= new VerticalGroup();

        materialsGroup.addActor(materialAddlabel);
        materialsGroup.addActor(addMaterial);
        materialsGroup.addActor(materialRemovelabel);
        materialsGroup.addActor(removeMaterial);


        groupsGroup= new VerticalGroup();
        Label groupsToAdd= new Label("Groups to Add", skin);
        Label groupsThingIn= new Label("Groups Belongs To", skin);

        thingsGroups= new SelectBox<String>(skin);







        groupsGroup.addActor(groupsToAdd);
        groupsGroup.addActor(groups);
        groupsGroup.addActor(addGroup);
        groupsGroup.addActor(groupsThingIn);
        groupsGroup.addActor(thingsGroups);
        groupsGroup.addActor(removeGroup);




        VerticalGroup atlasNameGroup= new VerticalGroup();
        Label label44 =  new Label("Atlas Name", skin);
        atlasNameGroup.addActor(label44);
        atlasNameGroup.addActor(atlasNames);






        Label label= new Label("Damage Value", skin);

         damage = new IntegerField("The Things Damage",  skin);
         HorizontalGroup damageGroup= new HorizontalGroup();
         damageGroup.addActor(label);
         damageGroup.addActor(damage);



        maxDamage=new IntegerField("The Things Max Damage" , skin);
        Label label2= new Label(" Max Damage Value", skin);
        HorizontalGroup maxDamageGroup= new HorizontalGroup();

        maxDamageGroup.addActor(label2);
        maxDamageGroup.addActor(maxDamage);



        Label label3= new Label(" Atlas Name", skin);

        name=new TextField("Item / Thing  Name" , skin);
        name.setText("The Things Name");
        HorizontalGroup nameGroup= new HorizontalGroup();
       nameGroup.addActor(label3);
        nameGroup.addActor(name);

        Label label111= new Label(" Name", skin);

        displayName=new TextField("" , skin);
        name.setText("The Things Name");
        HorizontalGroup displayNameGroup= new HorizontalGroup();
        displayNameGroup.addActor(label111);
        displayNameGroup.addActor(displayName);

        Label label4= new Label("Unidentified Info", skin);
         infoUnidentified =new TextField("", skin);





        HorizontalGroup infoUnidentifiedGroup= new HorizontalGroup();
        infoUnidentifiedGroup.addActor(label4);
       infoUnidentifiedGroup.addActor(infoUnidentified);

        Label label5= new Label("Identified Info", skin);

        infoIndentified=new TextField("", skin);
        HorizontalGroup infoIdentifiedGroup= new HorizontalGroup();

       infoIdentifiedGroup.addActor(label5);
        infoIdentifiedGroup.addActor(infoIndentified);
        Label label6= new Label("The x Axis Offset", skin);

        xOffset=new IntegerField("", skin);


        HorizontalGroup xOffsetdGroup= new HorizontalGroup();
        xOffsetdGroup.addActor(label5);
        xOffsetdGroup.addActor(xOffset);

        Label label7= new Label("The y Axis Offset", skin);

        yOffset=new IntegerField("", skin);
        HorizontalGroup yOffsetdGroup= new HorizontalGroup();


       yOffsetdGroup.addActor(label7);
       yOffsetdGroup.addActor(yOffset);
        Label label8= new Label("The x Axis Offset Bounds", skin);

       boundsOffsetX=new IntegerField("" , skin);


        HorizontalGroup boundsOffsetXGroup= new HorizontalGroup();

       boundsOffsetXGroup.addActor(label8);
        boundsOffsetXGroup.addActor(boundsOffsetX);
        Label label9= new Label("The y Axis Offset Bounds", skin);

        boundsOffsetY=new IntegerField("", skin);






        HorizontalGroup boundsOffsetYGroup= new HorizontalGroup();

       boundsOffsetYGroup.addActor(label9);
        boundsOffsetYGroup.addActor(boundsOffsetY);



        Label label20= new Label("The X axis bounds", skin);

        xBounds=new FloatField("" , skin);


        HorizontalGroup boundsXGroup= new HorizontalGroup();

        boundsXGroup.addActor(label20);
        boundsXGroup.addActor(xBounds);
        Label label21= new Label("The Y axis bounds ", skin);

        yBounds=new FloatField("", skin);






        HorizontalGroup boundsYGroup= new HorizontalGroup();

        boundsOffsetYGroup.addActor(label21);
        boundsOffsetYGroup.addActor(yBounds);









        Label label10= new Label("Height", skin);

        height=new PositiveIntegerField("Thing Height" , skin);
        HorizontalGroup heightGroup= new HorizontalGroup();

        heightGroup.addActor(label10);
        heightGroup.addActor(height);



        item= new CheckBox("is the Thing WoodWand Item", skin);
        fighter= new CheckBox("is the Thing WoodWand Fighter", skin);

        invisible= new CheckBox("is the Thing Invisible ", skin);





        Label label14= new Label("Current Speed", skin);

        speed=new PositiveFloatField("", skin);

        HorizontalGroup speedGroup= new HorizontalGroup();


        speedGroup.addActor(label14);
        speedGroup.addActor(speed);




        Label label16= new Label("Health", skin);

         health=new PositiveIntegerField("" , skin);

        HorizontalGroup healthGroup= new HorizontalGroup();

        healthGroup.addActor(label16);
        healthGroup.addActor(health);
        Label label17= new Label("Max Health", skin);

        maxHealth=new PositiveIntegerField("", skin);

        HorizontalGroup maxHealthGroup= new HorizontalGroup();

        maxHealthGroup.addActor(label17);
        maxHealthGroup.addActor(maxHealth);



        glows=new CheckBox("Does the thing glow", skin);

        glows.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    glowColor.setVisible(true);
                    glowLabel.setVisible(true);

                }

                else {
                    glowColor.setVisible(false);
                    glowLabel.setVisible(false);
                }

            }
        });




        ;




       indentified=new CheckBox("is the Thing Identified", skin);

        HorizontalGroup zIndexGroup= new HorizontalGroup();
        Label label22= new Label("Z-index", skin);

        zIndex=new PositiveIntegerField("" , skin);
        zIndexGroup.addActor(label22);
        zIndexGroup.addActor(zIndex);


        animated=new CheckBox("is the Thing Animated", skin);




        Label label23= new Label("Height From Ground", skin);

        heightFromGround=new PositiveIntegerField("" , skin);
        HorizontalGroup heightFromGroundGroup= new HorizontalGroup();


        heightFromGroundGroup.addActor(label23);
        heightFromGroundGroup.addActor(heightFromGround);

       glowLabel= new Label("Select The Glow Color", skin);
       Label textureColorLabel= new Label("The Textures Color", skin);


        Label problemsLabel= new Label(" Problems To Add", skin);
        Label imunitesLabel= new Label(" Immunity Strength", skin);
        Label problemsLabel2= new Label(" Problems Added", skin);
        Label problemsLabel3= new Label("  Negated Problems Added", skin);

        Label imunitesLabel2= new Label(" Immunites Added", skin);
        HorizontalGroup addProblemsButtonsGRoup=  new HorizontalGroup();
        addProblemsButtonsGRoup.addActor(addProblem);
        addProblemsButtonsGRoup.addActor(addNegatedProblem);

        problemsGroup= new VerticalGroup();
        problemsGroup.addActor(problemsLabel);
        problemsGroup.addActor(addProblemsButtonsGRoup);
        problemsGroup.addActor(problemsLabel2);
        problemsGroup.addActor(removeProblem);
        problemsGroup.addActor(problemsLabel3);
        problemsGroup.addActor(removeNegatedProblem);


        HorizontalGroup immunityStrengthGroup= new HorizontalGroup();

        immunityStrengthGroup.addActor(imunitesLabel);
       immunityStrengthGroup.addActor(immunityStrength);
       problemsGroup.addActor(immunityStrengthGroup);
       problemsGroup.addActor(addImmunity);
        problemsGroup.addActor(imunitesLabel2);
        problemsGroup.addActor(removeImmunity);



        densityLabel = new Label("Things Density: " + denisty, skin);
        weightLabel = new Label("Things Weight; " + weightNumber, skin);
        volumeLabel = new Label("Things Weight: " + volumeNumber, skin);
        massLabel = new Label("Things Weight: " + volumeNumber / 9.8, skin);
        burnTempLabel = new Label("Burn Temp: " + burnTempNumber, skin);
        freezeTempLabel = new Label("Freeze Temp: " + freezeTempNumber, skin);
        burnRateLabel = new Label(" Burn Rate: " + burnRateNumber, skin);
        earthMagicLabel = new Label(" earth magic " + earthMagicNumber, skin);
        fireMagicLabel = new Label(" fire magic " + fireMagicNumber, skin);
        windMagicLabel = new Label(" wind magic: " + windMagicNumber, skin);
        waterMagicLabel = new Label("water magicg " + waterMagicNumber, skin);
        darkMagicLabel = new Label("dark magic " + darkMagicNumber, skin);
        lightMagicLabel = new Label("light magic " + lightMagicNumber, skin);
        phLabel = new Label(" PH " + phNumber, skin);
        conductivityLabel = new Label("Conductivity " + conductivityNumber, skin);
        toxicityLabel = new Label("Toxicity" + toxicityNumber, skin);






        thingsStats= new VerticalGroup();

        thingsStats.addActor(weightLabel);
        thingsStats.addActor(massLabel);
        thingsStats.addActor(volumeLabel);
        thingsStats.addActor(densityLabel);
        thingsStats.addActor(phLabel);
        thingsStats.addActor(conductivityLabel);
        thingsStats.addActor(toxicityLabel);
        thingsStats.addActor(earthMagicLabel);
        thingsStats.addActor(windMagicLabel);
        thingsStats.addActor(waterMagicLabel);
        thingsStats.addActor(fireMagicLabel);
        thingsStats.addActor(darkMagicLabel);
        thingsStats.addActor(lightMagicLabel);


        drawThing= new CheckBox("is the Thing Drawn on the Screen", skin);



        thingGroup.addActor(nameGroup);
        thingGroup.addActor(atlasNameGroup);
        thingGroup.addActor(displayNameGroup);

        thingGroup.addActor(infoUnidentifiedGroup);

        thingGroup.addActor(infoIdentifiedGroup);


        thingGroup.addActor(boundsOffsetXGroup);

        thingGroup.addActor(boundsOffsetYGroup);

        thingGroup.addActor(boundsXGroup);

        thingGroup.addActor(boundsYGroup);


        thingGroup.addActor(xOffsetdGroup);

        thingGroup.addActor(yOffsetdGroup);


        thingGroup.addActor(zIndexGroup);

        thingGroup.addActor(heightGroup);

        thingGroup.addActor(heightFromGroundGroup);


        thingGroup.addActor(maxDamageGroup);
        thingGroup.addActor(maxDamageGroup);

        thingGroup.addActor(healthGroup);
        thingGroup.addActor(textureColorLabel);
        thingGroup.addActor(textureColor);

        thingGroup.addActor(glows);
        thingGroup.addActor(glowLabel);
        thingGroup.addActor(glowColor);
        
      
        


        thingGroup.addActor(invisible);
        thingGroup.addActor(indentified);
        thingGroup.addActor(drawThing);
        thingGroup.space(0);
        thingGroup.columnAlign(Align.left);








        window.addActor(showThingGroup);

        window.row();
        window.add(showThingGroup);
        window.row();
        window.add(thingGroup);
        thingGroup.columnAlign(Align.left);

        window.row();

        window.add(showGroupsGroup);

        window.row();

        window.add(groupsGroup);
        groupsGroup.columnAlign(Align.left);
        window.row();

        window.add(showProblemsGroup);
        window.row();
        problemsGroup.columnAlign(Align.left);

        window.add(problemsGroup);

        window.row();

        window.add(showMaterialsGroup);
        window.row();

        window.add(materialsGroup);
        materialsGroup.columnAlign(Align.left);

        window.row();

        window.add(showThingsStats);
        window.row();

        window.add(thingsStats);
        thingsStats.columnAlign(Align.left);

        window.row();

        window.add(makeThing);
        window.row();






    }



    public void setAnimationFrames() {
        int animationFrames=Integer.valueOf(this.animationFrames.getMessageText());
        this.animationRegions= new AtlasRegion[animationFrames];


    }
    public void addAnimationFrame(AtlasRegion region, int frameNumber){
       this.animationRegions[frameNumber]=region;






    }



    public AtlasRegion getRegion() {
        return region;
    }

    public void setRegion(AtlasRegion region) {
        this.region = region;
    }


    public void updateInfo(){

    }





}

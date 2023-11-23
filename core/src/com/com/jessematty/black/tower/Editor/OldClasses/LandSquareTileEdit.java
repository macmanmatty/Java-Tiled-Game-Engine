package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.Season;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveFloatField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveIntegerField;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class LandSquareTileEdit extends ObjectEdit {
   protected  VerticalGroup vGroup = new VerticalGroup();


    protected TextField locationx; //tile x location
    protected TextField locationy; // tile y location

    protected TextField sand;
    protected TextField silt;
    protected TextField clay;
    protected TextField peat;
    protected TextField chalk;
    protected TextField loam;

    protected TextField boundsOffsetX;
    protected TextField  boundsOffsetY;

    protected TextField temperature; // square temp
    protected TextField rainFallTotal;

    protected TextField cofficentOfFriction;
    protected String name="";
    protected SelectBox<TiledMapTile> tiles;


    protected TextField elevation; // square elevation
    protected TextField mapNumber; // mapnumber the sdquare is on.


    protected SelectBox<Season> seasons;



    protected TextField rainChance;  // the chance number for rain

    protected TextField maxRainFallChance;
    protected  TextField minRainFallChance;

    protected TextField winterMinTempChange;
    protected TextField winterMaxTempChange;
    protected TextField fallMinTempChange;
    protected TextField fallMaxTempChange;
    protected TextField springMinTempChange;
    protected TextField springMaxTempChange;
    protected TextField summerMinTempChange;
    protected TextField summerMaxTempChange;
    protected TextField winterMinRainChange;
    protected TextField winterMaxRainChange;
    protected TextField fallMinRainChange;
    protected TextField fallMaxRainChange;
    protected TextField springMinRainChange;
    protected TextField springrMaxRainChange;
    protected TextField summerMinRainChange;
    protected TextField summerMaxRainChange;

   protected TextField nitrogen;
    protected  TextField phosperphous;
    protected  TextField potassium;
    protected TextField iron;
    protected TextField calcium;
    protected  TextField sulfur;
    protected   TextField managese;

    protected CheckBox cityLimit; //is the tile part of atown;
    protected CheckBox enterable;
    protected CheckBox wet;
    protected CheckBox solid;









    
    protected Label sandLabel;
    protected Label siltLabel;
    protected Label clayLabel;
    protected Label peatLabel;
    protected Label chalkLabel;
    protected Label loamLabel;

    protected Label boundsOffsetXLabel;
    protected Label  boundsOffsetYLabel;

    protected Label temperatureLabel; // square temp
    protected Label rainFallTotalLabel;

    protected Label cofficentOfFrictionLabel;
   


    protected Label elevationLabel; // square elevation




    protected Label rainChanceLabel;  // the chance number for rain

    protected Label maxRainFallChanceLabel;
    protected  Label minRainFallChanceLabel;

    protected Label winterMinTempChangeLabel;
    protected Label winterMaxTempChangeLabel;
    protected Label fallMinTempChangeLabel;
    protected Label fallMaxTempChangeLabel;
    protected Label springMinTempChangeLabel;
    protected Label springMaxTempChangeLabel;
    protected Label summerMinTempChangeLabel;
    protected Label summerMaxTempChangeLabel;
    protected Label winterMinRainChangeLabel;
    protected Label winterMaxRainChangeLabel;
    protected Label fallMinRainChangeLabel;
    protected Label fallMaxRainChangeLabel;
    protected Label springMinRainChangeLabel;
    protected Label springrMaxRainChangeLabel;
    protected Label summerMinRainChangeLabel;
    protected Label summerMaxRainChangeLabel;

    protected Label nitrogenLabel;
    protected  Label phosperphousLabel;
    protected  Label potassiumLabel;
    protected Label ironLabel;
    protected Label calciumLabel;
    protected  Label sulfurLabel;
    protected   Label manageseLabel;




    protected HorizontalGroup sandHorizontalGroup;
    protected HorizontalGroup siltHorizontalGroup;
    protected HorizontalGroup clayHorizontalGroup;
    protected HorizontalGroup peatHorizontalGroup;
    protected HorizontalGroup chalkHorizontalGroup;
    protected HorizontalGroup loamHorizontalGroup;

    protected HorizontalGroup boundsOffsetXHorizontalGroup;
    protected HorizontalGroup  boundsOffsetYHorizontalGroup;

    protected HorizontalGroup temperatureHorizontalGroup; // square temp
    protected HorizontalGroup rainFallTotalHorizontalGroup;

    protected HorizontalGroup cofficentOfFrictionHorizontalGroup;



    protected HorizontalGroup elevationHorizontalGroup; // square elevation




    protected HorizontalGroup rainChanceHorizontalGroup;  // the chance number for rain

    protected HorizontalGroup maxRainFallChanceHorizontalGroup;
    protected  HorizontalGroup minRainFallChanceHorizontalGroup;

    protected HorizontalGroup winterMinTempChangeHorizontalGroup;
    protected HorizontalGroup winterMaxTempChangeHorizontalGroup;
    protected HorizontalGroup fallMinTempChangeHorizontalGroup;
    protected HorizontalGroup fallMaxTempChangeHorizontalGroup;
    protected HorizontalGroup springMinTempChangeHorizontalGroup;
    protected HorizontalGroup springMaxTempChangeHorizontalGroup;
    protected HorizontalGroup summerMinTempChangeHorizontalGroup;
    protected HorizontalGroup summerMaxTempChangeHorizontalGroup;
    protected HorizontalGroup winterMinRainChangeHorizontalGroup;
    protected HorizontalGroup winterMaxRainChangeHorizontalGroup;
    protected HorizontalGroup fallMinRainChangeHorizontalGroup;
    protected HorizontalGroup fallMaxRainChangeHorizontalGroup;
    protected HorizontalGroup springMinRainChangeHorizontalGroup;
    protected HorizontalGroup springrMaxRainChangeHorizontalGroup;
    protected HorizontalGroup summerMinRainChangeHorizontalGroup;
    protected HorizontalGroup summerMaxRainChangeHorizontalGroup;

    protected HorizontalGroup nitrogenHorizontalGroup;
    protected  HorizontalGroup phosperphousHorizontalGroup;
    protected  HorizontalGroup potassiumHorizontalGroup;
    protected HorizontalGroup ironHorizontalGroup;
    protected HorizontalGroup calciumHorizontalGroup;
    protected  HorizontalGroup sulfurHorizontalGroup;
    protected   HorizontalGroup manageseHorizontalGroup;


    protected GameMap map;
protected  LandSquareTile tile;


    public LandSquareTileEdit(GameAssets assetts, Skin skin) {
        super(skin, assetts);


        boundsOffsetX=new PositiveIntegerField("The x Axis Offset of The Things Bounds ", skin);
        boundsOffsetY=new PositiveIntegerField("The y Axis Offset of The Things Bounds ", skin);



        sand= new PositiveIntegerField("Amount Of Sand", skin);

        silt= new PositiveIntegerField("Amount Of Silt", skin);

        clay= new PositiveIntegerField("Amount Of Clay", skin);
        peat= new PositiveIntegerField("Amount of Peat", skin);
        chalk= new PositiveIntegerField("Amount of Chalk", skin);

        loam= new PositiveIntegerField("Amount Of Loam" , skin);

        cofficentOfFriction =new PositiveFloatField("Cofficent of friction value from 0 to 1  0=fullstop 1 = endless sliding", skin);


        temperature = new PositiveFloatField("Starting Tile Temperature Freezing<32", skin);

        rainFallTotal= new PositiveIntegerField("Total Tile NonMoveableGas Fall", skin);




        elevation= new PositiveIntegerField("Tile elevation", skin);





        rainChance= new PositiveIntegerField("NonMoveableGas Chance for Tile", skin);

        maxRainFallChance= new PositiveIntegerField("Maximum NonMoveableGas Fall Chance ", skin);

        minRainFallChance= new PositiveIntegerField("Minimum NonMoveableGas Fall Chance", skin);

        winterMinTempChange=new PositiveIntegerField("Minimum Winter Temp Change", skin);

        winterMaxTempChange=new PositiveIntegerField("Maximum Winter Temp Change", skin);

        fallMinTempChange=new PositiveIntegerField("Minimum Fall Temp Change", skin);

        fallMaxTempChange=new PositiveIntegerField("Maximum Fall Temp Change", skin);

        springMinTempChange=new PositiveIntegerField("Minimum Spring Temp Change", skin);

        springMaxTempChange=new PositiveIntegerField("Maximum Spring Temp Change", skin);

        summerMinTempChange=new PositiveIntegerField("Minimum Summer Temp Change", skin);

        summerMaxTempChange=new PositiveIntegerField("Maximum Summer Temp Change", skin);

        winterMinRainChange=new PositiveIntegerField("Minimum Winter NonMoveableGas Fall", skin);

        winterMaxRainChange=new PositiveIntegerField("Maximum Winter NonMoveableGas Fall", skin);

        fallMinRainChange=new PositiveIntegerField("Minimum Fall NonMoveableGas Fall", skin);

        fallMaxRainChange=new PositiveIntegerField("Maximum Fall NonMoveableGas Fall", skin);

        springMinRainChange=new PositiveIntegerField("Minimum Spring NonMoveableGas Fall", skin);

        springrMaxRainChange=new PositiveIntegerField("Maximum Spring NonMoveableGas Fall", skin);

        summerMinRainChange=new PositiveIntegerField("Minimum Summer NonMoveableGas Fall", skin);

        summerMaxRainChange=new PositiveIntegerField("Maximum Summer NonMoveableGas Fall", skin);

        nitrogen=new PositiveIntegerField(" Amount Of Nitrogen In Soil", skin);

        phosperphous=new PositiveIntegerField(" Amount Of Phospherous In Soil", skin);

        potassium=new PositiveIntegerField(" Amount Of Potassium In Soil", skin);

        iron=new PositiveIntegerField(" Amount Of Iron In Soil", skin);

        calcium=new PositiveIntegerField(" Amount Of Calcium In Soil", skin);

        sulfur=new PositiveIntegerField(" Amount Of Sulfur In Soil", skin);

        managese=new PositiveIntegerField(" Amount Of Managese In Soil", skin);


        boundsOffsetXLabel=new Label("The x Axis Offset of The Things Bounds ", skin);
        boundsOffsetYLabel=new Label("The y Axis Offset of The Things Bounds ", skin);



        sandLabel= new Label("Amount Of Sand", skin);

        siltLabel= new Label("Amount Of Silt", skin);

        clayLabel= new Label("Amount Of Clay", skin);

        peatLabel= new Label("Amount of Peat", skin);
        chalkLabel= new Label("Amount of Chalk", skin);

        loamLabel= new Label("Amount Of Loam" , skin);

        cofficentOfFrictionLabel=new Label("Cofficent of friction value from 0 to 1  0Label=fullstop 1 Label= endless sliding", skin);


        temperatureLabel= new Label("Starting Tile Temperature Freezing<32", skin);

        rainFallTotalLabel= new Label("Total Tile NonMoveableGas Fall", skin);




        elevationLabel= new Label("Tile elevation", skin);





        rainChanceLabel= new Label("NonMoveableGas Chance for Tile", skin);

        maxRainFallChanceLabel= new Label("Maximum NonMoveableGas Fall Chance ", skin);

        minRainFallChanceLabel= new Label("Minimum NonMoveableGas Fall Chance", skin);

        winterMinTempChangeLabel=new Label("Minimum Winter Temp Change", skin);

        winterMaxTempChangeLabel=new Label("Maximum Winter Temp Change", skin);

        fallMinTempChangeLabel=new Label("Minimum Fall Temp Change", skin);

        fallMaxTempChangeLabel=new Label("Maximum Fall Temp Change", skin);

        springMinTempChangeLabel=new Label("Minimum Spring Temp Change", skin);

        springMaxTempChangeLabel=new Label("Maximum Spring Temp Change", skin);

        summerMinTempChangeLabel=new Label("Minimum Summer Temp Change", skin);

        summerMaxTempChangeLabel=new Label("Maximum Summer Temp Change", skin);

        winterMinRainChangeLabel=new Label("Minimum Winter NonMoveableGas Fall", skin);

        winterMaxRainChangeLabel=new Label("Maximum Winter NonMoveableGas Fall", skin);

        fallMinRainChangeLabel=new Label("Minimum Fall NonMoveableGas Fall", skin);

        fallMaxRainChangeLabel=new Label("Maximum Fall NonMoveableGas Fall", skin);

        springMinRainChangeLabel=new Label("Minimum Spring NonMoveableGas Fall", skin);

        springrMaxRainChangeLabel=new Label("Maximum Spring NonMoveableGas Fall", skin);

        summerMinRainChangeLabel=new Label("Minimum Summer NonMoveableGas Fall", skin);

        summerMaxRainChangeLabel=new Label("Maximum Summer NonMoveableGas Fall", skin);

        nitrogenLabel=new Label(" Amount Of Nitrogen In Soil", skin);

        phosperphousLabel=new Label(" Amount Of Phospherous In Soil", skin);

        potassiumLabel=new Label(" Amount Of Potassium In Soil", skin);

        ironLabel=new Label(" Amount Of Iron In Soil", skin);

        calciumLabel=new Label(" Amount Of Calcium In Soil", skin);

        sulfurLabel=new Label(" Amount Of Sulfur In Soil", skin);

        manageseLabel=new Label(" Amount Of Managese In Soil", skin);






        sandHorizontalGroup= new HorizontalGroup();
        sandHorizontalGroup.addActor(sandLabel);
        sandHorizontalGroup.addActor(sand);



        siltHorizontalGroup= new HorizontalGroup();
        siltHorizontalGroup.addActor(siltLabel);
        siltHorizontalGroup.addActor(silt);


        clayHorizontalGroup= new HorizontalGroup();
        clayHorizontalGroup.addActor(clayLabel);
        clayHorizontalGroup.addActor(clay);


        peatHorizontalGroup= new HorizontalGroup();
        peatHorizontalGroup.addActor(peatLabel);
        peatHorizontalGroup.addActor(peat);


        chalkHorizontalGroup= new HorizontalGroup();
        chalkHorizontalGroup.addActor(chalkLabel);
        chalkHorizontalGroup.addActor(chalk);


        loamHorizontalGroup= new HorizontalGroup();
        loamHorizontalGroup.addActor(loamLabel);
        loamHorizontalGroup.addActor(loam);


        boundsOffsetXHorizontalGroup= new HorizontalGroup();
        boundsOffsetXHorizontalGroup.addActor(boundsOffsetXLabel);
        boundsOffsetXHorizontalGroup.addActor(boundsOffsetX);


        boundsOffsetYHorizontalGroup= new HorizontalGroup();
        boundsOffsetYHorizontalGroup.addActor(boundsOffsetYLabel);
        boundsOffsetYHorizontalGroup.addActor(boundsOffsetY);



        temperatureHorizontalGroup= new HorizontalGroup(); // square temp
        temperatureHorizontalGroup.addActor(temperatureLabel);
        temperatureHorizontalGroup.addActor(temperature);


        rainFallTotalHorizontalGroup= new HorizontalGroup();
        rainFallTotalHorizontalGroup.addActor(rainFallTotalLabel);
        rainFallTotalHorizontalGroup.addActor(rainFallTotal);


        cofficentOfFrictionHorizontalGroup= new HorizontalGroup();
        cofficentOfFrictionHorizontalGroup.addActor(cofficentOfFrictionLabel);
        cofficentOfFrictionHorizontalGroup.addActor(cofficentOfFriction);




        elevationHorizontalGroup= new HorizontalGroup(); // square elevation

        elevationHorizontalGroup.addActor(elevationLabel);
        elevationHorizontalGroup.addActor(elevation);




        rainChanceHorizontalGroup= new HorizontalGroup();  // the chance number for rain
        rainChanceHorizontalGroup.addActor(rainChanceLabel);
        rainChanceHorizontalGroup.addActor(rainChance);


        maxRainFallChanceHorizontalGroup= new HorizontalGroup();
        maxRainFallChanceHorizontalGroup.addActor(maxRainFallChanceLabel);
        maxRainFallChanceHorizontalGroup.addActor(maxRainFallChance);


        minRainFallChanceHorizontalGroup= new HorizontalGroup();
        minRainFallChanceHorizontalGroup.addActor(minRainFallChanceLabel);
        minRainFallChanceHorizontalGroup.addActor(minRainFallChance);


        winterMinTempChangeHorizontalGroup= new HorizontalGroup();
        winterMinTempChangeHorizontalGroup.addActor(winterMinTempChangeLabel);
        winterMinTempChangeHorizontalGroup.addActor(winterMinTempChange);


        winterMaxTempChangeHorizontalGroup= new HorizontalGroup();
        winterMaxTempChangeHorizontalGroup.addActor(winterMaxTempChangeLabel);
        winterMaxTempChangeHorizontalGroup.addActor(winterMaxTempChange);


        fallMinTempChangeHorizontalGroup= new HorizontalGroup();
        fallMinTempChangeHorizontalGroup.addActor(fallMinTempChangeLabel);
        fallMinTempChangeHorizontalGroup.addActor(fallMinTempChange);


        fallMaxTempChangeHorizontalGroup= new HorizontalGroup();
        fallMaxTempChangeHorizontalGroup.addActor(fallMaxTempChangeLabel);
        fallMaxTempChangeHorizontalGroup.addActor(fallMaxTempChange);


        springMinTempChangeHorizontalGroup= new HorizontalGroup();
        springMinTempChangeHorizontalGroup.addActor(springMinTempChangeLabel);
        springMinTempChangeHorizontalGroup.addActor(springMinTempChange);


        springMaxTempChangeHorizontalGroup= new HorizontalGroup();
        springMaxTempChangeHorizontalGroup.addActor(springMaxTempChangeLabel);
        springMaxTempChangeHorizontalGroup.addActor(springMaxTempChange);


        summerMinTempChangeHorizontalGroup= new HorizontalGroup();
        summerMinTempChangeHorizontalGroup.addActor(summerMinTempChangeLabel);
        summerMinTempChangeHorizontalGroup.addActor(summerMinTempChange);


        summerMaxTempChangeHorizontalGroup= new HorizontalGroup();
        summerMaxTempChangeHorizontalGroup.addActor(summerMaxTempChangeLabel);
        summerMaxTempChangeHorizontalGroup.addActor(summerMaxTempChange);


        winterMinRainChangeHorizontalGroup= new HorizontalGroup();
        winterMinRainChangeHorizontalGroup.addActor(winterMinRainChangeLabel);
        winterMinRainChangeHorizontalGroup.addActor(winterMinRainChange);


        winterMaxRainChangeHorizontalGroup= new HorizontalGroup();
        winterMaxRainChangeHorizontalGroup.addActor(winterMaxRainChangeLabel);
        winterMaxRainChangeHorizontalGroup.addActor(winterMaxRainChange);


        fallMinRainChangeHorizontalGroup= new HorizontalGroup();
        fallMinRainChangeHorizontalGroup.addActor(fallMinRainChangeLabel);
        fallMinRainChangeHorizontalGroup.addActor(fallMinRainChange);


        fallMaxRainChangeHorizontalGroup= new HorizontalGroup();
        fallMaxRainChangeHorizontalGroup.addActor(fallMaxRainChangeLabel);
        fallMaxRainChangeHorizontalGroup.addActor(fallMaxRainChange);


        springMinRainChangeHorizontalGroup= new HorizontalGroup();
        springMinRainChangeHorizontalGroup.addActor(springMinRainChangeLabel);
        springMinRainChangeHorizontalGroup.addActor(springMinRainChange);


        springrMaxRainChangeHorizontalGroup= new HorizontalGroup();

        springrMaxRainChangeHorizontalGroup.addActor(springrMaxRainChangeLabel);
        springrMaxRainChangeHorizontalGroup.addActor(springrMaxRainChange);


        summerMaxRainChangeHorizontalGroup= new HorizontalGroup();

        summerMaxRainChangeHorizontalGroup.addActor(summerMaxRainChangeLabel);
        summerMaxRainChangeHorizontalGroup.addActor(summerMaxRainChange);
        summerMinRainChangeHorizontalGroup= new HorizontalGroup();
        summerMinRainChangeHorizontalGroup.addActor(summerMinRainChangeLabel);
        summerMinRainChangeHorizontalGroup.addActor(summerMinRainChange);



        nitrogenHorizontalGroup= new HorizontalGroup();
        nitrogenHorizontalGroup.addActor(nitrogenLabel);
        nitrogenHorizontalGroup.addActor(nitrogen);
        phosperphousHorizontalGroup= new HorizontalGroup();
        phosperphousHorizontalGroup.addActor(phosperphousLabel);
        phosperphousHorizontalGroup.addActor(phosperphous);
        potassiumHorizontalGroup= new HorizontalGroup();
        potassiumHorizontalGroup.addActor(potassiumLabel);
        potassiumHorizontalGroup.addActor(potassium);
        ironHorizontalGroup= new HorizontalGroup();
        ironHorizontalGroup.addActor(ironLabel);
        ironHorizontalGroup.addActor(iron);
        calciumHorizontalGroup= new HorizontalGroup();
        calciumHorizontalGroup.addActor(calciumLabel);
        calciumHorizontalGroup.addActor(calcium);
        sulfurHorizontalGroup= new HorizontalGroup();
        sulfurHorizontalGroup.addActor(sulfurLabel);
        sulfurHorizontalGroup.addActor(sulfur);
        manageseHorizontalGroup= new HorizontalGroup();
        manageseHorizontalGroup.addActor(manageseLabel);
        manageseHorizontalGroup.addActor(managese);




        cityLimit=new CheckBox("Is the Tile ina City Limit ", skin);
        enterable=new CheckBox("Is the Tile  Enterable ", skin);
        enterable.setChecked(tile.isEnterable());

        wet=new CheckBox("Is the Tile Wet City Limit ", skin);

        solid=new CheckBox("Is the Tile Solid  / AirTight Fluids  Won't Pass Through ", skin);
        vGroup.addActor(boundsOffsetXHorizontalGroup);
        vGroup.addActor(boundsOffsetYHorizontalGroup);
        vGroup.addActor(clayHorizontalGroup);
        vGroup.addActor(chalkHorizontalGroup);
        vGroup.addActor(siltHorizontalGroup);
        vGroup.addActor(sandHorizontalGroup);
        vGroup.addActor(loamHorizontalGroup);
        vGroup.addActor(peatHorizontalGroup);
        vGroup.addActor(nitrogenHorizontalGroup);
        vGroup.addActor(phosperphousHorizontalGroup);
        vGroup.addActor(calciumHorizontalGroup);
        vGroup.addActor(potassiumHorizontalGroup);
        vGroup.addActor(ironHorizontalGroup);
        vGroup.addActor(manageseHorizontalGroup);
        vGroup.addActor(sulfurHorizontalGroup);
        vGroup.addActor(springMaxTempChangeHorizontalGroup);
        vGroup.addActor(springMinRainChangeHorizontalGroup);
        vGroup.addActor(springrMaxRainChangeHorizontalGroup);
        vGroup.addActor(springMinRainChangeHorizontalGroup);
        vGroup.addActor(summerMaxTempChangeHorizontalGroup);
        vGroup.addActor(summerMinTempChangeHorizontalGroup);
        vGroup.addActor(summerMaxRainChangeHorizontalGroup);
        vGroup.addActor(summerMinRainChangeHorizontalGroup);
        vGroup.addActor(fallMaxTempChangeHorizontalGroup);
        vGroup.addActor(fallMinRainChangeHorizontalGroup);
        vGroup.addActor(fallMaxRainChangeHorizontalGroup);
        vGroup.addActor(fallMinRainChangeHorizontalGroup);
        vGroup.addActor(winterMaxTempChangeHorizontalGroup);
        vGroup.addActor(winterMinTempChangeHorizontalGroup);
        vGroup.addActor(winterMaxRainChangeHorizontalGroup);
        vGroup.addActor(winterMinRainChangeHorizontalGroup);
        vGroup.addActor(rainChanceHorizontalGroup);
        vGroup.addActor(rainFallTotalHorizontalGroup);
        vGroup.addActor(elevationHorizontalGroup);
        vGroup.addActor(temperatureHorizontalGroup);
        vGroup.addActor(cofficentOfFrictionHorizontalGroup);
        vGroup.addActor(enterable);
        vGroup.addActor(wet);
        vGroup.addActor(cityLimit);
        vGroup.addActor(solid);
        vGroup.columnAlign(Align.left);

        window.addActor(vGroup);

    }





    public   LandSquareTile makeTile(LandSquareTile tile2, GameMap map){



            tile.setEnterable(enterable.isChecked());


    return tile;




 }




 public void setTextFieldsToTile(LandSquareTile tile){







 }


}

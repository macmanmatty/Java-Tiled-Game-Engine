package com.jessematty.black.tower.Editor.EditMode.Screens;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveIntegerField;
import com.jessematty.black.tower.SquareTiles.EnterenceSquareTile;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.util.ArrayList;

public  class BuildingMapEditScreen {



        protected LandSquareTile outdoorEnterenceTile;
        protected LandSquareTile IndoorEnterenceTile;



        protected TextField buildingName; // the name of the building
    protected PositiveIntegerField buildingXTiles;
    protected PositiveIntegerField buildingYTiles;
    protected PositiveIntegerField stories;
    ArrayList<EnterenceSquareTile> buildingEnterenceTiles= new ArrayList<EnterenceSquareTile>();
    ArrayList<EnterenceSquareTile> buildingOutdoorEnterenceTiles= new ArrayList<EnterenceSquareTile>();
    TextButton addEnterence;


}

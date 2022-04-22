package com.jessematty.black.tower.Editor.EditMode.Screens;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.World.WorldObjects;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveIntegerField;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.EnterenceSquareTile;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Editor.EditMode.TopMenuBar.TopMenu;

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
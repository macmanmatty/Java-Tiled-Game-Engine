package com.jessematty.black.tower.Generators.MapGenerators;

import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.Maps.Buildings.Rooms.Room;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.EnterenceSquareTile;

import java.util.ArrayList;

public class BuildingGenerator  extends MapGenerator{


    RandomNumbers value=new RandomNumbers();

    com.jessematty.black.tower.Generators.MapGenerators.BuildingSpecs specs;


    public BuildingGenerator(GameAssets assetts, int xSize, int ySize, BuildingSpecs specs) {
        super(assetts,  xSize, ySize);
        this.specs = specs;
        map= new Building( );
        makeTiles();


    }

    @Override
    public GameMap makeMap() {
        return null;
    }










    public boolean  connectRooms(Room room1, Room room2){
        ArrayList<EnterenceSquareTile> enterenceTiles=room1.getEnterenceTiles();
        ArrayList<EnterenceSquareTile> nonConnectedTiles= new ArrayList<EnterenceSquareTile>();
        int size= enterenceTiles.size();
        for(int count=0; count<size; count++){
            if(enterenceTiles.get(count).getEnterenceTile()==null){ // if tiles has no connections addEntity it

                nonConnectedTiles.add(enterenceTiles.get(count));



            }

        }
        if(nonConnectedTiles.size()==0){
            return false;

        }

        ArrayList<EnterenceSquareTile> enterenceTiles2=room2.getEnterenceTiles();
        ArrayList<EnterenceSquareTile> nonConnectedTiles2= new ArrayList<EnterenceSquareTile>();
        int size2= enterenceTiles2.size();
        for(int count=0; count<size2; count++){
            if(enterenceTiles2.get(count).getEnterenceTile()==null){ // if tiles has no connections addEntity it

                nonConnectedTiles2.add(enterenceTiles2.get(count));



            }

        }

        if(nonConnectedTiles2.size()==0){
            return false;

        }

        EnterenceSquareTile enterenceSquareTile1=nonConnectedTiles.get(value.getRandomNumber(0, nonConnectedTiles.size()-1));
        EnterenceSquareTile enterenceSquareTile2=nonConnectedTiles2.get(value.getRandomNumber(0, nonConnectedTiles2.size()-1));


        enterenceSquareTile1.setEnterenceTile(enterenceSquareTile2);
        enterenceSquareTile2.setEnterenceTile(enterenceSquareTile1);


        return true;








    }




}

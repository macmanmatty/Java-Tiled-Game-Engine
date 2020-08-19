package com.jessematty.black.tower.Maps.PlacesOfInterest;

import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Generators.MapGenerators.NumberMapGen;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMask;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public  abstract class PlaceOfInterest {

    protected  LandSquareTile[] [] map;
    int xSize;
    int ySize;
    protected float[][] heightMap; // the height map
    protected float[][] rainMap; // the rain map
    protected float[][] temperatureMap; // tempertaure map
    protected float[][] fertilityMap; // soil; fertility map
    protected float heightAverage; // the average of all the tiles for the height map
    protected float rainAverage;
    protected float fertilityAverage;
    protected float temperatureAverage;
    protected BitMask bitMask= new BitMask(); // bitmasking class for bitmasking tiles
    protected boolean setMap; // wether the base landsquaretile  map is set using ouside data or cretaed  using number maps
    protected GameAssets assets; // the gaame assetts class with asset manager
protected int mapNumber;

    public PlaceOfInterest(int xSize, int ySize, int mapNumber, GameAssets assets) {
        this.xSize = xSize;
        this.ySize = ySize;

        this.mapNumber=mapNumber;

        this.assets = assets;
    }
   public abstract   LandSquareTile [] [] makePlaceOfInterest();


    protected  void makeNumberMaps( int minHeight, int maxHeight, int minRain, int maxRain,  int maxTemp, int minTemp, int maxFertility, int minFertility,  int numberOfRivers, int numberOfMountians){
        NumberMapGen  mapGenerator= new NumberMapGen();
        mapGenerator.setMapSize(xSize, ySize);
        float heightTotal = 0;
        float rainTotal = 0;
        float temperatureTotal = 0;
        float fertilityTotal = 0;

        heightMap = mapGenerator.makeHeightMap( minHeight, maxHeight, 4, numberOfMountians); // makes WoodWand height with specified number of rivers and mountians and maxHeight for the heightmap
        rainMap = mapGenerator.makeRainMap(maxRain, minRain, numberOfRivers, 4);
        temperatureMap = mapGenerator.makeTempMap(maxTemp, minTemp, 4, 3 , true);
        fertilityMap=mapGenerator.makeSoilFertilityMap(maxFertility, minFertility, 4);

        for (int countx = 0; countx < xSize; countx++) { // finds the height average;
            for (int county = 0; county < ySize; county++) {
                heightTotal = heightTotal + heightMap[countx][county];
                rainTotal = rainTotal + rainMap[countx][county];
                temperatureTotal = temperatureTotal + temperatureMap[countx][county];
                fertilityTotal = fertilityTotal + fertilityMap[countx][county];

            }
        }
        int mapCells = xSize * ySize;
        temperatureAverage = temperatureTotal / mapCells;
        rainAverage = rainTotal / mapCells;
        heightAverage = heightTotal / mapCells;
        fertilityAverage = fertilityTotal / mapCells;



    }




}

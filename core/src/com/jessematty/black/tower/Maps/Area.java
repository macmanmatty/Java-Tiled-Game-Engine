package com.jessematty.black.tower.Maps;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.IntArray;
import com.jessematty.black.tower.Components.SolidObject;
import com.jessematty.black.tower.GameBaseClasses.Generators.Sets.CliffSet;
import com.jessematty.black.tower.GameBaseClasses.Generators.Sets.TileSet;
import com.jessematty.black.tower.GameBaseClasses.Generators.Specs.FighterSpecs;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.GameBaseClasses.Calculators.PathFind.Astar8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class Area extends LandMap { // class for an area of land to place  people and things  and item plants ect.



     protected  int xTileStart;
     protected  int yTileStart;
    protected  int xTileEnd;
    protected  int yTileEnd;
    protected String id;
    protected String specsPath;
    protected int minTemp=-1;
    protected int maxTemp=-2;
    protected int tempChange=-1;
    protected boolean randomSets;
    Area upArea;
    Area downArea;
    Area leftArea;
    Area rightArea;






    protected IntArray entrencePoints = new IntArray();
    protected ArrayList<com.jessematty.black.tower.GameBaseClasses.Generators.Sets.TileSet> tileSets= new ArrayList<com.jessematty.black.tower.GameBaseClasses.Generators.Sets.TileSet>();


     protected  RandomNumbers value= new RandomNumbers();

   protected   ArrayList<FighterSpecs> fighterSpecs= new ArrayList<FighterSpecs>();

    protected ArrayList<CliffSet> cliffSets= new ArrayList<CliffSet>();
    protected String thingJsonSheetPath;








    public Area() {
        id = UUID.randomUUID().toString();






    }

    public  void setUp() {
        this.xTileStart =1;
        this.yTileStart = 1;
        this.xTileEnd = 1;
        this.yTileEnd = 1;

        this.minTemp = -20;
        this.maxTemp = 100;
        this.tempChange = 2;
        this.randomSets = true;
        this.entrencePoints = new IntArray();
        entrencePoints.add(22);
        this.tileSets = new ArrayList<TileSet>();
        TileSet set =new TileSet();
        set.setColored(true);
        set.setCliffNumber(1);
        set.setRiverBankNumber(1);
        set.setTerrianKind("dirt");
        set.setTileNumbers();



        tileSets.add(set);

        FighterSpecs specs= new FighterSpecs();
        CliffSet cSet = new CliffSet();
        cSet.setCliffNumber(1);
        cSet.setColored(true);
        cSet.setRiverBankNumber(1);
        cSet.setTerrianKind("dirt");
        cSet.setTileNumbers();
        cSet.setHeightIncrease(true);
        cliffSets.add(cSet);









    }

    public Area( int xTileStart, int yTileStart, int xTileEnd, int yTileEnd, boolean randomSets, ArrayList<TileSet> tileSets) {
        this.xTileStart = xTileStart;
        this.yTileStart = yTileStart;
        this.xTileEnd = xTileEnd;
        this.yTileEnd = yTileEnd;
        this.randomSets = randomSets;

        this.tileSets = tileSets;
        if(randomSets==true){

            Collections.shuffle(tileSets);
        }



    }

    public void setTiles(int xTileStart, int yTileStart, int xTileEnd, int yTileEnd){
        this.xTileStart = xTileStart;
        this.yTileStart = yTileStart;
        this.xTileEnd = xTileEnd;
        this.yTileEnd = yTileEnd;


        int xEnd=xTileStart+xTileEnd;
        int yEnd=yTileStart+yTileEnd;
        for(int countx=xTileStart; countx<xEnd; countx++) {
            for (int county = yTileStart; county < yEnd; county++) {
               //getMapSquare(countx, county).setArea(this);





            }

        }
    }


    public void   deSeralize(GameAssets assetts){





    }
    public void loadSpecs(String path){


        this.specsPath=path;

        int counter=0;
        FighterSpecs specs=new FighterSpecs();

        while(specs!=null) {
      specs = (FighterSpecs) gameAssets.loadObject(path + counter + ".json", FighterSpecs.class);


      if(specs!=null){

          fighterSpecs.add(specs);



      }

           counter++;

       }




    }





    public void placeThing(int xTileStart, int yTileStart, int xTileEnd, int yTileEnd, Entity thing, boolean mustBeReachable){

        int xLocation=value.getRandomNumber(xTileStart, xTileEnd);
        int yLocation=value.getRandomNumber(yTileStart, yTileEnd);
        LandSquareTile tile = getMapSquare(xLocation, yLocation);

        boolean reachable=true;
        boolean enterable=tile.isEnterable();


        if(mustBeReachable==true) {
            reachable=false;

            Astar8 pathFind = new Astar8(xSize, ySize, this, null);


            int size=entrencePoints.size;

            for(int count=0; count<size; count=count+2) {
                IntArray locations = pathFind.getPath(entrencePoints.get(count),entrencePoints.get(count+1) ,xLocation ,yLocation );
                if(locations.get(0)==xLocation && locations.get(1)==yLocation){
                    reachable=true;
                    break;



                }

            }


        }

        if(thing.getComponent(SolidObject.class)!=null){
            int xTiles=0;
           // xTiles=(int) ((thing.getWidth()/32))+1;
            int yTiles=0;
            //yTiles=(int) ((thing.getHeight()/32))+1;
            int xStart=xLocation-1;
            int yStart=yLocation-1;
            int xEnd=xLocation+xTiles;
            int yEnd=yLocation+yTiles;


            out:
            for (int countx=xStart;  countx<xEnd; countx++){
                for (int county=yStart;  county<yEnd; county++){
                    LandSquareTile location= getMapSquareOrNull(countx, county);
                    if(location==null || location.isEnterable()==false || location.getEntities(SolidObject.class).size>0){
                        enterable=false;
                        break out;


                    }




                }




                }






        }



        boolean placement=false;
        while (placement==false){

             xLocation=value.getRandomNumber(xTileStart, xTileEnd);
             yLocation=value.getRandomNumber(yTileStart, yTileEnd);
            tile = getMapSquare(xLocation, yLocation);

           reachable=true;
           enterable=tile.isEnterable();


            if(mustBeReachable==true) {
                reachable=false;

                Astar8 pathFind = new Astar8(xSize, ySize,this, null);


                int size=entrencePoints.size;

                for(int count=0; count<size; count=count+2) {
                    IntArray locations = pathFind.getPath(entrencePoints.get(count),entrencePoints.get(count+1) ,xLocation ,yLocation );
                    if(locations.get(0)==xLocation && locations.get(1)==yLocation){
                        reachable=true;
                        break;



                    }

                }


            }

            if(thing.getComponent(SolidObject.class)!=null){

                int xTiles=0;
                // xTiles=(int) ((thing.getWidth()/32))+1;
                int yTiles=0;
                //yTiles=(int) ((thing.getHeight()/32))+1;
                int xStart=xLocation-1;
                int yStart=yLocation-1;
                int xEnd=xLocation+xTiles;
                int yEnd=yLocation+yTiles;


                out:
                for (int countx=xStart;  countx<xEnd; countx++){
                    for (int county=yStart;  county<yEnd; county++){
                        LandSquareTile location= getMapSquareOrNull(countx, county);
                        if(location==null || location.isEnterable()==false || location.getEntities(SolidObject.class).size>0){
                            enterable=false;
                            break out;


                        }




                    }




                }






            }
            if(reachable==true && enterable==true){

                placement=true;
            }




        }


        tile.addEntity(thing);
    }




    public void act(){


    }










    public int getxTileStart() {
        return xTileStart;
    }

    public void setxTileStart(int xTileStart) {
        this.xTileStart = xTileStart;
    }

    public int getyTileStart() {
        return yTileStart;
    }

    public void setyTileStart(int yTileStart) {
        this.yTileStart = yTileStart;
    }

    public int getxTileEnd() {
        return xTileEnd;
    }

    public void setxTileEnd(int xTileEnd) {
        this.xTileEnd = xTileEnd;
    }

    public int getyTileEnd() {
        return yTileEnd;
    }

    public void setyTileEnd(int yTileEnd) {
        this.yTileEnd = yTileEnd;
    }





    public IntArray getEntrencePoints() {
        return entrencePoints;
    }

    public void setEntrencePoints(IntArray entrencePoints) {
        this.entrencePoints = entrencePoints;
    }

    public ArrayList<TileSet> getTileSets() {
        return tileSets;
    }

    public void setTileSets(ArrayList<TileSet> tileSets) {
        this.tileSets = tileSets;
    }

    public ArrayList<FighterSpecs> getFighterSpecs() {
        return fighterSpecs;
    }

    public void setFighterSpecs(ArrayList<FighterSpecs> fighterSpecs) {
        this.fighterSpecs = fighterSpecs;
    }


    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getTempChange() {
        return tempChange;
    }

    public void setTempChange(int tempChange) {
        this.tempChange = tempChange;
    }

    public boolean isRandomSets() {
        return randomSets;
    }

    public void setRandomSets(boolean randomSets) {
        this.randomSets = randomSets;
    }

    public ArrayList<CliffSet> getCliffSets() {
        return cliffSets;
    }

    public void setCliffSets(ArrayList<CliffSet> cliffSets) {
        this.cliffSets = cliffSets;
    }

    public String getThingJsonSheetPath() {
        return thingJsonSheetPath;
    }

    public void setThingJsonSheetPath(String thingJsonSheetPath) {
        this.thingJsonSheetPath = thingJsonSheetPath;
    }


    public Area getUpArea() {
        return upArea;
    }

    public void setUpArea(Area upArea) {
        this.upArea = upArea;
    }

    public Area getDownArea() {
        return downArea;
    }

    public void setDownArea(Area downArea) {
        this.downArea = downArea;
    }

    public Area getLeftArea() {
        return leftArea;
    }

    public void setLeftArea(Area leftArea) {
        this.leftArea = leftArea;
    }

    public Area getRightArea() {
        return rightArea;
    }

    public void setRightArea(Area rightArea) {
        this.rightArea = rightArea;
    }
}

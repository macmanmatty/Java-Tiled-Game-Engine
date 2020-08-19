package com.jessematty.black.tower.GameBaseClasses.Generators.MapGenerators;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Grass;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Calculators.PathFind.PathFind;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Generators.ObjectGenerator;
import com.jessematty.black.tower.GameBaseClasses.Generators.Sets.CliffSet;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMask;
import com.jessematty.black.tower.Maps.Area;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.Maps.Buildings.Shops.Shop;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.SquareTiles.BaseSquareTile;
import com.jessematty.black.tower.SquareTiles.EnterenceSquareTile;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LandMapGenerator extends MapGenerator {
   protected ArrayList<Shop> shops= new ArrayList<Shop>();

    protected  ArrayList<Building>  buildings = new ArrayList<Building>();
    ArrayList<Area> areas= new ArrayList<Area>();
    Area defaultArea;
    protected ArrayList< Integer> soilMapNumbers= new ArrayList<Integer>();
    protected ArrayList< Integer> heightMapNumbers= new ArrayList<Integer>();
    protected ArrayList<CliffSet> cliffSets;
    protected  ArrayList<EnterenceSquareTile> roadEnterenceTiles= new ArrayList<EnterenceSquareTile>();
    protected  ArrayList<EnterenceSquareTile> roadExitTiles= new ArrayList<EnterenceSquareTile>();
    BitMask mask= new BitMask();
    boolean coldUp=true;
    boolean[] [] soilMaskMap;
    int [] []  soilBitNumberMap;
    int  [] [] heightBitNumberMap;
    int averageHeightNumber;
    protected int[][] heightMap;
    protected int[][] waterMap;
    protected int[][] temperatureMap;
    protected int[][] clayMap;
    protected int[][] siltMap;
    protected int[][] loamMap;
    protected int[][] sandMap;
    protected int[][] soilMap;
    protected int [] [] roadMap;
    protected int [] [] roadBitMap;
    protected int [] []  fireMagicMap;
    protected int [] []  earthMagicMap;
    protected int [] [] windMagicMap;
    protected int [] [] waterMagicMap;
    protected int [][] darkMagicMap;
    protected int [] [] lightMagicMap;
    protected int  [] [] cliffMap;
    protected int [] [] lightMap;
    protected int  nMap [] []  ;
    protected int pMap [] [] ;
    protected int kMap [] [];
    protected int caMap[] [];
    protected int mnMap [] [];
    protected int feMap [] [];
    protected int sMap [] [];
    protected int arMap [] [];
    protected int pbMap [] [];
    protected int auMap [] [];
    protected int cuMap [] [];
    protected int agMap [] [];
    protected int snMap [] [];
    protected ArrayList<Integer> heightNumbers;
    protected int [] [] peatMap;
    protected int[][] chalkMap;
    int sandAverage;
    int clayAverage;
    int peatAverage;
    int siltAverage;
    int loamAverage;
    int chalkAverage;
    protected int heightAverage;
    protected int temperatureAverage;
    protected int  tileSize;
    protected int maxCliffHeight;
    protected String dataPath;
    protected String sectionName;
    protected int rainAverage;
    protected int fertilityAverage;
    protected int [] []  townPoints; // the starting x and y for the towns;
    protected int [] [] townSizes;
    ObjectGenerator objectGenerator;
   PathFind pathFind;
   LandMapSpecs mapSpecs;
    public LandMapGenerator(GameAssets assetts, LandMapSpecs specs) {
        super(assetts, specs.getxSize(), specs.getySize());
        this.mapSpecs=specs;
        map= new LandMap(assetts.getMapDraw(), xSize, ySize, assetts, assetts.getDefaultSkin());
        tiledMap= new TiledMap();
       MapProperties properties= tiledMap.getProperties();
       properties.put("width", xSize);
       properties.put("height",ySize);
        properties.put("tilewidth",32);
        properties.put("tileheight",32);
        properties.put("renderorder","right-down");
        mapLayers=tiledMap.getLayers();
        mapLayers.add(new TiledMapTileLayer(xSize,ySize,32,32));
        landSquareTileMap =new LandSquareTile[xSize][ySize];
        makeTiles();
        pathFind= new PathFind(map);
    objectGenerator=new ObjectGenerator(map, assetts);
    }
    public boolean makeTiledMap(){
        defaultArea=mapSpecs.getDefaultArea();
        if(defaultArea==null){
            return false;
        }
        areas=mapSpecs.getAreas();
        makeBaseTiles();
        makeNumberMaps();
      //loadPremadeSections(dataPath, sectionName);
           makeSoilTiles();
          // makeCliffs();
        return true;
    }
    public void makeRoads( int width, int roadNumber){
        int exitGatesSize=roadEnterenceTiles.size();
        int enterGatesSize=roadExitTiles.size();
        int enterenceDifference=Math.abs(enterGatesSize-exitGatesSize);
        int size=enterGatesSize;
        boolean enter=false;
        if(size>exitGatesSize){
            size=exitGatesSize;
            enter=true;
        }
        for(int count=0; count<size; count++) {
            LandSquareTile enterence=enterGates.get(count);
            LandSquareTile exit=exitGates.get(count);
            //ArrayList<LandSquareTile> landSquareTile=pathFind.pathFind(enterence, exit, width,  true, false);
            //setRoads(landSquareTile, width);
        }
        for(int count=0; count<enterenceDifference; count++){
            if(enter==true){
                LandSquareTile tile1=enterGates.get(count);
                int randomx=value.getRandomNumber(3, xSize-4);
                int randomy=value.getRandomNumber(3, ySize-4);
                LandSquareTile tile2=landSquareTileMap[randomx][randomy];
                if(exitGatesSize!=0) {
                    //tile2 = findRoad();
                }
                //ArrayList<LandSquareTile> tiles=pathFind.pathFind(tile1, tile2, width, tileFlags, true, false);
                //(tiles, width);
            }
        }
        size=buildings.size();
        for(int count=0; count<size; count++){
           List<LandSquareTile> enterences=new ArrayList<LandSquareTile>();
            int size2=enterences.size();
            for(int count2=0; count2<size2; count2++){
                LandSquareTile tile1=enterences.get(count);
                LandSquareTile tile2=findRoad();
               // ArrayList<LandSquareTile> tiles=pathFind.pathFind(tile1, tile2,  width, tileFlags, true, false);
               // setRoads(tiles, width);
            }
        }
        maskRoads(roadNumber);
    }
    private void maskRoads(int roadNumber){
TiledMapTileLayer layer= new TiledMapTileLayer(xSize, ySize, 32, 32)    ;
layer.setName("Road");
        for (int countx = 0; countx < xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
               int bitNumber= bitMask.eightSideBitMapCalculator(1, countx, county, roadMap);
                if(bitNumber!=0){
                    String name="road"+"."+roadNumber+"."+bitNumber;
                    AtlasNamedAtlasRegion region=getTexture(name, atlasName);
                    Cell cell= new Cell();
                    AtlasStaticTiledMapTile tile= new AtlasStaticTiledMapTile(region);
                    cell.setTile(tile);
                    layer.setCell(countx, county, cell);
                }
            }
            }
            tiledMap.getLayers().add(layer);
    }
    private LandSquareTile findRoad(){
        LandSquareTile roadTile=null;
        while (roadTile==null) {
            for (int countx = 0; countx < xSize; countx++) {
                for (int county = 0; county < ySize; county++) {
                    int random=value.getRandomNumber(1,20);
                    if(roadMap[countx][countx]==1 && random%3==0){
                        roadTile=landSquareTileMap[countx][county];
                        break;
                    }
                }
            }
        }
        return roadTile;
    }
    private void setRoads(ArrayList<LandSquareTile> tiles, int width){
        int size=tiles.size();
        Direction direction=Direction.DOWN;
        for(int count=0; count<size; count++) {
            LandSquareTile tile=tiles.get(count);
            LandSquareTile tile2=tiles.get(count+1);
            int x=tile.getLocationX();
            int y=tile.getLocationY();
            int x2=tile2.getLocationX();
            int y2=tile2.getLocationY();
            if(count<size-1) {
                direction= Direction.getDirection(x, y, x2, y2);
            }
            setRoadMapNumbers(x, y,direction, width);
        }
    }
    private void setRoadMapNumbers( int x, int y, Direction direction, int width) {
        if(direction==Direction.DOWN || direction==Direction.UP){
            for (int count=-width; count<width; count++){
               roadMap[x+count][y]=1;
            }
        }
        if(direction==Direction.LEFT || direction==Direction.RIGHT){
            for (int count=-width; count<width; count++){
                roadMap[x][y+count]=1;
            }
        }
    }
    private boolean assignAreas() {
        if(areas.size()==0){
            makeArea(0, 0, xSize, ySize, defaultArea);
        }
        return true;
    }
    private void makeArea(int startX, int startY, int xSize, int ySize, Area area) {
        int endX=startX+xSize;
        int endY=startY+ySize;
        for(int countx=startX; countx<endX; countx++) {
            for (int county = startY; county < endY; county++) {
                landSquareTileMap[countx][county].setArea(area);
            }
        }
    }
    private void makeBaseTiles(){ // fill the map up with empty landqaure tile objects
        for(int countx=0; countx<xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                LandSquareTile tile= new BaseSquareTile(countx, county);
                tile.setMap(map);
                tile.setArea(defaultArea);
                landSquareTileMap[countx][county]= tile;
            }
            }
            map.setMap(landSquareTileMap);
    }
    private void makeGrass(){
        for(int countx=0; countx<xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                if(landSquareTileMap[countx][county].isUnchangeble()==false &&  nMap[countx][county]>10){
                   Entity grass=  objectGenerator.makeGrass( feMap[countx][county]);

                   map.addEntity(grass);
                   landSquareTileMap[countx][county].addEntity(grass);
                }
            }
            }
    }
    private void makePathThroughGrass(){
        int pointX1=value.getRandomNumber(0, xSize-1);
        int pointX2=value.getRandomNumber(0, xSize-1);
        int pointY1=value.getRandomNumber(0, ySize-1);
        int pointY2=value.getRandomNumber(0, ySize-1);
        ArrayList <LandSquareTile>landSquareTiles= new ArrayList<LandSquareTile>();
        //pathFind.pathFind(landSquareTileMap[pointX1][pointY1],landSquareTileMap[pointX2][pointY2] , 1, change,   false, false);
      int size=landSquareTiles.size();
      for(int count=0; count<size; count++){
          LandSquareTile tile=landSquareTiles.get(count);
         Array<Entity> plants=tile.getEntities(Grass.class);
          int size2=plants.size;
                  map.removeEntity(plants.get(count));
          }
          }
        public void makeSoilTiles() {
            int numberOfSoilLayers=soilMapNumbers.size();

            for (int count = 0; count < numberOfSoilLayers; count++) {

                TiledMapTileLayer layer2 = new TiledMapTileLayer(xSize, ySize, 32, 32);
                layer2.setName("layer" + mapLayers.size());
                tiledMap.getLayers().add(layer2);
                TiledMapTileLayer layer = new TiledMapTileLayer(xSize, ySize, 32, 32);
                layer.setName("layer" + mapLayers.size());
                tiledMap.getLayers().add(layer);

            }

        soilBitNumberMap=mask.makeTrimmedHeightTileBitMap(soilMapNumbers, soilMap); // make trimmed bit map
        for (int countx = 0; countx < xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                for (int count = 0; count < numberOfSoilLayers; count++) {

                    if (soilMap[countx][county] ==soilMapNumbers.get(count)) {
                        Cell cell = new Cell();
                        AtlasNamedAtlasRegion region = assetts.getAtlasRegionByName("dirt." + (count) + "." + soilBitNumberMap[countx][county] + ".0", "assetts");
                        cell.setTile(  new AtlasStaticTiledMapTile(region));
                        TiledMapTileLayer  layer = (TiledMapTileLayer) tiledMap.getLayers().get(count );
                        layer.setCell(countx, ySize-county, cell);
                        if(soilBitNumberMap[countx][county]!=255  && count>0) { // make the layer under the same as layer above
                             cornerCheck(countx, county, count, 1);
                            }
                    }
                }
            }
        }
    }
    private void cornerCheck(int countx, int county, int count, int number){
      TiledMapTileLayer  layer = (TiledMapTileLayer) tiledMap.getLayers().get(count - number);
         Cell cell = new Cell();
        int bitValue= bitMask.eightSideBitMapCalculator(soilMap[countx][county]-number, countx, county, soilMap);
        AtlasNamedAtlasRegion region;
        if(number>=count){
            region = assetts.getAtlasRegionByName("dirt." + (count - number) + "." + 255 + ".0", "assetts");
            cell.setTile(new AtlasStaticTiledMapTile(region));
            layer.setCell(countx, ySize - county, cell);
            return;
        }
        if (bitValue!=255 ) {
             region = assetts.getAtlasRegionByName("dirt." + (count - number) + "." + bitValue + ".0", "assetts");
            cell.setTile(new AtlasStaticTiledMapTile(region));
            layer.setCell(countx, ySize - county, cell);
        }
        else {
            region = assetts.getAtlasRegionByName("dirt." + (count - number) + "." + 255 + ".0", "assetts");
            cell.setTile(new AtlasStaticTiledMapTile(region));
            layer.setCell(countx, ySize - county, cell);
            return;
        }
        if(number<count) {
            number++;
            cornerCheck(countx, county, count, number);
        }
    }
@Override
    public GameMap makeMap(){
        map.setTiledMap(tiledMap);
        map.setMap(landSquareTileMap);
        return map;
    }
/*
    private void  makeCliffs() {
        TiledMapTileLayer layer = new TiledMapTileLayer(xSize, ySize, 32, 32);
        layer.setName("layer" + mapLayers.size());
        tiledMap.getLayers().addEntity(layer);
        heightBitNumberMap = mask.makeTrimmedTileBitMap(heightMapNumbers, heightMap);
        ArrayList<ArrayList<LandSquareTile>> tiles=getAreas(heightMap, averageHeightNumber-1, false);
        for(int count=0; count<tiles.size(); count++) {
            System.out.println("Area " + tiles.get(count).size());
        }
        int tileWidth=1;
        int size=tiles.size();
        for (int count = 0; count < size; count++) {
            int size2= tiles.get(count).size();
            if(size2<30){
                continue;
            }
           ArrayList< CliffSet > cliffSets=tiles.get(count).get(0).getArea().getCliffSets();
           CliffSet cliffSet=cliffSets.get(value.getRandomNumber(0, cliffSets.size()-1));
            int cliffHeight=cliffSet.getMinHeight();
            if(cliffSet.isHeightIncrease()==true) {
                cliffHeight = value.getRandomNumber(cliffHeight, mapSpecs.getMaxCliffHeight());
            }
            Cliff cliff=objectGenerator.makeCliff(this, cliffSet);
            for (int count2 = 0; count2 < size2; count2++) {
                LandSquareTile tile = tiles.get(count).get(count2);
                int y=tile.getLocationY();
                int x=tile.getLocationX();
                int bitNumber = heightBitNumberMap[x][y];
                if ( tile.isUnchangeable() == false) {
 if (bitNumber == 22 || bitNumber == 11 || bitNumber == 31) {
                            for (int county = 0; county < cliffHeight; county++) {
                                Cell cell = new Cell();
                                cell.setFlipVertically(true); // tiled map rendering system requires to do this to get correct y axis
                                int yy = y + county;
                                if (yy >= ySize) {
                                    break;
                                }
                                tile = landSquareTileMap[x][yy];
                                AtlasNamedAtlasRegion region = null;
                                if (county == 0) {
                                    region = assetts.getAtlasRegionByName("cliff." + (0) + "." + bitNumber + "." + 0  + ".0", "assetts.atlas");
                                    System.out.println("cliff." + (0) + "." + bitNumber + "." + 0  + ".0") ;                                                                              tileWidth=(int)(region.getRegionWidth()/32 );
                                    if (region != null) {
                                        placeRegion(region, landSquareTileMap[x][yy], cliff, layer, true);
                                    }
                                } else if (county > 0 && county < cliffHeight - 1) {
                                    region = assetts.getAtlasRegionByName("cliff." + (0) + "." + bitNumber + "." + 1  + ".0", "assetts.atlas");
                                    System.out.println("cliff." + (0) + "." + bitNumber + "." + 1  + ".0") ;                                                                              tileWidth=(int)(region.getRegionWidth()/32 );
                                    if (region != null) {
                                        placeRegion(region, landSquareTileMap[x][yy], cliff, layer, true);
                                    }
                                } else {
                                    String name="cliff." + (0)  + "." + bitNumber + "." + 2 + ".0";
                                    region = assetts.getAtlasRegionByName(name, "assetts.atlas");
                                    System.out.println(name) ;                                                                              tileWidth=(int)(region.getRegionWidth()/32 );
                                    if (region != null) {
                                        placeRegion(region, landSquareTileMap[x][yy], cliff, layer, true);
                                    }
                                }
                                Cliff cliff2= (Cliff) cliff.makeCopy();
                                objectGenerator.setMaterialsToCliff(cliff2, this);
                                tile.addEntity(cliff2);
                                cliff2.setBounds(32, 32);
                            }
                        } else if (bitNumber == 127 || bitNumber == 223) {
                            for (int county = 0; county < cliffHeight; county++) {
                                Cell cell = new Cell();
                                cell.setFlipVertically(true); // tiled map rendering system requires to do this to get correct y axis
                                int yy = y + county;
                                if (yy >= ySize) {
                                    break;
                                }
                                int bitNumber2=heightBitNumberMap[x][yy];
                                if(bitNumber2==255){
                                    bitNumber2=0;
                                }
                                tile = landSquareTileMap[x][yy];
                                AtlasNamedAtlasRegion region = null;
                                if (county == 0) {
                                    region = assetts.getAtlasRegionByName("cliff." + (0) + "."  + bitNumber+".0.0" + ".0", "assetts.atlas");
                                    System.out.println("cliff." + (0) + "."  + bitNumber+ ".0" + "." + ".0");
                                    if (region != null) {
                                        placeRegion(region, landSquareTileMap[x][yy], cliff, layer, true);
                                    }
                                } else if (county > 0 && county < cliffHeight - 1) {
                                    String name="cliff." + (0) + "." + bitNumber  +"."+ bitNumber2 + ".1" + ".0.0";
                                    region = assetts.getAtlasRegionByName(name, "assetts.atlas");
                                    System.out.println(name);
                                    if (region != null) {
                                        placeRegion(region, landSquareTileMap[x][yy], cliff, layer, true);
                                    }
                                } else {
                                    String name="cliff." + (0) + "." + bitNumber  +"."+ bitNumber2 + ".2" + ".0.0";
                                    region = assetts.getAtlasRegionByName(name, "assetts.atlas");
                                    System.out.println(name);
                                    if (region != null) {
                                        placeRegion(region, landSquareTileMap[x][yy], cliff, layer, true);
                                    }
                                }
                                Cliff cliff2= (Cliff) cliff.makeCopy();
                                objectGenerator.setMaterialsToCliff(cliff2, this);
                                tile.addEntity(cliff2);
                                cliff2.setBounds(32, 32);
                            }
                }
            else {
                            AtlasNamedAtlasRegion region = assetts.getAtlasRegionByName("cliff." + (0) + "."+ bitNumber + ".0.0", "assetts.atlas");
                            System.out.println("cliff." + (0) + "."  + bitNumber  + ".0.0") ;
                            if (region != null && bitNumber!=255) {
                                placeRegion(region, landSquareTileMap[x][y], cliff, layer, true);
                            }
                else if (region != null) {
                        placeRegion(region, landSquareTileMap[x][y], cliff, layer, false);
                             }
                            if(bitNumber!=255 && bitNumber!=0){
                                tile = tiles.get(count).get(count2);
                                Cliff cliff2= (Cliff) cliff.makeCopy();
                                objectGenerator.setMaterialsToCliff(cliff2, this);
                                tile.addEntity(cliff2);
                                cliff2.setBounds(32, 32);
                            }
                        }
                    }
                    else{
                     eraseTiles(tiles.get(count), layer);
                     continue;
                }
                }
        }
            }
*/
        private  boolean placeCheck(int x, int y, int width, int length) {
                int sizeX=x+width;
                int sizeY=y+length;
            for (int countx = x; countx < sizeX; countx++) {
                for (int county = y; county < sizeY; county++) {
                    boolean unchangeable=map.getMapSquare(countx, county).isUnchangeble();
                    if(unchangeable==true){
                        return false;
                    }
                }
            }
            return true;
        }
    public LandSquareTile[][] getLandSquareTileMap() {
        return landSquareTileMap;
    }
    protected  void makeNumberMaps(){
        int numberOfSquares=xSize*ySize;
        soilMaskMap= new boolean [xSize][ySize];
        soilBitNumberMap= new int [xSize][ySize];
      //  roadMap= new int [xSize][ySize];
      //  roadBitMap= new int [xSize][ySize];
        SoilMapGen soilMapGen= new SoilMapGen(xSize, ySize);
        soilMap=soilMapGen.makeSoilMap(mapSpecs.getMaxSoilDepth(), mapSpecs.getMinSoilDepth(), mapSpecs.getSoilSmoothness());
       soilMapNumbers= soilMapGen.getSoilNumbers();
        Collections.sort(soilMapNumbers);
        HeightMapGen heightMapMaker= new HeightMapGen(xSize, ySize);
        heightMap=heightMapMaker.makeHeightMap(mapSpecs.getMaxHeight(),mapSpecs.getMinHeight(),mapSpecs.getHeightSmoothness());
        heightMapNumbers=heightMapMaker.getHeightNumbers();
        averageHeightNumber =heightMapMaker.getAverageHeightNumber();
        /*
        waterMap =heightMapMaker.makeMap(1,10,mapSpecs.getSoilSmoothness());
        //temperatureMap=heightMapMaker.makeTempMap(mapSpecs.getMaxTemp(),mapSpecs.getMinTemp(),2, mapSpecs.getTempChange(), coldUp);
        clayMap=heightMapMaker.makeMap(1,10,mapSpecs.getSoilSmoothness());
        siltMap=heightMapMaker.makeMap(1,10,mapSpecs.getSoilSmoothness());
        loamMap=heightMapMaker.makeMap(1,10,mapSpecs.getSoilSmoothness());
        sandMap=heightMapMaker.makeMap(1,10,mapSpecs.getSoilSmoothness());
        cliffMap=heightMapMaker.makeMap(1,10,mapSpecs.getSoilSmoothness());
        lightMap=heightMapMaker.makeMap(1,10,mapSpecs.getSoilSmoothness());
        soilMap =heightMapMaker.makeMap(1,10,mapSpecs.getSoilSmoothness());
        peatMap=heightMapMaker.makeMap(1,10,mapSpecs.getSoilSmoothness());
        chalkMap=heightMapMaker.makeMap(1,10,mapSpecs.getSoilSmoothness());
         nMap =heightMapMaker.makeMap(1,10,mapSpecs.getNutrientSmoothness());
      pMap =heightMapMaker.makeMap(1,10,mapSpecs.getNutrientSmoothness());
       kMap =heightMapMaker.makeMap(1,10,mapSpecs.getNutrientSmoothness());
        caMap=heightMapMaker.makeMap(1,10,mapSpecs.getNutrientSmoothness());
        mnMap =heightMapMaker.makeMap(1,10,mapSpecs.getNutrientSmoothness());
        feMap =heightMapMaker.makeMap(1,10,mapSpecs.getNutrientSmoothness());
         sMap =heightMapMaker.makeMap(1,10,mapSpecs.getNutrientSmoothness());
        arMap =heightMapMaker.makeMap(1,10,mapSpecs.getNutrientSmoothness());
        pbMap =heightMapMaker.makeMap(1,10,mapSpecs.getNutrientSmoothness());
        fireMagicMap =heightMapMaker.makeMap(1,10,mapSpecs.getNutrientSmoothness());
        earthMagicMap =heightMapMaker.makeMap(1,10,mapSpecs.getNutrientSmoothness());
        windMagicMap =heightMapMaker.makeMap(1,10,mapSpecs.getNutrientSmoothness());
        waterMagicMap =heightMapMaker.makeMap(1,10,mapSpecs.getNutrientSmoothness());
        darkMagicMap =heightMapMaker.makeMap(1,10,mapSpecs.getNutrientSmoothness());
        lightMagicMap =heightMapMaker.makeMap(1,10,mapSpecs.getNutrientSmoothness());
        int heightTotal=0;
        int rainTotal=0;
        int sandTotal=0;
        int clayTotal=0;
        int loamTotal=0;
        int peatTotal=0;
        int tempTotal=0;
        int chalkTotal=0;
        int siltTotal=0;
        for(int countx=0; countx<xSize; countx++){
            for(int county=0; county<ySize; county++){
                heightTotal=heightTotal+heightMap[countx][county];
                rainTotal=rainTotal+ waterMap[countx][county];
                sandTotal=sandTotal+sandMap[countx][county];
                clayTotal=clayTotal+clayMap[countx][county];
                loamTotal=loamTotal+loamMap[countx][county];
                peatTotal=peatTotal+peatMap[countx][county];
                tempTotal=tempTotal+temperatureMap[countx][county];
                chalkTotal=chalkTotal+chalkMap[countx][county];
                siltTotal=siltTotal+siltMap[countx][county];
            }
            }
            heightAverage=heightTotal/numberOfSquares;
        rainAverage=rainTotal/numberOfSquares;
        rainAverage=rainTotal/numberOfSquares;
        sandAverage=sandTotal/numberOfSquares;
        clayAverage=clayTotal/numberOfSquares;
        peatAverage=peatTotal/numberOfSquares;
        chalkAverage=chalkTotal/numberOfSquares;
        siltAverage=siltTotal/numberOfSquares;
        loamAverage=loamTotal/numberOfSquares;
        temperatureAverage=tempTotal/numberOfSquares;
*/
    }
    public int getMapValue(String value, int x, int y ){
        if(value=="Ca")   {
            return caMap[x][y];
        }
        if(value=="Fe")   {
            return feMap[x][y];
        }
        if(value=="K")   {
            return kMap[x][y];
        }
        if(value=="p") {
            return pMap[x][y];
        }
        if(value=="N")   {
            return nMap[x][y];
        }
        if(value=="Sn")   {
            return snMap[x][y];
        }
        if(value=="Cu")   {
            return cuMap[x][y];
        }
         if(value=="Pb")   {
            return pbMap[x][y];
        }
        if(value=="Mn")   {
            return mnMap[x][y];
        }
        if(value=="S")   {
            return sMap[x][y];
        }
        if(value=="Ag")   {
            return agMap[x][y];
        }
        if(value=="Au")   {
            return auMap[x][y];
        }
        if(value=="Peat")   {
            return peatMap[x][y];
        }
        if(value=="Sand")   {
            return sandMap[x][y];
        }
        if(value=="Loam")   {
            return loamMap[x][y];
        }
        if(value=="Clay")   {
            return peatMap[x][y];
        }
        if(value=="Silt")   {
            return peatMap[x][y];
        }
        if(value=="Earth Magic")   {
            return earthMagicMap[x][y];
        }
        if(value=="Fire Magic")   {
            return fireMagicMap[x][y];
        }
        if(value=="Wind Magic")   {
            return windMagicMap[x][y];
        }
        if(value=="Water Magic")   {
            return waterMagicMap[x][y];
        }
        if(value=="Dark Magic")   {
            return darkMagicMap[x][y];
        }
        if(value=="Lightable Magic")   {
            return lightMagicMap[x][y];
        }
        return 0;
                          }
    public ArrayList<CliffSet> getCliffSets() {
        return cliffSets;
    }
    public void setCliffSets(ArrayList<CliffSet> cliffSets) {
        this.cliffSets = cliffSets;
    }
}

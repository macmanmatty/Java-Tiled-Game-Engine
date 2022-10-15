package com.jessematty.black.tower.Generators.MapGenerators;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.Grass;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.NumberedTile;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TileSet;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TerrainSet;
import com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.PathFind;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.Generators.Sets.CliffSet;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMask;
import com.jessematty.black.tower.Generators.ObjectGenerator;
import com.jessematty.black.tower.Generators.Sets.MaskMode;
import com.jessematty.black.tower.Maps.Area;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.Maps.Buildings.Shops.Shop;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.SquareTiles.EnterenceSquareTile;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.util.ArrayList;
import java.util.List;

public class LandMapGenerator extends MapGenerator {
   protected ArrayList<Shop> shops= new ArrayList<Shop>();
    protected  ArrayList<Building>  buildings = new ArrayList<Building>();
    ArrayList<Area> areas= new ArrayList<Area>();
    Area defaultArea;
    protected Array<Array< Integer> > mapNumbers = new Array<Array<Integer>>();
    protected Array<Array< Integer>> heightMapNumbers= new Array<Array<Integer>>();
    protected ArrayList<CliffSet> cliffSets;
    protected  ArrayList<EnterenceSquareTile> roadEnterenceTiles= new ArrayList<EnterenceSquareTile>();
    protected  ArrayList<EnterenceSquareTile> roadExitTiles= new ArrayList<EnterenceSquareTile>();
    BitMask mask= new BitMask();
    protected Array<int [] []> numberMaps= new Array<int [] []>();
    protected Array<boolean [] []> maskMaps= new Array<boolean [] []>();
    protected ComponentMapper<NumericStats> numericStatComponentMapper= GameComponentMapper.getNumericStatsComponentMapper();


    protected int [] []  townPoints; // the starting x and y for the towns;
    protected int [] [] townSizes;
    protected int [] [] roadMap;

    com.jessematty.black.tower.Generators.ObjectGenerator objectGenerator;
   PathFind pathFind;
   LandMapSpecs mapSpecs;
    public LandMapGenerator(GameAssets assetts, LandMapSpecs specs) {
        super(assetts, specs.getxSize(), specs.getySize());
        this.mapSpecs=specs;
        map= new LandMap(xSize, ySize);
        map.setSkin(assetts.getDefaultSkin());
        tiledMap= new TiledMap();
       MapProperties properties= tiledMap.getProperties();
       properties.put("width", xSize);
       properties.put("height",ySize);
        properties.put("tileWidth",32);
        properties.put("tileHeight",32);
        properties.put("renderorder","right-down");
        mapLayers=tiledMap.getLayers();
        mapLayers.add(new TiledMapTileLayer(xSize,ySize,32,32));
        landSquareTileMap =new LandSquareTile[xSize][ySize];
        makeTiles();
        pathFind= new PathFind(map);
    objectGenerator=new ObjectGenerator(map, assetts);
    }
    public boolean makeTiledMap(){
        makeBaseTiles();
        makeTiledMaps();
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
            }
        }
    }
    private void makeBaseTiles(){ // fill the map up with empty land sqaure tile objects
        for(int countx=0; countx<xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                LandSquareTile tile= new LandSquareTile(countx, county, ySize);
                landSquareTileMap[countx][county]= tile;
            }
            }
            map.setMap(landSquareTileMap);
    }

    private void makePathThroughGrass(MaskMode maskMode) {
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
                    boolean unchangeable=map.getTile(countx, county).isUnchangeble();
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



    protected  void makeTiledMaps() {
        NumberMapGenerator numberMapGenerator = new NumberMapGenerator(xSize, ySize);
        Array<TerrainSet> tileSets = mapSpecs.tileSets;


        int numberOfSquares = xSize * ySize;

        //  roadMap= new int [xSize][ySize];
        //  roadBitMap= new int [xSize][ySize];

        int numberOfSets=tileSets.size;

        for (int count = 0; count < numberOfSets; count++) {
            TerrainSet tileSet=tileSets.get(count);

           NumericStat numericStat=null;
           // create height map
            int [] [] numberMap=numberMapGenerator.makeNumberMap(numericStat.getMaxIntValue(), numericStat.getMinIntValue(), numericStat.getIntValue());
            numberMaps.add(numberMap);
            Array<Integer> numbers= numberMapGenerator.getMapNumbers();
            numbers.sort();
            mapNumbers.add( numbers);
            makeTiledMapTiles( tileSet, numbers, numberMap);
        /*


        }

*/
        }
    }

   //  makes a tileMap from a bit mask map;
    public void makeTiledMapTiles(TerrainSet terrainSet, Array<Integer> tileNumbers, int [] [] tileNumberMap) {
        int numberOfSoilLayers= tileNumbers.size;

        for (int count = 0; count < numberOfSoilLayers; count++) {

            TiledMapTileLayer layer2 = new TiledMapTileLayer(xSize, ySize, 32, 32);
            layer2.setName("layer" + mapLayers.size());
            tiledMap.getLayers().add(layer2);
            TiledMapTileLayer layer = new TiledMapTileLayer(xSize, ySize, 32, 32);
            layer.setName("layer" + mapLayers.size());
            tiledMap.getLayers().add(layer);

        }
// make bit mask map
        MaskMode maskMode= terrainSet.getMaskMode();

        int [] [] bitNumberMap=mask.makeTrimmedHeightTileBitMap(tileNumbers, tileNumberMap, maskMode); // make trimmed bit map

        for (int countx = 0; countx < xSize; countx++) {
            for (int county = 0; county < ySize; county++) {
                for (int count = 0; count < numberOfSoilLayers; count++) {

                    if (tileNumberMap[countx][county] == tileNumbers.get(count)) {


                        TileSet tileSet= terrainSet.getTileSets().get(count);
                        String atlasName=tileSet.getAtlasName();


                        Cell cell = new Cell();
                        AtlasNamedAtlasRegion region = assetts.getAtlasRegionByName(getTileImageName(tileSet, bitNumberMap[countx][county], count), atlasName);
                        cell.setTile(  new AtlasStaticTiledMapTile(region));
                        TiledMapTileLayer  layer = (TiledMapTileLayer) tiledMap.getLayers().get(count );
                        layer.setCell(countx, ySize-county, cell);
                        if(bitNumberMap[countx][county]!=255  && count>0) { // make the layer under the same as layer above
                            cornerCheck( tileSet, tileNumberMap, countx, county, count, 1);
                        }
                    }


                }
            }
        }
    }

    // returns a tile image for bit mask number and a set  number  from a tile set
    private String getTileImageName(TileSet tileSet, int bitMaskNumber, int setNumber) {
            ObjectMap<Integer, Array<NumberedTile>> regionNames=tileSet.getSetRegionNames();
            Array<NumberedTile>  bitRegions= regionNames.get(bitMaskNumber);
            int random= RandomNumbers.getRandomNumber(0, bitRegions.size);
            return  bitRegions.get(random).getRegionName();


    }

    private void cornerCheck(  TileSet tileSet, int[] [] tileNumberMap , int countx, int county, int count, int number){
        TiledMapTileLayer  layer = (TiledMapTileLayer) tiledMap.getLayers().get(count - number);
        String atlasName=tileSet.getAtlasName();
        Cell cell = new Cell();
        int bitValue= bitMask.eightSideBitMapCalculator(tileNumberMap[countx][county]-number, countx, county, tileNumberMap);
        AtlasNamedAtlasRegion region;
        if(number>=count){
            region = assetts.getAtlasRegionByName(getTileImageName(tileSet,bitValue, count ), atlasName);
            cell.setTile(new AtlasStaticTiledMapTile(region));
            layer.setCell(countx, ySize - county, cell);
            return;
        }
        if (bitValue!=255 ) {
            region = assetts.getAtlasRegionByName(getTileImageName(tileSet,bitValue, count ), atlasName);
            cell.setTile(new AtlasStaticTiledMapTile(region));
            layer.setCell(countx, ySize - county, cell);
        }
        else {
            region = assetts.getAtlasRegionByName(getTileImageName(tileSet,255, count ), atlasName);
            cell.setTile(new AtlasStaticTiledMapTile(region));
            layer.setCell(countx, ySize - county, cell);
            return;
        }
        if(number<count) {
            number++;
            cornerCheck(tileSet,tileNumberMap, countx, county, count, number);
        }
    }

}

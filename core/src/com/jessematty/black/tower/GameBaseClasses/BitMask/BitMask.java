package com.jessematty.black.tower.GameBaseClasses.BitMask;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.CompareMode;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.TiledMapTileChangable;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.util.ArrayList;

public class BitMask {
    int [] [] bitValues = new int [3][3];
    int [] [] fourBitValues = new int [3][3];
    int  [] [] bitValuesTile2= new int [3][3];
    int [] eightBitMaskValues = new int [16];
ArrayList<Integer> trimValues  = new ArrayList <Integer>();
    public BitMask(){
        trimValues.add(0);
        trimValues.add(1);
        trimValues.add(2);
        trimValues.add(4);
        trimValues.add(7);
        trimValues.add(3);
        trimValues.add(6);
        trimValues.add(8);
        trimValues.add(16);
        trimValues.add(32);
        trimValues.add(64);
        trimValues.add(128);
        trimValues.add(224);
        trimValues.add(7);
        trimValues.add(41);
        trimValues.add(148);
        bitValues[0][0]=1;
        bitValues[1][0]=2;
        bitValues[2][0]=4;
        bitValues[0][1]=8;
        bitValues[2][1]=16;
        bitValues[0][2]=32;
        bitValues[1][2]=64;
        bitValues[2][2]=128;
        bitValuesTile2[0][0]=512;
        bitValuesTile2[1][0]=1024;
        bitValuesTile2[2][0]=2048;
        bitValuesTile2[0][01]=4096;
        bitValuesTile2[1][1]=256;
        bitValuesTile2[2][1]=8192;
        bitValuesTile2[0][2]=16384;
        bitValuesTile2[1][2]=32768;
        bitValuesTile2[2][2]=65536;
        fourBitValues[0][0]=0;
        fourBitValues[1][0]=1;
        fourBitValues[2][0]=0;
        fourBitValues[0][1]=2;
        fourBitValues[2][1]=4;
        fourBitValues[0][2]=0;
        fourBitValues[1][2]=8;
        fourBitValues[2][2]=0;
        eightBitMaskValues[0]=0;
        eightBitMaskValues[1]=11;
        eightBitMaskValues[2]=22;
        eightBitMaskValues[3]=31;
        eightBitMaskValues[4]=104;
        eightBitMaskValues[5]=107;
        eightBitMaskValues[6]=126;
        eightBitMaskValues[7]=127;
        eightBitMaskValues[8]=208;
        eightBitMaskValues[9]=214;
        eightBitMaskValues[10]=219;
        eightBitMaskValues[11]=223;
        eightBitMaskValues[12]=248;
        eightBitMaskValues[13]=251;
        eightBitMaskValues[14]=254;
        eightBitMaskValues[15]=255;
    }
    public int [] [] makeTrimmedHeightTileBitMap(ArrayList<Integer> numbers , int [] [] map) { // makes a bit map  with tiles containing only the 16 bit
        // mask values listed in the eight bit values int array.
        int xSize=map.length;
        int ySize=map[0].length;
      int [] [] bitNumberMap = new int [xSize][ySize];
        boolean trimmed = true;
        while (trimmed == true) { // if  tile has been trimmed  keep masking.
            boolean trimmedSomthing = false;
            for (int countx = 0; countx < xSize; countx++) {
                for (int county = 0; county < ySize; county++) {
                    int size2 = numbers.size();
                    for (int count = 0; count < size2; count++) {
                        if (map[countx][county] == numbers.get(count)) {
                            int bit=eightSideBitMapCalculator(numbers.get(count), countx, county, map); //get mask value
                            boolean trimmedTile = trimTile(bit); // if tiles bit value not in the list it was trimmed
                            if (trimmedTile == true) {
                                map[countx][county]--; // make layer number lower  as tile has been cut out .
                                if (trimmedSomthing == false) {
                                    trimmedSomthing = trimmedTile;
                                }
                                continue;
                            }
                            bitNumberMap[countx][county] = bit;
                            continue;
                        }
                    }
                }
            }
            trimmed = trimmedSomthing;
        }
        return bitNumberMap;
    }
    public int [] [] makeTrimmedTileBitMap(ArrayList<Integer> numbers , int [] [] map) { // makes a bit map  with tiles containing only the 16 bit
        // mask values listed in the eight bit values int array.
        int xSize=map.length;
        int ySize=map[0].length;
        int [] [] bitNumberMap = new int [xSize][ySize];
        boolean trimmed = true;
        while (trimmed == true) { // if  tile has been trimmed  keep masking.
            boolean trimmedSomthing = false;
            for (int countx = 0; countx < xSize; countx++) {
                for (int county = 0; county < ySize; county++) {
                    int size2 = numbers.size();
                    for (int count = 0; count < size2; count++) {
                        if (map[countx][county] == numbers.get(count)) {
                            int bit=eightSideBitMapEqualCalculator(numbers.get(count), countx, county, map); //get mask value
                            boolean trimmedIt = trimTile(bit); // if tiles bit value not in the list it was trimmed
                            if (trimmedIt == true) {
                                map[countx][county]--; // make map number lower  as tile has been cut out .
                                if (trimmedSomthing == false) {
                                    trimmedSomthing = trimmedIt;
                                }
                                continue;
                            }
                            bitNumberMap[countx][county] = bit;
                            continue;
                        }
                    }
                }
            }
            trimmed = trimmedSomthing;
        }
        return bitNumberMap;
    }
    private   int fiveBitConverter(boolean [][]  map, int centerBit ){ // converts  WoodWand array of boolean s for adjecent tiles on whether or not  they match the center tile  and then  retuns the value
        int bitMapValue = centerBit;                    // then returns the value
        for (int countx = 0; countx < 3; countx++) {
            for (int county = 0; county < 3; county++) {
                if ( (countx==1 && county==1)) {
                    continue;
                }
                if (map[countx][county]==true) {
                    if (county==0 && countx==0 && map[0][1]==true && map[1][0]==true) {
                        bitMapValue = bitMapValue + this.fourBitValues[countx][county];
                        continue;
                    }
                    else if(county==0 && countx==0 ) {
                        continue;
                    }
                    else if (county==0 && countx==2 && map[2][1]==true && map[1][0]==true) {
                        bitMapValue = bitMapValue + this.fourBitValues[countx][county];
                        continue;
                    }
                    else if(county==0 && countx==2 ) {
                        continue;
                    }
                    else if (county==2 && countx==0 && map[0][1]==true && map[1][2]==true) {
                        bitMapValue = bitMapValue + this.fourBitValues[countx][county];
                        continue;
                    }
                    else if(county==2 && countx==0 ) {
                        continue;
                    }
                    else if (county==2 && countx==2 && map[2][1]==true && map[1][2]==true) {
                        bitMapValue = bitMapValue + this.fourBitValues[countx][county];
                        continue;
                    }
                    else if(county==2 && countx==2 ) {
                        continue;
                    }
                    bitMapValue = bitMapValue + this.fourBitValues[countx][county];
                }
            }
        }
        return bitMapValue;
    }
    public int fiveBitMapCalculator(int terrianNumber,  boolean type1,  int locationX, int locationY, int [] [] map) {
        int centerBit=0;
        if(type1==true) {
            centerBit=16;
        }
        boolean [] [] bitMap = new boolean [3] [3];
        for (int countx = -1; countx < 2; countx++) {
            for (int county = -1; county < 2; county++) {
                int x=countx+locationX;
                int y=county+locationY;
                if(x<0){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                if (x>map.length-1){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                if(y<0){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                if (y>map[0].length-1){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                int terrianNumber2= map[x][y];
                if (terrianNumber == terrianNumber2) {
                    bitMap[countx+1][county+1]=true;
                }
            }
        }
        return fiveBitConverter(bitMap,centerBit);
    }
    private boolean trimTile(int maskNumber) { // determines if tile has a bit number that corresponds to  one of the 16 various 8 bit  bitmasking values
        for(int count=0; count<16; count++){
            if(maskNumber==eightBitMaskValues[count]){
                return false;
            }
        }
        return true;
    }

    public int fourSideBitMapCalculator( LandSquareTile tile,  Entity entity, GameMap map ){ //calculates  the  bitmap value for tile based on wether or  the 4  compass directions are the same as it
        int bitMapValue = 0;                    // then returns the value
        int bitValues=0;
        for (int countx = -1; countx < 2; countx++) {
            for (int county = -1; county < 2; county++) {
                if (!(countx != 0 && county != 0) || (countx==0 && county==0)) {
                    continue;
                }
                if(bitValues==0) {
                    bitValues=1;
                }
                else {
                    bitValues=bitValues*2;
                }
                int x=countx+tile.getLocationX();
                int y=county+tile.getLocationY();
                LandSquareTile tile2 = map.getMapSquareOrNull(x, y);
                    if ( tile2==null || tile2.hasEntity(entity)) {
                        bitMapValue = bitMapValue + bitValues;
                    }
                bitValues=bitValues*2;
            }
        }
        return bitMapValue;
    }

    public int eightSideBitMapCalculator( LandSquareTile tile, Entity entity,  GameMap map){  // does the same thing  as the previous function but for all eight directions
        boolean [] [] bitMap = new boolean [3] [3];
        for (int countx = -1; countx < 2; countx++) {
            for (int county = -1; county < 2; county++) {
                int x=countx+tile.getLocationX();
                int y=county+tile.getLocationY();
                LandSquareTile tile2 = map.getMapSquareOrNull(x, y);
                if (tile2==null|| tile2.hasEntity(entity)) {
                    bitMap[countx+1][county+1]=true;
                }
            }
        }
        int bitMapValue=eightBitConverter(bitMap);
        return bitMapValue;
    }



    private int eightSideBitMapCalculator( int number,  int[][] map, int startX, int startY ){  // does the same thing  as the previous function but for all eight directions
        int xSize=map.length;
        int ySize=map[0].length;
        boolean [] [] bitMap = new boolean [3] [3];
        for (int countx = -1; countx < 2; countx++) {
            for (int county = -1; county < 2; county++) {
                int x=countx+startX;
                int y=county+startY;
                if (y>=0 && y<ySize && x>=0 && x<xSize) {
                   int number2 = map[x][ y];
                    if (number2 == number) {
                        bitMap[countx+1][county+1]=true;
                    }
                }
            }
        }
        int bitMapValue=eightBitConverter(bitMap);
        return bitMapValue;
    }
    public int sixteenSideBitMapCalculator( LandSquareTile tile, LandSquareTile tile2,  LandSquareTile [] [] map ){  // does the same thing  as the previous function but for all eight directions
        int [] [] bitMap = new int [3] [3];
        for (int county = -1; county < 2; county++) {
            for (int countx = -1; countx < 2; countx++) {
                int x=countx+tile.getLocationX();
                int y=county+tile.getLocationY();
                    LandSquareTile tile3 = map[x][y];
                    if (tile3.getClass() == tile.getClass()) {
                        bitMap[countx+1][county+1]=1;
                    }
                    else if (tile3.getClass() == tile2.getClass()) {
                        bitMap[countx+1][county+1]=2;
                    }
                }
        }
        int bitMapValue=sixteenBitConverter(bitMap);
        return bitMapValue;
    }
    private   int fiveBitConverter( int centerBit, boolean [][]  map ){ // converts  WoodWand array of boolean s for adjecent tiles on whether or not  they match the center tile  and then  retuns the value
        int bitMapValue = centerBit;                    // then returns the value
        for (int countx = 0; countx < 3; countx++) {
            for (int county = 0; county < 3; county++) {
                if ( (countx==1 && county==1)) {
                    continue;
                }
                if (map[countx][county]==true) {
                    if (county==0 && countx==0 && map[0][1]==true && map[1][0]==true) {
                        bitMapValue = bitMapValue + this.bitValues[countx][county];
                        continue;
                    }
                    else if(county==0 && countx==0 ) {
                        continue;
                    }
                    else if (county==0 && countx==2 && map[2][1]==true && map[1][0]==true) {
                        bitMapValue = bitMapValue + this.bitValues[countx][county];
                        continue;
                    }
                    else if(county==0 && countx==2 ) {
                        continue;
                    }
                    else if (county==2 && countx==0 && map[0][1]==true && map[1][2]==true) {
                        bitMapValue = bitMapValue + this.bitValues[countx][county];
                        continue;
                    }
                    else if(county==2 && countx==0 ) {
                        continue;
                    }
                    else if (county==2 && countx==2 && map[2][1]==true && map[1][2]==true) {
                        bitMapValue = bitMapValue + this.bitValues[countx][county];
                        continue;
                    }
                    else if(county==2 && countx==2 ) {
                        continue;
                    }
                    bitMapValue = bitMapValue + this.bitValues[countx][county];
                }
            }
        }
        return bitMapValue;
    }
    public int eightSideBitMapCalculator(LandSquareTile tile, GameMap map ,  Class< ? extends Component>... components){ // does the same as pervious function but for all eight sides
        boolean [] [] bitMap = new boolean [3] [3];
        for (int countx = -1; countx < 2; countx++) {
            for (int county = -1; county < 2; county++) {
                int x=countx+tile.getLocationX();
                int y=county+tile.getLocationY();
                // if the bit maskable adjoins non displayable tiles (out of bounds)  they are assumed to have the bitsmaskable on them
                if(x<0){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                if (x>map.getXSize()-1){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                if(y<0){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                if (y>map.getYSize()-1){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                LandSquareTile tile2 = map.getMapSquare(x, y);
                  Array<Entity> things = tile2.getEntities(components);
                  int size=things.size;
                    if (size>0) {
                        bitMap[countx+1][county+1]=true;
                    }
                }
        }
        int bitMapValue=eightBitConverter(bitMap);
        return bitMapValue;
    }
    public int eightSideBitMapCalculator(LandSquareTile tile, BooleanStat  flag,  boolean flagState , LandSquareTile [] [] map ){
        // calculates  an eight bit bit number for bit masking based on whether or not a tile has a certain component
        boolean [] [] bitMap = new boolean [3] [3];
        for (int countx = -1; countx < 2; countx++) {
            for (int county = -1; county < 2; county++) {
                int x = countx + tile.getLocationX();
                int y = county + tile.getLocationY();
                if (x < 0) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                if (x > map.length - 1) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                if (y < 0) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                if (y > map[0].length - 1) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                LandSquareTile tile2 = map[x][y];
                BooleanStat stat = tile2.getComponent(BooleanStats.class).getBooleanStat(flag.getName());
                if (stat != null) {
                    boolean flagSet = stat.getFlag();
                    if (flagSet == flagState) {
                        bitMap[countx + 1][county + 1] = true;
                    }
                }
            }
        }
        int bitMapValue=eightBitConverter(bitMap);
        return bitMapValue;
    }


    public int eightSideBitMapCalculator(int cellX, int cellY ,  TextureRegion  textureRegion,  TiledMapTileLayer layer, int xSize, int ySize ){
        // calculates  an eight bit bit number for bit masking based on whether or not a tiled map cell  has tile with given texture region
        boolean [] [] bitMap = new boolean [3] [3];
        for (int countx = -1; countx < 2; countx++) {
            for (int county = -1; county < 2; county++) {
                int x = countx + cellX;
                int y = county + cellY;
                if (x < 0) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                if (x > xSize - 1) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                if (y < 0) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                if (y > ySize - 1) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                Cell cell2 = layer.getCell(x, y);
                TextureRegion region2 = cell2.getTile().getTextureRegion();
                if (region2 != null) {
                    if (textureRegion == region2) {
                        bitMap[countx + 1][county + 1] = true;
                    }
                }
            }
        }
        int bitMapValue=eightBitConverter(bitMap);
        return bitMapValue;
    }
    public int eightSideBitMapCalculator(LandSquareTile tile, NumericStat stat, LandSquareTile [] [] map, CompareMode compareMode){
        // does the same as previous  function  but compares a numeric stat to a given tile  for generating the bit mask number;
        if(compareMode==null){
            compareMode=CompareMode.EQUALS;
        }
        boolean [] [] bitMap = new boolean [3] [3];
        for (int countx = -1; countx < 2; countx++) {
            for (int county = -1; county < 2; county++) {
                int x = countx + tile.getLocationX();
                int y = county + tile.getLocationY();
                if (x < 0) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                if (x > map.length - 1) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                if (y < 0) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                if (y > map[0].length - 1) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                LandSquareTile tile2 = map[x][y];
                NumericStat stat2 = tile2.getComponent(NumericStats.class).getNumericStat(stat.getName());
                if (stat2 != null) {
                    double terrianNumber2 = stat2.getDoubleValue();
                    double terrianNumber = stat.getDoubleValue();
                    switch (compareMode) {


                        case GREATER_THAN: {
                            if (terrianNumber > terrianNumber2) {
                                bitMap[countx + 1][county + 1] = true;
                            }
                            break;
                        }

                        case LESS_THAN: {
                            if (terrianNumber < terrianNumber2) {
                                bitMap[countx + 1][county + 1] = true;
                            }
                            break;
                        }

                        case EQUALS: {
                            if (terrianNumber == terrianNumber2) {
                                bitMap[countx + 1][county + 1] = true;
                            }
                            break;
                        }

                        case NOT_EQUAL_TO: {
                            if (terrianNumber != terrianNumber2) {
                                bitMap[countx + 1][county + 1] = true;
                            }
                        }

                        break;


                    }



                }
            }
        }
        int bitMapValue=eightBitConverter(bitMap);
        return bitMapValue;
    }

    public int eightSideBitMapCalculator(LandSquareTile tile, StringStat stat, LandSquareTile [] [] map ){ // does the same as pervious function but for all eight sides and with a string stat
        boolean [] [] bitMap = new boolean [3] [3];
        for (int countx = -1; countx < 2; countx++) {
            for (int county = -1; county < 2; county++) {
                int x = countx + tile.getLocationX();
                int y = county + tile.getLocationY();
                if (x < 0) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                if (x > map.length - 1) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                if (y < 0) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                if (y > map[0].length - 1) {
                    bitMap[countx + 1][county + 1] = true;
                    continue;
                }
                LandSquareTile tile2 = map[x][y];
                StringStat stat2 = tile2.getComponent(StringStats.class).getStringStat(stat.getName());
                if (stat2 != null) {
                    String text2 = stat2.getText();
                    String text= stat.getText();
                    if (text.equalsIgnoreCase(text2)) {
                        bitMap[countx + 1][county + 1] = true;
                    }
                }
            }
        }
        int bitMapValue=eightBitConverter(bitMap);
        return bitMapValue;
    }
    public int eightSideBitMapCalculator( int terrianNumber, int locationX, int locationY,  int [] [] map ){ // does the same as previous function but for all eight sides and with a tile boolean flag rather than an Item
        boolean [] [] bitMap = new boolean [3] [3];
        for (int countx = -1; countx < 2; countx++) {
            for (int county = -1; county < 2; county++) {
                int x=countx+locationX;
                int y=county+locationY;
                if(x<0){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                if (x>map.length-1){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                if(y<0){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                if (y>map[0].length-1){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                int terrianNumber2= map[x][y];
                if (terrianNumber <=terrianNumber2) {
                    bitMap[countx+1][county+1]=true;
                }
            }
        }
        return eightBitConverter(bitMap);
    }
    public int eightSideBitMapEqualCalculator( int terrianNumber, int locationX, int locationY,  int [] [] map ){ // does the same as previous function but for all eight sides and with a tile boolean flag rather than an Item
        boolean [] [] bitMap = new boolean [3] [3];
        for (int countx = -1; countx < 2; countx++) {
            for (int county = -1; county < 2; county++) {
                int x=countx+locationX;
                int y=county+locationY;
                if(x<0){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                if (x>map.length-1){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                if(y<0){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                if (y>map[0].length-1){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                int terrianNumber2= map[x][y];
                if (terrianNumber ==terrianNumber2) {
                    bitMap[countx+1][county+1]=true;
                }
            }
        }
        return eightBitConverter(bitMap);
    }
    private   int eightBitConverter(boolean [][]  map ){ // converts   a array of boolean s for adjecent tiles on whether or not  they match the center tile  and then  retuns the value
        int bitMapValue = 0;                    // then returns the value
        for (int countx = 0; countx < 3; countx++) {
            for (int county = 0; county < 3; county++) {
                if ( (countx==1 && county==1)) {
                    continue;
                }
                if (map[countx][county]==true) {
                    if (county==0 && countx==0 && map[0][1]==true && map[1][0]==true) {
                        bitMapValue = bitMapValue + this.bitValues[countx][county];
                        continue;
                    }
                    else if(county==0 && countx==0 ) {
                        continue;
                    }
                    else if (county==0 && countx==2 && map[2][1]==true && map[1][0]==true) {
                        bitMapValue = bitMapValue + this.bitValues[countx][county];
                        continue;
                    }
                    else if(county==0 && countx==2 ) {
                        continue;
                    }
                    else if (county==2 && countx==0 && map[0][1]==true && map[1][2]==true) {
                        bitMapValue = bitMapValue + this.bitValues[countx][county];
                        continue;
                    }
                    else if(county==2 && countx==0 ) {
                        continue;
                    }
                    else if (county==2 && countx==2 && map[2][1]==true && map[1][2]==true) {
                        bitMapValue = bitMapValue + this.bitValues[countx][county];
                        continue;
                    }
                    else if(county==2 && countx==2 ) {
                        continue;
                    }
                    bitMapValue = bitMapValue + this.bitValues[countx][county];
                }
            }
        }
        return bitMapValue;
    }
    public int eightSideBitMapCalculator(TiledMapTileChangable tile, TextureRegion region, TiledMap map ){ // does the smae as pervious function but for all eight sides
        if (region==null){
            return 0;
        }
        MapProperties prop = map.getProperties();
        int layer=tile.getLayer();
        int xSize = prop.get("weaponWidth", Integer.class);
        int ySize = prop.get("height", Integer.class);
        MapLayers layers=map.getLayers();
        TiledMapTileLayer tileLayer= (TiledMapTileLayer) layers.get(layer);
        boolean [] [] bitMap = new boolean [3] [3];
        for (int countx = -1; countx < 2; countx++) {
            for (int county = -1; county < 2; county++) {
                int x=countx+tile.getLocationX();
                int y=county+tile.getLocationY();
                if (y>=0 && y<ySize && x>=0 && x<xSize) {
                    Cell cell=tileLayer.getCell(x, y);
                    TextureRegion region2 =cell.getTile().getTextureRegion();
                    if (region.equals(region2)) {
                        bitMap[countx+1][county+1]=true;
                    }
                }
            }
        }
        int bitMapValue=eightBitConverter(bitMap);
        return bitMapValue;
    }
    public int fullEightSideBitMapCalculator( LandSquareTile tile,   LandSquareTile [] [] map, Class< ? extends Component>... components ){ // does the smae as pervious function but for all eight sides
        boolean [] [] bitMap = new boolean [3] [3];
        for (int countx = -1; countx < 2; countx++) {
            for (int county = -1; county < 2; county++) {
                int x=countx+tile.getLocationX();
                int y=county+tile.getLocationY();
                if(x<0){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                if (x>map.length-1){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                if(y<0){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                if (y>map[0].length-1){
                    bitMap[countx+1][county+1]=true;
                    continue;
                }
                    LandSquareTile tile2 = map[x][y];
                   Array<Entity> things = tile.getEntities(components);
                    if (things.size>0) {
                        bitMap[countx+1][county+1]=true;
                    }
                }
        }
        int bitMapValue=fullEightBitConverter(bitMap);
        return bitMapValue;
    }
    private  int sixteenBitConverter(int [][]  map ){ // converts  WoodWand array of boolean s for adjecent tiles on whether or not  they match the center tile  and then  retuns the value
        int bitMapValue = 0;                    // then returns the value
        for (int countx = 0; countx < 3; countx++) {
            for (int county = 0; county < 3; county++) {
                if (map[countx][county]==1) {
                    if (county==0 && countx==0 && map[0][1]==1 && map[1][0]==1) {
                        bitMapValue = bitMapValue + this.bitValues[countx][county];
                        continue;
                    }
                    else if(county==0 && countx==0 ) {
                        continue;
                    }
                    else if (county==0 && countx==2 && map[2][1]==1 && map[1][0]==1) {
                        bitMapValue = bitMapValue + this.bitValues[countx][county];
                        continue;
                    }
                    else if(county==0 && countx==2 ) {
                        continue;
                    }
                    else if (county==2 && countx==0 && map[0][1]==1 && map[1][2]==1) {
                        bitMapValue = bitMapValue + this.bitValues[countx][county];
                        continue;
                    }
                    else if(county==2 && countx==0 ) {
                        continue;
                    }
                    else if (county==2 && countx==2 && map[2][1]==1 && map[1][2]==1) {
                        bitMapValue = bitMapValue + this.bitValues[countx][county];
                        continue;
                    }
                    else if(county==2 && countx==2 ) {
                        continue;
                    }
                        bitMapValue = bitMapValue + this.bitValues[countx][county];
                    continue;
                    }
                   else  if (map [countx][county]==2){
                    if (county==0 && countx==0 && map[0][1]==2 && map[1][0]==2) {
                        bitMapValue = bitMapValue + this.bitValuesTile2[countx][county];
                        continue;
                    }
                    else if(county==0 && countx==0 ) {
                        bitMapValue = bitMapValue + this.bitValuesTile2[countx][county];
                    }
                    else if (county==0 && countx==2 && map[2][1]==2 && map[1][0]==2) {
                        bitMapValue = bitMapValue + this.bitValuesTile2[countx][county];
                        continue;
                    }
                    else if(county==0 && countx==2 ) {
                        continue;
                    }
                    else if (county==2 && countx==0 && map[0][1]==2 && map[1][2]==2) {
                        bitMapValue = bitMapValue + this.bitValuesTile2[countx][county];
                        continue;
                    }
                    else if(county==2 && countx==0 ) {
                        continue;
                    }
                    else if (county==2 && countx==2 && map[2][1]==2 && map[1][2]==2) {
                        bitMapValue = bitMapValue + this.bitValuesTile2[countx][county];
                        continue;
                    }
                    else if(county==2 && countx==2 ) {
                        continue;
                    }
                        bitMapValue = bitMapValue + this.bitValuesTile2[countx][county];
                    }
                }
        }
        return bitMapValue;
    }
    private  int fullEightBitConverter(boolean [][] map ) { // converts  WoodWand array of boolean s for adjecent tiles on whether or not  they match the center tile  and then  retuns the value
        int bitMapValue = 0;                    // then returns the value
        int bitValues = 0;
        for (int countx = 0; countx < 3; countx++) {
            for (int county = 0; county < 3; county++) {
                if ((countx == 1 && county == 1)) {
                    continue;
                }
                if (bitValues == 0) {
                    bitValues = 1;
                } else {
                    bitValues = bitValues * 2;
                }
                if (map[countx][county] == true) {
                    bitMapValue = bitMapValue + bitValues;
                    continue;
                }
                else {
                    continue;
                }
            }
        }
        return bitMapValue;
        }
    public void trimMap(int[][] map, int number) {
int xSize=map.length;
int ySize=map[0].length;
            for (int countx=0; countx<xSize;  countx++){
                for (int county=0; county<ySize; county++){
                    boolean in=false;
                    int size=trimValues.size();
                    for (int count=0; count<size; count++) {
                        int bitValue=eightSideBitMapCalculator(number,map, countx, county);
                        if (trimValues.get(count).equals(bitValue)) {
                            map[countx][county]--;
                        }
                    }
                }
            }
        }
    public int[] getEightBitMaskValues() {
        return eightBitMaskValues;
    }



}

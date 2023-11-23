package com.jessematty.black.tower.GameBaseClasses.Calculators;

import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class LargestAreaCalculator {
     private static int area=0;
    private static int largestArea;
   private static  int targetSize;
    private static ArrayList<ArrayList <LandSquareTile>> areaTiles= new ArrayList< ArrayList<LandSquareTile>>();
    private  static Comparator <ArrayList<LandSquareTile>> compare= new Comparator<ArrayList<LandSquareTile>>() {
    @Override
    public int compare(ArrayList<LandSquareTile> landSquareTiles, ArrayList<LandSquareTile> t1) {
        int size=landSquareTiles.size();
        int size2=t1.size();
        return size-size2;
    }
};
       public static  ArrayList<ArrayList<LandSquareTile>> largestArea(LandSquareTile[][] map, LandSquareTile tile, int targetSize) {
            int xSize=map.length;
            int ySize=map[0].length;
            for (int countx = 0; countx < xSize; countx++) {
                for (int county = 0; county < ySize; county++) {
                    LandSquareTile tileToCheck  = map[countx][county];
                    if (tileToCheck.getClass() != tile.getClass()){
                        continue;
                    }
                    ArrayList<LandSquareTile> landSquareTiles;
                    ArrayList<LandSquareTile> closedLandSquareTiles= new ArrayList<LandSquareTile>();
                    landSquareTiles= findTiles(tileToCheck,  countx, county, map);
                    landSquareTiles.add(map[countx][county]);
                    closedLandSquareTiles.addAll(landSquareTiles);
                    for (int count=0; count<landSquareTiles.size(); count++){
                        int x=landSquareTiles.get(count).getLocationX();
                        int y=landSquareTiles.get(count).getLocationY();
                        ArrayList<LandSquareTile> LandSquareTiles2= findTiles(tileToCheck, x, y, map);
                        for (int count2=0; count2<LandSquareTiles2.size(); count2++) {
                            boolean closed=false;
                            for (int count3=0; count3<closedLandSquareTiles.size(); count3++) {
                                if (LandSquareTiles2.get(count2).getLocationX()==closedLandSquareTiles.get(count3).getLocationX()   && LandSquareTiles2.get(count2).getLocationY()==closedLandSquareTiles.get(count3).getLocationY()){
                                    closed=true;
                                }
                            }
                            if (closed==false) {
                                landSquareTiles.add(LandSquareTiles2.get(count2));
                                closedLandSquareTiles.add(LandSquareTiles2.get(count2));
                            }
                        }
                        area=landSquareTiles.size();
                    }
                        areaTiles.add(landSquareTiles);
                    area=0;
                }
            }
            Collections.sort(areaTiles, compare);
    return areaTiles;
        }
        private static  ArrayList<LandSquareTile> findTiles(LandSquareTile tile, int numberx, int numbery, LandSquareTile[][] map ){
            int xSize=map.length;
            int ySize=map[0].length;
            ArrayList<LandSquareTile> LandSquareTiles=new ArrayList<LandSquareTile>();
            for (int county = -1; county < 2; county++) {
                for (int countx = -1; countx < 2; countx++) {
                    if (countx==0 && county==0) {
                        continue;
                    }
                    int x=countx+numberx;
                    int y=county+numbery;
                    if (x!=numberx && y!=numbery){
                        continue;
                    }
                    if (y>=0 && y<ySize && x>=0 && x<xSize) {
                        LandSquareTile tile2 = map[x][ y];
                        if (tile2.getClass() == tile.getClass()) {
                            LandSquareTiles.add(tile2);
                        }
                    }
                }
            }
            return LandSquareTiles;
        }
    private static  ArrayList<LandSquareTile>  findTilesSquare(LandSquareTile tile,  LandSquareTile[][] map ){
        int xSize=map.length;
        int ySize=map[0].length;
        int numberx=tile.getLocationX();
        int numbery=tile.getLocationY();
        int [] []  counterMap= new int [xSize+1][ySize+1];
        int largestNumber=0;
        int largestX=0;
        int largestY=0;
        ArrayList<LandSquareTile> landSquareTiles=new ArrayList<LandSquareTile>();
        for (int countx = 1; countx < xSize; countx++) {
            for (int county =1; county <ySize; county++) {
                if (map[countx][county].getClass() == tile.getClass()) {
                    int smallest = Math.min(counterMap[countx-1][county], Math.min(counterMap[countx-1][county-1], counterMap[countx][county-1]));
                    counterMap[countx][county]=1+smallest;
                    if (counterMap[countx][county]>largestNumber) {
                        largestNumber=counterMap[countx][county];
                        largestX=countx;
                        largestY=county;
                    }
                }
            }
        }
        for (int countx = largestX; countx > largestX-largestNumber; countx--) {
            for (int county = largestY; county >largestY-largestNumber; county--) {
                landSquareTiles.add(map[countx][county]);
            }
        }
        return landSquareTiles;
    }
}

package com.jessematty.black.tower.Generators.MapGenerators;

import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;

import java.util.ArrayList;

public class HeightMapGen {


    RandomNumbers value=new RandomNumbers();
    int xSize;
    int ySize;
    ArrayList<Integer> heightNumbers = new ArrayList<Integer>();
    int averageHeightNumber;



    public HeightMapGen(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public void setMapSize(int xSize, int ySize){ // makes a 2d array of  ints that resemble geographical height landSquareTileMap
        this.xSize=xSize;
        this.ySize=ySize;
    }




    public int[][] makeSoilFertilityMap(int maxFert, int minFert, int smoothness){ // makes 2d array of ints that resembles WoodWand rain landSquareTileMap
        int[][] soilMap= new int[xSize][ySize];
        int x;
        int y;
        int a;
        int b;









        for (int countx=0; countx<xSize;  countx++){
            for (int county=0; county<ySize; county++){
                soilMap[countx][county]=value.getRandomNumber(minFert, maxFert);

            }
        }

        for (int countx=0; countx<xSize;  countx++) {
            for (int county = 0; county < ySize; county++) {

            }
        }








        for(int smooth=0; smooth<smoothness; smooth++){
            for (int countx=0; countx<xSize;  countx++){
                for (int county=0; county<ySize; county++){
                    x=countx-1;
                    y=county-1;
                    a=countx+1;
                    b=county+1;
                    if (x<0){
                        x=0;
                    }
                    if (y<0){
                        y=0;
                    }
                    if (a>=xSize){
                        a=xSize-1;
                    }
                    if (b>=ySize){
                        b=ySize-1;
                    }

                    soilMap[countx][county]=((soilMap[a][county]+soilMap[x][county]+soilMap[a][b]+soilMap[a][y]+soilMap[countx][b]+soilMap[countx][y]+soilMap[x][y]+soilMap[x][b])/8);

                }
            }
        }



        for (int countx=0; countx<xSize;  countx++){
            for (int county=0; county<ySize; county++){


                boolean inSoil= numberCheck(soilMap[countx][county]);
                if(inSoil=false){
                    heightNumbers.add(soilMap[countx][county]);


                }






            }

        }



        return soilMap;

    }



    private boolean numberCheck(int number){
        int size= heightNumbers.size();
        for (int count=0; count<size; count++){

            if(heightNumbers.get(count)==number){

                return true;

            }
        }


        return false;

    }





    public int[][] makeHeightMap(int maxNumber, int minNumber, int smoothness){ // makes 2d array of ints that resembles a height map and adds all the unique numbers in the map to an array list
        int[][] heightMap= new int[xSize][ySize];
        int x;
        int y;
        int a;
        int b;









        for (int countx=0; countx<xSize;  countx++){
            for (int county=0; county<ySize; county++){
                heightMap[countx][county]=value.getRandomNumber(minNumber, maxNumber);

            }
        }










        for(int smooth=0; smooth<smoothness; smooth++){
            for (int countx=0; countx<xSize;  countx++){
                for (int county=0; county<ySize; county++){
                    x=countx-1;
                    y=county-1;
                    a=countx+1;
                    b=county+1;
                    if (x<0){
                        x=0;
                    }
                    if (y<0){
                        y=0;
                    }
                    if (a>=xSize){
                        a=xSize-1;
                    }
                    if (b>=ySize){
                        b=ySize-1;
                    }

                    heightMap[countx][county]=((heightMap[a][county]+heightMap[x][county]+heightMap[a][b]+heightMap[a][y]+heightMap[countx][b]+heightMap[countx][y]+heightMap[x][y]+heightMap[x][b])/8);

                }
            }
        }



        int total=0;
        for (int countx=0; countx<xSize;  countx++) {
            for (int county = 0; county < ySize; county++) {


                boolean inMap = numberCheck(heightMap[countx][county]);
                if (inMap == false) {



                        heightNumbers.add(heightMap[countx][county]);
                        total=total+heightMap[countx][county];





                }


            }
        }


        averageHeightNumber=(int)(total/heightNumbers.size());



                return heightMap;

    }


    private int  largestArea(int number,  int [][] map) {
        int xSize = map.length;
        int ySize = map[0].length;
        int[][] counterMap = new int[xSize + 1][ySize + 1];

        int largestNumber = 0;


        for (int countx = 1; countx < xSize; countx++) {
            for (int county = 1; county < ySize; county++) {


                if (map[countx][county] == number) {
                    int smallest = Math.min(counterMap[countx - 1][county], Math.min(counterMap[countx - 1][county - 1], counterMap[countx][county - 1]));


                    counterMap[countx][county] = 1 + smallest;
                    if (counterMap[countx][county] > largestNumber) {
                        largestNumber = counterMap[countx][county];



                    }


                }


            }
        }

        return largestNumber;

    }

    public ArrayList<Integer> getHeightNumbers() {
        return heightNumbers;
    }

    public int getAverageHeightNumber() {
        return averageHeightNumber;
    }
}


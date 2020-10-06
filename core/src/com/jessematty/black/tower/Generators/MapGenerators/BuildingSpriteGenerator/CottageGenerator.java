package com.jessematty.black.tower.Generators.MapGenerators.BuildingSpriteGenerator;


import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Generators.MapGenerators.LandMapGenerator;

public class CottageGenerator extends BuildingSpriteGenerator {

    int roofPitch;// thepitch of the roof
    boolean roofUp; // wether the roof faces up and down or left and right



    public CottageGenerator(GameAssets assetts, LandMapGenerator map) {
        super(assetts, map);
    }

    @Override
    boolean makeBuilding() {

        int mapXSize=mapGenerator.getxSize()-3;
        int mapYSize=mapGenerator.getySize()-3;


        int yRoofSize=innerYSize/2;

        int yFrontSize=stories*3;
        int xFrotSize=innerXSize;
        int xRoofSize=innerXSize;

        int yTotal=yRoofSize+yFrontSize;

        boolean canPlace=false;
        int counter=0;

        while(canPlace==false){
            int locationX=value.getRandomNumber(3, mapXSize);
            int locationY=value.getRandomNumber(3, mapYSize);
            canPlace=mapGenerator.areaCheck(locationX, locationY, yTotal, innerXSize);
            if(canPlace==true){
                this.locationX=locationX;
                this.locationY=locationY;


            }
            counter++;





        }

        int yRoofStart=locationY+(yRoofSize/2);

        for (int county = yRoofStart; county < yRoofSize; county++) {

        for (int countx = locationX; countx < innerXSize+locationX; countx++) {


            }
        }






        return true;

    }
}

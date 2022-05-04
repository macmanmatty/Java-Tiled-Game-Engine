package com.jessematty.black.tower.AI;import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;public class AICode {
RandomNumbers value= new RandomNumbers();
GameMap map;
int xSize;
int ySize;    private LandSquareTile pickASquare(){ // finds a new square for the enemy to floatThing to after he has rested or when he is created
        LandSquareTile newTile=null;
        while(newTile==null){
            int xRandom=value.getRandomNumber(0, xSize-1);
            int yRandom=value.getRandomNumber(0, ySize-1);
            LandSquareTile moveTile=map.getMapSquare(xRandom, yRandom);
            boolean enterable=moveTile.isEnterable();
            if (enterable==true){
                newTile=moveTile;
            }        }        return newTile;
    }
}

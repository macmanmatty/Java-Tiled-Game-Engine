package com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;


public class TextureAtlasRegionNames { // class for holding all of the numbered  region names assoicated with  a texture atlas  IE tile1 tile2 ect.


    private Array<String> textureNames = new Array<String>();


    private ArrayList<Integer> amounts= new ArrayList<Integer>();

    private  String atlasName;
    private String atlasPath;


    public TextureAtlasRegionNames() {


    }

    public TextureAtlasRegionNames( String atlasName) {
        this.atlasName = atlasName;

    }

    public Array<String> getTextureNames() {
        return textureNames;
    }

    public void setTextureNames(Array<String> textureNames) {
        this.textureNames = textureNames;
    }

    public void addAssettName(String assettName){

        textureNames.add(assettName);
    }


    public String getAtlasName() {
        return atlasName;
    }

    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }



    public ArrayList<Integer> getAmounts() {
        return amounts;
    }

    public void setAmounts(ArrayList<Integer> amounts) {
        this.amounts = amounts;
    }


    public void addAmount(int amount){

        amounts.add(amount);

    }

    public int getAmounts(int index){


        return amounts.get(index);

    }


    public String getAtlasPath() {
        return atlasPath;
    }

    public void setAtlasPath(String atlasPath) {
        this.atlasPath = atlasPath;
    }
}

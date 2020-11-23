package com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.GameBaseClasses.Utilities.ColorUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.Generators.Sets.MaskMode;

// class for  a set of bitmaskable terrain tiles
public class TileSet {
    protected boolean colored;
    protected NamedColor color= NamedColor.WHITE;
    protected MaskMode maskMode;
    protected String atlasName;
    protected String name;
    // map of texture regions names array to bit numbers
  protected ObjectMap<Integer, Array<NumberedTile>> setRegionNames = new ObjectMap<>(); // number of images for each mask number

    public TileSet(String name, ObjectMap<Integer, Array<NumberedTile>> setRegionNames) {
        this.name = name;
        this.setRegionNames = setRegionNames;
    }

    public TileSet() {
    }

    public TileSet(String name) {
        this.name = name;
    }



    public TileSet(TileSet other) {
        this.colored = other.colored;
        this.color = other.color;
        this.maskMode = other.maskMode;
        this.atlasName = other.atlasName;
        this.setRegionNames = other.setRegionNames;
    }

    public boolean isColored() {
        return colored;
    }
    public ObjectMap<Integer, Array<NumberedTile>> getSetRegionNames() {
        return setRegionNames;
    }
    public MaskMode getMaskMode() {
        return maskMode;
    }
    public void setMaskMode(MaskMode maskMode) {
        this.maskMode = maskMode;
    }
    public NamedColor getColor() {
        return color;
    }
    public void setColor(NamedColor color) {
        this.color = color;
        colored=true;
        if(ColorUtilities.isWhite(color)){
            colored=false;
        }
    }
    public String getAtlasName() {
        return atlasName;
    }
    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }

    public void addNumberedTile(int bitNumber, NumberedTile region){
        setRegionNames.get(bitNumber).add(region);
    }

    // returns a path to a random texture region or null if no region is associated witha given bit number
    public String getRandomRegion(int bitNumber){
        Array<NumberedTile> regionNames=setRegionNames.get(bitNumber);
        if(regionNames==null || regionNames.size==0){
            return  "";

        }
        int max=setRegionNames.size;
        int random= RandomNumbers.getRandomNumber(0, max);
        return  regionNames.get(random).getRegionName();
    }

    public boolean isInSet(String atlasRegionName) {


        Values<Array<NumberedTile>> namesValues= setRegionNames.values();

        while(namesValues.hasNext){

            Array<NumberedTile>  numberedTiles= namesValues.next();
            boolean hasName= InList.isInList(numberedTiles, atlasRegionName);
            if(hasName==false){
                return  false;
            }

        }

        return true;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

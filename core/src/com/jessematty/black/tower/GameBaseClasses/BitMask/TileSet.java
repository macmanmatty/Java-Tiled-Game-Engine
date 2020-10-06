package com.jessematty.black.tower.GameBaseClasses.BitMask;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.GameBaseClasses.Utilities.ColorUtilities;
import com.jessematty.black.tower.Generators.Sets.MaskMode;
public class TileSet {
    protected String stringName;
    protected boolean colored;
    protected boolean addStatToTile;
    protected NamedColor color= NamedColor.WHITE;
    protected MaskMode maskMode;
    protected String atlasName;
  protected ObjectMap<Integer, Array<String>> setRegionNames = new ObjectMap<>(); // number of images for each mask number
    protected transient    ObjectMap<Integer, Array<AtlasNamedAtlasRegion>> setRegions= new ObjectMap<>(); // number of images for each mask number

    public TileSet() {
    }

    public TileSet(TileSet other) {
        this.stringName = other.stringName;
        this.colored = other.colored;
        this.addStatToTile = other.addStatToTile;
        this.color = other.color;
        this.maskMode = other.maskMode;
        this.atlasName = other.atlasName;
        this.setRegionNames = other.setRegionNames;
        this.setRegions = other.setRegions;
    }

    public String getTerrianKind() {
        return stringName;
    }
    public void setTerrianKind(String terrianKind) {
        this.stringName = terrianKind;
    }
    public boolean isColored() {
        return colored;
    }
    public String getStringName() {
        return stringName;
    }
    public void setStringName(String stringName) {
        this.stringName = stringName;
    }

    
   
    public boolean isAddStatToTile() {
        return addStatToTile;
    }
    public void setAddStatToTile(boolean addStatToTile) {
        this.addStatToTile = addStatToTile;
    }
    public ObjectMap<Integer, Array<String>> getSetRegionNames() {
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

    public void addRegion(int bitNumber, AtlasNamedAtlasRegion region){
        setRegions.get(bitNumber).add(region);
        setRegionNames.get(bitNumber).add(region.name);
    }

    public ObjectMap<Integer, Array<AtlasNamedAtlasRegion>> getSetRegions() {
        return setRegions;
    }

    public void setSetRegions(ObjectMap<Integer, Array<AtlasNamedAtlasRegion>> setRegions) {
        this.setRegions = setRegions;
    }
}
